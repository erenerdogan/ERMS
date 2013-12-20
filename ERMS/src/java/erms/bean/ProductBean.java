/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import erms.model.CategoryModel;
import erms.model.ImageModel;
import erms.model.ProductModel;
import erms.service.FactoryDao;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class ProductBean {
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;
    
    @ManagedProperty(value="#{categoryBean}")
    private CategoryBean categoryBean;
    
    @ManagedProperty("#{param.productID}")
    private int productID;
    
    private List<ProductModel> allProducts;
    private int categoryID;
    private String productName;
    private String productDescription;
    private boolean productStatus;
    private double productPrice;
    private int productCalorie;
    
    private UploadedFile imageFile;

    public ProductBean() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    

    public int getProductCalorie() {
        return productCalorie;
    }

    public void setProductCalorie(int productCalorie) {
        this.productCalorie = productCalorie;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    
    
    public UploadedFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(UploadedFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }
    
    

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    
    

    public CategoryBean getCategoryBean() {
        return categoryBean;
    }

    public void setCategoryBean(CategoryBean categoryBean) {
        this.categoryBean = categoryBean;
    }
    
    

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    public List<ProductModel> getAllProducts() {
        allProducts = FactoryDao.getProductDao().getAllProduct(userBean.getId());
        return allProducts;
    }

    public void setAllProducts(List<ProductModel> allProducts) {
        this.allProducts = allProducts;
    }
    
    public String add() {
        System.out.println("Add");
        String imageName = fileUpload(imageFile);
        
        ImageModel im = new ImageModel();
        im.setImagePath(imageName);
        im.setImageThumb(imageName);
        
        System.out.println(imageName + " " +categoryID + " " + productPrice);
        CategoryModel cm = new CategoryModel();
        cm.setCategoryID(categoryID);
        
        ProductModel pm = new ProductModel();
        pm.setProductCalorie(productCalorie);
        pm.setProductDescription(productDescription);
        pm.setProductName(productName);
        pm.setProductPrice(productPrice);
        pm.setProductStatus(productStatus);
        pm.setProductCategoryModel(cm);
        pm.setProductImageModel(im);
        
        FactoryDao.getProductDao().createProduct(pm);
        return "product?faces-redirect=true";
    }
    
    public String fileUpload(UploadedFile f) {
        System.out.println("File Upload : " + f.getFileName());
        try {
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
            String name = fmt.format(new Date())
                    + f.getFileName().substring(
                    f.getFileName().lastIndexOf('.'));
            File file = new File(path + "images/" + name);

            InputStream is = f.getInputstream();
            OutputStream out = new FileOutputStream(file);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                System.out.println(buf.length);
                out.write(buf, 0, len);
            }
            is.close();
            out.close();
            System.out.println(file.getPath());
            return name;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void remove(){
        FactoryDao.getProductDao().deleteProduct(productID);
    }
    
    public String edit(){
        return "categoryEdit?categoryID="+categoryID+"faces-redirect=true";
    }
}
