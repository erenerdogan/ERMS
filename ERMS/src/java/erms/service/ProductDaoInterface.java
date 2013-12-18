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
    
    void createProduct(ProductModel product, int categoryID);
    void deleteProduct(ProductModel product);
    void editProduct(ProductModel product);
    void editStatus(ProductModel product);
    List<ProductModel> getAllProduct();
    ProductModel getProduct(int ID);
}
