package com.baeldung.controller;

import com.baeldung.controller.repository.AddressRepository;
import com.baeldung.controller.repository.UserRepository;
import com.baeldung.controller.service.UserService;
import com.baeldung.entity.Address;
import com.baeldung.entity.User;
import com.baeldung.entity.dto.UserDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryController {

    @Autowired
    private UserRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<UserDto> queryOverUser(
            @RequestParam("address") String address,
            @RequestParam(value = "page", defaultValue  = "0") Integer page,
            @RequestParam(value = "limit", defaultValue  = "10") Integer limit
    ) {
        List<User> list = userService.getList(page,address,limit);
        return UserDto.getListDto(list);
    }

    @GetMapping(value = "/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Address> queryOverAddress(@QuerydslPredicate(root = Address.class) Predicate predicate) {
        final BooleanBuilder builder = new BooleanBuilder();
        return addressRepository.findAll(builder.and(predicate));
    }
}
