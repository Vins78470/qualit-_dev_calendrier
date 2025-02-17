package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.CalendrierDuMois;
import modele.DateCalendrier;

import java.util.List;

import static modele.ConstantesCalendrier.MOIS;

public class VBoxRoot extends VBox {

    private StackPane stackPaneMois;

    public VBoxRoot() {
        DateCalendrier today = new DateCalendrier();
        Label labeltitle = new Label(MOIS[today.getMois()-1]);
        this.getChildren().add(labeltitle);

        stackPaneMois = new StackPane();

        for (int mois = 1; mois <= 12; mois++) {
            CalendrierDuMois cale = new CalendrierDuMois(mois, today.getAnnee());


            VBox vertiBox = new VBox();
            vertiBox.setSpacing(10);

            for (DateCalendrier date : cale.getDates()) {
                Label label = new Label(date.toString());
                vertiBox.getChildren().add(label);
            }

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vertiBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            scrollPane.setAccessibleText(MOIS[mois-1]);
            stackPaneMois.getChildren().add(scrollPane);


        }



        List<Node> listMonthCalendars = stackPaneMois.getChildren();
        final int lastIndice = listMonthCalendars.size()-1;
        Node premierMois = listMonthCalendars.get(0);
        Node dernierMois = listMonthCalendars.get(lastIndice);

        //placer le mois courant en haut de la pile

        while (!listMonthCalendars.get(lastIndice).getAccessibleText().equals(MOIS[today.getMois()-1])){
            listMonthCalendars.get(lastIndice).toBack();
        }

        this.getChildren().add(stackPaneMois);

        ToggleGroup buttonGroup = new ToggleGroup();
        ToggleButton buttonNext = new ToggleButton(">");
        ToggleButton buttonBefore = new ToggleButton("<");

        buttonBefore.setToggleGroup(buttonGroup);
        buttonNext.setToggleGroup(buttonGroup);

        this.getChildren().addAll(buttonBefore, buttonNext);

        buttonBefore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                    stackPaneMois.getChildren().get(lastIndice).toBack();
                    labeltitle.setText(listMonthCalendars.get(lastIndice).getAccessibleText());
            }
        });

        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                    stackPaneMois.getChildren().get(0).toFront();
                    labeltitle.setText(listMonthCalendars.get(lastIndice).getAccessibleText());

            }
        });
    }
}
