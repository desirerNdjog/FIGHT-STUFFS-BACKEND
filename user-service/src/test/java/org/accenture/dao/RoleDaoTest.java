package org.accenture.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.accenture.domain.dto.RoleDto;
import org.accenture.domain.models.Role;
import org.accenture.mappers.role.DtoToRole;
import org.accenture.mappers.role.RoleToDto;
import org.accenture.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 10:08
 * @project: FLIGHTSTUFF
 */

@ExtendWith(MockitoExtension.class)
class RoleDaoTest {
    @Mock
    private EntityManager em;
    private RoleService service;

    @BeforeEach
    void setUp() {
        service = new RoleDao(
                em,
                new RoleToDto(),
                new DtoToRole()
        );
    }

    @Test
    @DisplayName(value = "Return List Of Roledto")
    void givenSearchAndPageAndSizeReturnListOfPageableRole(){
        //given
        String search = "";
        int page = 0;
        int size = 6;
        Pageable pageable = mock(Pageable.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Role> criteriaQuery = mock(CriteriaQuery.class);
        Root<Role> root = mock(Root.class);
        TypedQuery<Role> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Role.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Role.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getResultList()).thenReturn(this.list());
        when(em.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).setMaxResults(size)).thenReturn(typedQuery);

        //when
        Page<RoleDto> response = service.listRolePaginate(search, page, size);

        //then
        assertThat(response.getTotalElements()).isEqualTo(2);
        assertThat(response.getContent()).isNotEmpty();

    }

    @Test
    @DisplayName(value = "Create Roledto")
    void givenValidPersonThenCreate(){
        //when
        RoleDto person = RoleDto.builder()
                .id(1L)
                .label("administrator")
                .build();
        doNothing().when(em).persist(any(Role.class));

        //when
        service.create(person);

        //when
        verify(em, times(1)).persist(any(Role.class));

    }

    @Test
    @DisplayName(value = "Update RoleDto")
    void givenValidRoleAndUpdateAndReturnRole(){
        //given
        RoleDto persondto = RoleDto.builder()
                .id(1L)
                .label("administrator")
                .build();
        Role personne = Role.builder()
                .id(1L)
                .label("administrator")
                .build();
        when(em.merge(any(Role.class))).thenReturn(personne);

        //when
        RoleDto person = service.update(persondto);

        //then
        assertThat(person).isNotNull();
    }

    @Test
    @DisplayName(value = "Find Role By Id")
    void givendIdAndReturnPersondt(){
        //given
        Long id = 1L;
        Role personne = Role.builder()
                .id(1L)
                .label("administrator")
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Role> criteriaQuery = mock(CriteriaQuery.class);
        Root<Role> root = mock(Root.class);
        TypedQuery<Role> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Role.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Role.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenReturn(personne);

        //when
        RoleDto response = service.findById(id);

        //then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName(value = "Find Role By Id and Throw Exception")
    void givendIdAndReturnThrowException(){
        //given
        Long id = 1L;
        Role personne = Role.builder()
                .id(1L)
                .label("administrator")
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Role> criteriaQuery = mock(CriteriaQuery.class);
        Root<Role> root = mock(Root.class);
        TypedQuery<Role> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Role.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Role.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenThrow(new NoResultException());

        //when
        RoleDto response = service.findById(id);

        //then
        assertThat(response).isNull();
    }

    @Test
    @DisplayName(value = "Find Role By Label")
    void givendLabelAndReturnRoleDto(){
        //given
        String label = "administrator";
        Role personne = Role.builder()
                .id(1L)
                .label("administrator")
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Role> criteriaQuery = mock(CriteriaQuery.class);
        Root<Role> root = mock(Root.class);
        TypedQuery<Role> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Role.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Role.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenReturn(personne);

        //when
        RoleDto response = service.findByLabel(label);

        //then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName(value = "Find Role By Label and Throw Exception")
    void givendLabelAndReturnThrowException(){
        //given
        String label = "administrator";
        Role personne = Role.builder()
                .id(1L)
                .label("administrator")
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Role> criteriaQuery = mock(CriteriaQuery.class);
        Root<Role> root = mock(Root.class);
        TypedQuery<Role> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Role.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Role.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenThrow(new NoResultException());

        //when
        RoleDto response = service.findByLabel(label);

        //then
        assertThat(response).isNull();
    }

    private List<Role> list(){
        return List.of(
                Role.builder()
                        .id(1L)
                        .label("user")
                        .build(),

                Role.builder()
                        .id(1L)
                        .label("administrator")
                        .build()
        );
    }

}