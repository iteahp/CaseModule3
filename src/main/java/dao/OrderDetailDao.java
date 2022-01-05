package dao;

import model.Order;
import model.OrderDetail;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao {
    private Connection connection = ConnectMySql.getConnection();
    public List<OrderDetail> getAllOrderDetail() {
        String sqlGetAll = "SELECT * FROM orderdetail ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<OrderDetail> orderDetailList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int orderId = resultSet.getInt("orderId");
                int productId = resultSet.getInt("productId");
                double quantity = resultSet.getDouble("quantity");
                orderDetailList.add(new OrderDetail(id,orderId,productId,quantity));
            }
            return orderDetailList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  boolean saveOrderDetail(OrderDetail orderDetail) {
        boolean result=false;
        String saveSQl = "INSERT INTO orderdetail (orderId,productId,quantity) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
            preparedStatement.setInt(1,orderDetail.getOrderId());
            preparedStatement.setInt(2,orderDetail.getProductId());
            preparedStatement.setDouble(3,orderDetail.getQuantity());
            result = preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public  void deleteOrder(int id){
        String deleteSQL = "DELETE from orders where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
