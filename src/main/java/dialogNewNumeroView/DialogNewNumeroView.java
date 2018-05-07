package dialogNewNumeroView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class DialogNewNumeroView extends BorderPane {

    private Label labelNouveauNumero;
    private TextField textFieldNumero;
    private Button buttonAnnuler;
    private Button buttonAjouterNumero;
    private Label labelTargetName;
    private TextField textFiledTargetName;

    public DialogNewNumeroView() {

        init();
    }

    private void init() {

        GridPane gridPane = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(40);

        labelNouveauNumero = new Label("Nouveau numéro:");
        gridPane.add(labelNouveauNumero,0,0);

        textFieldNumero = new TextField();
        gridPane.add(textFieldNumero,1,0);

        labelTargetName = new Label("Nom du target");
        gridPane.add(labelTargetName,0,1);

        textFiledTargetName = new TextField();
        gridPane.add(textFiledTargetName,1,1);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(20);

        gridPane.getColumnConstraints().addAll(columnConstraints);
        gridPane.getRowConstraints().addAll(rowConstraints);

        this.setCenter(gridPane);

        buttonAnnuler = new Button("Annuler");
        buttonAjouterNumero = new Button("Ajouter");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(buttonAnnuler,buttonAjouterNumero);

        this.setBottom(hBox);
    }

    public TextField getTextFieldNumero() {
        return textFieldNumero;
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public Button getButtonAjouterNumero() {
        return buttonAjouterNumero;
    }

    public TextField getTextFiledTargetName() {
        return textFiledTargetName;
    }
}
