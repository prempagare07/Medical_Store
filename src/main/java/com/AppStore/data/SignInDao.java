package com.AppStore.data;
import com.AppStore.utils.Utils;

import java.sql.*;

public class SignInDao {
    String sql1 = "select * from customerData where Email=? or Telephone=?";
    String query = "insert into customerData (Name,Email,Telephone, Password)" + " values (?,?,?,?)";
    String url = "jdbc:mysql://localhost:3306/awp";
    String username = "root";

    public boolean checkDetails(String name, String email, String tel, String pass) throws SQLException {

        System.out.println("hello");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username, Utils.SQL_PASSWORD);

            PreparedStatement st = con.prepareStatement(sql1);
            st.setString(1,email);
            st.setString(2,tel);
            ResultSet rs = st.executeQuery();

            if(rs.next()) {
                System.out.println("hello1");
                return true;
            }
            else {
                System.out.println("hello2");
                PreparedStatement st1 = con.prepareStatement(query);
                st1.setString(1,name);
                st1.setString(2,email);
                st1.setString(3,tel);
                st1.setString(4, pass);
                st1.execute();
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block

        }

        return false;
    }

}

