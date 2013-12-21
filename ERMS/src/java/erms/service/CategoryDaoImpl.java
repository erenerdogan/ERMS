/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.db.Database;
import erms.model.CategoryModel;
import erms.model.ImageModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eren
 */
public class CategoryDaoImpl implements CategoryDaoInterface {

    private Database db;
    private ResultSet rs;
    private PreparedStatement ps;

    public CategoryDaoImpl() {
        db = new Database();
    }

    @Override
    public void createCategory(CategoryModel category, int userID) {
        ImageModel im = category.getCategoryImageModel();
        int imageID = FactoryDao.getImageDao().addImage(im);
        String query = "INSERT INTO Categories (cname, cuid,ciid) VALUES (?, ?, ?)";
        try {

            ps = db.getCon().prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, userID);
            ps.setInt(3, imageID);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteCategory(int categoryID) {
        try {
            String query = "DELETE FROM CATEGORIES WHERE cid = ?";
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, categoryID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editCategory(CategoryModel category, int userID) {
        try {
            int categoryID = category.getCategoryID();
            String query = "SELECT * FROM CATEGORIES WHERE cid = ?";
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, categoryID);
            rs = ps.executeQuery();

            while (rs.next()) {
                //cm.setCategoryID(rs.getInt("cid"));
                if (!category.getCategoryName().equalsIgnoreCase(rs.getString("cname"))) {
                    //cm.setCategoryName(category.getCategoryName());
                    query = "UPDATE Categories SET cname=? WHERE cid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, categoryID);
                    ps.setString(1, category.getCategoryName());
                    ps.executeUpdate();
                }

                //yeni resim eklendimi
                if (category.getCategoryImageModel() != null) {
                    ImageModel im = category.getCategoryImageModel();
                    int imageID = FactoryDao.getImageDao().addImage(im);
                    query = "UPDATE Categories SET ciid=? WHERE cid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, categoryID);
                    ps.setInt(1, imageID);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<CategoryModel> getAllCategory(int userID) {
        String query = "SELECT * FROM CATEGORIES WHERE cuid = ?";
        List<CategoryModel> allCategories = new ArrayList<CategoryModel>();
        try {
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoryModel cm = new CategoryModel();
                cm.setCategoryID(rs.getInt("cid"));
                cm.setCategoryName(rs.getString("cname"));
                query = "SELECT * FROM IMAGES WHERE iid = ?";

                ps = db.getCon().prepareStatement(query);
                ps.setInt(1, rs.getInt("ciid"));
                ResultSet rs2 = ps.executeQuery();
                while (rs2.next()) {
                    ImageModel im = new ImageModel();
                    im.setImageID(rs2.getInt("iid"));
                    im.setImagePath(rs2.getString("ipath"));
                    im.setImageThumb(rs2.getString("ithumb"));
                    cm.setCategoryImageModel(im);
                }

                allCategories.add(cm);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCategories;
    }

    @Override
    public CategoryModel getCategory(int categoryID) {
        try {
            String query = "SELECT * FROM CATEGORIES WHERE cid = ?";
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, categoryID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoryModel cm = new CategoryModel();
                cm.setCategoryID(rs.getInt("cid"));
                cm.setCategoryName(rs.getString("cname"));
                query = "SELECT * FROM IMAGES WHERE iid = ?";
                ps = db.getCon().prepareStatement(query);
                ps.setInt(1, rs.getInt("ciid"));
                rs = ps.executeQuery();
                while (rs.next()) {
                    ImageModel im = new ImageModel();
                    im.setImageID(rs.getInt("iid"));
                    im.setImagePath(rs.getString("ipath"));
                    im.setImageThumb(rs.getString("ithumb"));
                    cm.setCategoryImageModel(im);
                }
                return cm;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<CategoryModel> getWSAllCategory(String appKey) {
        List<CategoryModel> allCategories = new ArrayList<CategoryModel>();
        try {
            String query = "SELECT uid FROM USERS WHERE uappkey=?";
            ps = db.getCon().prepareStatement(query);
            ps.setString(1, appKey);
            rs = ps.executeQuery();
            int userID = 0;
            while (rs.next()) {
                userID = rs.getInt("uid");
            }
            query = "SELECT * FROM CATEGORIES WHERE cuid = ?";
            

            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoryModel cm = new CategoryModel();
                cm.setCategoryID(rs.getInt("cid"));
                cm.setCategoryName(rs.getString("cname"));
                query = "SELECT * FROM IMAGES WHERE iid = ?";

                ps = db.getCon().prepareStatement(query);
                ps.setInt(1, rs.getInt("ciid"));
                ResultSet rs2 = ps.executeQuery();
                while (rs2.next()) {
                    ImageModel im = new ImageModel();
                    im.setImageID(rs2.getInt("iid"));
                    im.setImagePath(rs2.getString("ipath"));
                    im.setImageThumb(rs2.getString("ithumb"));
                    cm.setCategoryImageModel(im);
                }

                allCategories.add(cm);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCategories;
    }
}
