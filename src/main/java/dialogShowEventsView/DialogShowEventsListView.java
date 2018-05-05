package dialogShowEventsView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import jfxtras.scene.control.CalendarPicker;
import models.Event;

import java.util.ArrayList;
import java.util.List;

public class DialogShowEventsListView extends BorderPane {


    private TableView<Event> tableEvents;
    private TableColumn<Event,String > columnDate;
    private TableColumn<Event,String> columnSynopsis;
    private Button buttonAnnuler;
    private ComboBox<String> comboRelevancy;
    private CalendarPicker calendarBasse;
    private CalendarPicker calendarHaute;
    private Label labelNbTuple;

    public static final String TEXT_LABEL_TUPLE = "Nombre d'enregistrement affiché:";
    private TableColumn<Event, String> columnCaller;
    private TableColumn<Event, String> columnCalled;
    private TableColumn<Event, String> columnEventType;


    public DialogShowEventsListView() {

        init();
    }

    private void init() {


       GridPane gridPane = new GridPane();
       Label labelRelevancy = new Label("Relevancy");
       comboRelevancy = new ComboBox<String>();
       List<String> relevancyType = new ArrayList<>();
       relevancyType.add("Relevant");
       relevancyType.add("Irrelevant");
       relevancyType.add("Not set");
       ObservableList<String> observableList = FXCollections.observableList(relevancyType);
       comboRelevancy.setItems(observableList);

       Label labelDateBasse = new Label("Date basse:");
       Label labelDateHaute = new Label("Date haute");

       calendarBasse = new CalendarPicker();
       calendarHaute = new CalendarPicker();
       calendarBasse.withShowTime(true);
       calendarHaute.withShowTime(true);

       gridPane.addRow(0,labelRelevancy,comboRelevancy,labelDateBasse,calendarBasse,labelDateHaute,calendarHaute);

       labelNbTuple = new Label(TEXT_LABEL_TUPLE);
       gridPane.addRow(1,labelNbTuple);
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
       this.setBottom(hBox);
       buttonAnnuler = new Button("Annuler");
       hBox.getChildren().addAll(buttonAnnuler);


    }

    public TableView<Event> getTableEvents() {
        return tableEvents;
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public ComboBox<String> getComboRelevancy() {
        return comboRelevancy;
    }

    public CalendarPicker getCalendarBasse() {
        return calendarBasse;
    }

    public CalendarPicker getCalendarHaute() {
        return calendarHaute;
    }
}
