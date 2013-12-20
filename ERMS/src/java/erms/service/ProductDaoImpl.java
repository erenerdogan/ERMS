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
            if(product.isProductStatus()==true)
                ps.setInt(5, 1);
            else
                ps.setInt(5, 0);
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
        throw new UnsupportedOperationException("Not supported yet.");
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



                // Image Islemi Yapilacak
                allProducts.add(pm);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProducts;
    }

    @Override
    public ProductModel getProduct(int ID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
