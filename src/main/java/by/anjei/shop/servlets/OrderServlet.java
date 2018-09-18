package by.anjei.shop.servlets;

import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.User;
import by.anjei.shop.db.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 10.04.18
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */

public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();

        List<Item> items = productService.allAvailableItems();

        String action = (String) request.getParameter("action");

        User user = (User) request.getSession().getAttribute("user");

        String itemName = request.getParameter("itemName");

        List<Item> currentItems = (List<Item>) request.getSession().getAttribute("currentItems");
        List<Item> orderedItems = (List<Item>) request.getSession().getAttribute("orderedItems");

        if (action == null) {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
        if (action.equals("add")) {
            for (Item item : items) {
                if (itemName.equals(item.getItemName())) {
                    currentItems.add(item);
                    break;
                }
            }
            double orderPrice = 0;
            for (Item item : currentItems) {
                orderPrice += item.getPrice();
            }

            request.getSession().invalidate();
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("orderedItems", orderedItems);
            request.getSession().setAttribute("currentItems", currentItems);
            request.getSession().setAttribute("orderPrice", orderPrice);
            request.getSession().setAttribute("items", items);
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        } else if (action.equals("enter")) {
            double orderPrice = 0;
            for (Item item : currentItems) {
                orderPrice += item.getPrice();
            }
            Order order = new Order(user.getId(), orderPrice);
            productService.saveOrder(order);
            order = productService.lastUserOrder(user);

            productService.saveOrderedItems(currentItems, order);
            orderedItems.addAll(currentItems);

            request.getSession().invalidate();
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("orderedItems", orderedItems);
            request.getSession().setAttribute("currentItems", currentItems);
            request.getSession().setAttribute("orderPrice", orderPrice);
            request.getSession().setAttribute("items", items);
            response.sendRedirect("/shop/jsp/resultcheck.jsp");
        } else {
            request.getRequestDispatcher("/jsp/404.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response) ;
    }
}
