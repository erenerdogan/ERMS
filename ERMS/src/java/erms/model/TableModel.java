/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.model;

/**
 *
 * @author eren
 */
public class TableModel {
    
    private int tableID;
    private UserModel tableUserModel;
    private String tableName;

    public TableModel() {
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public UserModel getTableUserModel() {
        return tableUserModel;
    }

    public void setTableUserModel(UserModel tableUserModel) {
        this.tableUserModel = tableUserModel;
    }
    
}
