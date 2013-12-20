/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import erms.model.CategoryModel;
import erms.model.ImageModel;
import erms.service.FactoryDao;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class CategoryBean implements Serializable {

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    
    
    @ManagedProperty("#{param.categoryID}")
    private int categoryID;
    
    private List<CategoryModel> allCategories;
    private String categoryName;
    private UploadedFile imageFile;
    

    public CategoryBean() {
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

    

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public List<CategoryModel> getAllCategories() {
        allCategories = FactoryDao.getCategoryDao().getAllCategory(userBean.getId());
        return allCategories;
    }

    public void setAllCategories(List<CategoryModel> allCategories) {
        this.allCategories = allCategories;
    }

    public String aktar() {
        System.out.println("Aktar");
        String imageName = fileUpload(imageFile);
        ImageModel im = new ImageModel();
        im.setImagePath(imageName);
        im.setImageThumb(imageName);
        
        CategoryModel cm = new CategoryModel();
        cm.setCategoryImageModel(im);
        cm.setCategoryName(categoryName);
        
        FactoryDao.getCategoryDao().createCategory(cm, userBean.getId());
        return "category?faces-redirect=true";
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
    
    public String edit(){
        return "categoryEdit?categoryID="+categoryID+"faces-redirect=true";
    }
    
    public void remove(){
        FactoryDao.getCategoryDao().deleteCategory(categoryID);
    }
}
