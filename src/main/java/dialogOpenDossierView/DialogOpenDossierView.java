package dialogOpenDossierView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class DialogOpenDossierView extends BorderPane {
    private Button buttonOk;
    private Button buttonAnnuler;
    private ListDossierView listDossierView;

    public DialogOpenDossierView() {
        super();

        init();
    }

    private void init() {

        listDossierView = new ListDossierView();
        this.setCenter(listDossierView);
        HBox hBox = new HBox();
        Insets insets = new Insets(16);
        hBox.setPadding(insets);
        this.setBottom(hBox);

        buttonOk = new Button("Selectionner");
        buttonAnnuler = new Button("Annuler");
        buttonOk.setAlignment(Pos.CENTER_RIGHT);

        hBox.getChildren().addAll(buttonAnnuler,buttonOk);
    }

    public Button getButtonOk() {
        return buttonOk;
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public ListDossierView getListDossierView() {
        return listDossierView;
    }
}
