package UIManager;

import ApplicationPages.Controllers;
import CommObjects.Scenes.ScenesEnum;
import CommObjects.TaskData.Tasks;
import TaskScheduler.TaskScheduler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIManager {
    private ScenesEnum current_scene;
    public Tasks task_data_arr;
    public TaskScheduler scheduler;

    Stage main_stage;

    FXMLLoader[] loaders;
    Scene[] scenes;
    Controllers[] controllers;

    
    public UIManager(TaskScheduler current_scheduler) throws Exception{
        scheduler = current_scheduler;
        loaders = new FXMLLoader[ScenesEnum.values().length];
        scenes = new Scene[ScenesEnum.values().length];
        controllers = new Controllers[ScenesEnum.values().length];
    }

    public void setScene(ScenesEnum newScene){
        current_scene = newScene;
        updateScene(current_scene);
    }

    private void updateScene(ScenesEnum newScene){
        switch (newScene) {
            case MainPageScene:
                main_stage.setScene(scenes[ScenesEnum.MainPageScene.ordinal()]);
                break;
            case CalendarPageScene:
                generate_scheduler();
                main_stage.setScene(scenes[ScenesEnum.CalendarPageScene.ordinal()]);
                break;
        }
    }

    public void loader_subscription(FXMLLoader loader, ScenesEnum scene) throws Exception{
        Parent loader_root = loader.load();
        loaders[scene.ordinal()] = loader;
        scenes[scene.ordinal()] = new Scene(loader_root);
        controllers[scene.ordinal()] = loader.getController();
        controllers[scene.ordinal()].set_manager(this);
        
    }

    public void stage_subscription(Stage stage){
        main_stage = stage;
        main_stage.setResizable(false);
    }

    public void start_app(){
        main_stage.setTitle("Your Day Worth It");
        main_stage.setScene(scenes[ScenesEnum.MainPageScene.ordinal()]);
        main_stage.show();
    }

    public void update_tasks(Tasks tasks){
        task_data_arr = tasks;
    }

    private void generate_scheduler(){
        scheduler.update(task_data_arr,this);
    }

    public void calendar_obj_ready(Object schedule){
        controllers[ScenesEnum.CalendarPageScene.ordinal()].update_scene_data(schedule);
    }


}
