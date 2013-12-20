/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import erms.model.ImageModel;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class ImageBean implements Serializable {

    private ImageModel imageModel;
    @ManagedProperty(value = "#{param.imageID}")
    private int imageID;
    private UploadedFile upload;
    

    public ImageBean() {
    }

    public UploadedFile getUpload() {
        return upload;
    }

    public void setUpload(UploadedFile upload) {
        this.upload = upload;
    }
    
    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public String fileUpload(UploadedFile f) {
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
