package MainView;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SingletonConnection {

    public static final String TIME_ZONE = "Europe/Brussels";

    private Connection connection;

    private static final Logger log = Logger.getLogger(SingletonConnection.class.getName());

    private static SingletonConnection ourInstance = new SingletonConnection();

    public static SingletonConnection getInstance() {
        return ourInstance;
    }

    private SingletonConnection() {

        try {
            CConfig config = new CConfig();

            if(getConnection() == null){
                Class.forName(config.getDriver());
                String url = config.getUrl();
                connection = DriverManager.getConnection(url,config.getLogin(),config.getPassword());
                int i=0;
            }


        } catch (IOException e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        } catch (PropertiesNotFoundException e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        }


    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
