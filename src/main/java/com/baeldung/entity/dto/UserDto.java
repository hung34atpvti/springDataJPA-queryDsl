package com.baeldung.entity.dto;

import com.baeldung.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;

    private String name;

    private String address;

    public UserDto() {
    }

    public UserDto(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress().getAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static List<UserDto> getListDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for(User u : users) {
            UserDto userDto = new UserDto(u);
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
