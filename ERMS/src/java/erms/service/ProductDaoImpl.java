/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.db.Database;
import erms.model.CategoryModel;
import erms.model.ImageModel;
import erms.model.ProductModel;
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
public class ProductDaoImpl implements ProductDaoInterface {

    private Database db;
    private ResultSet rs;
    private PreparedStatement ps;

    public ProductDaoImpl() {
        db = new Database();
    }

    @Override
    public void createProduct(ProductModel product) {
        ImageModel im = product.getProductImageModel();
        int imageID = FactoryDao.getImageDao().addImage(im);
        String query = "INSERT INTO Products (pname, pdescription, pcalori, pprice, pstatus, piid, pcid) VALUES (?, ?, ?,?, ?, ?,?)";
        try {

            ps = db.getCon().prepareStatement(query);
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductDescription());
            ps.setInt(3, product.getProductCalorie());
            ps.setDouble(4, product.getProductPrice());
            if (product.isProductStatus() == true) {
                ps.setInt(5, 1);
            } else {
                ps.setInt(5, 0);
            }
            ps.setInt(6, imageID);
            ps.setInt(7, product.getProductCategoryModel().getCategoryID());


            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteProduct(int productID) {
        try {
            String query = "DELETE FROM PRODUCTS WHERE pid = ?";
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, productID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editProduct(ProductModel product) {
        try {
            int productID = product.getProductID();
            int categoryID = product.getProductCategoryModel().getCategoryID();
            String query = "SELECT * FROM PRODUCTS WHERE pid = ?";
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (!product.getProductName().equalsIgnoreCase(rs.getString("pname"))) {
                    query = "UPDATE PRODUCTS SET pname=? WHERE pid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, productID);
                    ps.setString(1, product.getProductName());
                    ps.executeUpdate();
                }

                if (!product.getProductDescription().equalsIgnoreCase(rs.getString("pdescription"))) {
                    query = "UPDATE PRODUCTS SET pdescription=? WHERE pid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, productID);
                    ps.setString(1, product.getProductDescription());
                    ps.executeUpdate();
                }

                if (product.getProductCalorie() != rs.getInt("pcalori")) {
                    query = "UPDATE PRODUCTS SET pcalori=? WHERE pid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, productID);
                    ps.setInt(1, product.getProductCalorie());
                    ps.executeUpdate();
                }

                if (product.getProductPrice() != rs.getDouble("pprice")) {
                    query = "UPDATE PRODUCTS SET pprice=? WHERE pid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, productID);
                    ps.setDouble(1, product.getProductPrice());
                    ps.executeUpdate();
                }

                int s = 0;
                if (product.isProductStatus() == true) {
                    s = 1;
                }
                int t = 0;
                if (rs.getInt("pstatus") == 1) {
                    t = 1;
                }

                if (t != s) {
                    query = "UPDATE PRODUCTS SET pstatus=? WHERE pid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, productID);
                    ps.setInt(1, s);
                    ps.executeUpdate();
                }
                
                if(product.getProductCategoryModel().getCategoryID()!=rs.getInt("pcid")){
                    query = "UPDATE PRODUCTS SET pcid=? WHERE pid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, productID);
                    ps.setInt(1, product.getProductCategoryModel().getCategoryID());
                    ps.executeUpdate();
                }

                //yeni resim eklendimi
                if (product.getProductImageModel() != null) {
                    ImageModel im = product.getProductImageModel();
                    int imageID = FactoryDao.getImageDao().addImage(im);
                    query = "UPDATE PRODUCTS SET piid=? WHERE pid=?";
                    ps = db.getCon().prepareStatement(query);
                    ps.setInt(2, productID);
                    ps.setInt(1, imageID);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editStatus(ProductModel product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ProductModel> getAllProduct(int userID) {
        String query = "SELECT * FROM PRODUCTS,CATEGORIES WHERE categories.cuid=? AND categories.cid =products.pcid";
        List<ProductModel> allProducts = new ArrayList<ProductModel>();
        try {
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductModel pm = new ProductModel();
                pm.setProductID(rs.getInt("pid"));
                pm.setProductCalorie(rs.getInt("pcalori"));
                pm.setProductDescription(rs.getString("pdescription"));
                pm.setProductPrice(rs.getDouble("pprice"));
                pm.setProductName(rs.getString("pname"));
                pm.setProductStatus(rs.getBoolean("pstatus"));
                query = "SELECT * FROM CATEGORIES WHERE categories.cid = ?";
                ps = db.getCon().prepareStatement(query);
                ps.setInt(1, rs.getInt("pcid"));
                ResultSet rs2 = ps.executeQuery();
                while (rs2.next()) {
                    CategoryModel cm = new CategoryModel();
                    cm.setCategoryID(rs2.getInt("cid"));
                    cm.setCategoryName(rs2.getString("cname"));
                    pm.setProductCategoryModel(cm);
                }

                query = "SELECT * FROM IMAGES WHERE iid = ?";

                ps = db.getCon().prepareStatement(query);
                ps.setInt(1, rs.getInt("piid"));
                ResultSet rs3 = ps.executeQuery();
                while (rs3.next()) {
                    ImageModel im = new ImageModel();
                    im.setImageID(rs3.getInt("iid"));
                    im.setImagePath(rs3.getString("ipath"));
                    im.setImageThumb(rs3.getString("ithumb"));
                    pm.setProductImageModel(im);
                }
                allProducts.add(pm);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProducts;
    }

    @Override
    public ProductModel getProduct(int categoryID, int productID) {
        try {
            String query = "SELECT * FROM PRODUCTS,CATEGORIES WHERE products.pid=? AND categories.cid=? AND categories.cid =products.pcid";
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, categoryID);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductModel pm = new ProductModel();
                pm.setProductID(rs.getInt("pid"));
                pm.setProductCalorie(rs.getInt("pcalori"));
                pm.setProductDescription(rs.getString("pdescription"));
                pm.setProductPrice(rs.getDouble("pprice"));
                pm.setProductName(rs.getString("pname"));
                pm.setProductStatus(rs.getBoolean("pstatus"));

                query = "SELECT * FROM CATEGORIES WHERE categories.cid = ?";
                ps = db.getCon().prepareStatement(query);
                ps.setInt(1, rs.getInt("pcid"));
                ResultSet rs2 = ps.executeQuery();
                while (rs2.next()) {
                    CategoryModel cm = new CategoryModel();
                    cm.setCategoryID(rs2.getInt("cid"));
                    cm.setCategoryName(rs2.getString("cname"));
                    pm.setProductCategoryModel(cm);
                }

                query = "SELECT * FROM IMAGES WHERE iid = ?";

                ps = db.getCon().prepareStatement(query);
                ps.setInt(1, rs.getInt("piid"));
                ResultSet rs3 = ps.executeQuery();
                while (rs3.next()) {
                    ImageModel im = new ImageModel();
                    im.setImageID(rs3.getInt("iid"));
                    im.setImagePath(rs3.getString("ipath"));
                    im.setImageThumb(rs3.getString("ithumb"));
                    pm.setProductImageModel(im);
                }
                db.connectionClose();
                return pm;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
