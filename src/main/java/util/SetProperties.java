package util;

import java.io.IOException;

public class SetProperties {
    public static void setProperties() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
    }
}
