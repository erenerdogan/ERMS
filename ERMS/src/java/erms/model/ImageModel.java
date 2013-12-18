/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.model;

/**
 *
 * @author eren
 */
public class ImageModel {
    
    private int imageID;
    private String imagePath;
    private String imageThumb;

    public ImageModel() {
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }
           
}
