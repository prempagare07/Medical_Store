package com.AppStore.data;
import com.AppStore.utils.Utils;

import java.sql.*;

public class LoginDao {
    String sql1 = "select * from customerData where Email=? and Password=?";
    String sql2 = "select * from customerData where Telephone=? and Password=?";
    String url = "jdbc:mysql://localhost:3306/awp";
    String username = "root";

    public boolean checkDetails(String uname, String pass) throws SQLException {

        try{
            System.out.println("In dao " + uname + pass);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username, Utils.SQL_PASSWORD);

            PreparedStatement st = con.prepareStatement(sql1);
            st.setString(1,uname);
            st.setString(2,pass);
            ResultSet rs = st.executeQuery();
//            System.out.println("In rs");

            PreparedStatement st1 = con.prepareStatement(sql2);
            st1.setString(1,uname);
            st1.setString(2,pass);
            ResultSet rs1 = st1.executeQuery();

            if(rs.next()) {
                return true;
            }
            else if(rs1.next()) {
                return true;
            }

        } catch (ClassNotFoundException e) {

        }

        return false;
    }
}
