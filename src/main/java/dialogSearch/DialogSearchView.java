package dialogSearch;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import jfxtras.scene.control.CalendarPicker;
import models.Event;
import models.Numero;

import java.util.ArrayList;
import java.util.List;

public class DialogSearchView extends BorderPane {


    private TableView<Event> tableEvents;
    private TableColumn<Event,String > columnDate;
    private TableColumn<Event,String> columnSynopsis;
    private Button buttonAnnuler;
    private Label labelNbTuple;

    public static final String TEXT_LABEL_TUPLE = "Nombre d'enregistrement affiché:";
    private TableColumn<Event, String> columnCaller;
    private TableColumn<Event, String> columnCalled;
    private TableColumn<Event, String> columnEventType;
    private Button buttonExport;

    public static final String NAME = "Recherche";
    private ComboBox<Numero> comboNumero;
    private TextField textSearch;
    private Button buttonSearch;


    public DialogSearchView() {

        init();
    }

    private void init() {


       GridPane gridPane = new GridPane();

       Label labelNumero = new Label("Numero sur lequel les recherches sont effectuées: ");
       labelNumero.setWrapText(true);

       comboNumero = new ComboBox<Numero>();
       gridPane.addRow(0,labelNumero,comboNumero);

       Label labelSearch = new Label("Mot ou phase clé recherché: ");
       textSearch = new TextField();
       buttonSearch = new Button("Rechercher");

       gridPane.addRow(1,labelSearch,textSearch,buttonSearch);

       RowConstraints rowConstraints = new RowConstraints();
       rowConstraints.setValignment(VPos.CENTER);
       ColumnConstraints columnConstraints = new ColumnConstraints();
       columnConstraints.setPercentWidth(25);

       gridPane.getColumnConstraints().addAll(columnConstraints);
       gridPane.getRowConstraints().addAll(rowConstraints);


       labelNbTuple = new Label(TEXT_LABEL_TUPLE);
       gridPane.addRow(2,labelNbTuple);
       this.setTop(gridPane);


       tableEvents = new TableView<Event>();
       tableEvents.itemsProperty().addListener((options,oldValue,newValue) -> {
           if(newValue != null) {
               labelNbTuple.setText(TEXT_LABEL_TUPLE + " " + newValue.size());
           }
       });

      this.setCenter(tableEvents);

       columnDate = new TableColumn<Event,String>("Date");
       columnSynopsis = new TableColumn<Event,String>("Synopsis");
       columnCaller = new TableColumn<Event,String>("Appelant");
       columnCalled = new TableColumn<Event,String>("Appelé");
       columnEventType = new TableColumn<Event,String>("Type");
       columnDate.setCellValueFactory(new PropertyValueFactory("startDate"));
       columnSynopsis.setCellValueFactory(new PropertyValueFactory("synopsis"));
       columnCaller.setCellValueFactory(new PropertyValueFactory("callerId"));
       columnCalled.setCellValueFactory(new PropertyValueFactory("calledId"));
       columnEventType.setCellValueFactory(new PropertyValueFactory("eventType"));
       tableEvents.getColumns().addAll(columnDate,columnCaller,columnCalled,columnEventType,columnSynopsis);


       HBox hBox = new HBox();
       hBox.setAlignment(Pos.CENTER_RIGHT);
       hBox.setPadding(new Insets(8));
       this.setBottom(hBox);
       buttonAnnuler = new Button("Annuler");
       buttonExport = new Button("Export rapport");
       buttonExport.setStyle("-fx-backgroundColor:blue");

       hBox.getChildren().addAll(buttonExport,buttonAnnuler);


    }

    public TableView<Event> getTableEvents() {
        return tableEvents;
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public Button getButtonExport() {
        return buttonExport;
    }

    public ComboBox<Numero> getComboNumero() {
        return comboNumero;
    }

    public TextField getTextSearch() {
        return textSearch;
    }

    public Button getButtonSearch() {
        return buttonSearch;
    }
}
