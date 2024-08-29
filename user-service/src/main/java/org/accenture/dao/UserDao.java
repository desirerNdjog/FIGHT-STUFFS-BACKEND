package org.accenture.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.accenture.domain.dto.UserDto;
import org.accenture.domain.models.User;
import org.accenture.mappers.user.DtoToUser;
import org.accenture.mappers.user.UserToDto;
import org.accenture.service.UserService;
import org.accenture.utils.complex.Tuplet;
import org.accenture.utils.generic.GenericEntityDao;
import org.assertj.core.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 21:36
 * @project: FLIGHTSTUFF
 */

@Repository
@Transactional
@RequiredArgsConstructor
public class UserDao implements UserService {
    private final EntityManager em;
    private final UserToDto userToDto;
    private final DtoToUser dtoToUser;

    @Override
    public Page<UserDto> allUserPaginate(String search, int page, int size) {
        Tuplet<CriteriaBuilder, CriteriaQuery<User>, Root<User>> tuplet = GenericEntityDao.daoContext(em, User.class);
        CriteriaBuilder criteriaBuilder = tuplet.getFrist();
        CriteriaQuery<User> criteriaQuery = tuplet.getSeconde();
        Root<User> root = tuplet.getThird();
        Pageable pageable = PageRequest.of(page, size);
        List<Predicate> predicates = new ArrayList<>();

        if (!Strings.isNullOrEmpty(search)){
            predicates.add(
                    criteriaBuilder.like(root.get("username"), "%"+search+"%")
            );
        }

        if (!Strings.isNullOrEmpty(search)){
            predicates.add(
                    criteriaBuilder.like(root.get("email"), "%"+search+"%")
            );
        }

        if (!Strings.isNullOrEmpty(search)){
            predicates.add(
                    criteriaBuilder.like(root.get("userstatus"), "%"+search+"%")
            );
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    criteriaBuilder.or(
                            predicates.toArray(new Predicate[0])
                    )
            );

        }

        long count = em.createQuery(criteriaQuery).getResultList().size();

        List<User> list = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Page<User> result = new PageImpl<>(list, pageable, count);
        return result.map(userToDto);
    }

    @Override
    public void create(UserDto userDto) {
        userDto.setFirstLogin(false);
        User user = dtoToUser.apply(userDto);
        em.persist(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User personne = dtoToUser.apply(userDto);
        User person = em.merge(personne);
        return userToDto.apply(person);
    }

    @Override
    public UserDto findById(Long id) {
        try {
            Tuplet<CriteriaBuilder, CriteriaQuery<User>, Root<User>> tuplet = GenericEntityDao.daoContext(em, User.class);
            CriteriaBuilder criteriaBuilder = tuplet.getFrist();
            CriteriaQuery<User> criteriaQuery = tuplet.getSeconde();
            Root<User> root = tuplet.getThird();

            if (id != null){
                criteriaQuery.where(
                        criteriaBuilder.equal(root.get("id"), id)
                );
            }
            User role = em.createQuery(criteriaQuery).getSingleResult();
            return userToDto.apply(role);
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        try {
            Tuplet<CriteriaBuilder, CriteriaQuery<User>, Root<User>> tuplet = GenericEntityDao.daoContext(em, User.class);
            CriteriaBuilder criteriaBuilder = tuplet.getFrist();
            CriteriaQuery<User> criteriaQuery = tuplet.getSeconde();
            Root<User> root = tuplet.getThird();

            if (email != null){
                criteriaQuery.where(
                        criteriaBuilder.equal(root.get("email"), email)
                );
            }
            User role = em.createQuery(criteriaQuery).getSingleResult();
            return userToDto.apply(role);
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public UserDto findByUserName(String username) {
        try {
            Tuplet<CriteriaBuilder, CriteriaQuery<User>, Root<User>> tuplet = GenericEntityDao.daoContext(em, User.class);
            CriteriaBuilder criteriaBuilder = tuplet.getFrist();
            CriteriaQuery<User> criteriaQuery = tuplet.getSeconde();
            Root<User> root = tuplet.getThird();

            if (username != null){
                criteriaQuery.where(
                        criteriaBuilder.equal(root.get("username"), username)
                );
            }
            User role = em.createQuery(criteriaQuery).getSingleResult();
            return userToDto.apply(role);
        }catch (NoResultException exception){
            return null;
        }
    }
}
