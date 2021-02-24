package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    private static final String GET_ALL_USERS = "http://localhost:8080/api/getAllUsers";
    private static final String GET_USER = "http://localhost:8080/api/getUserByID/{id}";
    private static final String CREATE_USER = "http://localhost:8080/api/addUser";
    private static final String DELETE_USER = "http://localhost:8080/api/deleteUser/{id}";

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
    callGetAllUsersAPI();
    }

    private static void callGetAllUsersAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(GET_ALL_USERS, HttpMethod.GET, entity, String.class);

        System.out.println(response);
    }

    private static void callGetUserByIdAPI() {
        Map<String, String> param = new HashMap<>();
        param.put("id", "1");

        User user =  restTemplate.getForObject(GET_USER, User.class, param);
        System.out.println(user.firstName);
        System.out.println(user.lastName);
        System.out.println(user.email);
    }

    private static void callCreateUserAPI() {
        User user = new User();

        ResponseEntity<User> createdUser = restTemplate.postForEntity(CREATE_USER, user, User.class);

        System.out.println(createdUser.getBody());
    }

    private static void callDeleteUserByIdAPI() {
        Map<String, String> param = new HashMap<>();
        param.put("id", "");
        restTemplate.delete(DELETE_USER, param);
    }
}