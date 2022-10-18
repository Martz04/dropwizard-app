package com.mario.api.v1.resource;

import com.mario.api.v1.resource.request.UserRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/user")
public interface UserResource {

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response add(@Valid @NotNull UserRequest request);

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getById(@Valid @NotNull @PathParam("id") int id);

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getAll();
}
