package com.mario.resource;

import com.mario.api.v1.resource.UserResource;
import com.mario.api.v1.resource.request.UserRequest;
import com.mario.bundles.db.user.User;
import com.mario.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public class UserResourceImpl implements UserResource {

    private final UserService userService;

    @Inject
    public UserResourceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Response add(UserRequest request) {
        userService.addUser(request.getFullName(), request.getJobTitle());
        return Response.status(201).build();
    }

    @Override
    public Response getById(int id) {
        Optional<User> optionalUser = userService.getUserById(id);
        return Response.ok(optionalUser.orElseGet(()-> new User(0L, "", ""))).build();
    }

    @Override
    public Response getAll() {
        List<User> users = userService.getUsers();
        return Response.ok(users).build();
    }
}
