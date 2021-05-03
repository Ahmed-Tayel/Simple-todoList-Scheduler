package ApplicationPages;

import CommObjects.Scenes.ScenesEnum;
import CommObjects.Schedule.Schedule;

import CommObjects.TaskData.Task;
import CommObjects.TaskData.Tasks;
import CommObjects.TaskData.daysEnum;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.util.Iterator;

public class CalendarPageController extends Controllers {

    public Schedule schedule;

    @FXML
    public VBox vbox_schedule;
    @FXML
    public HBox hbox_saturday;
    @FXML
    public HBox hbox_sunday;
    @FXML
    public HBox hbox_monday;
    @FXML
    public HBox hbox_tuesday;
    @FXML
    public HBox hbox_wednesday;
    @FXML
    public HBox hbox_thrusday;
    @FXML
    public HBox hbox_friday;

    public HBox[] hbox_days;
    public Task task;
    public Tasks tasks;
    public Iterator<Task> tasks_iter;


    @FXML
    public void initialize(){
        hbox_days = get_hbox_days_array();
    }

    @FXML
    public void back_button_pressed(ActionEvent evt){
        this.sceneChange(ScenesEnum.MainPageScene);
    }

    public void update_scene_data(Object posterior_schedule) {
        schedule = (Schedule) posterior_schedule;
        clear_hboxes(hbox_days);
        for (daysEnum day: daysEnum.values()){
            tasks = schedule.get_day_tasks(day);
            tasks_iter = tasks.iterator();
            while(tasks_iter.hasNext()){
                task = tasks_iter.next();
                allocate_task_to_hbox(hbox_days[day.ordinal()],task);
            }
        }

    }

    private void allocate_task_to_hbox(HBox hbox, Task task){
        String feature = task.name;
        String start_time = get_output_time(task.start);
        String end_time = get_output_time(task.end);

        GridPane grid_pane = new GridPane();
        Text todo = new Text(feature);
        Text from = new Text(start_time);
        Text to = new Text(end_time);
        grid_pane_init(grid_pane,todo,from,to);
        hbox.getChildren().addAll(grid_pane);
    }

    private HBox[] get_hbox_days_array(){
        hbox_days = new HBox[daysEnum.values().length - 1];
        hbox_days[daysEnum.SAT.ordinal()] = hbox_saturday;
        hbox_days[daysEnum.SUN.ordinal()] = hbox_sunday;
        hbox_days[daysEnum.MON.ordinal()] = hbox_monday;
        hbox_days[daysEnum.TUES.ordinal()] = hbox_tuesday;
        hbox_days[daysEnum.WED.ordinal()] = hbox_wednesday;
        hbox_days[daysEnum.THURS.ordinal()] = hbox_thrusday;
        hbox_days[daysEnum.FRI.ordinal()] = hbox_friday;
        for (int index=0; index<7; index++){
            hbox_days[index].setSpacing(40);
        }
        return hbox_days;
    }

    private String get_output_time(int task_time){
        String retVal;
        String time_period;
        if (task_time == 24){
            time_period = "AM";
            task_time = 00;
        }
        else if (task_time == 12){
            time_period = "PM";
        }
        else if (task_time > 12){
            time_period = "PM";
            task_time = task_time - 12;
        }
        else {time_period = "AM";}
        retVal = String.valueOf(task_time);
        retVal += " " + time_period;
        return retVal;
    }

    private void grid_pane_init(GridPane gridPane, Text TODO, Text FROM, Text TO){
        Text todoLabel =  new Text("To do: ");
        Text fromLabel =  new Text("From: ");
        Text toLabel =  new Text("To: ");

        style_cal_label(TODO);
        style_cal_label(FROM);
        style_cal_label(TO);
        style_cal_label(todoLabel);
        style_cal_label(fromLabel);
        style_cal_label(toLabel);

        //Application Relevant layout Dimensions
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setPadding(new Insets(1,1,1,1));
        gridPane.setPrefWidth(100);
        gridPane.setPrefHeight(25);

        gridPane.add(todoLabel,0,0);
        gridPane.add(TODO,1,0);
        gridPane.add(fromLabel,0,1);
        gridPane.add(FROM,1,1);
        gridPane.add(toLabel,0,2);
        gridPane.add(TO,1,2);
    }

    private void style_cal_label(Text textlabel){
        textlabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.SEMI_BOLD, 18));
        textlabel.setFill(Color.BLACK);
        //textlabel.setStroke(Color.GREEN);
    }

    private void clear_hboxes(HBox[] hboxes){
        for(int index=0; index < daysEnum.values().length - 1; index++){
            hboxes[index].getChildren().clear();
        }
    }


}
