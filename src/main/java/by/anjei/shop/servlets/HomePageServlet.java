package by.anjei.shop.servlets;

import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.User;
import by.anjei.shop.db.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 10.04.18
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */

public class HomePageServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();

        List<Item> items = productService.allAvailableItems();

        String login = (String) request.getParameter("login");
        String agreement = (String) request.getParameter("agreement");
        if (agreement == null || !isValidLogin(login)) {
            request.getRequestDispatcher("/jsp/invalidlogindata.jsp").forward(request, response);
        } else {

            User userFromDatabase = productService.userByLogin(login);
            if (userFromDatabase == null) {
                User user = new User(login);
                productService.saveUser(user);

                user = productService.userByLogin(login);

                List<Item> orderedItems = new ArrayList<>();
                List<Item> currentItems = new ArrayList<>();

                request.getSession().invalidate();
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("orderedItems", orderedItems);
                request.getSession().setAttribute("currentItems", currentItems);
                request.getSession().setAttribute("items", items);
            } else {
                List<Item> orderedItems = productService.orderedItems(userFromDatabase);
                List<Item> currentItems = new ArrayList<>();

                request.getSession().invalidate();
                request.getSession().setAttribute("user", userFromDatabase);
                request.getSession().setAttribute("orderedItems", orderedItems);
                request.getSession().setAttribute("currentItems", currentItems);
                request.getSession().setAttribute("items", items);
            }
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private boolean isValidLogin(String login) {
        if (login == null || login.equals("")) {
            return false;
        }

        return true;
    }
}
