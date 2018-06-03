package dialogShowEventsView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import jfxtras.scene.control.CalendarPicker;
import models.Event;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private Button buttonExport;
    private TableColumn<Event, String> columnEventId;


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

       Locale local = new Locale("FR");
       calendarBasse.withLocale(local);
       calendarHaute.withLocale(local);

       ColumnConstraints columnConstraintsFirst = new ColumnConstraints();
       columnConstraintsFirst.setPercentWidth(10);
       ColumnConstraints columnConstraintsDate = new ColumnConstraints();
       columnConstraintsDate.setPercentWidth(30);
       gridPane.addRow(0,labelRelevancy,labelDateBasse,labelDateHaute);
       gridPane.addRow(1,comboRelevancy,calendarBasse,calendarHaute);
       gridPane.getColumnConstraints().add(0,columnConstraintsFirst);
       gridPane.getColumnConstraints().add(1,columnConstraintsDate);
       gridPane.getColumnConstraints().add(2,columnConstraintsDate);


       labelNbTuple = new Label(TEXT_LABEL_TUPLE);
       gridPane.addRow(1,labelNbTuple);
       this.setTop(gridPane);



       tableEvents = new TableView<Event>();
       tableEvents.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       tableEvents.itemsProperty().addListener((options,oldValue,newValue) -> {
           if(newValue != null) {
               labelNbTuple.setText(TEXT_LABEL_TUPLE + " " + newValue.size());
           }
       });



       this.setCenter(tableEvents);


       columnEventId = new TableColumn<Event,String>("Event Id");
       columnDate = new TableColumn<Event,String>("Date");
       columnSynopsis = new TableColumn<Event,String>("Synopsis");
       columnCaller = new TableColumn<Event,String>("Appelant");
       columnCalled = new TableColumn<Event,String>("Appelé");
       columnEventType = new TableColumn<Event,String>("Type");
       columnEventId.setCellValueFactory(new PropertyValueFactory("eventId"));
       columnDate.setCellValueFactory(new PropertyValueFactory("startDate"));
       columnSynopsis.setCellValueFactory(new PropertyValueFactory("synopsis"));
       columnCaller.setCellValueFactory(new PropertyValueFactory("callerId"));
       columnCalled.setCellValueFactory(new PropertyValueFactory("calledId"));
       columnEventType.setCellValueFactory(new PropertyValueFactory("eventType"));
       tableEvents.getColumns().addAll(columnEventId,columnDate,columnCaller,columnCalled,columnEventType,columnSynopsis);


       columnEventId.setCellFactory(p -> {
           return new TableCell<Event,String>(){
               @Override
               protected void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);

                   if(item != null && !empty) {
                       this.setText(item);
                       if(this.getTableRow() != null) {
                           Event event = (Event) this.getTableRow().getItem();

                           // si la transcription est terminée
                           if (event != null && event.isTranscriptionDone()) {
                               this.getTableRow().setStyle("-fx-background-color:#aed6f1");
                               return;
                           }

                           // si la transcription est commencée mais pas terminée
                           if (event != null && event.getTranscription() != null && !event.getTranscription().isEmpty()) {
                               this.getTableRow().setStyle("-fx-background-color:#d6eaf8");
                               return;
                           }

                           this.getTableRow().setStyle("");
                       }


                   }

               }
           };
       });


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
