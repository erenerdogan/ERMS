/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.model;

/**
 *
 * @author eren
 */
public class ProductModel {
    
    private int productID;
    private String productName;
    private String productDescription;
    private int productCalorie;
    private double productPrice;
    private boolean productStatus;
    private ImageModel productImageModel;
    private CategoryModel productCategoryModel; 

    public ProductModel() {
    }

    public int getProductCalorie() {
        return productCalorie;
    }

    public void setProductCalorie(int productCalorie) {
        this.productCalorie = productCalorie;
    }

    public CategoryModel getProductCategoryModel() {
        return productCategoryModel;
    }

    public void setProductCategoryModel(CategoryModel productCategoryModel) {
        this.productCategoryModel = productCategoryModel;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public ImageModel getProductImageModel() {
        return productImageModel;
    }

    public void setProductImageModel(ImageModel productImageModel) {
        this.productImageModel = productImageModel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    
}
