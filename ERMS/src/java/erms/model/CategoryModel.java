/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.model;

/**
 *
 * @author eren
 */
public class CategoryModel {
    
    private int categoryID;
    private String categoryName;
    private ImageModel categoryImageModel;
    private UserModel categoryUserModel;

    public CategoryModel() {
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public ImageModel getCategoryImageModel() {
        return categoryImageModel;
    }

    public void setCategoryImageModel(ImageModel categoryImageModel) {
        this.categoryImageModel = categoryImageModel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public UserModel getCategoryUserModel() {
        return categoryUserModel;
    }

    public void setCategoryUserModel(UserModel categoryUserModel) {
        this.categoryUserModel = categoryUserModel;
    }

}
