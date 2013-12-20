/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.model.ImageModel;

/**
 *
 * @author eren
 */
public interface ImageDaoInterface{
    
    int addImage(ImageModel image);
    ImageModel getImage(int imageID);
    void editImage(ImageModel image);
    
    
}
