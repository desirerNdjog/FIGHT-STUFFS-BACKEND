package org.accenture.utils.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.accenture.utils.complex.Tuplet;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:48
 * @project: FIGHTSTUFF
 */

public class GenericEntityDao {

    private GenericEntityDao(){

    }

    public static  <T> Tuplet<CriteriaBuilder, CriteriaQuery<T>, Root<T>> daoContext(EntityManager em, Class<T> tClass){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        return new Tuplet<>(criteriaBuilder, criteriaQuery, root);
    }
}
