package ApplicationPages.CompundFXObjects;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class SelectDaysRoutine {
    RadioButton everyday;
    RadioButton customized;
    ToggleGroup radioGroup;
    Label selectdayLabel;
    public GridPane grid;

    public SelectDaysRoutine(){
        radioGroup = new ToggleGroup();
        everyday = new RadioButton("Everyday");
        everyday.setToggleGroup(radioGroup);
        customized = new RadioButton("Customized");
        customized.setToggleGroup(radioGroup);

        selectdayLabel = new Label("Select your Daily Routine");
        grid = new GridPane();

        init_grid(grid);
    }

    //INITIALIZATIONS ############################################################################################################
    private void init_grid(GridPane grid){
        //Application Relevant layout Dimensions
        grid.setHgap(10);
        grid.setVgap(1);
        grid.setPadding(new Insets(1,1,1,1));
        grid.setPrefWidth(450);
        grid.setPrefHeight(100);
        grid.add(selectdayLabel,0,0);
        grid.add(everyday,1,0);
        grid.add(customized,2,0);
    }


    //PROVIDED METHODS ############################################################################################################
    public GridPane getGrid(){
        return grid;
    }

    public String getValRadiobuttonPressed(){
        return ((RadioButton) radioGroup.getSelectedToggle()).getText();
    }

    public void radioButtonGroup_SetonAction(ChangeListener ch){
        radioGroup.selectedToggleProperty().addListener(ch);
    }
    
}
