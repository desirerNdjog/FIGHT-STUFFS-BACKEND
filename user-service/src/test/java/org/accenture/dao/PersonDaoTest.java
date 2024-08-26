package org.accenture.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.accenture.domain.dto.PersonDto;
import org.accenture.domain.models.Personne;
import org.accenture.mappers.personne.DtoToPerson;
import org.accenture.mappers.personne.PersonToDto;
import org.accenture.service.PersonneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author: desirejuniorndjog.
 * @created: 23/08/2024 : 21:47
 * @project: FLIGHTSTUFF
 */

@ExtendWith(MockitoExtension.class)
class PersonDaoTest {
    @Mock
    private EntityManager em;
    private PersonneService service;

    @BeforeEach
    void setUp() {
        service = new PersonDao(
                em,
                new PersonToDto(),
                new DtoToPerson()
        );
    }

    @Test
    @DisplayName(value = "Return List Of Persondto")
    void givenSearchAndPageAndSizeReturnListOfPageablePerson(){
        //given
        String search = "";
        int page = 0;
        int size = 6;
        Pageable pageable = mock(Pageable.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Personne> criteriaQuery = mock(CriteriaQuery.class);
        Root<Personne> root = mock(Root.class);
        TypedQuery<Personne> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Personne.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Personne.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getResultList()).thenReturn(this.list());
        when(em.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).setMaxResults(size)).thenReturn(typedQuery);

        //when
        Page<PersonDto> response = service.listPersonnePaginate(search, page, size);

        //then
        assertThat(response.getTotalElements()).isEqualTo(2);
        assertThat(response.getContent()).isNotEmpty();

    }

    @Test
    @DisplayName(value = "Create Persondt")
    void givenValidPersonThenCreate(){
        //when
        PersonDto person = PersonDto.builder()
                .id(1L)
                .firstname("Desire Junior")
                .middlename("IForgotMyName")
                .lastname("NDJOG")
                .birthdate(LocalDate.now())
                .build();
        doNothing().when(em).persist(any(Personne.class));

        //when
        service.create(person);

        //when
        verify(em, times(1)).persist(any(Personne.class));

    }

    @Test
    @DisplayName(value = "Update Persondto")
    void givenValidPersondtoAndUpdateAndReturnPersondto(){
        //given
        PersonDto persondto = PersonDto.builder()
                .id(1L)
                .firstname("Desire Junior")
                .middlename("IForgotMyName")
                .lastname("NDJOG")
                .birthdate(LocalDate.now())
                .build();
        Personne personne = Personne.builder()
                .id(1L)
                .firstname("Desire Junior")
                .middlename("IForgotMyName")
                .lastname("NDJOG")
                .birthdate(LocalDate.now())
                .build();
        when(em.merge(any(Personne.class))).thenReturn(personne);

        //when
        PersonDto person = service.update(persondto);

        //then
        assertThat(person).isNotNull();
    }

    @Test
    @DisplayName(value = "Find Personne By Id")
    void givendIdAndReturnPersondt(){
        //given
        Long id = 1L;
        Personne personne = Personne.builder()
                .id(1L)
                .firstname("Desire Junior")
                .middlename("IForgotMyName")
                .lastname("NDJOG")
                .birthdate(LocalDate.now())
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Personne> criteriaQuery = mock(CriteriaQuery.class);
        Root<Personne> root = mock(Root.class);
        TypedQuery<Personne> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Personne.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Personne.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenReturn(personne);

        //when
        PersonDto response = service.findById(id);

        //then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName(value = "Find Personne By Id and Throw Exception")
    void givendIdAndReturnThrowException(){
        //given
        Long id = 1L;
        Personne personne = Personne.builder()
                .id(1L)
                .firstname("Desire Junior")
                .middlename("IForgotMyName")
                .lastname("NDJOG")
                .birthdate(LocalDate.now())
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Personne> criteriaQuery = mock(CriteriaQuery.class);
        Root<Personne> root = mock(Root.class);
        TypedQuery<Personne> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Personne.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Personne.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenThrow(new NoResultException());

        //when
        PersonDto response = service.findById(id);

        //then
        assertThat(response).isNull();
    }

    private List<Personne> list(){
        return List.of(
                  Personne.builder()
                          .id(1L)
                          .firstname("Desire Junior")
                          .middlename("IForgotMyName")
                          .lastname("NDJOG")
                          .birthdate(LocalDate.now())
                          .build(),

                Personne.builder()
                        .id(1L)
                        .firstname("Arthur Junior")
                        .middlename("IForgotMyName")
                        .lastname("NDJOG")
                        .birthdate(LocalDate.now())
                        .build()
        );
    }
}