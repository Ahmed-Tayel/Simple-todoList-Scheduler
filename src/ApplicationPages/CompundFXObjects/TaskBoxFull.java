package ApplicationPages.CompundFXObjects;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

import java.util.*;

public class TaskBoxFull {
    TaskTimeSpecs task_time_specs;
    SelectDayChoiceBox select_day_choice_box;
    PrioritiesChoiceBox selectPriority;
    GridPane mainGrid;
    GridPane dayAndPriorityGrid;
    RowConstraints row1;
    RowConstraints row2;
    ColumnConstraints col1;
    ColumnConstraints col2;

    public TaskBoxFull(){
        task_time_specs = new TaskTimeSpecs();
        select_day_choice_box = new SelectDayChoiceBox();
        selectPriority = new PrioritiesChoiceBox();


        row1 = new RowConstraints();
        row1.setPercentHeight(30);

        row2 = new RowConstraints();
        row2.setPercentHeight(70);

        col1 = new ColumnConstraints();
        col1.setPercentWidth(25);

        col2 = new ColumnConstraints();
        col2.setPercentWidth(75);

        dayAndPriorityGrid = new GridPane();
        dayAndPriorityGrid.setVgap(0);
        dayAndPriorityGrid.getRowConstraints().addAll(row1, row2);
        dayAndPriorityGrid.add(select_day_choice_box.getGrid(),0,0);
        dayAndPriorityGrid.add(selectPriority.getGrid(),0,1);

        init_choice_box();

        mainGrid = new GridPane();
        init_grid(mainGrid);
    }

    //INITIALIZATION ############################################################################################################

    private void init_grid(GridPane grid){
        //Application Relevant layout Dimensions
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(1,1,1,1));
        grid.setPrefWidth(650);
        grid.setPrefHeight(150);
        grid.getColumnConstraints().addAll(col1, col2);
        grid.add(dayAndPriorityGrid,0,0);
        mainGrid.add(task_time_specs.getGrid(),1,0);
    }

    private void init_choice_box(){
        select_day_choice_box.setonAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                selectDay_pressed();
            }
        });
    }

    //CALL BACKS ############################################################################################################

    private void selectDay_pressed() {
        mainGrid.getChildren().remove(dayAndPriorityGrid);
        mainGrid.getChildren().remove(task_time_specs.getGrid());

        mainGrid.add(dayAndPriorityGrid,0,0);
        mainGrid.add(task_time_specs.getGrid(),1,0);


    }

    //PROVIDED METHODS ############################################################################################################

    public Dictionary check_inputs_and_read_data(){
        if(task_time_specs.check_inputs_and_read_data() != null){
            Dictionary retVal = task_time_specs.check_inputs_and_read_data();
            retVal.put("Day", select_day_choice_box.getValue());
            retVal.put("Priority", selectPriority.getValue());
            return retVal;
        }
        else{
            return  null;
        }
    }

    public void clear_all_data(){
        task_time_specs.clear_all_data();
        select_day_choice_box.clear_data();
    }

    public void set_default_name(String defaultName){
        task_time_specs.set_default_name(defaultName);
    }

    public GridPane getMainGrid(){
        return mainGrid;
    }
}
