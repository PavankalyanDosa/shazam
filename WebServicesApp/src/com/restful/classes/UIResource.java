package com.restful.classes;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/webapp/api")
public class UIResource {
	UIRepository repository = new UIRepository();

	@GET
	@Path("/getAllUsers/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UserInfo> getAllUsersOp() {
		return repository.getAllUsers();
	}

	@GET
	@Path("/getUser/{eid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserInfo getOpUser(@PathParam("eid") String eid) {
		if (repository.getUser(eid).getEmployeeId() != null) {
			System.out.println("Test Connection:");
			return repository.getUser(eid);
		}
		return null;
	}

	@POST
	@Path("/createAccount")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void createOp(UserInfo userInfo) {
		if (repository.getUser(userInfo.getEmployeeId()) == null) {
			repository.createUser(userInfo);
		}
	}

	@PUT
	@Path("/updateAccount")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void updateOp(UserInfo userInfo) {
		if (repository.getUser(userInfo.getEmployeeId()) != null) {
			repository.updateUser(userInfo);
		} else {
			repository.createUser(userInfo);
		}
	}

	@DELETE
	@Path("/removeAccount/{eid}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deleteOp(@PathParam("eid") String eid) {
		if (repository.getUser(eid).getEmployeeId() != null) {
			repository.deleteUser(eid);
		}
	}

	@PUT
	@Path("/enableAccount/{eid}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void enableOp(@PathParam("eid") String eid) {
		if (repository.getUser(eid).getEmployeeId() != null) {
			repository.enableAccount(eid);
		}
	}

	@PUT
	@Path("/disableAccount/{eid}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void disableOp(@PathParam("eid") String eid) {
		if (repository.getUser(eid).getEmployeeId() != null) {
			repository.disableAccount(eid);
		}
	}

	@GET
	@Path("/getAllGroups")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<GroupInfo> getOpGroups() {
		return repository.getAllGroups();
	}

	@POST
	@Path("/addEntitlement")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void addEnts(ForGroup forGroup) {
		repository.addEntitlement(forGroup);
	}

	@DELETE
	@Path("/removeEntitlement")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deleteEnts(ForGroup forGroup) {
		repository.removeEntitlement(forGroup);
	}

}
