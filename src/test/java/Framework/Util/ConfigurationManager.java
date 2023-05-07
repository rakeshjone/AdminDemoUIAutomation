package Framework.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ConfigurationManager {
	private static ConfigurationManager manager = null;
    private static final Properties PROPERTIES = new Properties();

    private ConfigurationManager() throws IOException{
        PROPERTIES.load(getInputStream("Default.properties"));
    }

    public static ConfigurationManager getInstance() {
        if (manager == null){
            try {
                manager = new ConfigurationManager();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return manager;
    }

    public String getProperty(String propertyName){
        return System.getProperty(propertyName, PROPERTIES.getProperty(propertyName));
        //return PROPERTIES.getProperty(propertyName);
    }

    private InputStream getInputStream(String filename) {
        //return ConfigurationManager.class.getClassLoader().getResourceAsStream(filename);
/*
        File source = new File("C:\\Dev\\Projects\\AdminDemoUIAutomation\\src\\test\\resources\\Configurations\\Default.properties");
        try{
        return new FileInputStream(source);}catch (Excepti on e) {
        }
        return null;*/

        try {
            List<URL> urls = Collections.list(Thread.currentThread().getContextClassLoader().getResources(filename));
            return urls == null || urls.isEmpty() ? null : urls.get(0).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void LoadAdditionalProperties(String fileName){
        try{
        PROPERTIES.load(getInputStream(fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        /*
        File source = new File("C:\\Dev\\Projects\\AdminDemoUIAutomation\\src\\test\\resources\\Configurations\\Delivery.properties");
        try{
            PROPERTIES.load(new FileInputStream(source));}catch (Exception e) {
        }*/
    }
}
