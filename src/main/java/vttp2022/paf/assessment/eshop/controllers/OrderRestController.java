package vttp2022.paf.assessment.eshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.services.WarehouseService;


@RestController
@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class OrderRestController {
    
    @Autowired
	private WarehouseService warehouseSvc;

	@PostMapping
	public RequestEntity<String> orderToWarehouse() {

        // List<LineItem> lineItems = (List<LineItem>)sess.getAttribute("cart");

        RequestEntity<String> req = RequestEntity
            .post("http://paf.chuklee.com/dispatch")
            .contentType(MediaType.APPLICATION_JSON)
            .body(json.toString(), String.class);
		

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
	}


}
