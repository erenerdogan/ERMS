/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.model.OrderModel;
import java.util.List;

/**
 *
 * @author eren
 */
public interface OrderDaoInterface {
    
    void deleteOrder(int orderID);
    void deleteTableOrder(int tableID);
    void editStatus(List<OrderModel> order, boolean status);
}
