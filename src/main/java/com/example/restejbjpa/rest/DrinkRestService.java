package com.example.restejbjpa.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restejbjpa.domain.*;
import com.example.restejbjpa.service.DrinkManager;

@Path("/drink")
public class DrinkRestService {

  @EJB
  DrinkManager dm;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Drink addDrink(Drink drink) {
    dm.addDrink(drink);
    return drink;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Drink getDrink(@PathParam("id") int id) {
    return dm.getDrink(id);
  }


  @GET
  @Path("/query/name/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Drink> getDrinkByName(@PathParam("name") String name) {
    return dm.findByName(name);
  }

  @GET
  @Path("/query/findByCompany/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Drink> getDrinkByCompanyName(@PathParam("name") String name) {
    return dm.findByCompany(name);
  }


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Drink> getAll() {
    return dm.getAll();
  }

  @POST
  @Path("/filters")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Drink> getAll(Map<String, Object> request) {
    return dm.getWithFilters(request);
  }

  @DELETE
  public Response deleteAll() {
    dm.deleteAll();
    return Response.status(Response.Status.OK).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteDrink(@PathParam("id") int drinkId) {
    if (dm.deleteDrink(drinkId)) return Response.status(Response.Status.OK).build();
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @POST
  @Path("/init")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Drink> init() { return dm.init(); }

}
