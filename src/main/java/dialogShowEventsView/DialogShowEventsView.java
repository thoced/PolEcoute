package dialogShowEventsView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Event;
import models.Numero;



public class DialogShowEventsView extends VBox {

    private Numero numero;
    private TextField textEventId;
    private TextField textId;
    private TextField textDateTime;
    private TextField textDuration;
    private TextField textRelevancy;
    private TextField textCallerId;
    private TextField textCalledId;
    private TextField textCallerImei;
    private TextField textCallerImsi;
    private TextField textCalledImei;
    private TextField textCalledImsi;
    private TextArea textSynopsis;
    private TextArea textTranscription;
    private TextField textEventType;
    private TextField textLocation;
    private Button buttonAnnuler;
    private Button buttonEnregistrer;
    private Event currentEvent;
    private Button buttonEnregistrerAndExit;

    public static final String WARNING_WRITE_DIALOG = "Le fenetre va se fermer, voulez-vous sauvegarder vos modifications";


    public DialogShowEventsView(Numero numero) {
        this.numero  = numero;
        init();
    }

    private void init() {

        this.setAlignment(Pos.CENTER);



        GridPane gridPaneTop = new GridPane();
        gridPaneTop.setAlignment(Pos.CENTER);
        this.getChildren().add(gridPaneTop);
        ColumnConstraints columnConstraintsTop = new ColumnConstraints();
        columnConstraintsTop.setPercentWidth(15);
        columnConstraintsTop.setFillWidth(false);
        RowConstraints rowConstraintsTop = new RowConstraints();
        rowConstraintsTop.setPercentHeight(15);


        textEventId = new TextField();
        textId = new TextField();
        textDateTime = new TextField();
        textDuration = new TextField();
        textRelevancy = new TextField();
        textCallerId = new TextField();
        textCalledId = new TextField();
        textCallerImei = new TextField();
        textCallerImsi = new TextField();
        textCalledImei = new TextField();
        textCalledImsi = new TextField();
        textEventType = new TextField();
        textLocation = new TextField();
        textLocation = new TextField();
        textLocation.setMinWidth(512);

        textEventId.setDisable(true);
        textId.setDisable(true);
        textDateTime.setDisable(true);
        textDuration.setDisable(true);
        textRelevancy.setDisable(true);
        textCallerId.setDisable(true);
        textCalledId.setDisable(true);
        textCallerImei.setDisable(true);
        textCallerImsi.setDisable(true);
        textCalledImei.setDisable(true);
        textCalledImsi.setDisable(true);
        textEventType.setDisable(true);
        textLocation.setDisable(true);
        
        

        Label labelEventId = new Label("EventId");
        Label labelDateTime = new Label("DateTime");
        Label labelCallerId = new Label("Numero appelant");
        Label labelCallerImei = new Label("Imei appelant");
        Label labelCallerImsi = new Label("Imsi appelant");

        Label labelEventType = new Label("Event Type");
        Label labelDuration = new Label("Duration");

        Label labelTargetName = new Label("Target name");
        Label labelRelevancy = new Label("Relevancy");
        Label labelCalledId = new Label("Numéro appelé");
        Label labelCalledImei = new Label("Imei appelé");
        Label labelCalledImsi = new Label("Imsi appelé");
        Label labelLocation = new Label("Localisation");




        gridPaneTop.addColumn(0,labelEventId,labelDateTime,labelCallerId,labelCallerImei,labelCallerImsi);
        gridPaneTop.addColumn(1,textEventId,textDateTime,textCallerId,textCallerImei,textCallerImsi);
        gridPaneTop.addColumn(2,labelEventType,labelDuration);
        gridPaneTop.addColumn(3,textEventType,textDuration);
        gridPaneTop.addColumn(4,labelTargetName,labelRelevancy,labelCalledId,labelCalledImei,labelCalledImsi);
        gridPaneTop.addColumn(5,textId,textRelevancy,textCalledId,textCalledImei,textCalledImsi);

        gridPaneTop.getColumnConstraints().add(0,columnConstraintsTop);
        gridPaneTop.getColumnConstraints().add(1,columnConstraintsTop);
        gridPaneTop.getColumnConstraints().add(2,columnConstraintsTop);
        gridPaneTop.getColumnConstraints().add(3,columnConstraintsTop);
        gridPaneTop.getColumnConstraints().add(4,columnConstraintsTop);
        gridPaneTop.getColumnConstraints().add(5,columnConstraintsTop);
        gridPaneTop.getRowConstraints().addAll(rowConstraintsTop);


        GridPane gridPaneBottom = new GridPane();
        this.getChildren().add(gridPaneBottom);
        ColumnConstraints columnConstraintsBottom = new ColumnConstraints();
        columnConstraintsBottom.setPercentWidth(90);
        RowConstraints rowConstraintsBottom = new RowConstraints();
        rowConstraintsBottom.setPercentHeight(45);
        rowConstraintsBottom.setFillHeight(true);

        RowConstraints rowConstraintsLabel = new RowConstraints();
        rowConstraintsLabel.setPercentHeight(5);


        RowConstraints rowConstraintsLocation = new RowConstraints();
        HBox hBoxLocation = new HBox();
        hBoxLocation.setAlignment(Pos.CENTER_LEFT);
        hBoxLocation.setSpacing(16);
        hBoxLocation.getChildren().addAll(labelLocation,textLocation);


        textSynopsis = new TextArea();
        textTranscription = new TextArea();
        textSynopsis.setWrapText(true);
        textSynopsis.setDisable(true);
        textSynopsis.setStyle("-fx-background-color:lightblue;-fx-text-inner-color:blue; -fx-font-size: 16pt;");


        Label labelSynopsis = new Label("Synopsis:");
        Label labelTranscription = new Label("Transcription:");

        gridPaneBottom.addColumn(0,hBoxLocation,labelSynopsis,textSynopsis,labelTranscription,textTranscription);
        gridPaneBottom.getColumnConstraints().add(0,columnConstraintsBottom);
        gridPaneBottom.getRowConstraints().add(0,rowConstraintsLabel);
        gridPaneBottom.getRowConstraints().add(1,rowConstraintsLabel);
        gridPaneBottom.getRowConstraints().add(2,rowConstraintsBottom);
        gridPaneBottom.getRowConstraints().add(3,rowConstraintsLabel);
        gridPaneBottom.getRowConstraints().add(4,rowConstraintsBottom);


        HBox hBox = new HBox();
        buttonAnnuler = new Button("Annuler");
        buttonEnregistrer = new Button("Enregistrer");
        buttonEnregistrerAndExit = new Button("Enregistrer et Quitter");
        hBox.setPadding(new Insets(8));
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(8);
        hBox.getChildren().addAll(buttonAnnuler,buttonEnregistrer,buttonEnregistrerAndExit);
        this.getChildren().add(hBox);





    }

    public void showEvent(Event event){

        this.currentEvent = event;
        textEventId.setText(event.getEventId());
        textId.setText(event.getTargetName());
        textDateTime.setText(event.getStartDate());
        textDuration.setText(event.getDuration());
        textRelevancy.setText(event.getRelevancy());
        textCallerId.setText(event.getCallerId());
        textCalledId.setText(event.getCalledId());
        textCallerImei.setText(event.getCallerImei());
        textCallerImsi.setText(event.getCallerImsi());
        textCalledImei.setText(event.getCalledImei());
        textCalledImsi.setText(event.getCalledImsi());
        textEventType.setText(event.getEventType());
        textSynopsis.setText(event.getSynopsis());
        textTranscription.setText(event.getTranscription());
        textLocation.setText(event.getLocation());
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public Button getButtonEnregistrer() {
        return buttonEnregistrer;
    }

    public Button getButtonEnregistrerAndExit() {
        return buttonEnregistrerAndExit;
    }

    public TextArea getTextTranscription() {
        return textTranscription;
    }

    public TextField getTextLocation() {
        return textLocation;
    }

    public Event getCurrentEvent() {
        currentEvent.setTranscription(textTranscription.getText());
        return currentEvent;
    }
}
