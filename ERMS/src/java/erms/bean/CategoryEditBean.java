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
public class CategoryEditBean implements Serializable {

    @ManagedProperty(value = "#{param.categoryID}")
    private int categoryID;
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    private UploadedFile imageFile;
    private String categoryName;

    public CategoryEditBean() {
    }

    public String getCategoryName() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        categoryID = Integer.parseInt(params.get("categoryID"));
        categoryName = FactoryDao.getCategoryDao().getCategory(categoryID).getCategoryName();
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

    public UploadedFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(UploadedFile imageFile) {
        this.imageFile = imageFile;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String save() {
        System.out.println("Save");
        String imageName = fileUpload(imageFile);
        ImageModel im = new ImageModel();
        im.setImagePath(imageName);
        im.setImageThumb(imageName);
        
        CategoryModel cm = new CategoryModel();
        System.out.println("Save Category ID : "+ categoryID);
        cm.setCategoryID(categoryID);
        cm.setCategoryImageModel(im);
        cm.setCategoryName(categoryName);
        
        FactoryDao.getCategoryDao().editCategory(cm, userBean.getId());
        
        return "categoryEdit?categoryID="+categoryID+"faces-redirect=true";
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
