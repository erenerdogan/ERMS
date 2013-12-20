/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import erms.db.Database;
import erms.model.UserModel;
import erms.service.FactoryDao;
import erms.service.UserDaoImpl;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eren
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String appKey;
    private String newPassword;
    private String oldPassword;

    public UserBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String doLogin() {
        System.out.println("Login");
        UserModel um = FactoryDao.getUserDao().getUser(email, password);
        if (um != null) {
            id = um.getUserID();
            name = um.getUserName();
            surname = um.getUserSurname();
            address = um.getUserAddress();
            phone = um.getUserPhone();
            appKey = um.getUserAppKey();
            return "admin?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public String doCreateUser() {
        UserModel um = new UserModel();
        um.setUserMail(email);
        um.setUserAddress(address);
        um.setUserName(name);
        um.setUserSurname(surname);
        um.setUserPhone(phone);
        um.setUserPassword(password);
        FactoryDao.getUserDao().createUser(um);
        return doLogin();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
    
    public String changePassword(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(password.equals(oldPassword))
        {
            FactoryDao.getUserDao().changePassword(id, newPassword);
            context.addMessage(null, new FacesMessage("Successful", "Password has changed."));
        }else{
            context.addMessage(null, new FacesMessage("Error", "Password has not changed."));
        }
        return "admin?faces-redirect=true";
    }
}
