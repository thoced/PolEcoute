package dialogShowEventsView;

import MainView.CConfig;
import MainView.PropertiesNotFoundException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class DialogFonction extends BorderPane {

    private TextField textF1;
    private TextField textF2;

    public DialogFonction() {
        super();

        init();
    }

    private void init() {

        GridPane gridPane = new GridPane();

        Label labelF1 = new Label("(F1) : ");
        Label labelF2 = new Label("(F2) : ");
        textF1 = new TextField();
        textF2 = new TextField();

        gridPane.add(labelF1,0,0);
        gridPane.add(textF1,1,0);
        gridPane.add(labelF2,0,1);
        gridPane.add(textF2,1,1);

        this.setCenter(gridPane);

    }

    public String getTextF1() {
        return textF1.getText();
    }

    public void setTextF1(String textF1) {
        this.textF1.setText(textF1);
    }

    public String getTextF2() {
        return textF2.getText();
    }

    public void setTextF2(String textF2) {
        this.textF2.setText(textF2);
    }

    public void saveFonction() throws IOException, PropertiesNotFoundException {
        CConfig cConfig = CConfig.getInstance();
        cConfig.setF1(textF1.getText());
        cConfig.setF2(textF2.getText());
        cConfig.saveConfig();
    }


    public void loadFonction() throws IOException, PropertiesNotFoundException {
        CConfig cConfig = CConfig.getInstance();
        textF1.setText(cConfig.getF1());
        textF2.setText(cConfig.getF2());
        cConfig.setF2(textF2.getText());
        cConfig.saveConfig();
    }
}
