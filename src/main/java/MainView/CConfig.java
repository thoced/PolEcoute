package MainView;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;


public class CConfig
{

    public static CConfig instance = null;

    private String pathHomeDirectory;
    private String pathDirectoryConfig;
    private String pathFileConfig;


    private String login;
    private String password;
    private String driver;
    private String url;

    private String F1;
    private String F2;

    private static final Logger log = Logger.getLogger(CConfig.class.getName());

    public String getF1() {
        return F1;
    }

    public void setF1(String f1) {
        F1 = f1;
    }

    public String getF2() {
        return F2;
    }

    public void setF2(String f2) {
        F2 = f2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static CConfig getInstance() throws IOException, PropertiesNotFoundException {
        if(instance == null) {
            instance = new CConfig();
            instance.loadConfig();

        }
            return instance;
    }

    private void loadConfig() throws IOException
    {
        FileInputStream fis = new FileInputStream(pathFileConfig);

        Properties prop = new Properties();

        prop.load(fis);

        login = prop.getProperty("login");
        password = prop.getProperty("password");
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        F1 = prop.getProperty("F1");
        F2 = prop.getProperty("F2");


    }


    public void saveConfig() throws IOException
    {
        FileOutputStream fos = new FileOutputStream(pathFileConfig);

        Properties prop = new Properties();

        prop.setProperty("login", this.getLogin());
        prop.setProperty("password", this.getPassword());
        prop.setProperty("driver",this.getDriver());
        prop.setProperty("url", this.getUrl());
        prop.setProperty("F1",this.getF1());
        prop.setProperty("F2",this.getF2());


        prop.store(fos,null);
    }

    private CConfig() throws IOException,PropertiesNotFoundException
    {
        // récupération du home directory
        pathHomeDirectory = System.getProperty("user.home");
        pathDirectoryConfig = pathHomeDirectory + File.separator + "polecoute";
        pathFileConfig = pathDirectoryConfig + File.separator + "polecouteconfig.cfg";

        // création d'un répertoire option
        File file = new File(pathDirectoryConfig);
        if(file.mkdir())
        {
            createFileConfig();
            throw new PropertiesNotFoundException();
        }
        else
        {
            // le répertoire était déja créer
            file = new File(pathFileConfig);
            if(!file.exists())
            {
                createFileConfig();
                throw new PropertiesNotFoundException();

            }
        }

        // chargement du properties
        loadConfig();

    }

    private void createFileConfig() throws IOException
    {
        // le fichier n'existe pas, on le créer
        FileOutputStream output = new FileOutputStream(pathFileConfig);
        Properties prop = new Properties();

        prop.setProperty("login","");
        prop.setProperty("password","");
        prop.setProperty("driver","com.mysql.cj.jdbc.Driver");
        prop.setProperty("url", "jdbc:mysql://localhost/db_nicetrack?serverTimezone=UTC");
        prop.setProperty("F1","- Numéro cible: ");
        prop.setProperty("F2","- Numéro correspondant: ");

        prop.store(output,null);





    }



}