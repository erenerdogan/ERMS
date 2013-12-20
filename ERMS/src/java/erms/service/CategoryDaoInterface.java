/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.model.CategoryModel;
import java.util.List;

/**
 *
 * @author eren
 */
public interface CategoryDaoInterface {
    
    void createCategory(CategoryModel category, int userID);
    void deleteCategory(int categoryID);
    void editCategory(CategoryModel category, int userID);
    List<CategoryModel> getAllCategory(int userID);
    CategoryModel getCategory(int categoryID);
}
