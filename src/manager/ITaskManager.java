package Task_Tracker.src.manager;

import java.util.ArrayList;
import java.util.List;

import Task_Tracker.src.type_task.Epic;
import Task_Tracker.src.type_task.SubTask;
import Task_Tracker.src.type_task.Task;

public interface ITaskManager {

    void addEpic(Epic epic);
    void addSubTask(SubTask subTask);
    void addTask(Task task);

    ArrayList<Epic> getEpics();
    ArrayList<SubTask> getSubTask();
    ArrayList<Task> getTask();

    void clearTasks();
    void clearEpics();
    void clearSubTasks();

    Epic getByIdEpic (int id);
    SubTask getByIdSubTask (int id);
    Task getIdTask (int id);

    void updateEpicById (Epic epic, int id);
    void upateSubTaskById (SubTask subTask, int id);
    void upateTaskById (Task task, int id);

    void removeEpicById (int id);
    void removeSubTaskById(int id);

    List<SubTask> getSubTasksFromEpic(int epicId);

    void updateEpicStatus (Epic epic);

    List<Task> getHistory();
}
