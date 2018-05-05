package dialogImportEvents;

import dao.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import models.Dossier;
import models.Numero;

import java.sql.SQLException;
import java.util.List;

public class ListNumeroView extends ListView {

    private Dossier dossier;

    public ListNumeroView(Dossier dossier) throws SQLException {

        this.dossier = dossier;
        List<Numero> list = DAOFactory.getInstance().getNUMERO_DAO().selectFromForeignKey(dossier.getId());
        ObservableList<Numero> observableList = FXCollections.observableList(list);
        this.setItems(observableList);

    }
}
