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
public interface UserDaoInterface {
    void createUser(UserModel user);
    void deleteUser(UserModel user);
    void editUser(UserModel user);
    String getUserAppKey(int id);
    UserModel getUser(String email, String password);
    void changePassword(int userID, String newPassword);
}
