package CommObjects.Schedule;

import CommObjects.TaskData.Task;
import CommObjects.TaskData.Tasks;
import CommObjects.TaskData.daysEnum;

import java.util.Iterator;

public class Schedule {
    private Tasks[] day_tasks;


    public Schedule(){
        day_tasks = new Tasks[daysEnum.values().length];
        for (int index=0; index < daysEnum.values().length; index ++){
            day_tasks[index] = new Tasks();
        }
    }

    public void add_task(daysEnum day, Task task){
        day_tasks[day.ordinal()].add_task(task);
    }

    public void add_task(int dayIndex, Task task){
        day_tasks[dayIndex].add_task(task);
    }

    public void add_day_tasks(daysEnum day, Tasks tasks){
        day_tasks[day.ordinal()].clear();
        Iterator<Task> taskIter = tasks.iterator();
        while (taskIter.hasNext()){
            day_tasks[day.ordinal()].add_task(taskIter.next());
        }
    }

    public Tasks get_day_tasks(daysEnum day){
        return day_tasks[day.ordinal()];
    }

    public void remove_day_task(daysEnum day, int index){
        day_tasks[day.ordinal()].remove(index);
    }

    public void clear_day_tasks(daysEnum day){
        day_tasks[day.ordinal()].clear();
    }

    public void print(){
        System.out.println("Printing Schedule Start ...");
        for (int index=0; index < daysEnum.values().length; index ++){
            day_tasks[index].print();
        }
        System.out.println("Printing Schedule End ...");
    }

    public boolean isEmpty(){
        boolean result = true;
        for (int index=0; index < daysEnum.values().length; index ++){
            if(day_tasks[index].iterator().hasNext()){
                result = false;
                break;
            }
        }
        return result;
    }
}
