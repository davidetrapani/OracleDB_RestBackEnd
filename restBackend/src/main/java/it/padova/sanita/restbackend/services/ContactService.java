
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
	private Gson gson = new Gson();
	private ContactDAO contactDAO = new ContactDAO();
	
	@GET
	@Path("contact/{id}")
	@Produces("application/json")
	public Response getContact(@PathParam("id") Long id)
	{
		try
		{
			//Get specific values
			Contact _contact = contactDAO.findById(id);

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
		try
		{
			//Get specific values
			List<Contact> _contacts = contactDAO.findAll();

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
	public Response createOrUpdateContact(String payload) {
		Contact contact = gson.fromJson(payload, Contact.class);
		try {
			contact = contactDAO.saveOrUpdate(contact);
			return Response.status(200).entity(null).build();

		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
	}
	
	@POST
	@Path("/contactStoredPro")
	public Response createOrUpdateViaStoredPro(String payload) {
		Contact contact = gson.fromJson(payload, Contact.class);
		
		try {
			String ret = contactDAO.saveOrUpdateViaStoredPro(contact);
			return Response.status(200).entity(ret).build();

		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
	}
	
	@DELETE
	@Path("contact/{id}")
	public Response deleteContact(@PathParam("id") Long id)
	{
		try
		{
			//Get specific values
			Contact _contact = contactDAO.findById(id);

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
