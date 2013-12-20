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
import java.util.Map;
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
public class ProductEditBean implements Serializable{
    
    @ManagedProperty(value="#{param.productID}")
    private int productID;
    @ManagedProperty(value="#{param.categoryID}")
    private int categoryID;
    
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    
    private String productName;
    private String productDescription;
    private boolean productStatus;
    private double productPrice;
    private int productCalorie;
    
    private UploadedFile imageFile;

    public ProductEditBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        categoryID = Integer.parseInt(params.get("categoryID"));
        productID = Integer.parseInt(params.get("productID"));
        System.out.println("CategoryID : "+ categoryID + " ProductID : "+ productID);
        
        ProductModel pm = FactoryDao.getProductDao().getProduct(categoryID, productID);
        productName = pm.getProductName();
        productDescription = pm.getProductDescription();
        productCalorie = pm.getProductCalorie();
        productStatus = pm.isProductStatus();
        productPrice =pm.getProductPrice();
        
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public UploadedFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(UploadedFile imageFile) {
        this.imageFile = imageFile;
    }

    public int getProductCalorie() {
        return productCalorie;
    }

    public void setProductCalorie(int productCalorie) {
        this.productCalorie = productCalorie;
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
    
    public String save() {
        System.out.println("Save");
        ImageModel im = null;
        if (!imageFile.getFileName().isEmpty()) {
            String imageName = fileUpload(imageFile);
            im = new ImageModel();
            im.setImagePath(imageName);
            im.setImageThumb(imageName);
        }
        
        CategoryModel cm = new CategoryModel();
        cm.setCategoryID(categoryID);
        
        ProductModel pm = new ProductModel();
        pm.setProductID(productID);
        pm.setProductCalorie(productCalorie);
        pm.setProductDescription(productDescription);
        pm.setProductName(productName);
        pm.setProductPrice(productPrice);
        pm.setProductStatus(productStatus);
        pm.setProductImageModel(im);
        pm.setProductCategoryModel(cm);
        
        FactoryDao.getProductDao().editProduct(pm);

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
    
}
