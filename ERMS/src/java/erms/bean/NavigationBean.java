/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class NavigationBean {
    
    
    public String goAdmin(){
        System.out.println("Admin Navigation");
        return "admin?faces-redirect=true";
    }
    
    public String goCategory(){
        System.out.println("Category Navigation");
        return "category?faces-redirect=true";
    }
    
    public String goProduct(){
        System.out.println("Product Navigation");
        return "product?faces-redirect=true";
    }
    
    public String goTable(){
        return "table?faces-redirect=true";
    }
    
    public String goOrder(){
        return "order?faces-redirect=true";
    }
    
}
