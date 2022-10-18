package com.mario.api.v1.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1")
public interface HelloResource {

    @Path("/hello/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response get(@Valid @NotNull @PathParam("name") String name);
}
