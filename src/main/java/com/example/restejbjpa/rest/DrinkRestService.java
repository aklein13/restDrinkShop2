package com.example.restejbjpa.rest;

import java.util.ArrayList;
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

//	@GET
//	@Path("/query/game.findByOwner/{firstName}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getOwnerGames(@PathParam("firstName") String firstName){
//
//		List<Object[]> rawGames = dm.getGamesOfOwnerByOwnerName(firstName);
//		JsonArrayBuilder games = Json.createArrayBuilder();
//
//		for(Object[] rawGame: rawGames){
//
//			String fName = (String) rawGame[0];
//			String lName = (String) rawGame[1];
//			String title = (String) rawGame[2];
//
//			games.add(Json.createObjectBuilder()
//					.add("firstName", fName)
//					.add("lastName", lName)
//					.add("title", title));
//		}
//
//		JsonObject json =  Json.createObjectBuilder().add("result", games).build();
//		return Response.ok(json, MediaType.APPLICATION_JSON).build();
//	}


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
    dm.deletAll();
    return Response.status(Response.Status.OK).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteDrink(@PathParam("id") int drinkId) {
    if (dm.deleteDrink(drinkId)) {
      return Response.status(Response.Status.OK).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

}
