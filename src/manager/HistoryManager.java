package Task_Tracker.src.manager;

import java.util.List;

import Task_Tracker.src.type_task.Task;

public interface HistoryManager {
    void addTask(Task task);
    List<Task> getHistory();
    void remove(int id);
}
