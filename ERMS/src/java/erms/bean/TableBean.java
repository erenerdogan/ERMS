/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import erms.model.TableModel;
import erms.service.FactoryDao;
import java.io.Serializable;
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
public class TableBean implements Serializable{
    
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    
    @ManagedProperty(value="#{param.tableID}")
    private int tableID;
    
    private List<TableModel> allTables;
    private String tableName;

    public TableBean() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }
    
    

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    

    public List<TableModel> getAllTables() {
        allTables = FactoryDao.getTableDao().getAllTables(userBean.getId());
        return allTables;
    }

    public void setAllTables(List<TableModel> allTables) {
        this.allTables = allTables;
    }
    
    public void remove(){
        FactoryDao.getTableDao().deleteTable(tableID);
    }
    
    public String add() {
        System.out.println("Add");
        TableModel tm = new TableModel();
        tm.setTableName(tableName);
        
        
        FactoryDao.getTableDao().addTable(tm, userBean.getId());
        return "table?faces-redirect=true";
    }
    
}
