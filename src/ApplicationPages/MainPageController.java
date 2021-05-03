package ApplicationPages;

import ApplicationPages.CompundFXObjects.*;
import CommObjects.Scenes.ScenesEnum;
import CommObjects.TaskData.Tasks;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.*;

public class MainPageController extends Controllers {
    List<TaskBoxFull> day_and_time_list_custom;

    Button add_task_routine_button;
    Button remove_task_routine_button;

    ScrollPane daily_routines_pane;
    HBox hbox_second_subscene;
    VBox vbox_third_subscene;

    @FXML
    VBox vbox_main;

    @FXML
    public void initialize() {

        day_and_time_list_custom = new ArrayList<TaskBoxFull>();

        add_task_routine_button = new Button("Add Task +");
        remove_task_routine_button = new Button("Remove Last Task -");

        daily_routines_pane = new ScrollPane();

        hbox_second_subscene= new HBox();
        vbox_third_subscene = new VBox();

        initializeAddDailyRoutineButton(add_task_routine_button);
        initializeRemoveDailyRoutineButton(remove_task_routine_button);
        initializeScrollPane(daily_routines_pane);
        initializeVboxMain(vbox_main);
        initializeVboxSlots(vbox_third_subscene);
        initializeHboxSceneTwo(hbox_second_subscene);

        vbox_main.getChildren().addAll(hbox_second_subscene);
        daily_routines_pane.setContent(vbox_third_subscene);
        vbox_main.getChildren().addAll(daily_routines_pane);

        first_subscene_update();

    }


    //  INITIALIZATIONS ############################################################################################################

    private void initializeVboxMain(VBox vbox_main) {
        vbox_main.setMaxHeight(400);
    }

    private void initializeVboxSlots(VBox vbox_third_subscene) {
        vbox_third_subscene.setPrefWidth(645);
        vbox_third_subscene.setSpacing(0);
    }

    private void initializeHboxSceneTwo(HBox hbox_second_subscene) {
        hbox_second_subscene.setMaxHeight(100);
        hbox_second_subscene.setMinHeight(80);
        hbox_second_subscene.setSpacing(15);
    }

    private void initializeScrollPane(ScrollPane daily_routines_pane) {
        daily_routines_pane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        daily_routines_pane.setMaxHeight(320);
        daily_routines_pane.setMinHeight(200);
        daily_routines_pane.setPrefWidth(645);
    }

    private void initializeAddDailyRoutineButton(Button add_daily_routine) {
        add_daily_routine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addDailyRoutine_button_pressed();
            }
        });
    }

    private void initializeRemoveDailyRoutineButton(Button remove_daily_routine_button) {
        remove_daily_routine_button.setVisible(false);
        remove_daily_routine_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeDailyRoutine_button_pressed();
            }
        });
    }

    //  SUBSCENES UPDATE ############################################################################################################

    private void first_subscene_update() {
        hbox_second_subscene.getChildren().clear();
        hbox_second_subscene.getChildren().addAll(add_task_routine_button);
        hbox_second_subscene.getChildren().addAll(remove_task_routine_button);
    }

    private void second_subscene_update(){
        Iterator<TaskBoxFull> day_and_time_iter = day_and_time_list_custom.iterator();
        vbox_third_subscene.getChildren().clear();

        while(day_and_time_iter.hasNext()){
            vbox_third_subscene.getChildren().add(day_and_time_iter.next().getMainGrid());
        }
    }

    // CALLBACKS ############################################################################################################

    public void addDailyRoutine_button_pressed(){
        remove_task_routine_button.setVisible(true);
        TaskBoxFull day_and_time = new TaskBoxFull();
        day_and_time_list_custom.add(day_and_time);
        second_subscene_update();
    }

    private void removeDailyRoutine_button_pressed() {
        if(day_and_time_list_custom.size() != 0){
            int last_element_index = day_and_time_list_custom.size() - 1;
            day_and_time_list_custom.remove(last_element_index);
            if (day_and_time_list_custom.size() == 0){
                remove_task_routine_button.setVisible(false);
            }
            second_subscene_update();
        }
    }

    @FXML
    public void generateSchedule_pressed(ActionEvent evt){
        Tasks tasks = new Tasks();
        boolean data_is_healthy = true;
        Iterator<TaskBoxFull> iter = day_and_time_list_custom.iterator();
        while (iter.hasNext()){
            Dictionary returnVal = iter.next().check_inputs_and_read_data();
            if (returnVal == null){
                data_is_healthy = false;
                tasks.clear();
                break;
            }
            else {
                tasks.add_task(update_UI_data_to_task(returnVal));
            }
        }
        if(data_is_healthy){
            send_data(tasks);
            this.sceneChange(ScenesEnum.CalendarPageScene);
        }
    }

    @FXML
    public void resetButton_pressed(ActionEvent evt){
        Iterator<TaskBoxFull> iter = day_and_time_list_custom.iterator();
        while (iter.hasNext()){
            iter.next().clear_all_data();
        }
    }

    //PROVIDED METHODS ############################################################################################################
    @Override
    public void update_scene_data(Object scene_data) {

    }

    //PRIVATE FUNCTIONS ############################################################################################################
    private void remove_items_list(){
        day_and_time_list_custom.clear();
        remove_task_routine_button.setVisible(false);
    }

}
