package com.baeldung.controller.service;

import com.baeldung.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getList(int pageNo, String keyword, int rowsOnPage);
}
