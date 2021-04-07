package com.baeldung.controller.repository;

import com.baeldung.entity.QUser;
import com.baeldung.entity.User;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
    @Override
    default void customize(final QuerydslBindings bindings, final QUser root) {
        bindings.bind(String.class)
                .first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }

    List<User> getUserByIdAfterOrderByNameDesc(Long id);
    List<User> findByIdAfterOrderByNameDesc(Long id, Pageable pageable);
}
