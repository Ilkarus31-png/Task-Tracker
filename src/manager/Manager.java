package Task_Tracker.src.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Task_Tracker.src.type_task.Epic;
import Task_Tracker.src.type_task.Status;
import Task_Tracker.src.type_task.SubTask;
import Task_Tracker.src.type_task.Task;

public class Manager{
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();


    public void addEpic(Epic epic) {
        if (epic == null) return;
        epics.put(epic.getId(), epic);
        updateEpicStatus(epic);
    }
    public void addSubTask(SubTask subTask) {
        if (subTask == null) return;

        Epic epic = epics.get(subTask.getEpicId());

        if (epic == null) { return ;}

        subTasks.put(subTask.getId(), subTask);
        epic.addSubTaskId(subTask.getId());
        updateEpicStatus(epic);
    }
    public void addTask(Task task) {

        if (task == null) return;

        tasks.put(task.getId(), task);
    }


    public HashMap<Integer, Epic> getEpics() {
        return new HashMap<>(epics);
    }
    public HashMap<Integer, SubTask> getSubTask() {
        return new HashMap<>(subTasks);
    }
    public HashMap<Integer, Task> getTask() {
        return new HashMap<>(tasks);
    }


    public void clearTasks () {
        tasks.clear();
    }
    public void clearEpics () {
        epics.clear();
        subTasks.clear();
    }
    public void clearSubTasks () {
        subTasks.clear();

        for (Epic epic : epics.values()) {

            for (int subId : epic.getSubTaskIds()) {
                epic.removeSubTaskId(subId);
            }

            updateEpicStatus(epic);
        }
    }


    public Epic getByIdEpic (int id) {
        return epics.get(id);
    }
    public SubTask getByIdSubTask (int id) {
        return subTasks.get(id);
    }
    public Task getIdTask (int id) {
        return tasks.get(id);
    }

    public void updateEpicById (Epic epic, int id) {
        if (epic == null) return;

        if (!epics.containsKey(id)) return;

        epics.put(id, epic);
        updateEpicStatus(epic);
    }
    public void upateSubTaskById (SubTask subTask, int id) {
        if (subTask == null) return;

        if (!subTasks.containsKey(id)) return;

        subTasks.put(id, subTask); //Тут просто по приколу комментарий оставлю
        Epic epic = epics.get(subTask.getEpicId()); //Ибо не фиг короче

        if (epic != null) {
            updateEpicStatus(epic);
        }
    }
    public void upateTaskById (Task task, int id) {

        if (task == null) return;

        if (!tasks.containsKey(id)) return;

        tasks.put(id, task);
    }

    public void removeEpicById (int id) {
        Epic epic = epics.remove(id);

        if (epic == null) { return; }

        for (int subId : epic.getSubTaskIds()) {
            subTasks.remove(subId);
        }
    }
    public void removeSubTaskById(int id) {
        SubTask subTask = subTasks.remove(id);

        if (subTask == null) { return; }

        Epic epic = epics.get(subTask.getEpicId());

        if (epic != null) {
            epic.removeSubTaskId(id);
            updateEpicStatus(epic);
        }
    }

    public List<SubTask> getSubTasksFromEpic(int epicId) {
        Epic epic = epics.get(epicId);

        if (epic == null) {
            return new ArrayList<>();
        }

        List<SubTask> result = new ArrayList<>();

        for (int subId : epic.getSubTaskIds()) {
            SubTask subTask = subTasks.get(subId);

            if (subTask != null) {
                result.add(subTask);
            }
        }

        return result;
    }

    public void updateEpicStatus (Epic epic) {

        if (epic == null) return;

        List<Integer> subIds = epic.getSubTaskIds();

        if (subIds.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allNew = true;
        boolean allDone = true;

        for (int subId : subIds) {
            SubTask subTask = subTasks.get(subId);

            if (subTask == null) {
                allNew = false;
                allDone = false;
                continue;
            }

            if (subTask.getStatus() != Status.NEW) {
                allNew = false;
            }

            if (subTask.getStatus() != Status.DONE) {
                allDone = false;
            }
        }

        if(allNew) {
            epic.setStatus(Status.NEW);
        }

        else if (allDone) {
            epic.setStatus(Status.DONE);
        }
        
        else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}