/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AppStore.data;

import com.AppStore.domain.AppCategory;
import com.AppStore.domain.Application;
import com.AppStore.domain.Downloads;
import com.AppStore.domain.UserDownloadsStatus;
import com.AppStore.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppDao {
    Connection conn;

    public AppDao() {
        DatabaseInitialize initialize = new DatabaseInitialize();
        initialize.initializeDatabase();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/awp", "root", Utils.SQL_PASSWORD);
        } catch (SQLException throwables) {
        }
    }

    public List<Downloads> getAllDownloads() {
        List<Downloads> orders = new ArrayList<>();
        try (
                Statement stm = conn.createStatement();
        ) {
            ResultSet results = stm.executeQuery("SELECT * FROM downloads");
            while (results.next()) {
                Downloads order = new Downloads();
                order.setId(results.getLong("id"));
                order.setStatus(results.getString("status"));
                Map<Application, Double> orderMap = convertContentsToDownloadsMap(results.getString("contents"));
                order.setContents(orderMap);
                order.setCustomer(results.getString("customer"));
                order.setPercentage(results.getInt("percentage"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }



    private List<Application> buildApplication(ResultSet results) throws SQLException {
        List<Application> items = new ArrayList<>();
        while (results.next()) {
            Application item = new Application();
            item.setId(results.getInt("id"));
            item.setDescription(results.getString("description"));
            item.setName(results.getString("name"));
            item.setNumDownloads(results.getInt("downloads"));
            item.setRating(results.getDouble("rating"));
            item.setLogo(results.getString("logo"));
            item.setVersion(results.getDouble("version"));
            item.setCategory(AppCategory.valueOf(results.getString("category")));
            items.add(item);
        }
        return items;
    }

    public List<Application> getFullApplication() {
        List<Application> items = null;
        try (
             Statement stm = conn.createStatement();
             ResultSet results = stm.executeQuery("SELECT * FROM apps");
        ) {
            items = buildApplication(results);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public List<Application> find(String searchString) {
        List<Application> items = null;
        try (
             PreparedStatement stm = conn.prepareStatement("SELECT * FROM apps WHERE name LIKE ? OR description LIKE ? OR category LIKE ? ");
        ) {

            stm.setString(1, "%" + searchString + "%");
            stm.setString(2, "%" + searchString + "%");
            stm.setString(3, "%" + searchString + "%");

            ResultSet results = stm.executeQuery();
            items = buildApplication(results);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public Application getItem(int id) {
        List<Application> items = null;
        try (
             PreparedStatement stm = conn.prepareStatement("SELECT * FROM apps WHERE id = ?");
        ) {

            stm.setInt(1, id);

            ResultSet results = stm.executeQuery();
            items = buildApplication(results);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items.get(0);
    }


    public Downloads newDownloads(String customer) {
        Downloads order = new Downloads();
        order.setStatus("downloading");
        order.setCustomer(customer);
        try (
             PreparedStatement stm = conn.prepareStatement("INSERT INTO downloads (status, customer) values (?,?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            stm.setString(1, order.getStatus());
            stm.setString(2, order.getCustomer());
            stm.execute();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                generatedKeys.next();
                order.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    public Map<Application, Double> convertContentsToDownloadsMap(String contents) {
        Map<Application, Double> orderMap = new HashMap<>();
        if (contents == null || contents.equals("")) {
            return orderMap;
        }
        String[] items = contents.split(":");
        for (String item1 : items) {
            String key = item1.split(",")[0];
            String value = item1.split(",")[1];
            Application item = getItem(Integer.valueOf(key));
            orderMap.put(item, Double.valueOf(value));
        }
        return orderMap;
    }

    private String convertDownloadsMapToContents(Map<Application, Double> orderMap) {
        String contents = "";
        if (orderMap.keySet().isEmpty()) {
            return contents;
        }
        for (Application item : orderMap.keySet()) {
            contents = contents + item.getId() + "," + orderMap.get(item) + ":";
        }
        contents = contents.substring(0, contents.length() - 1);
        return contents;
    }

    public void addToDownloads(String userName, Application item, Double version) {
        try (
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery("SELECT * FROM downloads WHERE customer = '" + userName + "'");
             PreparedStatement stmUpdate = conn.prepareStatement("UPDATE downloads SET contents = ? WHERE customer = ?");
        ) 
        {
            res.next();
            String contents = res.getString("contents");
            Map<Application, Double> orderMap = convertContentsToDownloadsMap(contents);
            if (orderMap.get(item) != null) {
                orderMap.put(item, version);
            } else {
                orderMap.put(item, version);
            }
            contents = convertDownloadsMapToContents(orderMap);
            stmUpdate.setString(1, contents);
            stmUpdate.setString(2, userName);
            stmUpdate.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    

    public Downloads getDownloads(Long id) {
        try (
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery("SELECT * FROM downloads WHERE id = " + id);
        ) {
            res.next();
            Map<Application, Double> orderMap = convertContentsToDownloadsMap(res.getString("contents"));
            Downloads order = new Downloads();
            order.setCustomer(res.getString("customer"));
            order.setId(res.getLong("id"));
            order.setStatus(res.getString("status"));
            order.setContents(orderMap);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateDownloadsStatus(Long id, String status) {
        try (
             Statement stm = conn.createStatement();
             PreparedStatement stmUpdate = conn.prepareStatement("UPDATE downloads SET status = ? WHERE id = ?");
        ) {
            stmUpdate.setString(1, status);
            stmUpdate.setLong(2, id);
            stmUpdate.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Downloads getUserDownloads(String userName) {
        List<Application> retval = new ArrayList<>();
        try (
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery("SELECT * FROM downloads WHERE customer = '" + userName + "'");
        ) {
            if (!res.next()) {
                return newDownloads(userName);
            }
            Map<Application, Double> orderMap = convertContentsToDownloadsMap(res.getString("contents"));
            Downloads order = new Downloads();
            order.setCustomer(res.getString("customer"));
            order.setId(res.getLong("id"));
            order.setStatus(res.getString("status"));
            order.setContents(orderMap);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void newDownloadsN(String username) throws SQLException {
        
        String STATEMENT = "INSERT INTO downloads (userName, contents) values (?, '')";
        PreparedStatement stmt = conn.prepareStatement(STATEMENT);
        stmt.setString(1, username);
        stmt.execute();
    }

    public UserDownloadsStatus getDownloadsNConn(String userName, Connection conn) {
        UserDownloadsStatus retval = null;
        try {
            String QUERY = "SELECT * FROM downloads WHERE userName = ?";
            PreparedStatement stmt = conn.prepareStatement(QUERY);
            stmt.setString(1, userName);
            ResultSet results = stmt.executeQuery();
            if (!results.next()) {
                newDownloadsN(userName);
                return getDownloadsN(userName);
            }
            retval = new UserDownloadsStatus(results.getString("userName"), results.getString("contents"));
        } catch (SQLException e) {
        }
        return retval;
    }

    public UserDownloadsStatus getDownloadsN(String userName) {
        UserDownloadsStatus retval = null;
        try {
            
            String QUERY = "SELECT * FROM downloads WHERE userName = ?";
            PreparedStatement stmt = conn.prepareStatement(QUERY);
            stmt.setString(1, userName);
            ResultSet results = stmt.executeQuery();
            if (!results.next()) {
                newDownloadsN(userName);
                return getDownloadsN(userName);
            }
            retval = new UserDownloadsStatus(results.getString("userName"), results.getString("contents"));
        } catch (SQLException e) {
        }
        return retval;
    }

    public void addAppToDownloads(String userName, Application app) throws SQLException {
        UserDownloadsStatus curDownloads = getDownloadsN(userName);
        curDownloads.addApp(app);
        System.out.println(curDownloads.contents);
        writeDownloadsToDatabase(curDownloads);

    }

    public void writeDownloadsToDatabase(UserDownloadsStatus status) {
        try {
            
            String UPDATE = "UPDATE downloads SET contents = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, status.contents);
            stmt.setString(2, status.username);
            stmt.execute();
        } catch (SQLException e) {
        }
    }
}

