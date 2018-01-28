package com.example.restejbjpa.rest;

import com.example.restejbjpa.domain.Buyer;
import com.example.restejbjpa.service.BuyerManager;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/buyer")
public class BuyerRestService {

  @EJB
  BuyerManager bm;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Buyer addCompany(Buyer buyer) {
    bm.addBuyer(buyer);
    return buyer;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Buyer getBuyer(@PathParam("id") int id) {
    return bm.getBuyer(id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Buyer> getAll() {
    return bm.getAll();
  }

  @DELETE
  public Response deleteAll() {
    bm.deleteAll();
    return Response.status(Response.Status.OK).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteBuyer(@PathParam("id") int id) {
    if (bm.deleteBuyer(id)) return Response.status(Response.Status.OK).build();
    return Response.status(Response.Status.NOT_FOUND).build();
  }

}
