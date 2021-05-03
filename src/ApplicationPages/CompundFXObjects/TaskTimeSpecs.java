package ApplicationPages.CompundFXObjects;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.*;


public class TaskTimeSpecs {
    public RadioButton from_to;
    public RadioButton hours_per_day;

    public String radiobutton_selected = null;

    public ToggleGroup radioGroup;

    public Label from_label;
    public TextField from_input;
    public Label to_label;
    public TextField to_input;
    public Label hours_label;
    public TextField hours_input;
    public Label taskNameLabel;
    public TextField   taskName;

    public Label error_msg_taskName;
    public Label error_msg_from;
    public Label error_msg_to;
    public Label error_msg_hours;

    public GridPane grid;

    public TaskTimeSpecs(){
        radioGroup = new ToggleGroup();
        from_to = new RadioButton("From/To");
        from_to.setToggleGroup(radioGroup);
        hours_per_day = new RadioButton("Hours/day");
        hours_per_day.setToggleGroup(radioGroup);
        init_radio_buttons();

        from_input = new TextField();
        to_input = new TextField();
        hours_input = new TextField();
        taskName = new TextField();
        init_textfield(from_input);
        init_textfield(to_input);
        init_textfield(hours_input);
        init_textfield(taskName);

        from_label = new Label("FROM");
        to_label = new Label("TO");
        hours_label = new Label("HOURS");
        taskNameLabel = new Label("Slot Name");
        init_label(from_label);
        init_label(to_label);
        init_label(hours_label);
        init_label(taskNameLabel);

        error_msg_taskName = new Label();
        error_msg_from = new Label();
        error_msg_to = new Label();
        error_msg_hours = new Label();
        init_error_msg(error_msg_taskName);
        init_error_msg(error_msg_from);
        init_error_msg(error_msg_to);
        init_error_msg(error_msg_hours);

        grid = new GridPane();
        from_to.fire();
        init_grid(grid);
        update_grid(grid,from_to.getText());

    }



    //INITIALIZATION ############################################################################################################
    private void init_label(Label label) {
        label.setStyle("-fx-background: #FFFFFF;");
    }

    private void init_textfield(TextField text) {
        text.setStyle("-fx-background: #FFFFFF;");
    }

    private void init_radio_buttons(){
        from_to.setStyle("-fx-background: #FFFFFF;");
        hours_per_day.setStyle("-fx-background: #FFFFFF;");
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton) radioGroup.getSelectedToggle();

                if (rb != null) {
                    radiobutton_selected = rb.getText();
                    update_grid(grid,radiobutton_selected);
                }
            }
        });
    }

    private void init_error_msg(Label error_msg) {
        error_msg.setVisible(false);
        error_msg.setStyle("-fx-background: #FF0000;");
        error_msg.setTextFill(Color.RED);
    }

    private void init_grid(GridPane grid){
        //Application Relevant layout Dimensions
        grid.setHgap(10);
        grid.setVgap(1);
        grid.setPadding(new Insets(1,1,1,1));
        grid.setPrefWidth(500);
        grid.setPrefHeight(100);
        grid.add(taskNameLabel,0,0);
        grid.add(taskName,1,0);
        grid.add(error_msg_taskName,2,0);
        grid.add(from_to,0,1);
        grid.add(hours_per_day,1,1);
    }

    private void update_grid(GridPane grid, String radiobutton_val){

        grid.getChildren().remove(from_label);
        grid.getChildren().remove(from_input);
        grid.getChildren().remove(to_label);
        grid.getChildren().remove(to_input);
        grid.getChildren().remove(hours_label);
        grid.getChildren().remove(hours_input);
        grid.getChildren().remove(error_msg_from);
        grid.getChildren().remove(error_msg_to);
        grid.getChildren().remove(error_msg_hours);


        if (radiobutton_val == "From/To"){
            grid.add(from_label,0,2);
            grid.add(from_input,1,2);
            grid.add(error_msg_from,2,2);
            grid.add(to_label,0,3);
            grid.add(to_input,1,3);
            grid.add(error_msg_to,2,3);
        }

        else {
            grid.add(hours_label,0,2);
            grid.add(hours_input,1,2);
            grid.add(error_msg_hours,2,2);
            //grid.add(new Text("TO"),0,2);
            //grid.add(to_input,1,2);
        }
    }

    //PROVIDED METHODS ############################################################################################################
    public Dictionary check_inputs_and_read_data(){
        Dictionary retList = new Hashtable();

        if (check_user_input_name(taskName, error_msg_taskName)){
            retList.put("SlotName",taskName.getText());
        }
        else {
            return null;
        }
        
        if ((radiobutton_selected == "From/To") && check_user_input_numeric(from_input, error_msg_from) && check_user_input_numeric(to_input, error_msg_to)){
            retList.put("From",Integer.parseInt(from_input.getText()));
            retList.put("To",Integer.parseInt(to_input.getText()));
            retList.put("Hours",0);
            return retList;
            }
        else if (check_user_input_numeric(hours_input, error_msg_hours)){
            retList.put("Hours",Integer.parseInt(hours_input.getText()));
            retList.put("From",0);
            retList.put("To",0);
            return retList;
        }
        else{
            return null;
        }
    }

    public GridPane getGrid(){
        return grid;
    }

    public void clear_all_data(){
        from_input.clear();
        to_input.clear();
        hours_input.clear();
        taskName.clear();
    }

    public void set_default_name(String defaultName){
        taskName.setText(defaultName);
    }
    //PRIVATE METHODS ############################################################################################################

    private boolean check_user_input_numeric(TextField text, Label err_msg){
        boolean retVal = true;
        try
        {
            int result = Integer.parseInt(text.getText());
            if (result > 24 || result < 0){
                err_msg.setText("Required 24 hour format");
                err_msg.setVisible(true);
                retVal = false;
            }
            else{
                err_msg.setVisible(false);
            }
        }
        catch (NumberFormatException e)
        {
            err_msg.setText("Required an Integer number");
            err_msg.setVisible(true);
            retVal = false;
        }
        return retVal;
    }

    private boolean check_user_input_name(TextField text, Label err_msg){
        boolean retVal = true;

        String result = text.getText();

        if (result.equals("")){
            err_msg.setText("Required a Name");
            err_msg.setVisible(true);
            retVal = false;
        }
        else {
            err_msg.setVisible(false);
        }
        return retVal;
    }

}
