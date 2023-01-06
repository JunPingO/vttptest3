package vttp2022.paf.assessment.eshop.services;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;

@Service
public class WarehouseService {

	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public OrderStatus dispatch(Order order, Customer cust) {

		// TODO: Task 4
		


		JsonObjectBuilder payloadBuilder = Json.createObjectBuilder();
		payloadBuilder.add("orderId", "asd")
					.add("name", "asd")
					.add("address", 25)
					.add("email", "asd")
					.add("createdBy", "Ong Jun Ping");

		JsonArrayBuilder lineBuilder = Json.createArrayBuilder();

		for (LineItem li: lineItems){

			JsonObjectBuilder item = Json.createObjectBuilder();
			item.add("item", "")
				.add("quantity", "");
			
			lineBuilder.add(item);
		}

		
		

		payloadBuilder.add("lineItems", lineBuilder);
		JsonObject employee = empBuilder.build();



		OrderStatus tempPlace = new OrderStatus();
		return tempPlace;
	}


	
}
