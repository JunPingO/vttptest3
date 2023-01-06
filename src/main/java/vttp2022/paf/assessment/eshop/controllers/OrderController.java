package vttp2022.paf.assessment.eshop.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;

@Controller
@RequestMapping(path = "")
public class OrderController {

	//TODO: Task 3

	@Autowired
	private CustomerRepository CustomerRepository;

	@GetMapping(path = "/orders")
	public ResponseEntity<String> checkUser(@RequestParam String name){
		
        // Integer numTickets = Integer.parseInt(form.getFirst("num_tickets"));
		
		Optional<Customer> opt = CustomerRepository.findCustomerByName(name);

		System.out.println(">>>>" + opt.get().getName());
		
		String error_msg = "Customer "+ name +" not found";
		String found_msg = "Customer "+ name +" is found";

		JsonObject body = Json.createObjectBuilder()
			.add("error", error_msg).build();

		if (opt.get().getName() == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body.toString());
		}
		return ResponseEntity.ok(found_msg);
	}

	@PostMapping(path = "/orders")
    public String postCart(@RequestBody MultiValueMap<String, String> form
            , Model model, HttpSession sess) {

        List<LineItem> lineItems = (List<LineItem>)sess.getAttribute("cart");
        if (null == lineItems) {
            lineItems = new LinkedList<>();
            sess.setAttribute("cart", lineItems);
        }
		String name = form.getFirst("name");
		sess.setAttribute("name", name);
		System.out.println(name);
        String item = form.getFirst("item");
        Integer quantity  = Integer.parseInt(form.getFirst("quantity"));
        lineItems.add(new LineItem(item, quantity));

        for (LineItem li: lineItems)
            System.out.printf("description: %s, quantity: %d\n", li.getItem(), li.getQuantity());

        model.addAttribute("lineItems", lineItems);
		model.addAttribute("name", name);
        
        return "orders";
    }

}
