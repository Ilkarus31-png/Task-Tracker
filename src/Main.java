package Task_Tracker.src;

import Task_Tracker.src.manager.Manager;
import Task_Tracker.src.type_task.Epic;
import Task_Tracker.src.type_task.Status;
import Task_Tracker.src.type_task.SubTask;
import Task_Tracker.src.type_task.Task;

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();

        System.out.println("Создал обычные задачи");
        Task task1 = new Task("Помыть посуду", "Вечером после ужина");
        Task task2 = new Task("Купить хлеб", "В магазине у дома");

        System.out.println("Задача: " + task1.getName()
                + ", статус: " + task1.getStatus());
        System.out.println("Задача: " + task2.getName()
                + ", статус: " + task2.getStatus());

        System.out.println();

        System.out.println("Создал эпик и две подзадачи");
        Epic epic = new Epic("Ремонт кухни", "Полный ремонт");

        SubTask sub1 = new SubTask("Снять старые обои", "Стена у окна", epic.getId());
        SubTask sub2 = new SubTask("Купить краску", "Белая матовая", epic.getId());

        System.out.println("Эпик: " + epic.getName()
                + ", id=" + epic.getId()
                + ", статус: " + epic.getStatus());

        System.out.println("Подзадача: " + sub1.getName()
                + ", id=" + sub1.getId()
                + ", epicId=" + sub1.getEpicId()
                + ", статус: " + sub1.getStatus());

        System.out.println("Подзадача: " + sub2.getName()
                + ", id=" + sub2.getId()
                + ", epicId=" + sub2.getEpicId()
                + ", статус: " + sub2.getStatus());

        System.out.println();

        System.out.println("Типо добавил всё в манагер");
        manager.addEpic(epic);
        manager.addSubTask(sub1);
        manager.addSubTask(sub2);
        manager.addTask(task1);
        manager.addTask(task2);

        System.out.println("Эпиков: " + manager.getEpics().size());
        System.out.println("Подзадач: " + manager.getSubTask().size());
        System.out.println("Задач: " + manager.getTask().size());

        System.out.println();

        System.out.println("Подзадачи эпика");
        for (SubTask s : manager.getSubTasksFromEpic(epic.getId())) {
            System.out.println("- " + s.getName() + " (" + s.getStatus() + ")");
        }

        System.out.println();

        System.out.println("Первая подзадача в IN_PROGRESS");
        sub1.setStatus(Status.IN_PROGRESS);
        manager.upateSubTaskById(sub1, sub1.getId());
        System.out.println("Эпик: " + epic.getStatus());

        System.out.println();

        System.out.println("Обе подзадачи DONE");
        sub1.setStatus(Status.DONE);
        sub2.setStatus(Status.DONE);
        manager.upateSubTaskById(sub1, sub1.getId());
        manager.upateSubTaskById(sub2, sub2.getId());
        System.out.println("Эпик: " + epic.getStatus()); // DONE

        System.out.println();

        System.out.println("Вторая подзадача в NEW");
        sub2.setStatus(Status.NEW);
        manager.upateSubTaskById(sub2, sub2.getId());
        System.out.println("Эпик: " + epic.getStatus());

        System.out.println();

        System.out.println("Обновил задачу");
        task1.setName("Помыть всю посуду");
        task1.setStatus(Status.IN_PROGRESS);
        manager.upateTaskById(task1, task1.getId());
        System.out.println("Задача: " + task1.getName()
                + ", статус: " + task1.getStatus());

        System.out.println();

        System.out.println("Удалил первую подзадачу");
        manager.removeSubTaskById(sub1.getId());
        System.out.println("Подзадач в менеджере: " + manager.getSubTask().size());
        System.out.println("Подзадач у эпика: " + manager.getSubTasksFromEpic(epic.getId()).size());
        System.out.println("Эпик: " + epic.getStatus());

        System.out.println();

        System.out.println("Очистил все обычные задачи");
        manager.clearTasks();
        System.out.println("Задач в менеджере: " + manager.getTask().size());

        System.out.println();

        System.out.println("Очистил все подзадачи");
        manager.clearSubTasks();
        System.out.println("Подзадач в менеджере: " + manager.getSubTask().size());
        System.out.println("Подзадач у эпика: " + manager.getSubTasksFromEpic(epic.getId()).size());
        System.out.println("Эпик: " + epic.getStatus());

        System.out.println();

        System.out.println("Удалил эпик");
        manager.removeEpicById(epic.getId());
        System.out.println("Эпиков в менеджере: " + manager.getEpics().size());
    }
}