package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    
    public static String SQL_FIND_CUSTOMER = "select name, email, address from customers where name = ?";
    public static String SQL_INSERT_ORDER = "insert into orders(order_id, order_date, name) values (?, ?, ?)";
    public static String SQL_INSERT_LINE_ITEM = "insert into line_item(item_text, quantity, order_id) values (?, ?, ?)";

    public static String SQL_RETRIEVE_ORDER = """
        select *
        from orders o
        join line_item li
        on li.order_id = o.order_id
        where o.order_id = ?
        ;
            """;

}
