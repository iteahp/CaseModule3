package controller;

import dao.ProductDao;
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

@WebServlet(urlPatterns = "/managerProduct")
public class ManagerServlet extends HttpServlet {
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
            case "delete": {
                deleteProduct(req,resp);
                break;
            }
            case "update": {
                showUpdateForm(req,resp);
                break;
            }
            default: {
                showProductList(req,resp);
                break;
            }
        }
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productList=productService.getProductList();
        Product product = productList.get(productService.findIndexById(id));
        req.setAttribute("productUpdate",product);
        requestDispatcher = req.getRequestDispatcher("/manager/updateProduct.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleleProduct(id);
        resp.sendRedirect("/managerProduct");
    }

    private void showProductList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("productList",productService.getProductList());
        requestDispatcher = req.getRequestDispatcher("/manager/managerProduct.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action = "";
        }
        switch (action){
            case "create": {
                createProduct(req,resp);
                break;
            }
            case "update" :{
                updateProduct(req,resp);
                break;
            }
            case "view" :{
                productDetail(req,resp);
                break;
            }
            default: {
                showProductList(req,resp);
                break;
            }
        }
    }

    private void productDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productFound = productService.getProductList().get(productService.findIndexById(id));
        req.setAttribute("productFound",productFound);
        requestDispatcher = req.getRequestDispatcher("/manager/detailProduct.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String img = req.getParameter("img");
        double price = Double.parseDouble(req.getParameter("price"));
        long quantity = Long.parseLong(req.getParameter("quantity"));
        Product productUpdate = new Product(id,name,price,description,quantity,img);
        productService.updateProduct(productUpdate);
        resp.sendRedirect("/managerProduct");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        String description = req.getParameter("description");
        long quantity = Long.parseLong(req.getParameter("quantity"));
        String img = req.getParameter("img");
        Product product = new Product(1,name,price,description,quantity,img);
        productService.addProduct(product);
        resp.sendRedirect("/managerProduct");
    }
}
