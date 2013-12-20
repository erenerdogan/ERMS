/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.service;

import erms.db.Database;
import erms.model.UserModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eren
 */
public class UserDaoImpl implements UserDaoInterface {

    private Database db;
    private ResultSet rs;
    private PreparedStatement ps;

    public UserDaoImpl() {
        db = new Database();

    }

    @Override
    public void createUser(UserModel user) {
        String query = "INSERT INTO Users (uname, usurname, uaddress, uphone, uemail, upassword,uappkey) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {

            ps = db.getCon().prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserSurname());
            ps.setString(3, user.getUserAddress());
            ps.setString(4, user.getUserPhone());
            ps.setString(5, user.getUserMail());
            ps.setString(6, user.getUserPassword());
            MessageDigest md;

            md = MessageDigest.getInstance("MD5");

            byte[] b = md.digest((user.getUserName() + user.getUserSurname() + user.getUserMail()).getBytes());
            String ak = "";
            for (byte c : b) {
                ak += c;
            }
            ps.setString(7, ak);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(UserModel user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editUser(UserModel user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUserAppKey(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserModel getUser(String email, String password) {
        UserModel um = null;
        String query = "SELECT * FROM USERS WHERE uemail = ? AND upassword= ?";
        try {
            ps = db.getCon().prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getInt("uid") != 0) {
                    um = new UserModel();
                    um.setUserID(rs.getInt("uid"));
                    um.setUserMail(rs.getString("uemail"));
                    um.setUserName(rs.getString("uname"));
                    um.setUserSurname(rs.getString("usurname"));
                    um.setUserAddress(rs.getString("uaddress"));
                    um.setUserPhone(rs.getString("uphone"));
                    um.setUserAppKey(rs.getString("uappkey"));
                    um.setUserPassword(rs.getString("upassword"));
                }
            }
            ps.close();
            return um;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void changePassword(int userID, String newPassword) {
        String query = "UPDATE USERS SET upassword = ? WHERE uid=?";
        try {
            ps = db.getCon().prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setInt(2, userID);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
