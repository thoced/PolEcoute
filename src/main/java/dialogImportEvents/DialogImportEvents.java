package dialogImportEvents;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import models.Dossier;

import java.sql.SQLException;

public class DialogImportEvents extends BorderPane {

    private ListNumeroView listNumeroView;

    private Dossier dossier;
    private Button buttonSelected;
    private Button buttonAnnuler;

    public DialogImportEvents(Dossier dossier) {
        super();

        this.dossier = dossier;

        init();
    }

    private void init() {

        try {
            listNumeroView = new ListNumeroView(dossier);

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(8));

            buttonSelected = new Button("Selectionner");
            buttonAnnuler = new Button("Annuler");

            hBox.getChildren().addAll(buttonAnnuler,buttonSelected);

            this.setCenter(listNumeroView);
            this.setBottom(hBox);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ListNumeroView getListNumeroView() {
        return listNumeroView;
    }

    public void setListNumeroView(ListNumeroView listNumeroView) {
        this.listNumeroView = listNumeroView;
    }

    public Button getButtonSelected() {
        return buttonSelected;
    }

    public void setButtonSelected(Button buttonSelected) {
        this.buttonSelected = buttonSelected;
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public void setButtonAnnuler(Button buttonAnnuler) {
        this.buttonAnnuler = buttonAnnuler;
    }
}
