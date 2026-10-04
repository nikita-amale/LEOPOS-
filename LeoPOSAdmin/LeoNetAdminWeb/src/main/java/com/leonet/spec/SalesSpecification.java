package com.leonet.spec;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.leonet.common.entity.SalesEntity;
import com.leonet.common.filter.SalesFilter;
import com.leonet.common.filter.SalesFilterGroup;

public class SalesSpecification {

    /** Build Specification from multiple filter groups */
	public static Specification<SalesEntity> build(List<SalesFilterGroup> groups) {
	    return (Root<SalesEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        Predicate finalPredicate = null; // Start empty

	        for (SalesFilterGroup group : groups) {
	            Predicate groupPredicate = null; // AND inside each group

	            for (SalesFilter filter : group.getFilters()) {
	                Predicate p = buildPredicate(root, cb, filter);

	                if (groupPredicate == null) {
	                    groupPredicate = p;
	                } else {
	                    groupPredicate = filter.isUseOr() 
	                        ? cb.or(groupPredicate, p)
	                        : cb.and(groupPredicate, p);
	                }
	            }

	            if (groupPredicate != null) {
	                finalPredicate = (finalPredicate == null) 
	                    ? groupPredicate 
	                    : cb.or(finalPredicate, groupPredicate); // OR between groups
	            }
	        }

	        // Return a valid predicate even if groups are empty
	        return finalPredicate != null ? finalPredicate : cb.conjunction();
	    };
	}

	// Helper method for creating predicate based on operator
	private static Predicate buildPredicate(Root<SalesEntity> root, CriteriaBuilder cb, SalesFilter filter) {
	    String field = filter.getField();
	    Object value = filter.getValue();

	    switch (filter.getOperator()) {
	        case EQ: 
	            return cb.equal(root.get(field), value);

	        case IN:
	            if (value instanceof List<?>) return root.get(field).in((List<?>) value);
	            if (value instanceof Object[]) return root.get(field).in((Object[]) value);
	            throw new IllegalArgumentException("IN operator requires List or Array");

	        case BETWEEN:
	            if (value instanceof Object[] && ((Object[]) value).length == 2) {
	                Object[] range = (Object[]) value;
	                return cb.between(root.get(field), (Comparable) range[0], (Comparable) range[1]);
	            }
	            throw new IllegalArgumentException("BETWEEN requires Object[2]");

	        case LIKE:
	            return cb.like(root.get(field), "%" + value + "%");

	        case GT:
	            return cb.greaterThan(root.get(field), (Comparable) value);

	        case LT:
	            return cb.lessThan(root.get(field), (Comparable) value);

	        default:
	            throw new IllegalArgumentException("Unsupported operator: " + filter.getOperator());
	    }
	}

}
