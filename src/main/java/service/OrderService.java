package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    List<Product> productListAddOrder = new ArrayList<>();

    public List<Product> getProductListAddOrder(){
        return productListAddOrder;
    }
    public void addProductToOrder(Product product){
        productListAddOrder.add(product);
    }
    public void deleteProductFromOrder(int index){
        productListAddOrder.remove(index);
    }
    public  int findIndexById(int id){
        for (int i = 0; i < productListAddOrder.size(); i++) {
            if (productListAddOrder.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }
    public double totalListOrder(){
        double sum=0;
        for (Product productOrder: productListAddOrder
             ) {
            sum+=productOrder.getPrice();
        }
        return sum;
    }
}
