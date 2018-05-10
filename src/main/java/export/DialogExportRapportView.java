package export;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DialogExportRapportView extends BorderPane {
    private TextField textFieldNotice;
    private TextField textFieldNumInstruction;
    private Button buttonAnnuler;
    private Button buttonRapport;

    public static final String NAME = "Information de dossier";
    private TextField textNumeroRapport;
    private TextField textPeriodeRapport;

    public DialogExportRapportView() {

        init();
    }

    private void init() {

        GridPane gridPane = new GridPane();

        Label labelNotice = new Label("Numéro de Notice: ");
        textFieldNotice = new TextField();

        Label labelNumInstruction = new Label("Numero d'instruction et Juge");
        textFieldNumInstruction = new TextField();

        Label labelNumeroRapport = new Label("Numero du rapport");
        textNumeroRapport = new TextField();

        Label labelDateRapport = new Label("Période du rapport");
        textPeriodeRapport = new TextField();

        gridPane.addRow(0,labelNotice,textFieldNotice);
        gridPane.addRow(1,labelNumInstruction,textFieldNumInstruction);
        gridPane.addRow(2,labelNumeroRapport,textNumeroRapport);
        gridPane.addRow(3,labelDateRapport,textPeriodeRapport);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(30);
        gridPane.getColumnConstraints().addAll(columnConstraints);

        setCenter(gridPane);

        HBox hBox = new HBox();
        buttonAnnuler = new Button("Annuler");
        buttonRapport = new Button("Creer le rapport");
        hBox.getChildren().addAll(buttonAnnuler,buttonRapport);

        setBottom(hBox);
    }

    public TextField getTextFieldNotice() {
        return textFieldNotice;
    }

    public TextField getTextFieldNumInstruction() {
        return textFieldNumInstruction;
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public Button getButtonRapport() {
        return buttonRapport;
    }

    public TextField getTextNumeroRapport() {
        return textNumeroRapport;
    }

    public TextField getTextPeriodeRapport() {
        return textPeriodeRapport;
    }
}
