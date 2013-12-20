/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.db.Database;
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
public class TableDaoImpl implements TableDaoInterface{
    
    private Database db;
    private ResultSet rs;
    private PreparedStatement ps;

    public TableDaoImpl() {
        db = new Database();
    }
    
    @Override
    public void addTable(TableModel tableModel,int userID) {
        String query = "INSERT INTO Tables (tuid, tname) VALUES (?, ?)";
        try {
            ps = db.getCon().prepareStatement(query);
            ps.setString(2, tableModel.getTableName());
            ps.setInt(1, userID);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteTable(int tableID) {
        try {
            String query = "DELETE FROM TABLES WHERE tid = ?";
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, tableID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<TableModel> getAllTables(int userID) {
        String query = "SELECT * FROM TABLES WHERE tuid = ?";
        List<TableModel> allTables = new ArrayList<TableModel>();
        try {
            ps = db.getCon().prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                TableModel tm = new TableModel();
                tm.setTableID(rs.getInt("tid"));
                tm.setTableName(rs.getString("tname"));
                allTables.add(tm);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allTables;
    }
    
}
