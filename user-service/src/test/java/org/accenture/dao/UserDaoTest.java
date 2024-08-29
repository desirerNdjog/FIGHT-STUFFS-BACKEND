package org.accenture.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.accenture.domain.dto.UserDto;
import org.accenture.domain.models.Permission;
import org.accenture.domain.models.Role;
import org.accenture.domain.models.User;
import org.accenture.mappers.user.DtoToUser;
import org.accenture.mappers.user.UserToDto;
import org.accenture.service.UserService;
import org.accenture.utils.UserStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 21:37
 * @project: FLIGHTSTUFF
 */

@ExtendWith(MockitoExtension.class)
class UserDaoTest {
    @Mock
    private EntityManager em;
    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserDao(
                em,
                new UserToDto(),
                new DtoToUser()
        );
    }

    @Test
    @DisplayName(value = "Return List Of Userdto")
    void givenSearchAndPageAndSizeReturnListOfPageableUser(){
        //given
        String search = "";
        int page = 0;
        int size = 6;
        Pageable pageable = mock(Pageable.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getResultList()).thenReturn(this.list());
        when(em.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).setMaxResults(size)).thenReturn(typedQuery);

        //when
        Page<UserDto> response = service.allUserPaginate(search, page, size);

        //then
        assertThat(response.getTotalElements()).isEqualTo(2);
        assertThat(response.getContent()).isNotEmpty();

    }

    @Test
    @DisplayName(value = "Create UserDto")
    @Disabled(value = "stop this")
    void givenValidPersonThenCreate(){
        //gien
        doNothing().when(em).persist(any(User.class));

        //when
        service.create(userDto());

        //when
        verify(em, times(1)).persist(any(Role.class));
    }

    @Test
    @DisplayName(value = "Update UserDto")
    void givenValidRoleAndUpdateAndReturnRole(){
        //given
        when(em.merge(any(User.class))).thenReturn(list().get(0));

        //when
        UserDto person = service.update(userDto());

        //then
        assertThat(person).isNotNull();
    }

    @Test
    @DisplayName(value = "Find User By Id")
    void givendIdAndReturnPersondt(){
        //given
        Long id = 1L;
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(criteriaQuery).getSingleResult()).thenReturn(list().get(0));

        //when
        UserDto response = service.findById(id);

        //then
        assertThat(response).isNotNull();
    }

    private UserDto userDto(){
        Role role = new Role(
                1L,
                "administrator",
                Set.of(
                        new Permission(
                                1L,
                                "create",
                                LocalDateTime.now()
                        )
                )
        );
        return   UserDto.builder()
                .id(1L)
                .username("Desire Junior")
                .email("desire.junior.ndjog@accenture.com")
                .password("Juniorndjog@007")
                .firstLogin(false)
                .userstatus(UserStatusEnum.ACCOUNT_NOT_VERIFIED)
                .roles(Set.of(role))
                .build();
    }


    private List<User> list(){
        Role role = new Role(
                1L,
                "administrator",
                Set.of(
                        new Permission(
                                1L,
                                "create",
                                LocalDateTime.now()
                        )
                )
        );
        return List.of(
                User.builder()
                        .id(1L)
                        .username("Desire Junior")
                        .email("desire.junior.ndjog@accenture.com")
                        .password("Juniorndjog@007")
                        .firstLogin(false)
                        .userstatus(UserStatusEnum.ACCOUNT_NOT_VERIFIED)
                        .roles(Set.of(role))
                        .build(),
                User.builder()
                        .id(1L)
                        .username("Etuba Miller")
                        .email("desire.junior.ndjog@accenture.com")
                        .password("Juniorndjog@007")
                        .firstLogin(false)
                        .userstatus(UserStatusEnum.ACCOUNT_NOT_VERIFIED)
                        .roles(Set.of(role))
                        .build()
        );
    }
}