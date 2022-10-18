package com.mario.resource;

import com.mario.api.v1.resource.HelloResource;
import com.mario.api.v1.resource.response.HelloWorldResponse;

import javax.ws.rs.core.Response;

public class HelloResourceImpl implements HelloResource {

    @Override
    public Response get(String name) {
        return Response.ok(
            HelloWorldResponse.builder().message("Hello " + name).build()
        ).build();
    }
}
