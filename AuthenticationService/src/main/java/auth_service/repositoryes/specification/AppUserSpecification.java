package auth_service.repositoryes.specification;

import auth_service.entities.AppUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AppUserSpecification {
    public static Specification<AppUser> valueLike(String field,String value) {
        return (Root<AppUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> criteriaBuilder.like(root.get(field), String.format("%%%s%%", value));
    }
}
