/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.model.UserModel;

/**
 *
 * @author eren
 */
public interface TableInterface {
    
    void addTable(int userID);
    void deleteTable(int userID,int tableID);
    void getTable(int tableID);
}
