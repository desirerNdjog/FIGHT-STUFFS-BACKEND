package org.accenture.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.accenture.domain.dto.PermissionDto;
import org.accenture.domain.models.Permission;
import org.accenture.mappers.permission.DtoToPermission;
import org.accenture.mappers.permission.PermissionToDto;
import org.accenture.service.PermissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
 * @created: 26/08/2024 : 18:27
 * @project: FLIGHTSTUFF
 */

@ExtendWith(MockitoExtension.class)
class PermissionDaoTest {
    @Mock
    private EntityManager em;
    private PermissionService service;

    @BeforeEach
    void setUp() {
        service = new PermissionDao(
                em,
                new PermissionToDto(),
                new DtoToPermission()
        );
    }

    @Test
    @DisplayName(value = "Return List Of Permissiondto")
    void givenSearchAndPageAndSizeReturnListOfPageablePermission(){
        //given
        String search = "";
        int page = 0;
        int size = 6;
        Pageable pageable = mock(Pageable.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Permission> criteriaQuery = mock(CriteriaQuery.class);
        Root<Permission> root = mock(Root.class);
        TypedQuery<Permission> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Permission.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Permission.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getResultList()).thenReturn(this.list());
        when(em.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).setMaxResults(size)).thenReturn(typedQuery);

        //when
        Page<PermissionDto> response = service.listPermissionPaginate(search, page, size);

        //then
        assertThat(response.getTotalElements()).isEqualTo(2);
        assertThat(response.getContent()).isNotEmpty();

    }

    @Test
    @DisplayName(value = "Create Permissiondto")
    void givenValidPersonThenCreate(){
        //when
        PermissionDto permission = PermissionDto.builder()
                .id(1L)
                .label("create")
                .build();
        doNothing().when(em).persist(any(Permission.class));

        //when
        service.create(permission);

        //when
        verify(em, times(1)).persist(any(Permission.class));

    }

    @Test
    @DisplayName(value = "Update PermissionDto")
    void givenValidPermissionAndUpdateAndReturnPermission(){
        //given
        PermissionDto persondto = PermissionDto.builder()
                .id(1L)
                .label("administrator")
                .build();
        Permission personne = Permission.builder()
                .id(1L)
                .label("administrator")
                .build();
        when(em.merge(any(Permission.class))).thenReturn(personne);

        //when
        PermissionDto person = service.update(persondto);

        //then
        assertThat(person).isNotNull();
    }

    @Test
    @DisplayName(value = "Find Permission By Id")
    void givendIdAndReturnPersondt(){
        //given
        Long id = 1L;
        Permission personne = Permission.builder()
                .id(1L)
                .label("administrator")
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Permission> criteriaQuery = mock(CriteriaQuery.class);
        Root<Permission> root = mock(Root.class);
        TypedQuery<Permission> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Permission.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Permission.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenReturn(personne);

        //when
        PermissionDto response = service.findById(id);

        //then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName(value = "Find Permission By Id and Throw Exception")
    void givendIdAndReturnThrowException(){
        //given
        Long id = 1L;
        Permission personne = Permission.builder()
                .id(1L)
                .label("administrator")
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Permission> criteriaQuery = mock(CriteriaQuery.class);
        Root<Permission> root = mock(Root.class);
        TypedQuery<Permission> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Permission.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Permission.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenThrow(new NoResultException());

        //when
        PermissionDto response = service.findById(id);

        //then
        assertThat(response).isNull();
    }

    @Test
    @DisplayName(value = "Find Permission By Label")
    void givendLabelAndReturnPermissionDto(){
        //given
        String label = "administrator";
        Permission personne = Permission.builder()
                .id(1L)
                .label("administrator")
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Permission> criteriaQuery = mock(CriteriaQuery.class);
        Root<Permission> root = mock(Root.class);
        TypedQuery<Permission> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Permission.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Permission.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenReturn(personne);

        //when
        PermissionDto response = service.findByLabel(label);

        //then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName(value = "Find Permission By Label and Throw Exception")
    void givendLabelAndReturnThrowException(){
        //given
        String label = "administrator";
        Permission personne = Permission.builder()
                .id(1L)
                .label("administrator")
                .build();
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Permission> criteriaQuery = mock(CriteriaQuery.class);
        Root<Permission> root = mock(Root.class);
        TypedQuery<Permission> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Permission.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Permission.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenThrow(new NoResultException());

        //when
        PermissionDto response = service.findByLabel(label);

        //then
        assertThat(response).isNull();
    }

    private List<Permission> list(){
        return List.of(
                Permission.builder()
                        .id(1L)
                        .label("user")
                        .build(),

                Permission.builder()
                        .id(1L)
                        .label("administrator")
                        .build()
        );
    }
}