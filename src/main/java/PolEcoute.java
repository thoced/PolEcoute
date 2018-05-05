import MainView.MainView;
import dao.DAOFactory;
import dao.EventDAO;
import dialogImportEvents.DialogImportEvents;
import dialogNewDossierView.DialogNewDossierView;
import dialogNewNumeroView.DialogNewNumeroView;
import dialogOpenDossierView.DialogOpenDossierView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Dossier;
import models.Event;
import models.Numero;
import parserEventsXml.ParserEventsXML;

import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class PolEcoute extends Application {

    private Stage stage;

    private Dossier currentDossier = null;
    private Stage stageNewDossier;
    private Stage stageImport;
    private DialogNewNumeroView dialogNewNumeroView;
    private Stage stageNewNumero;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        MainView mainView = new MainView();
        mainView.getItemOpenDossier().setOnAction(a -> {
            DialogOpenDossierView dialogOpenDossierView = new DialogOpenDossierView();
            dialogOpenDossierView.getButtonAnnuler().setOnAction(an -> {
                stage.hide();
            });
            dialogOpenDossierView.getButtonOk().setOnAction(an -> {
                currentDossier = dialogOpenDossierView.getListDossierView().getSelectionModel().getSelectedItem();
                if(currentDossier != null){
                    mainView.getItemImport().setDisable(false);
                    mainView.getItemAddNumero().setDisable(false);
                    primaryStage.setTitle("Dossier en cours: " + currentDossier.toString());
                }
                else {
                    mainView.getItemImport().setDisable(true);
                    mainView.getItemAddNumero().setDisable(true);
                }
                stage.hide();
            });

         mainView.getItemNouveauDossier().setOnAction(nd -> {
             DialogNewDossierView dialogNewDossierView = new DialogNewDossierView();
             dialogNewDossierView.getButtonAnnuler().setOnAction(ba -> {
                 stageNewDossier.hide();
             });
             dialogNewDossierView.getButtonEnregistrer().setOnAction(be -> {
                 String nom = dialogNewDossierView.getTextFieldNom().getText();
                 String com = dialogNewDossierView.getTextFieldCom().getText();
                 Dossier dossier = new Dossier();
                 dossier.setNom(nom);
                 dossier.setCommentaire(com);
                 try {
                     DAOFactory.getInstance().getDOSSIER_DAO().insert(dossier);
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
                 stageNewDossier.hide();
             });




                Scene scene = new Scene(dialogNewDossierView);
                stageNewDossier = new Stage();
                stageNewDossier.setWidth(800);
                stageNewDossier.setHeight(600);
                stageNewDossier.initModality(Modality.APPLICATION_MODAL);
                stageNewDossier.setScene(scene);
                stageNewDossier.showAndWait();

         });

            mainView.getItemImport().setOnAction(ii -> {

                DialogImportEvents dialogImportEvents = new DialogImportEvents(currentDossier);
                dialogImportEvents.getButtonAnnuler().setOnAction(ba -> {
                    stageImport.hide();
                });
                dialogImportEvents.getButtonSelected().setOnAction(bs -> {
                    Numero numero = (Numero) dialogImportEvents.getListNumeroView().getSelectionModel().getSelectedItem();

                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(stage);
                    if(file != null){
                        ParserEventsXML parserEventsXML = new ParserEventsXML(file,numero);
                        List<Event> list = parserEventsXML.getListEvents();
                        ((EventDAO)DAOFactory.getInstance().getEVENT_DAO()).insertAll(list);
                    }
                    stageImport.hide();
                });

                Scene scene = new Scene(dialogImportEvents);
                stageImport = new Stage();
                stageImport.setScene(scene);
                stageImport.initModality(Modality.APPLICATION_MODAL);
                stageImport.showAndWait();


            });



            Scene scene = new Scene(dialogOpenDossierView);
            stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(800);
            stage.setHeight(600);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        });

        mainView.getItemAddNumero().setOnAction(ia -> {
            dialogNewNumeroView = new DialogNewNumeroView();
            dialogNewNumeroView.getButtonAnnuler().setOnAction(ba -> {
                stageNewNumero.hide();
            });
            dialogNewNumeroView.getButtonAjouterNumero().setOnAction(baa -> {
                String newNumero = dialogNewNumeroView.getTextFieldNumero().getText();
                Numero numero = new Numero();
                numero.setCallId(newNumero);
                numero.setRefIdDossier(currentDossier.getId());
                try {
                    DAOFactory.getInstance().getNUMERO_DAO().insert(numero);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stageNewNumero.hide();
            });

            Scene scene = new Scene(dialogNewNumeroView);
            stageNewNumero = new Stage();
            stageNewNumero.setScene(scene);
            stageNewNumero.initModality(Modality.APPLICATION_MODAL);
            stageNewNumero.showAndWait();

        });

        Scene scene = new Scene(mainView);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        primaryStage.setTitle("Pol Ecoute");
        primaryStage.show();
    }
}
