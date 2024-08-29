package org.accenture.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.accenture.domain.dto.PermissionDto;
import org.accenture.domain.models.Permission;
import org.accenture.mappers.permission.DtoToPermission;
import org.accenture.mappers.permission.PermissionToDto;
import org.accenture.service.PermissionService;
import org.accenture.utils.complex.Tuplet;
import org.accenture.utils.generic.GenericEntityDao;
import org.assertj.core.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: desirejuniorndjog.
 * @created: 26/08/2024 : 18:18
 * @project: FLIGHTSTUFF
 */

@Repository
@Transactional
@RequiredArgsConstructor
public class PermissionDao implements PermissionService {
    private final EntityManager em;
    private final PermissionToDto permissionToDto;
    private final DtoToPermission dtoToPermission;

    @Override
    public Page<PermissionDto> listPermissionPaginate(String search, int page, int size) {
        Tuplet<CriteriaBuilder, CriteriaQuery<Permission>, Root<Permission>> tuplet = GenericEntityDao.daoContext(em, Permission.class);
        CriteriaBuilder criteriaBuilder = tuplet.getFrist();
        CriteriaQuery<Permission> criteriaQuery = tuplet.getSeconde();
        Root<Permission> root = tuplet.getThird();
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

        List<Permission> list = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Page<Permission> result = new PageImpl<>(list, pageable, count);
        return result.map(permissionToDto);
    }

    @Override
    public void create(PermissionDto permissionDto) {
        permissionDto.setCreated(LocalDateTime.now());
        Permission role = dtoToPermission.apply(permissionDto);
        em.persist(role);
    }

    @Override
    public PermissionDto update(PermissionDto permissionDto) {
        Permission personne = dtoToPermission.apply(permissionDto);
        Permission person = em.merge(personne);
        return permissionToDto.apply(person);
    }

    @Override
    public PermissionDto findById(Long id) {
        try {
            Tuplet<CriteriaBuilder, CriteriaQuery<Permission>, Root<Permission>> tuplet = GenericEntityDao.daoContext(em, Permission.class);
            CriteriaBuilder criteriaBuilder = tuplet.getFrist();
            CriteriaQuery<Permission> criteriaQuery = tuplet.getSeconde();
            Root<Permission> root = tuplet.getThird();

            if (id != null){
                criteriaQuery.where(
                        criteriaBuilder.equal(root.get("id"), id)
                );
            }
            Permission role = em.createQuery(criteriaQuery).getSingleResult();
            return permissionToDto.apply(role);
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public PermissionDto findByLabel(String label) {
        try {
            Tuplet<CriteriaBuilder, CriteriaQuery<Permission>, Root<Permission>> tuplet = GenericEntityDao.daoContext(em, Permission.class);
            CriteriaBuilder criteriaBuilder = tuplet.getFrist();
            CriteriaQuery<Permission> criteriaQuery = tuplet.getSeconde();
            Root<Permission> root = tuplet.getThird();

            if (label != null){
                criteriaQuery.where(
                        criteriaBuilder.equal(root.get("label"), label)
                );
            }
            Permission role = em.createQuery(criteriaQuery).getSingleResult();
            return permissionToDto.apply(role);
        }catch (NoResultException exception){
            return null;
        }
    }
}
