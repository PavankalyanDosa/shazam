package com.restful.Client;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.classes.UserInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientGet {

	public static void main(String[] args) {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8082/RestApiWeb/rest/alien/");

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

			System.out.println("-----------------------------------------");

			ObjectMapper mapper = new ObjectMapper();
			try {

				// convert JSON array to Array objects
				UserInfo[] pp1 = mapper.readValue(output, UserInfo[].class);

				System.out.println("JSON array to Array objects...");
				for (UserInfo alien : pp1) {
					System.out.println(alien);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//		public void singleObject(String json) {
//			 ObjectMapper mapper = new ObjectMapper();
//		        try
//		        {
//		            Alien[] emp = mapper.readValue(json, Alien[].class);
//		             
//		            System.out.println(emp);
//		        } 
//		        catch (JsonGenerationException e) 
//		        {
//		            e.printStackTrace();
//		        } catch (JsonMappingException e) {
//		            e.printStackTrace();
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//		}
//	public void jsonObjectAsArrays() {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			// 1. convert JSON array to Array objects
//			Alien[] pp1 = mapper.readValue(output, Alien[].class);
//
//			System.out.println("JSON array to Array objects...");
//			for (Alien alien : pp1) {
//				System.out.println(alien);
//			}

//		// 2. convert JSON array to List of objects
//		List<Alien> ppl2 = Arrays.asList(mapper.readValue(output, Alien[].class));
//
//		System.out.println("\nJSON array to List of objects");
//		ppl2.stream().forEach(x -> System.out.println(x));
//
//		// 3. alternative
//		List<Alien> pp3 = mapper.readValue(output, new TypeReference<List<Alien>>() {
//		});
//
//		System.out.println("\nAlternative...");
//		pp3.stream().forEach(x -> System.out.println(x));

//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
