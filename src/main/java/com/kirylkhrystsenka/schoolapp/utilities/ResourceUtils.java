package com.kirylkhrystsenka.schoolapp.utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceUtils {
    public static Properties loadProperties(String path) {
        List<String> lines = null;
        try (InputStream inputStream = ResourceUtils.class
                .getClassLoader()
                .getResourceAsStream(path)) {
            assert inputStream != null:"File is not found";
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                lines = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Properties(lines.get(0), lines.get(1), lines.get(2));
    }

}
