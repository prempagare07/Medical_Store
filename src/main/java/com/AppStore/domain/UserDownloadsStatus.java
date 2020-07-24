package com.AppStore.domain;

public class UserDownloadsStatus {
    public String username;
    public String contents;

    public UserDownloadsStatus(String username, String contents) {
        this.username = username;
        this.contents = contents;
    }

    public void addApp(Application a) {
        contents = contents + a.json() + "|downloading|0\t";
    }

    public void incrementProgresses() {
        StringBuilder contentsNew = new StringBuilder();
        String[] parts = contents.split("\t");
        for (String part : parts) {
            String[] oneApp = part.split("\\|");
            contentsNew.append(oneApp[0]);
            int curProgress = Integer.parseInt(oneApp[2]);
            String status = oneApp[1];
            String newProg = oneApp[2];
            if (status.equals("downloading")) {
                newProg = String.valueOf(curProgress + 1);
                if (newProg.equals("100")) {
                    status = "done";
                }
            }
            contentsNew.append("|").append(status).append("|").append(newProg).append("\t");
        }
        contents = contentsNew.toString();
    }
}
