package com.kirylkhrystsenka.schoolapp;

import com.kirylkhrystsenka.schoolapp.apphelper.SchoolApplicationHelper;
import com.kirylkhrystsenka.schoolapp.utilities.DBUtils;
import com.kirylkhrystsenka.schoolapp.utilities.Properties;
import com.kirylkhrystsenka.schoolapp.utilities.ResourceUtils;
import com.zaxxer.hikari.HikariDataSource;


public class SchoolApplication {
    public static void main(String[] args) {
        Properties dbProperties = ResourceUtils.loadProperties("db.properties");

        try (HikariDataSource hikariDataSource = DBUtils.createDatasource(dbProperties)) {
            try (SchoolApplicationHelper app = new SchoolApplicationHelper(hikariDataSource)) {
                app.run();
            }
        }
    }
}
