/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.db.Database;
import erms.model.OrderModel;
import erms.model.ProductModel;
import erms.model.TableModel;
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
public class OrderDaoImpl implements OrderDaoInterface {

    private Database db;
    private ResultSet rs;
    private PreparedStatement ps;

    public OrderDaoImpl() {
        db = new Database();
    }
    
    

    @Override
    public void deleteOrder(int orderID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteTableOrder(int tableID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editStatus(List<OrderModel> order, boolean status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<OrderModel> getAllOrder(int userID) {
        try {
            String query = "SELECT * FROM ORDERS,TABLES,PRODUCTS WHERE TABLES.tuid = ? AND ORDERS.otid = TABLES.tid AND ORDERS.opid=PRODUCTS.pid";
            
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            List<OrderModel> orderList = new ArrayList<OrderModel>();
            while (rs.next()) {                
                OrderModel om = new OrderModel();
                om.setOrderDate(rs.getDate("odate"));
                om.setOrderID(rs.getInt("oid"));
                om.setOrderNum(rs.getInt("onumber"));
                om.setOrderStatus(rs.getBoolean("ostatus"));
                
                TableModel tm = new TableModel();
                tm.setTableID(rs.getInt("tid"));
                tm.setTableName(rs.getString("tname"));
                om.setOrderTableModel(tm);
                
                ProductModel pm = new ProductModel();
                pm.setProductID(rs.getInt("pid"));
                pm.setProductName(rs.getString("pname"));
                pm.setProductPrice(rs.getDouble("pprice"));
                pm.setProductCalorie(rs.getInt("pcalori"));
                om.setOrderProductModel(pm);
                
                orderList.add(om);
            }
            return orderList;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
