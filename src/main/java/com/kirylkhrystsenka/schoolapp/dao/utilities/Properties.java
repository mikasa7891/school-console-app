package com.kirylkhrystsenka.schoolapp.dao.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Properties {
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    BufferedReader bufferedReader;
    public Properties(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }
    public void setProperties() throws IOException {
        List<String> properties = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine())!= null){
            properties.add(line);
        }
        dbUrl = properties.get(0);
        dbUsername = properties.get(1);
        dbPassword = properties.get(2);
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
