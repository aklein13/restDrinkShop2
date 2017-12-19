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

//	@EJB
//  OwnerManager om;
//
//	@EJB
//  GameNumberManager gnm;
//
//	@EJB
//  CompanyManager cm;


//	@GET
//	@Path("/manytomany")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String testRelations(){
//
//		Person p = new Person("Niki", "Lauda", 1945);
//
//		Car c1 = new Car("Fiat", "Punto");
//		Car c2 = new Car("Ford", "Fiesta");
//
//		List<Car> cars = new ArrayList<>();
//		cars.add(c1);
//		cars.add(c2);
//
//		p.addCars(cars);
//		pm.addPerson(p);
//
//
//		System.out.println("Id c: " + c1.getId());
//
//		System.out.println("\n\n@@@ Size of owners: " + c1.getOwners().size());
//
//		//Car retrieved = pm.getCar(c1.getId());
//		//Car retrieved = pm.updateCar(c1);
//
//
//		//System.out.println("\n\n@@@ Size of owners: " + retrieved.getOwners().size());
//
//		return "ManyToMany";
//	}

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Drink addDrink(Drink drink) {

    Company comp = new Company("Coca-Cola", "USA");
    List<Owner> owner = new ArrayList<>();

    Owner o1 = new Owner("Karol", "Karolowski", 20);
    Owner o2 = new Owner("Borzydar", "Darowski", 22);

    owner.add(o1);
    owner.add(o2);

    drink.setCompany(comp);
//    drink.setGameNumber(gn);
//    drink.setOwner(owner);

    dm.addDrink(drink);

    return drink;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Drink getDrink(@PathParam("id") int id) {

    //Drink retrieved = gm.getDrink(id);

    return dm.getDrink(id);
  }


//  @GET
//  @Path("/query/yor/{yor}")
//  @Produces(MediaType.APPLICATION_JSON)
//  public List<Drink> getGameByYor(@PathParam("yor") int yop) {
//    return dm.findByYop(yop);
//  }

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
  public Response deletAll() {
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
