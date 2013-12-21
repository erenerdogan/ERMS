/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.model.ImageModel;
import erms.model.ProductModel;
import java.util.List;

/**
 *
 * @author eren
 */
public interface ProductDaoInterface {
    
    void createProduct(ProductModel product);
    void deleteProduct(int productID);
    void editProduct(ProductModel product);
    void editStatus(ProductModel product);
    List<ProductModel> getAllProduct(int userID);
    ProductModel getProduct(int categoryID,int productID);
    List<ProductModel> getWSAllProduct(String appKey);
}
