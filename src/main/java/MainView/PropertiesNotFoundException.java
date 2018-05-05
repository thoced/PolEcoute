package MainView;




import java.util.logging.Logger;

public class PropertiesNotFoundException extends Exception
{
    private static final Logger log = Logger.getLogger(PropertiesNotFoundException.class.getName());

    public PropertiesNotFoundException()
    {
        System.out.print("le fichier de configuration n'existe pas");

    }
    @Override
    public void printStackTrace() {
        // TODO Auto-generated method stub
        super.printStackTrace();
    }

}