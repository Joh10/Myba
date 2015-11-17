package Myba.UI;

import DAO.PropositionStageDAO;
import Myba.UI.customComponents.GoogleMap;
import Myba.UI.customComponents.MybaLayout;
import Myba.utils.GeoCodingUtils;
import Myba.utils.ParsingUtils;
import Myba.utils.ResourcesUtils;
import Server.PropositionStage;
import com.vaadin.server.Page;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class HomeView extends MybaLayout
{
    private final List<String> technologies = new ArrayList<>();
    private final List<String> entreprises = new ArrayList<>();
    private boolean isInActiveState = false;
    private GoogleMap map;

    public HomeView() throws FileNotFoundException
    {
        super("homeViewLayout", false, true, false);

        Image logo = new Image(null, ResourcesUtils.load("Logo-Full.png"));
        map = new GoogleMap(10, 10, 5);
        TextField searchField = new TextField();

        //Ajout des noms pour l'injection CSS
        setStyleName("root");
        searchField.setStyleName("searchField");
        map.setStyleName("map");
        logo.setStyleName("logo");

        //Configure les objets
        searchField.setInputPrompt("Recherche");
        searchField.addTextChangeListener(e -> HomeView.this.manageEntry(e.getText()));
        searchField.setWidth("100%");
        searchField.setImmediate(true);

        logo.setWidth("100%");

        map.setCenter(new LatLon(60.440963, 22.25122));
        map.setZoom(10);
        map.setSizeFull();
        map.setMinZoom(4);
        map.setMaxZoom(16);

        //Ajoute les composantns
        addComponent(logo, "logo");
        addComponent(map, "map");
        addComponent(searchField, "searchField");

        setSizeFull();
        injectInactiveCSS();
    }

    //Injecte le CSS quand la page est au repos, map non visible
    private void injectInactiveCSS()
    {
        Page.Styles styles = Page.getCurrent().getStyles();

        styles.add(".searchField {" +
                "margin: auto;" +
                "width: 50%;" +
                "position: static;" +
                "margin:auto;" +
                "margin-top:7%;" +
                "max-width: 500px;" +
                "-webkit-box-shadow: none;" +
                "-moz-box-shadow: none;" +
                "box-shadow: none;" +
                "}");

        styles.add(".map {" +
                "position: absolute;" +
                "height: 1px;" +
                "width: 1px;" +
                "margin:auto;" +
                "visibility: hidden;" +
                "}");

        styles.add(".logo {" +
                "position: static;" +
                "visibility: visible;" +
                "width: 35%;" +
                "height: auto;" +
                "margin: 50px auto auto;" +
                "max-width:309px;" +
                "max-height: 263px;" +
                "}");
    }

    //Injecte le CSS quand la map est visible
    private void injectActiveCSS()
    {
        Page.Styles styles = Page.getCurrent().getStyles();

        styles.add(".searchField {" +
                "position: absolute;" +
                "margin: auto;" +
                "top: 15px;" +
                "left: 12.5%;" +
                "right: 25%;" +
                "z-index: 20;" +
                "max-width: 500px;" +
                "-webkit-box-shadow: 6px 6px 5px 0px rgba(0,0,0,0.5);" +
                "-moz-box-shadow: 6px 6px 5px 0px rgba(0,0,0,0.5);" +
                "box-shadow: 6px 6px 5px 0px rgba(0,0,0,0.5);" +
                "}");

        styles.add(".map {" +
                "position: static;" +
                "height: 100%;" +
                "visibility: visible;" +
                "width: 100%;" +
                "margin:auto;" +
                "}");

        styles.add(".logo {" +
                "position: absolute;" +
                "width: 1px;" +
                "height: 1px;" +
                "visibility: hidden;" +
                "margin: 25px auto auto;" +
                "max-width:309px;" +
                "max-height: 263px;" +
                "}");
    }

    /////////////////////////
    //       PARSING       //
    /////////////////////////

    private void manageEntry(String input)
    {
        //Change layout
        if (input.isEmpty())
        {
            injectInactiveCSS();
            isInActiveState = false;
        } else
        {
            //Empeche l'injection a chaque changement du texte
            if (!isInActiveState)
            {
                injectActiveCSS();
                isInActiveState = true;
            }
        }

        //On normalise l'input, on enlève les accent, et on met tout en minuscule
        String normalizedInput = ParsingUtils.normalize(input);

        //RECHERHCE DE STAGE
        //TODO STAGE + PROPOSITION ?
        if (normalizedInput.startsWith("stage"))
        {
            //On supprime tout les mots communs
            String phrase = ParsingUtils.deleteCommonStageWords(normalizedInput);
            String words[] = phrase.split(" ");

            //Si on a des mots clef a analyser
            if (words.length > 0)
            {
                //On supprime les anciennes valeurs
                map.clearMarkers();
                technologies.clear();
                entreprises.clear();

                //Recupère tout les mots clef qui font référence a une technologie ou a un nom d'entreprise
                for (String t : words) if (ParsingUtils.isATechnology(t.trim())) technologies.add(t);
                for (String t : words) if (ParsingUtils.isAEntreprise(t.trim())) entreprises.add(t);

                //On recherche une distance si il y en a une
                String distance = ParsingUtils.extractDistance(phrase);

                //On supprime tout ces mots clef du string original
                phrase = ParsingUtils.deleteWords(phrase, technologies.toArray(new String[technologies.size()])).trim();
                phrase = ParsingUtils.deleteWords(phrase, entreprises.toArray(new String[entreprises.size()])).trim();
                phrase = ParsingUtils.deleteWords(phrase, distance);

                PropositionStageDAO DAO = new PropositionStageDAO();

                //Si le string qui reste est vide c'est qu'il n'y a pas d'adresse dedans
                //On a juste des nom d'entreprise et/ou de technologie
                if (phrase.isEmpty())
                {
                    for (PropositionStage propositionStage : DAO.findAll())
                        if (ParsingUtils.StageHaveOneOfTech(propositionStage, technologies) || ParsingUtils.StageIsFromEntreprise(propositionStage, entreprises))
                            map.addMarker(propositionStage.getLieuStage().getAdresse());
                }
                //Avec adresse
                else
                {
                    //Le restant c'est l'adresse
                    GeoCodingUtils.Location location = GeoCodingUtils.getLocation(GeoCodingUtils.encodeString(phrase));
                    if (location == null) return;

                    if (location.isCountry())
                    {
                        //On recupère tout les stages dans le pays donne avec au moins une des technologie
                        for (PropositionStage propositionStage : DAO.findAll())
                            if (propositionStage.getLieuStage().getAdresse().getCountry().toLowerCase().trim().equals(location.getCountry()) && ParsingUtils.StageHaveOneOfTech(propositionStage, technologies))
                                map.addMarker(propositionStage.getLieuStage().getAdresse());

                    } else
                    {
                        //Si c'est pas un pays c'est un lieu, mais ece qu'on recherche precisement ou a proximité ?
                        if (!distance.isEmpty())
                        {
                            double d = Double.parseDouble(distance.replaceAll("[^\\d.]", "").trim());

                            //On recupère tout les stages a proximite de cette adresse et qui ont au moins une des technologie
                            for (PropositionStage propositionStage : DAO.findAll())
                            {
                                GeoCodingUtils.Location locationLDS = GeoCodingUtils.getLocation(GeoCodingUtils.encodeString(propositionStage.getLieuStage().getAdresse().toString()));

                                if (GeoCodingUtils.distanceBetween(location, locationLDS) <= d && ParsingUtils.StageHaveOneOfTech(propositionStage, technologies))
                                    map.addMarker(propositionStage.getLieuStage().getAdresse());
                            }
                        } else
                        {
                            //On recupère tout les stages a cette adresse et qui ont au moins une des technologie
                            for (PropositionStage propositionStage : DAO.findAll())
                                if (ParsingUtils.AddressMatch(propositionStage.getLieuStage().getAdresse(), phrase) && ParsingUtils.StageHaveOneOfTech(propositionStage, technologies))
                                    map.addMarker(propositionStage.getLieuStage().getAdresse());
                        }
                    }

                    map.focusLocation(location);
                }
            }
        }

        //RECHERCHE TFE
        //(selon technologies, selon année académique)
        else if (normalizedInput.startsWith("tfe"))
        {
            //On supprime tout les mots communs
            String phrase = ParsingUtils.deleteCommonTFEWords(normalizedInput);
            String words[] = phrase.split(" ");

            //Si on a des mots clef a analyser
            if (words.length > 0)
            {
                //On supprime les anciennes valeurs
                map.clearMarkers();
                technologies.clear();

                //Recupère tout les mots clef qui font référence a une technologie
                for (String t : words) if (ParsingUtils.isATechnology(t.trim())) technologies.add(t);

                //On supprime tout ces mots clef du string original
                phrase = ParsingUtils.deleteWords(phrase, technologies.toArray(new String[technologies.size()])).trim();

                //Si le string est vide c'est qu'on a juste une ou des technologies pas de date
                if (phrase.isEmpty())
                {
                    //TODO
                } else
                {
                    //TODO
                }
            }
        }


    }
}
