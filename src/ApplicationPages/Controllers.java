package ApplicationPages;

import CommObjects.Scenes.ScenesEnum;
import CommObjects.TaskData.Task;
import CommObjects.TaskData.Tasks;
import CommObjects.TaskData.daysEnum;
import UIManager.UIManager;
import javafx.fxml.FXML;

import java.util.Dictionary;
import java.util.Enumeration;

public abstract class Controllers {
    public UIManager manager;

    @FXML
    public void initialize(){}

    protected void sceneChange(ScenesEnum newScene){
        manager.setScene(newScene);
    }
    public abstract void update_scene_data(Object obj);
    public void set_manager(UIManager manager){
        this.manager = manager;
    }

    protected void send_data(Tasks tasks){
        manager.update_tasks(tasks);
    }

    protected Task update_UI_data_to_task(Dictionary UIData) {
        Task task = new Task();
        String currentElement = new String();

        for (Enumeration k = UIData.keys(); k.hasMoreElements();)
        {
            currentElement = (String) k.nextElement();
            switch (currentElement){
                case "Day":
                    task.day = updateUIDays((String) UIData.get(currentElement));
                    break;

                case "Priority":
                    task.priority = updateUIPriorities((String) UIData.get(currentElement));
                    break;

                case "SlotName":
                    task.name = (String) UIData.get(currentElement);
                    break;

                case "From":
                    task.start = (Integer) UIData.get(currentElement);
                    break;

                case "To":
                    task.end = (Integer) UIData.get(currentElement);
                    break;

                case "Hours":
                    task.hours = (Integer) UIData.get(currentElement);
                    break;

                default: break;
            }
        }
        return task;
    }

    protected int updateUIPriorities(String priority) {
        switch (priority){
            case "Top":
                return 1;

            case "Medium":
                return 2;

            case "Low":
                return 3;

            default:
                return 0;
        }
    }

    protected daysEnum updateUIDays(String day){
        switch (day){
            case "Everyday":
                return daysEnum.EVERY;

            case "Saturday":
                return daysEnum.SAT;

            case "Monday":
                return daysEnum.MON;

            case "Tuesday":
                return daysEnum.TUES;

            case "Wednesday":
                return daysEnum.WED;

            case "Thursday":
                return daysEnum.THURS;

            case "Friday":
                return daysEnum.FRI;

            case "Sunday":
                return daysEnum.SUN;

            default:
                return null;
        }
    }

}
