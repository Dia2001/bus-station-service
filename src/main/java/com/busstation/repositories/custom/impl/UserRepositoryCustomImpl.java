package com.busstation.repositories.custom.impl;

import com.busstation.common.Validate;
import com.busstation.entities.Account;
import com.busstation.entities.Role;
import com.busstation.entities.User;
import com.busstation.enums.NameRoleEnum;
import com.busstation.repositories.custom.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<User> getFilter(String keyword, String role, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        List<Predicate> predList = new ArrayList<>();
        if (Validate.checkStringNotEmptyOrNull(keyword)) {
            predList.add(cb.or((cb.like(root.get("email"), "%" + keyword + "%")), cb.like(root.get("phoneNumber"), "%" + keyword + "%")));
        }
        Join<User, Account> join = root.join("account", JoinType.INNER);
        Join<Account, Role> roleJoin = join.join("role", JoinType.INNER);
        if (role.equalsIgnoreCase(NameRoleEnum.ROLE_USER.toString())) {
            predList.add(cb.equal(roleJoin.get("name"), NameRoleEnum.ROLE_USER.toString()));
        } else if (role.equalsIgnoreCase(NameRoleEnum.ROLE_DRIVER.toString())) {
            predList.add(cb.equal(roleJoin.get("name"), NameRoleEnum.ROLE_DRIVER.toString()));
        } else if (role.equalsIgnoreCase(NameRoleEnum.ROLE_EMPLOYEE.toString())) {
            predList.add(cb.equal(roleJoin.get("name"), NameRoleEnum.ROLE_EMPLOYEE.toString()));
        }
        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);
        query.where(predArray);
        query.select(root);

        List<User> typedQuery = entityManager.createQuery(query).setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        Page<User> users = new PageImpl<>(typedQuery, pageable, typedQuery.size());
        return users;
    }
}
