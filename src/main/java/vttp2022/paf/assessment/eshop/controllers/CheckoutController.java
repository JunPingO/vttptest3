package vttp2022.paf.assessment.eshop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@Controller
@RequestMapping(path="/checkout")
public class CheckoutController {


    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private OrderRepository orderRepo;

    @PostMapping
    public String checkoutAndSaveOrder(Model model, HttpSession sess) {

        List<LineItem> lineItems = (List<LineItem>)sess.getAttribute("cart");
        String name = (String) sess.getAttribute("name");

        System.out.println(">>>>" + name);

        Optional<Customer> opt = custRepo.findCustomerByName(name);

        System.out.println(opt.get().getName());

        if (opt.get().getName() == null){
            model.addAttribute("name", name);
            sess.invalidate();
			return "error_msg";
		}       

        Order order = new Order();

        orderRepo.saveOrder(order, opt.get());

        orderRepo.addLineItems(lineItems, order.getOrderId());


        model.addAttribute("orderId", order.getOrderId());

        return "order_success";
    }
}
