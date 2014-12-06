
package it.padova.sanita.restbackend.services;

import it.padova.sanita.restbackend.dao.ContactDAO;
import it.padova.sanita.restbackend.model.Contact;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/rest")
public class ContactService
{
	@GET
	@Path("contact/{id}")
	@Produces("application/json")
	public Response getContact(@PathParam("id") Long id)
	{
		ContactDAO contactDAO = new ContactDAO();

		try
		{
			//Get specific values
			Contact _contact = contactDAO.findById(id);
			Gson gson = new Gson();

			if(_contact != null) {
				return Response.status(200).entity(gson.toJson(_contact)).build(); 
			} else {
				return Response.status(404).entity("NOT FOUND").build();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return Response.status(500).entity("ERROR").build();
		}
	}

	@GET
	@Path("contact")
	@Produces("application/json")
	public Response getContacts()
	{
		ContactDAO contactDAO = new ContactDAO();
		try
		{
			//Get specific values
			List<Contact> _contacts = contactDAO.findAll();
			Gson gson = new Gson();

			if(_contacts != null) {
				return Response.status(200).entity(gson.toJson(_contacts)).build(); 
			} else {
				return Response.status(404).entity("NOT FOUND").build();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return Response.status(500).entity("ERROR").build();
		}
	}

	@POST
	@Path("/contact")
	public Response createOrUpdateContact(String c) {
		Gson gson = new Gson();
		Contact contact = gson.fromJson(c, Contact.class);
		ContactDAO contactDAO = new ContactDAO();
		try {
			contact = contactDAO.saveOrUpdate(contact);
			return Response.status(200).entity(null).build();

		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
	}
	
	@POST
	@Path("/contactStoredPro")
	public Response createOrUpdateViaStoredPro(String c) {
		Gson gson = new Gson();
		Contact contact = gson.fromJson(c, Contact.class);
		ContactDAO contactDAO = new ContactDAO();
		try {
			contactDAO.saveOrUpdateViaStoredPro(contact);
			return Response.status(200).entity(null).build();

		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
	}
	
	@DELETE
	@Path("contact/{id}")
	public Response deleteContact(@PathParam("id") Long id)
	{
		ContactDAO contactDAO = new ContactDAO();

		try
		{
			//Get specific values
			Contact _contact = contactDAO.findById(id);
			Gson gson = new Gson();

			if(_contact != null) {
				contactDAO.delete(_contact);
				return Response.status(200).entity(gson.toJson(null)).build(); 
			} else {
				return Response.status(404).entity("NOT FOUND").build();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return Response.status(500).entity("ERROR").build();
		}
	}
}
