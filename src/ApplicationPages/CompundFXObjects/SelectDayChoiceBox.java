package ApplicationPages.CompundFXObjects;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class SelectDayChoiceBox {
    Label select_day_text;
    ChoiceBox days;
    public GridPane grid;

    public SelectDayChoiceBox(){
        select_day_text = new Label("Select Day");
        select_day_text.setStyle("-fx-background: #FFFFFF;");

        days = new ChoiceBox();
        days.getItems().add("Everyday");
        days.getItems().add("Saturday");
        days.getItems().add("Sunday");
        days.getItems().add("Monday");
        days.getItems().add("Tuesday");
        days.getItems().add("Wednesday");
        days.getItems().add("Thursday");
        days.getItems().add("Friday");

        days.setValue("Everyday");
        
        days.setStyle("-fx-background: #FFFFFF;");

        grid = new GridPane();
        init_grid(grid);

    }

    //INITIALIZATION ############################################################################################################

    private void init_grid(GridPane grid){
        //Application Relevant layout Dimensions
        grid.setHgap(10);
        grid.setVgap(1);
        grid.setPadding(new Insets(1,1,1,1));
        grid.setPrefWidth(150);
        grid.setPrefHeight(100);
        grid.add(select_day_text,0,0);
        grid.add(days,0,1);
    }

    //PROVIDED METHODS ############################################################################################################

    public GridPane getGrid(){
        return grid;
    }

    public String getValue(){
        return (String) days.getValue();
    }

    public void setonAction(EventHandler ev){
        days.setOnAction(ev);
    }

    public void clear_data(){
        days.hide();
    }



}
