/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.model;

/**
 *
 * @author eren
 */
public class OrderModel {
    
    private int orderID;
    private ProductModel orderProductModel;
    private double orderNum;
    private String orderDate;
    private boolean orderStatus;
    private TableModel orderTableModel;

    public OrderModel() {
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(double orderNum) {
        this.orderNum = orderNum;
    }

    public ProductModel getOrderProductModel() {
        return orderProductModel;
    }

    public void setOrderProductModel(ProductModel orderProductModel) {
        this.orderProductModel = orderProductModel;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public TableModel getOrderTableModel() {
        return orderTableModel;
    }

    public void setOrderTableModel(TableModel orderTableModel) {
        this.orderTableModel = orderTableModel;
    }

    
}
