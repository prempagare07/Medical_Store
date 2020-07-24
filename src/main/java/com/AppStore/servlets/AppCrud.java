package com.AppStore.servlets;


import java.sql.*;
import java.util.*;

import com.AppStore.domain.AppCategory;
import com.AppStore.domain.Application;

public class AppCrud {
    String jdbcurl;
    String jdbcusername;
    String jdbcpassword;
    Connection jdbcConnection;

    public AppCrud(String jdbcurl, String jdbcusername, String jdbcpassword) {
        this.jdbcurl = jdbcurl;
        this.jdbcusername = jdbcusername;
        this.jdbcpassword = jdbcpassword;
        try {
            connect();
        } catch (Exception e) {
        }
    }

    protected void connect() throws Exception {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcurl, jdbcusername,
                    jdbcpassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public void insertApp(Application app) throws Exception {
        String sql = "INSERT INTO `apps` (`id`, `name`, `description`, `category`, `downloads`, `rating`, `logo`, `version`) VALUES (?, ?, ?, ?,?, ?, ?, ?)";
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, app.getId());
            statement.setString(2, app.getName());
            statement.setString(3, app.getDescription());
            statement.setString(4, app.getCategory().toString());
            statement.setInt(5, app.getNumDownloads());
            statement.setDouble(6, app.getRating());
            statement.setString(7, app.getLogo());
            statement.setDouble(8, app.getVersion());
        }
    }

    public List<Application> ListAllApps() throws Exception {
        List<Application> items = new ArrayList<>();
        String sql = "SELECT * FROM apps";
        try (Statement st = jdbcConnection.createStatement()) {
            ResultSet results = st.executeQuery(sql);
            
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
            results.close();
        }
        return items;
    }

    public Application getApp(int id) throws Exception {
        Application app = null;
        String sql = "SELECT * FROM `apps` WHERE id = ?";
        PreparedStatement st = jdbcConnection.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        if (res.next()) {
            String name = res.getString("name");
            String desc = res.getString("description");
            String category = res.getString("category");
            int downloads = res.getInt("downloads");
            double rating = res.getDouble("rating");
            String logo = res.getString("logo");
            double version = res.getDouble("version");

            app = new Application(id, name, desc, AppCategory.valueOf(category), downloads, rating, logo, version);

            res.close();
            st.close();

        }
        return app;
    }

    public void deleteApp(int id) throws Exception {
        String sql = "DELETE FROM `apps` WHERE `apps`.`id` = ?";
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
        }
    }

    public void updateApp(Application app) throws Exception {
        String sql = "UPDATE `apps` SET `name` = ? , `description` = ? , `category` = ? , `downloads` = ? , `rating`= ? , `logo` = ? , `version` = ? ";
        sql += "WHERE `apps`.`id` = ? ";
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, app.getName());
            statement.setString(2, app.getDescription());
            statement.setString(3, app.getCategory().toString());
            statement.setInt(4, app.getNumDownloads());
            statement.setDouble(5, app.getRating());
            statement.setString(6, app.getLogo());
            statement.setDouble(7, app.getVersion());
            statement.setInt(8, app.getId());
        }
    }
}

















