package org.accenture.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.accenture.domain.dto.PersonDto;
import org.accenture.domain.models.Personne;
import org.accenture.mappers.personne.DtoToPerson;
import org.accenture.mappers.personne.PersonToDto;
import org.accenture.service.PersonneService;
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
 * @created: 21/08/2024 : 17:42
 * @project: FIGHTSTUFF
 */

@Repository
@Transactional
@RequiredArgsConstructor
public class PersonDao implements PersonneService {
    private final EntityManager em;
    private final PersonToDto personToDto;
    private final DtoToPerson dtoToPerson;

    @Override
    public Page<PersonDto> listPersonnePaginate(String search, int page, int size) {
        Tuplet<CriteriaBuilder, CriteriaQuery<Personne>, Root<Personne>> tuplet = GenericEntityDao.daoContext(em, Personne.class);
        CriteriaBuilder criteriaBuilder = tuplet.getFrist();
        CriteriaQuery<Personne> criteriaQuery = tuplet.getSeconde();
        Root<Personne> root = tuplet.getThird();
        Pageable pageable = PageRequest.of(page, size);
        List<Predicate> predicates = new ArrayList<>();

        if (Strings.isNullOrEmpty(search)){
            predicates.add(
                criteriaBuilder.like(root.get("firstname"), "%"+search+"%")
            );
        }

        if (Strings.isNullOrEmpty(search)){
            predicates.add(
                    criteriaBuilder.like(root.get("middlename"), "%"+search+"%")
            );
        }

        if (Strings.isNullOrEmpty(search)){
            predicates.add(
                    criteriaBuilder.like(root.get("lastname"), "%"+search+"%")
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

        List<Personne> list = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Page<Personne> result = new PageImpl<>(list, pageable, count);
        return result.map(personToDto);
    }

    @Override
    public void create(PersonDto personDto) {
        Personne person = dtoToPerson.apply(personDto);
        em.persist(person);
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        Personne personne = dtoToPerson.apply(personDto);
        Personne person = em.merge(personne);
        return personToDto.apply(person);
    }

    @Override
    public PersonDto findById(Long id) {
        try {
            Tuplet<CriteriaBuilder, CriteriaQuery<Personne>, Root<Personne>> tuplet = GenericEntityDao.daoContext(em, Personne.class);
            CriteriaBuilder criteriaBuilder = tuplet.getFrist();
            CriteriaQuery<Personne> criteriaQuery = tuplet.getSeconde();
            Root<Personne> root = tuplet.getThird();

            if (id != null){
                criteriaQuery.where(
                        criteriaBuilder.equal(root.get("id"), id)
                );
            }
            Personne person = em.createQuery(criteriaQuery).getSingleResult();
            return personToDto.apply(person);
        }catch (NoResultException exception){
            return null;
        }
    }
}
