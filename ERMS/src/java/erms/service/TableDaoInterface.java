/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.model.TableModel;
import erms.model.UserModel;
import java.util.List;

/**
 *
 * @author eren
 */
public interface TableDaoInterface {
    
    void addTable(TableModel tableModel,int userID);
    void deleteTable(int tableID);
    List<TableModel> getAllTables(int userID);
}
