/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import erms.model.OrderModel;
import erms.service.FactoryDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class OrderBean {
    
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;        
    
    List<OrderModel> orderList;
    
    public OrderBean() {
    }
    
    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    public List<OrderModel> getOrderList() {
        orderList = FactoryDao.getOrderDao().getAllOrder(userBean.getId());
        return orderList;
    }

    public void setOrderList(List<OrderModel> orderList) {
        this.orderList = orderList;
    }

    
    
    
}
