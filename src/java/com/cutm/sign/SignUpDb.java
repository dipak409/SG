package com.cutm.sign;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpDb {

    Connection conn;

    public SignUpDb() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/StudentDB", "dipak", "dipak");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String userid, String password) {

        try {
            String query = " insert into signup values(?,?) ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userid);
            ps.setString(2, password);
            ps.executeUpdate();

        } catch (SQLException s) {
            // TODO Auto-generated catch block
            s.printStackTrace();
        }
    }

}
