package com.kirylkhrystsenka.schoolapp.utilities;

public class Properties {
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    public Properties(String dbUrl, String dbUsername, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public String getBDUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
