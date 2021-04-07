package com.baeldung.controller.service.impl;

import com.baeldung.controller.repository.UserRepository;
import com.baeldung.controller.service.UserService;
import com.baeldung.entity.QAddress;
import com.baeldung.entity.QUser;
import com.baeldung.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getList(int pageNo, String keyword, int rowsOnPage) {
        int offset = pageNo * rowsOnPage;
        int limit = rowsOnPage;

        QUser qUser = QUser.user;
        QAddress qAddress = QAddress.address1;

        JPAQuery<User> query = new JPAQuery<User>(entityManager);

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qAddress.address.containsIgnoreCase(keyword));

        return query
                .from(qUser)
                .innerJoin(qUser.address, qAddress)
                .where(predicate)
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}
