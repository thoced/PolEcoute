package dialogShowEventsView;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        columnConstraintsTop.setPercentWidth(33);
        columnConstraintsTop.setFillWidth(false);
        columnConstraintsTop.setHgrow(Priority.ALWAYS);
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

        gridPaneTop.addColumn(0,textEventId,textDateTime,textCallerId,textCallerImei,textCallerImsi);
        gridPaneTop.addColumn(1,textDuration,textEventType);
        gridPaneTop.addColumn(2,textId,textRelevancy,textCalledId,textCalledImei,textCalledImsi);

        gridPaneTop.getColumnConstraints().add(0,columnConstraintsTop);
        gridPaneTop.getColumnConstraints().add(1,columnConstraintsTop);
        gridPaneTop.getColumnConstraints().add(2,columnConstraintsTop);
        gridPaneTop.getRowConstraints().addAll(rowConstraintsTop);


        GridPane gridPaneBottom = new GridPane();
        this.getChildren().add(gridPaneBottom);
        ColumnConstraints columnConstraintsBottom = new ColumnConstraints();
        columnConstraintsBottom.setPercentWidth(90);
        RowConstraints rowConstraintsBottom = new RowConstraints();
        rowConstraintsBottom.setPercentHeight(45);
        rowConstraintsBottom.setFillHeight(true);


        textSynopsis = new TextArea();
        textTranscription = new TextArea();

        gridPaneBottom.addColumn(0,textSynopsis,textTranscription);
        gridPaneBottom.getColumnConstraints().add(0,columnConstraintsBottom);
        gridPaneBottom.getRowConstraints().add(0,rowConstraintsBottom);
        gridPaneBottom.getRowConstraints().add(1,rowConstraintsBottom);

    }

    public void showEvent(Event event){
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
    }



}
