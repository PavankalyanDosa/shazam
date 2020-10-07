package com.restful.Client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8082/RestApiWeb/rest/alien/create");

			String id = "IND003";
			String fn = "Ravi";
			String ln = "Varma";
			String un = fn+"."+ln;	//8082
			String mail = fn.toLowerCase()+"."+ln.toLowerCase()+"@test.com";
			String city = "TO";
			String input = "{\"employeeId\":\"" + id + "\",\"userName\":\"" + un + "\",\"firstName\":\"" + fn + "\",\"lastName\":\""
					+ ln + "\",\"location\":\"" + city + "\",\"email\":\"" + mail + "\"}";
			System.out.println(input);

			webResource.type("application/json").post(ClientResponse.class, input);
//			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
//			}

			System.out.println("Output from Server .... \n");
//			String output = response.getEntity(String.class);
//			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
