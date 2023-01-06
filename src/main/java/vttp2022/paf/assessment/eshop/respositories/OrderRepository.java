package vttp2022.paf.assessment.eshop.respositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired JdbcTemplate jdbcTemplate;

	@Transactional
	public Order saveOrder (Order order, Customer cust) {
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		order.setOrderId(orderId);
		order.setOrderDate(Date.valueOf(LocalDate.now()));
		order.setCustomer(cust);

		jdbcTemplate.update(SQL_INSERT_ORDER, order.getOrderId(), order.getOrderDate(), order.getName());

		return order;
	}

	@Transactional
	public void addLineItems(List<LineItem> lineItems, String orderId) {
        
        List<Object[]> data = new LinkedList<>();
        for (LineItem li: lineItems) {
            Object[] l = new Object[3];
            l[0] = li.getItem();
            l[1] = li.getQuantity();
			l[2] = orderId;
            data.add(l);
        }
        
        jdbcTemplate.batchUpdate(SQL_INSERT_LINE_ITEM, data);
    }
}
