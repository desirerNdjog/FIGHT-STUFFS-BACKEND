package org.accenture.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.accenture.domain.dto.RoleDto;
import org.accenture.domain.models.Role;
import org.accenture.mappers.role.DtoToRole;
import org.accenture.mappers.role.RoleToDto;
import org.accenture.service.RoleService;
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
 * @created: 26/08/2024 : 09:51
 * @project: FLIGHTSTUFF
 */

@Repository
@Transactional
@RequiredArgsConstructor
public class RoleDao implements RoleService {
    private final EntityManager em;
    private final RoleToDto roleToDto;
    private final DtoToRole dtoToRole;

    @Override
    public Page<RoleDto> listRolePaginate(String search, int page, int size) {
        Tuplet<CriteriaBuilder, CriteriaQuery<Role>, Root<Role>> tuplet = GenericEntityDao.daoContext(em, Role.class);
        CriteriaBuilder criteriaBuilder = tuplet.getFrist();
        CriteriaQuery<Role> criteriaQuery = tuplet.getSeconde();
        Root<Role> root = tuplet.getThird();
        Pageable pageable = PageRequest.of(page, size);
        List<Predicate> predicates = new ArrayList<>();

        if (!Strings.isNullOrEmpty(search)){
            predicates.add(
                    criteriaBuilder.like(root.get("label"), "%"+search+"%")
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

        List<Role> list = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Page<Role> result = new PageImpl<>(list, pageable, count);
        return result.map(roleToDto);
    }

    @Override
    public void create(RoleDto roleDto) {
        Role role = dtoToRole.apply(roleDto);
        em.persist(role);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        Role personne = dtoToRole.apply(roleDto);
        Role person = em.merge(personne);
        return roleToDto.apply(person);
    }

    @Override
    public RoleDto findById(Long id) {
        try {
            Tuplet<CriteriaBuilder, CriteriaQuery<Role>, Root<Role>> tuplet = GenericEntityDao.daoContext(em, Role.class);
            CriteriaBuilder criteriaBuilder = tuplet.getFrist();
            CriteriaQuery<Role> criteriaQuery = tuplet.getSeconde();
            Root<Role> root = tuplet.getThird();

            if (id != null){
                criteriaQuery.where(
                        criteriaBuilder.equal(root.get("id"), id)
                );
            }
            Role role = em.createQuery(criteriaQuery).getSingleResult();
            return roleToDto.apply(role);
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public RoleDto findByLabel(String label) {
        try {
            Tuplet<CriteriaBuilder, CriteriaQuery<Role>, Root<Role>> tuplet = GenericEntityDao.daoContext(em, Role.class);
            CriteriaBuilder criteriaBuilder = tuplet.getFrist();
            CriteriaQuery<Role> criteriaQuery = tuplet.getSeconde();
            Root<Role> root = tuplet.getThird();

            if (label != null){
                criteriaQuery.where(
                        criteriaBuilder.equal(root.get("label"), label)
                );
            }
            Role role = em.createQuery(criteriaQuery).getSingleResult();
            return roleToDto.apply(role);
        }catch (NoResultException exception){
            return null;
        }
    }
}
