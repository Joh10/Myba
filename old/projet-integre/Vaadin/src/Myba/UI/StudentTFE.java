package Myba.UI;

import Myba.UI.customComponents.MybaLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;

/**
 * Created by Nicolas on 3/06/2015.
 */
class StudentTFE extends MybaLayout
{

    public StudentTFE()
    {
        super("studentViewLayout", true, true, true);

        //        listes.addContainerProperty("Echéances", String.class, null);
        //        listes.addContainerProperty("Evaluations", String.class, null);
        //        listes.addContainerProperty("Défenses", String.class, null);

        Tree listes = new Tree();
        listes.addItem("racine");
        listes.addItem("Echéances");
        listes.setParent("Echéances", "racine");
        listes.addItem("Evaluations");
        listes.setParent("Evaluations", "racine");
        listes.addItem("Défenses");
        listes.setParent("Défenses", "racine");

        Label titre = new Label();
        this.addComponent(titre, "titre");
        Label annees = new Label();
        this.addComponent(annees, "annees");
        Label technologies = new Label();
        this.addComponent(technologies, "technologies");
        this.addComponent(listes, "listes");

        /*listes.addItemClickListener(itemClickEvent -> (

                        )
        );*/ // TODO
    }
}