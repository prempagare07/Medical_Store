/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AppStore.data;
import com.AppStore.domain.Application;
import com.AppStore.domain.mysubscription;
import com.AppStore.utils.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MysubscriptionDao{
    String sql1 = "select * from mysubscriptiondb where id=? and username=?";
    String query = "insert into mysubscriptiondb (id,username,status)" + " values (?,?,?)";
    String url;
    String un = "root";
    private AppDao newapp = null;
    public MysubscriptionDao() {
        this.url = "jdbc:mysql://localhost:3306/awp";
         newapp = DataConnection.getAppDao();
    }
    public void addtomysubscription(int id,String username, String status) throws SQLException {

        System.out.println("hello");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,un, Utils.SQL_PASSWORD);

            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            st.setString(2,username);
            st.setString(3,status);
            st.execute();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block

        }
      
     
     }
    public boolean checkifsubscribed(int id,String username) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,un, Utils.SQL_PASSWORD);

            PreparedStatement st = con.prepareStatement(sql1);
            st.setInt(1,id);
            st.setString(2,username);
            ResultSet rs = st.executeQuery();
            System.out.println("In rs");
            if(rs.next()) {
//				System.out.println("In if");
                return true;
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block

        }
        return false;
    }
    
    public String getStatus(int id,String username) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,un, Utils.SQL_PASSWORD);

        PreparedStatement st = con.prepareStatement(sql1);
        st.setInt(1,id);
        st.setString(2,username);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            return (String)rs.getString("status");
        }
        return "blank";
    }

    public void setStatus(int id,String username,String status) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,un, Utils.SQL_PASSWORD);
        String qqq = "UPDATE mysubscriptiondb SET status=? WHERE id=? and username=?";
        PreparedStatement st = con.prepareStatement(qqq);
        st.setString(1,status);
        st.setInt(2,id);
        st.setString(3,username); 
        st.executeUpdate();
    }
    public  List<mysubscription> getAllmysubscriptions(String sta) {
        String statusreport = "subscribed!";
        List<mysubscription> orders = new ArrayList<>();
        Application app = new Application();
        try {
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zenithdb", "root", Utils.SQL_PASSWORD);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM mysubscriptiondb WHERE status LIKE ?");
            st.setString(1,statusreport);
            ResultSet results = st.executeQuery();
            while (results.next()) {
                mysubscription order = new mysubscription();
                
                order.id1=results.getInt("id");
                order.username=results.getString("username");
                order.status1=results.getString("status");
                app =newapp.getItem(order.id1);
                order.appname=app.getName();
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
    
    public List<mysubscription> getAllPendingSubscriptions(String status) {
        String statusreport = "pending...";
        List<mysubscription> orders = new ArrayList<>();
        Application app = new Application();
        try {
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zenithdb", "root", Utils.SQL_PASSWORD);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM mysubscriptiondb WHERE status LIKE ?");
            st.setString(1,statusreport);
            ResultSet results = st.executeQuery();
            while (results.next()) {
                mysubscription order = new mysubscription();
                
                order.id1=results.getInt("id");
                order.username=results.getString("username");
                order.status1=results.getString("status");
                app =newapp.getItem(order.id1);
                order.appname=app.getName();
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
}



