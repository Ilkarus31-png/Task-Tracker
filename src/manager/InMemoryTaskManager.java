package Task_Tracker.src.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Task_Tracker.src.type_task.Epic;
import Task_Tracker.src.type_task.Status;
import Task_Tracker.src.type_task.SubTask;
import Task_Tracker.src.type_task.Task;

public class InMemoryTaskManager implements ITaskManager{
    private int id = 1;
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();

    private final HistoryManager historyManager;

    public InMemoryTaskManager (HistoryManager historyImplementation) {
        this.historyManager = historyImplementation;
    }

    @Override
    public void addEpic(Epic epic) {
        if (epic == null) return;

        epic.setId(id);
        epics.put(id, epic);
        updateEpicStatus(epic);

        id++;
    }
    @Override
    public void addSubTask(SubTask subTask) {
        if (subTask == null) return;

        Epic epic = epics.get(subTask.getEpicId());

        if (epic == null) { return; }

        subTask.setId(id);
        subTasks.put(id, subTask);
        epic.addSubTaskId(subTask.getId());
        updateEpicStatus(epic);

        id++;
    }
    @Override
    public void addTask(Task task) {

        if (task == null) return;

        task.setId(id);
        tasks.put(id, task);
        id++;
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }
    @Override
    public ArrayList<SubTask> getSubTask() {
        return new ArrayList<>(subTasks.values());
    }
    @Override
    public ArrayList<Task> getTask() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void clearTasks () {
        tasks.clear();
    }
    @Override
    public void clearEpics () {
        epics.clear();
        subTasks.clear();
    }
    @Override
    public void clearSubTasks () {
        subTasks.clear();

        for (Epic epic : epics.values()) {

            for (int subId : epic.getSubTaskIds()) {
                epic.removeSubTaskId(subId);
            }

            updateEpicStatus(epic);
        }
    }

    @Override
    public Epic getByIdEpic (int id) {
        Epic epic = epics.get(id);

        if (epic != null) {
            historyManager.addTask(epic);
        }
        return epic;
    }
    @Override
    public SubTask getByIdSubTask (int id) {
        SubTask subTask = subTasks.get(id);

        if (subTask != null) {
            historyManager.addTask(subTask);
        }

        return subTask;
    }
    @Override
    public Task getIdTask (int id) {
        Task task = tasks.get(id);

        if (task != null) {
            historyManager.addTask(task);
        }

        return task;
    }

    @Override
    public void updateEpicById (Epic epic, int id) {
        if (epic == null) return;

        if (!epics.containsKey(id)) return;

        epics.put(id, epic);
        updateEpicStatus(epic);
    }
    @Override
    public void upateSubTaskById (SubTask subTask, int id) {
        if (subTask == null) return;

        if (!subTasks.containsKey(id)) return;

        subTasks.put(id, subTask);
        Epic epic = epics.get(subTask.getEpicId());

        if (epic != null) {
            updateEpicStatus(epic);
        }
    }
    @Override
    public void upateTaskById (Task task, int id) {

        if (task == null) return;

        if (!tasks.containsKey(id)) return;

        tasks.put(id, task);
    }

    @Override
    public void removeEpicById (int id) {
        Epic epic = epics.remove(id);

        if (epic == null) { return; }

        historyManager.remove(id);

        for (int subId : epic.getSubTaskIds()) {
            subTasks.remove(subId);
            historyManager.remove(subId);
        }
    }
    @Override
    public void removeSubTaskById(int id) {
        SubTask subTask = subTasks.remove(id);

        if (subTask == null) { return; }

        historyManager.remove(id);

        Epic epic = epics.get(subTask.getEpicId());

        if (epic != null) {
            epic.removeSubTaskId(id);
            updateEpicStatus(epic);
        }
    }

    @Override
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

    @Override
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

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}