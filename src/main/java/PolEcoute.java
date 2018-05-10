import MainView.MainView;
import dao.DAOFactory;
import dao.EventDAO;
import dialogNumeroShow.DialogNumeroShow;
import dialogNewDossierView.DialogNewDossierView;
import dialogNewNumeroView.DialogNewNumeroView;
import dialogOpenDossierView.DialogOpenDossierView;
import dialogSearch.DialogSearchView;
import dialogShowEventsView.DialogShowEventsListView;
import dialogShowEventsView.DialogShowEventsView;
import export.DialogExportRapportView;
import export.ExportRapport;
import fr.opensagres.xdocreport.core.XDocReportException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Dossier;
import models.Event;
import models.Numero;
import models.OptionSearch;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jdom.JDOMException;
import parserEventsXml.ParserEventsXML;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PolEcoute extends Application {

    private Stage stage;

    private Dossier currentDossier = null;
    private Stage stageNewDossier;
    private Stage stageImport;
    private DialogNewNumeroView dialogNewNumeroView;
    private Stage stageNewNumero;
    private MainView mainView;
    private Stage stageShowEvents;
    private Stage stageShowNumero;
    private DialogShowEventsListView dialogShowEventsListView;
    private Stage stageEvent;
    private DialogSearchView dialogSearchView;
    private Stage stageSearch;
    private Stage stageExport;
    private DialogExportRapportView dialogExportRapportView;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainView = new MainView();

        mainView.getItemFermer().setOnAction(action -> {
            primaryStage.hide();
        });

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
                    mainView.getItemShowEvents().setDisable(false);
                    mainView.getItemSearch().setDisable(false);
                    primaryStage.setTitle("Dossier en cours: " + currentDossier.toString());
                }
                else {
                    mainView.getItemImport().setDisable(true);
                    mainView.getItemAddNumero().setDisable(true);
                    mainView.getItemShowEvents().setDisable(true);
                    mainView.getItemSearch().setDisable(true);
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

                DialogNumeroShow dialogNumeroShow = new DialogNumeroShow(currentDossier);
                dialogNumeroShow.getButtonAnnuler().setOnAction(ba -> {
                    stageImport.hide();
                });
                dialogNumeroShow.getButtonSelected().setOnAction(bs -> {
                    Numero numero = (Numero) dialogNumeroShow.getListNumeroView().getSelectionModel().getSelectedItem();

                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(stage);
                    if(file != null){
                        ParserEventsXML parserEventsXML = new ParserEventsXML(file,numero);
                        List<Event> list = parserEventsXML.getListEvents();
                        ((EventDAO)DAOFactory.getInstance().getEVENT_DAO()).insertAll(list);
                    }
                    stageImport.hide();
                });

                Scene scene = new Scene(dialogNumeroShow);
                stageImport = new Stage();
                stageImport.setScene(scene);
                stageImport.initModality(Modality.APPLICATION_MODAL);
                stageImport.showAndWait();


            });



            Scene scene = new Scene(dialogOpenDossierView);
            stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(600);
            stage.setHeight(400);
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
                String newTarget  = dialogNewNumeroView.getTextFiledTargetName().getText();
                Numero numero = new Numero();
                numero.setCallId(newNumero);
                numero.setTargetName(newTarget);

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

        mainView.getItemShowEvents().setOnAction(is -> {
            DialogNumeroShow dialogNumeroShow = new DialogNumeroShow(currentDossier);
            dialogNumeroShow.getButtonAnnuler().setOnAction(ba -> {
                stageShowNumero.hide();
            });
            dialogNumeroShow.getButtonSelected().setOnAction(bs -> {
                Numero numero  = (Numero) dialogNumeroShow.getListNumeroView().getSelectionModel().getSelectedItem();
                dialogShowEventsListView = new DialogShowEventsListView();
                dialogShowEventsListView.getTableEvents().getItems().clear();

                   // List<Event> events = DAOFactory.getInstance().getEVENT_DAO().selectFromForeignKey(numero.getId());
                   // ObservableList<Event> observableList = FXCollections.observableList(events);
                   // dialogShowEventsListView.getTableEvents().getItems().clear();
                   // dialogShowEventsListView.getTableEvents().setItems(observableList);



                dialogShowEventsListView.getComboRelevancy().valueProperty().addListener((options,oldValue,newValue) -> {
                    OptionSearch.getInstance().setRelevancy(newValue);
                    updateRefreshSearch(numero.getId());
                });

                dialogShowEventsListView.getCalendarBasse().calendarProperty().addListener((options,oldValue,newValue) -> {
                    OptionSearch.getInstance().setDateBasse(LocalDateTime.ofInstant(newValue.toInstant(),newValue.getTimeZone().toZoneId()));
                    updateRefreshSearch(numero.getId());
                });

                dialogShowEventsListView.getCalendarHaute().calendarProperty().addListener((options,oldValue,newValue) -> {
                    OptionSearch.getInstance().setDateHaute(LocalDateTime.ofInstant(newValue.toInstant(),newValue.getTimeZone().toZoneId()));
                    updateRefreshSearch(numero.getId());
                });

                dialogShowEventsListView.getButtonExport().setOnAction(action -> {


                    // Creation du Rapport
                    dialogExportRapportView = new DialogExportRapportView();

                    dialogExportRapportView.getButtonAnnuler().setOnAction(ba -> {
                        stageExport.hide();
                    });

                    dialogExportRapportView.getButtonRapport().setOnAction(br -> {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Indiquer l'emplace d'enregistrement du fichier ODT");
                        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("ODT files (*.odt)", "*.odt");
                        fileChooser.getExtensionFilters().addAll(filter);
                        File file = fileChooser.showSaveDialog(stageShowEvents);
                        if(file != null){
                            ExportRapport exportRapport = new ExportRapport(file);
                            try {
                                //exportRapport.export(dialogShowEventsListView.getTableEvents().getItems());
                                exportRapport.replace(dialogShowEventsListView.getTableEvents().getItems(),dialogExportRapportView.getTextFieldNotice().getText()
                                        ,dialogExportRapportView.getTextFieldNumInstruction().getText(),
                                        dialogExportRapportView.getTextNumeroRapport().getText(),
                                        dialogExportRapportView.getTextPeriodeRapport().getText());

                            } catch (IOException e) {
                                e.printStackTrace();

                            } catch (JDOMException e) {
                                e.printStackTrace();
                            } catch (InvalidFormatException e) {
                                e.printStackTrace();
                            } catch (XDocReportException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        stageExport.hide();
                    });


                    Scene scene = new Scene(dialogExportRapportView);
                    stageExport = new Stage();
                    stageExport.setScene(scene);
                    stageExport.setTitle(DialogExportRapportView.NAME);
                    stageExport.initModality(Modality.APPLICATION_MODAL);
                    stageExport.initOwner(stageShowEvents);
                    stageExport.showAndWait();
                    stageExport.hide();


                });

                dialogShowEventsListView.getTableEvents().setOnMouseClicked(val -> {
                    if(val.getClickCount() > 1){
                        Event event = dialogShowEventsListView.getTableEvents().getSelectionModel().getSelectedItem();
                        if(event != null) {
                            DialogShowEventsView dialogShowEventsView = new DialogShowEventsView(numero);
                            dialogShowEventsView.showEvent(event);


                            dialogShowEventsView.getButtonAnnuler().setOnAction(action -> {
                                stageEvent.hide();
                            });

                            dialogShowEventsView.getButtonEnregistrer().setOnAction(action -> {
                                try {
                                    DAOFactory.getInstance().getEVENT_DAO().update(dialogShowEventsView.getCurrentEvent());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });

                            dialogShowEventsView.getButtonEnregistrerAndExit().setOnAction(action -> {
                                try {
                                    DAOFactory.getInstance().getEVENT_DAO().update(dialogShowEventsView.getCurrentEvent());
                                    stageEvent.hide();
                                    updateRefreshSearch(numero.getId());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });



                            Scene scene = new Scene(dialogShowEventsView);
                            stageEvent = new Stage();
                            stageEvent.setScene(scene);
                            stageEvent.initModality(Modality.APPLICATION_MODAL);
                            stageEvent.setWidth(1024);
                            stageEvent.setHeight(768);
                            stageEvent.initOwner(stageShowEvents);
                            stageEvent.initStyle(StageStyle.DECORATED);
                            stageEvent.onHidingProperty().addListener((options,oldValue,newValue) -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Fermeture");
                                alert.setContentText(DialogShowEventsView.WARNING_WRITE_DIALOG);
                                ButtonType buttonOui = new ButtonType("Oui");
                                ButtonType buttonNon = new ButtonType("Non");
                                alert.getButtonTypes().addAll(buttonOui,buttonNon);
                                Optional<ButtonType> result = alert.showAndWait();
                                if(result.get() == buttonOui){
                                    try {
                                        DAOFactory.getInstance().getEVENT_DAO().update(dialogShowEventsView.getCurrentEvent());
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                stageEvent.hide();
                            });
                            stageEvent.showAndWait();

                        }
                    }
                });

                dialogShowEventsListView.getButtonAnnuler().setOnAction(b -> {
                    stageShowEvents.hide();
                });

                dialogShowEventsListView.getComboRelevancy().getSelectionModel().select("Not set");


                Scene scene = new Scene(dialogShowEventsListView);
                stageShowEvents = new Stage();
                stageShowEvents.setScene(scene);
                stageShowEvents.initModality(Modality.APPLICATION_MODAL);
                stageShowEvents.setWidth(1024);
                stageShowEvents.setHeight(768);
                stageShowEvents.initStyle(StageStyle.DECORATED);
                stageShowEvents.showAndWait();


            });


            Scene scene = new Scene(dialogNumeroShow);
            stageShowNumero = new Stage();
            stageShowNumero.setScene(scene);
            stageShowNumero.initModality(Modality.APPLICATION_MODAL);
            stageShowNumero.setTitle(DialogNumeroShow.NAME);
            stageShowNumero.showAndWait();


        });

        mainView.getItemSearch().setOnAction(action -> {
            dialogSearchView = new DialogSearchView();
            dialogSearchView.getButtonAnnuler().setOnAction(annuler -> {
                stageSearch.hide();
            });

            try {
                List<Numero> list = DAOFactory.getInstance().getNUMERO_DAO().selectFromForeignKey(currentDossier.getId());
                dialogSearchView.getComboNumero().setItems(FXCollections.observableList(list));

            } catch (SQLException e) {
                e.printStackTrace();
            }

            dialogSearchView.getButtonSearch().setOnAction(search -> {
                try {
                    OptionSearch.getInstance().setKeySearch(dialogSearchView.getTextSearch().getText());
                    Numero numero = dialogSearchView.getComboNumero().getValue();
                    List<Event> list = ((EventDAO)DAOFactory.getInstance().getEVENT_DAO()).selectFromForeignKeyANDSearch(numero.getId(),OptionSearch.getInstance());
                    dialogSearchView.getTableEvents().getItems().clear();
                    dialogSearchView.getTableEvents().setItems(FXCollections.observableList(list));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            dialogSearchView.getButtonSearchNumero().setOnAction(search -> {
                try {
                    OptionSearch.getInstance().setKeySearch(dialogSearchView.getTextSearchNumero().getText());
                    Numero numero = dialogSearchView.getComboNumero().getValue();
                    List<Event> list = ((EventDAO)DAOFactory.getInstance().getEVENT_DAO()).selectFromForeignKeyANDSearchNumero(numero.getId(),OptionSearch.getInstance());
                    dialogSearchView.getTableEvents().getItems().clear();
                    dialogSearchView.getTableEvents().setItems(FXCollections.observableList(list));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            dialogSearchView.getTableEvents().setOnMouseClicked(mouse -> {
                if(mouse.getClickCount() > 1){
                    Event event = dialogSearchView.getTableEvents().getSelectionModel().getSelectedItem();
                    if(event != null) {

                        try {
                            event = (Event) DAOFactory.getInstance().getEVENT_DAO().find(event.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        DialogShowEventsView dialogShowEventsView = new DialogShowEventsView(null);
                        dialogShowEventsView.showEvent(event);

                        dialogShowEventsView.getButtonAnnuler().setOnAction(annuler -> {

                           stageShowEvents.hide();
                        });

                        dialogShowEventsView.getButtonEnregistrer().setOnAction(enr -> {
                            try {
                                DAOFactory.getInstance().getEVENT_DAO().update(dialogShowEventsView.getCurrentEvent());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });

                        dialogShowEventsView.getButtonEnregistrerAndExit().setOnAction(enr -> {
                            try {
                                DAOFactory.getInstance().getEVENT_DAO().update(dialogShowEventsView.getCurrentEvent());
                                stageShowEvents.hide();
                                } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });

                        Scene scene = new Scene(dialogShowEventsView);
                        stageShowEvents = new Stage();
                        stageShowEvents.setScene(scene);
                        stageShowEvents.setWidth(1024);
                        stageShowEvents.setHeight(768);
                        stageShowEvents.initModality(Modality.APPLICATION_MODAL);
                        stageShowEvents.initStyle(StageStyle.DECORATED);
                        stageShowEvents.showAndWait();
                    }
                }

            });

            Scene scene = new Scene(dialogSearchView);
            stageSearch = new Stage();
            stageSearch.setScene(scene);
            stageSearch.setTitle(DialogSearchView.NAME);
            stageSearch.initModality(Modality.APPLICATION_MODAL);
            stageSearch.setWidth(1024);
            stageSearch.setHeight(768);
            stageSearch.initStyle(StageStyle.DECORATED);
            stageSearch.showAndWait();
        });


        /* -----------------------------------------
         Primary
         */

        Scene scene = new Scene(mainView);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        primaryStage.setTitle("Pol Ecoute");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
    }

    private void updateRefreshSearch(long id) {
        List<Event> events = null;
        try {
            events = ((EventDAO)DAOFactory.getInstance().getEVENT_DAO()).selectFromForeignKeyAndOption(id,OptionSearch.getInstance());
            ObservableList<Event> observableList = FXCollections.observableList(events);
            dialogShowEventsListView.getTableEvents().getItems().clear();
            dialogShowEventsListView.getTableEvents().setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
