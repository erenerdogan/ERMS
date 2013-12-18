/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

/**
 *
 * @author eren
 */
public class FactoryDao {

    public static UserDaoInterface getUserDao() {
        return new UserDaoImpl();
    }
}
