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
    
    public static CategoryDaoInterface getCategoryDao() {
        return new CategoryDaoImpl();
    }
    
    public static ProductDaoInterface getProductDao() {
        return new ProductDaoImpl();
    }
    
    public static ImageDaoInterface getImageDao() {
        return new ImageDaoImpl();
    }
    
    public static TableDaoInterface getTableDao() {
        return new TableDaoImpl();
    }
}
