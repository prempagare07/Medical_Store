package com.AppStore.domain;

import com.AppStore.data.AppDao;
import com.AppStore.data.DataConnection;
import com.AppStore.utils.Utils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;


@ServerEndpoint("/appStatus")
public class WebSocket {
    @OnOpen
    public void onOpen() {
        System.out.println("Open Connection ...");


    }

    @OnClose
    public void onClose() {
        System.out.println("Close Connection ...");
    }

    private void returnData(final Session session, final String userName) {
        final AppDao appData = DataConnection.getAppDao();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/awp", "root", Utils.SQL_PASSWORD);
        } catch (SQLException throwables) {
        }
        final Connection finalConn = conn;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                UserDownloadsStatus downloadsN = appData.getDownloadsNConn(userName, finalConn);
                System.out.println(downloadsN.contents);
                try {
                    session.getBasicRemote().sendText(downloadsN.contents);
                } catch (IOException e) {
                }
                downloadsN.incrementProgresses();
                appData.writeDownloadsToDatabase(downloadsN);
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    @OnMessage
    public String onMessage(String message, final Session session) {
        System.out.println("Message from the client: " + message);
        returnData(session, message);
        return "Echo from the server : " + message;
    }
}
