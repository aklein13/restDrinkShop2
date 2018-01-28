package com.example.restejbjpa.rest;

import com.example.restejbjpa.domain.Company;
import com.example.restejbjpa.service.CompanyManager;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/company")
public class CompanyRestService {

  @EJB
  CompanyManager cm;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Company addCompany(Company company) {
    cm.addCompany(company);
    return company;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Company getCompany(@PathParam("id") int id) {
    return cm.getCompany(id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Company> getAll() {
    return cm.getAll();
  }

  @DELETE
  public Response deleteAll() {
    cm.deleteAll();
    return Response.status(Response.Status.OK).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteBuyer(@PathParam("id") int id) {
    if (cm.deleteCompany(id)) return Response.status(Response.Status.OK).build();
    return Response.status(Response.Status.NOT_FOUND).build();
  }

}
