package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private  Connection connection = ConnectMySql.getConnection();

    public ProductDao() {
    }

    public  List<Product> getAllProduct() {
        String sqlGetAll = "SELECT * FROM product";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> listProduct = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                long quantity = resultSet.getLong("quantity");
                int categoryId = resultSet.getInt("categoryId");

                listProduct.add(new Product(id, name,  price,description,quantity,img,categoryId));
            }
            return listProduct;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  void updateProduct(Product product) {
        String saveSQl = "UPDATE product SET  name = ?, price = ?, quantity = ?, description = ?, img = ? WHERE (id = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setLong(3,product.getQuantity());
            preparedStatement.setString(4,product.getDescription());
            preparedStatement.setString(5,product.getImg());
            preparedStatement.setInt(6,product.getId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void saveProduct(Product product) {
        String saveSQl = "INSERT INTO product(name,price,description,quantity,img,categoryId) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setLong(4,product.getQuantity());
            preparedStatement.setString(5,product.getImg());
            preparedStatement.setInt(6,product.getCategoryId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void deleteProduct(int id){
        String deleteSQL = "DELETE from product where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Product> findProductByCategoryId(int categoryId){
        String sqlGetAll = "SELECT * FROM product where categoryId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            preparedStatement.setInt(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> listProduct = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                long quantity = resultSet.getLong("quantity");

                listProduct.add(new Product(id, name,  price,description,quantity,img,categoryId));
            }
            return listProduct;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Product> findProductByName(String nameFind){
        String sqlGetAll = "SELECT * FROM product where name like '%"+nameFind+"%\'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> listProduct = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                long quantity = resultSet.getLong("quantity");
                int categoryId = resultSet.getInt("categoryId");

                listProduct.add(new Product(id, name,  price,description,quantity,img,categoryId));
            }
            return listProduct;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
