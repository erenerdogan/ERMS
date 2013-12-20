/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.db.Database;
import erms.model.ImageModel;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eren
 */
public class ImageDaoImpl implements ImageDaoInterface {

    private Database db;
    private ResultSet rs;
    private PreparedStatement ps;

    public ImageDaoImpl() {
        db = new Database();
    }

    @Override
    public int addImage(ImageModel image) {
        String query = "INSERT INTO IMAGES (ipath, ithumb) VALUES (?,?)";
        try {

            ps = db.getCon().prepareStatement(query);
            ps.setString(1, image.getImagePath());
            ps.setString(2, image.getImageThumb());
            ps.executeUpdate();
            ps.close();
            query = "SELECT iid FROM IMAGES WHERE ipath = ?";
            ps = db.getCon().prepareStatement(query);
            ps.setString(1, image.getImagePath());
            rs = ps.executeQuery();
            
            while (rs.next()) {
                System.out.println("RS ADD IMAGE : " + rs.getInt("iid"));
                return rs.getInt("iid");
            }
        } catch (SQLException ex) {

            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public ImageModel getImage(int imageID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editImage(ImageModel image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
