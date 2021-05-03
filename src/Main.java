import CommObjects.Scenes.ScenesEnum;
import UIManager.UIManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import TaskScheduler.TaskScheduler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Create Scheduler
        TaskScheduler mainScheduler = new TaskScheduler();

        //Main Scenes
        FXMLLoader calendar_page_loader = new FXMLLoader(getClass().getResource("Resources/Calendar.fxml"));
        //Features Scenes
        FXMLLoader chill_page_loader = new FXMLLoader(getClass().getResource("Resources/Main.fxml"));

        //Create UI Manager
        UIManager uiManager = new UIManager(mainScheduler);
        //Manager Subscriptions to UI
        uiManager.loader_subscription(calendar_page_loader, ScenesEnum.CalendarPageScene);
        uiManager.loader_subscription(chill_page_loader, ScenesEnum.MainPageScene);
        uiManager.stage_subscription(primaryStage);

        //Manager Start Application
        uiManager.start_app();


    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
