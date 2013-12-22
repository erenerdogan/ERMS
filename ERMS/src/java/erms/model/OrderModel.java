/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.model;

import java.util.Date;



/**
 *
 * @author eren
 */
public class OrderModel {
    
    private int orderID;
    private ProductModel orderProductModel;
    private int orderNum;
    private Date orderDate;
    private boolean orderStatus;
    private TableModel orderTableModel;

    public OrderModel() {
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
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
