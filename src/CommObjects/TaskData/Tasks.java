package CommObjects.TaskData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tasks implements Iterable{

    private List<Task> Tasks;

    public Tasks(){
        Tasks = new ArrayList<Task>();
    }

    public void add_task(Task task){
        Tasks.add(task);
    }

    public void remove(int index){
        Tasks.remove(index);
    }

    public void clear(){
        Tasks.clear();
    }

    public void print(){
        Iterator<Task> iter = Tasks.iterator();
        while(iter.hasNext()){
            Task task = iter.next();
            task.print();
        }
    }

    public boolean isEmpty(){
        return Tasks.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return Tasks.iterator();
    }
}
