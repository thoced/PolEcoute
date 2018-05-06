package MainView;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

    private MenuBar menuBar;
    private MenuItem itemFermer;
    private MenuItem itemNouveauDossier;
    private MenuItem itemOpenDossier;
    private MenuItem itemImport;
    private MenuItem itemAddNumero;
    private MenuItem itemShowEvents;
    private MenuItem itemSearch;

    public MainView() {
        super();

        init();
    }

    private void init() {

        menuBar = new MenuBar();

        Menu menuFichier = new Menu("Fichier");
        Menu menuGestion = new Menu("Gestion");
        menuBar.getMenus().addAll(menuFichier,menuGestion);

        itemFermer = new MenuItem("Quitter");
        itemNouveauDossier = new MenuItem("Nouveau Dossier");
        itemOpenDossier = new MenuItem("Ouvrir un dossier");


        menuFichier.getItems().addAll(itemNouveauDossier,itemOpenDossier,itemFermer);

        itemImport = new MenuItem("Importer des events");
        itemImport.setDisable(true);

        itemAddNumero = new MenuItem("Ajouter un numéro au dossier");
        itemAddNumero.setDisable(true);

        itemShowEvents = new MenuItem("Afficher les events");
        itemShowEvents.setDisable(true);

        itemSearch = new MenuItem("Recherches");
        itemSearch.setDisable(true);

        menuGestion.getItems().addAll(itemImport,itemAddNumero,itemShowEvents,itemSearch);

        this.setTop(menuBar);

        Image img = new Image("logo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        this.setCenter(imageView);

    }

    public MenuItem getItemFermer() {
        return itemFermer;
    }

    public MenuItem getItemNouveauDossier() {
        return itemNouveauDossier;
    }

    public MenuItem getItemOpenDossier() {
        return itemOpenDossier;
    }

    public MenuItem getItemImport() {
        return itemImport;
    }

    public MenuItem getItemAddNumero() {
        return itemAddNumero;
    }

    public MenuItem getItemShowEvents() {
        return itemShowEvents;
    }

    public MenuItem getItemSearch() {
        return itemSearch;
    }
}
