package controller;

import model.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    List<Product> productList = productService.getProductList();
    RequestDispatcher requestDispatcher;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String action = req.getParameter("action");
      if (action==null){
          action = "";
      }
      switch (action){
          case "create":{
              createProductForm(req,resp);
              break;
          }
          case "delete":{

              break;
          }
          default:{
              showProductList(req,resp);
              break;
          }
      }
    }

    private void createProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestDispatcher =req.getRequestDispatcher("/manager/managerProduct.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void showProductList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.setAttribute("productList",productList);
         requestDispatcher = req.getRequestDispatcher("views/product.jsp");
         requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
