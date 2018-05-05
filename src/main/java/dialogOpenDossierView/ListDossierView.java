package dialogOpenDossierView;

import MainView.SingletonConnection;
import dao.DAOFactory;
import dao.DossierDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import models.Dossier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ListDossierView extends ListView<Dossier> {


    public ListDossierView()  {

        try {
            List<Dossier> list = DAOFactory.getInstance().getDOSSIER_DAO().selectAll();
            ObservableList<Dossier> observableList = FXCollections.observableList(list);
            this.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
