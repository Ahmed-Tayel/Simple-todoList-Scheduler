package ApplicationPages.CompundFXObjects;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PrioritiesChoiceBox {
    Label select_day_text;
    ChoiceBox priorities;
    GridPane grid;

    public PrioritiesChoiceBox(){
        select_day_text = new Label("Priority");
        select_day_text.setStyle("-fx-background: #FFFFFF;");

        priorities = new ChoiceBox();
        priorities.getItems().add("Top");
        priorities.getItems().add("Medium");
        priorities.getItems().add("Low");

        priorities.setValue("Low");

        grid = new GridPane();
        init_grid(grid);

    }

    //INITIALIZATION ############################################################################################################
    private void init_grid(GridPane grid){
        //Application Relevant layout Dimensions
        grid.setHgap(10);
        grid.setVgap(1);
        grid.setPadding(new Insets(1,1,1,1));
        grid.setPrefWidth(250);
        grid.setPrefHeight(100);
        grid.add(select_day_text,0,0);
        grid.add(priorities,0,1);
    }

    //PROVIDED METHODS ############################################################################################################

    // grid
    public GridPane getGrid(){
        return grid;
    }
    // Choicebox
    public String getValue(){
        return (String) priorities.getValue();
    }

    //LISTENERS
    // Choicebox update
    public void setonAction(EventHandler ev){
        priorities.setOnAction(ev);
    }
}