package dialogNewDossierView;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class DialogNewDossierView extends BorderPane {
    private Button buttonEnregistrer;
    private Button buttonAnnuler;
    private TextField textFieldNom;
    private TextField textFieldCom;

    public DialogNewDossierView() {
        super();

        init();
    }

    private void init() {

        GridPane gridPane = new GridPane();
        this.setCenter(gridPane);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(40);
        gridPane.getColumnConstraints().addAll(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(20);
        gridPane.getRowConstraints().addAll(rowConstraints);

        Label labelNom = new Label("Nom de dossier:");
        Label labelCom = new Label("Commentaire");

        gridPane.add(labelNom,0,0);
        gridPane.add(labelCom,0,1);

        textFieldNom = new TextField();
        textFieldCom = new TextField();

        gridPane.add(textFieldNom,1,0);
        gridPane.add(textFieldCom,1,1);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(8));

        buttonEnregistrer = new Button("Enregistrer");
        buttonAnnuler = new Button("Annuler");
        hBox.getChildren().addAll(buttonAnnuler,buttonEnregistrer);

        this.setBottom(hBox);


    }

    public Button getButtonEnregistrer() {
        return buttonEnregistrer;
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public TextField getTextFieldNom() {
        return textFieldNom;
    }

    public TextField getTextFieldCom() {
        return textFieldCom;
    }
}
