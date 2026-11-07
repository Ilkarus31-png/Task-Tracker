package Task_Tracker.src.type_task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private final List<Integer> subTaskIds = new ArrayList<>();

    public Epic(String name, String sipattama, int id) {
        super(name, sipattama, id);
    }

    public List<Integer> getSubTaskIds () {
        return new ArrayList<>(subTaskIds);
    }

    public void addSubTaskId(int subTaskId) {
        subTaskIds.add(subTaskId);
    }

    public void removeSubTaskId(int subTaskId) {
        subTaskIds.remove(Integer.valueOf(subTaskId));
    }
}
