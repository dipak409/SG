package com.cutm.db;

import java.sql.*;

public class DbDao {

    Connection conn;

    public DbDao() throws Exception {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/StudentDB", "dipak", "dipak");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int checkUser(String uid, String pwd) {
        int result = 0;
        try {
            //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String query = " SELECT * FROM signup WHERE userid=? and password=? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, uid);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = 1;
            } else {
                result = 0;
            }

        } catch (SQLException s) {
            // TODO Auto-generated catch block
            s.printStackTrace();
        }
        return result;
    }
}
