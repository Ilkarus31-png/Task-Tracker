package Task_Tracker.src;

import Task_Tracker.src.manager.Managers;
import Task_Tracker.src.manager.ITaskManager;
import Task_Tracker.src.type_task.Epic;
import Task_Tracker.src.type_task.Status;
import Task_Tracker.src.type_task.SubTask;
import Task_Tracker.src.type_task.Task;

public class Main {
    public static void main(String[] args) {

        ITaskManager manager = Managers.getDefault();

        System.out.println("Создал обычные задачи");
        Task task1 = new Task("Помыть посуду", "Вечером после ужина", 500);
        Task task2 = new Task("Купить хлеб", "В магазине у дома", 501);
        manager.addTask(task1);
        manager.addTask(task2);

        System.out.println("Задача: " + task1.getName()
                + ", статус: " + task1.getStatus());
        System.out.println("Задача: " + task2.getName()
                + ", статус: " + task2.getStatus());

        System.out.println();

        System.out.println("Создал эпик и две подзадачи");
        Epic epic = new Epic("Ремонт кухни", "Полный ремонт",200);
        manager.addEpic(epic);

        SubTask sub1 = new SubTask("Снять старые обои", "Стена у окна", 50, epic.getId());
        SubTask sub2 = new SubTask("Купить краску", "Белая матовая", 205, epic.getId());
        manager.addSubTask(sub1);
        manager.addSubTask(sub2);

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

        System.out.println("Эпиков: " + manager.getEpics().size());
        System.out.println("Подзадач: " + manager.getSubTask().size());
        System.out.println("Задач: " + manager.getTask().size());

        System.out.println();

        System.out.println("Подзадачи эпика");
        for (SubTask s : manager.getSubTasksFromEpic(epic.getId())) {
        	System.out.println("- " + s.getName() + " (" + s.getStatus() + ")");
        }

        System.out.println();

        System.out.println("\nПервая подзадача в IN_PROGRESS");
        sub1.setStatus(Status.IN_PROGRESS);
        manager.upateSubTaskById(sub1, 2);
        System.out.println("Эпик: " + epic.getStatus());

        System.out.println();

        System.out.println("\nОбе подзадачи DONE");
        sub1.setStatus(Status.DONE);
        sub2.setStatus(Status.DONE);
        manager.upateSubTaskById(sub1, 2);
        manager.upateSubTaskById(sub2, 3);
        System.out.println("Эпик: " + epic.getStatus()); // DONE

        System.out.println();

        System.out.println("\nВторая подзадача в NEW");
        sub2.setStatus(Status.NEW);
        manager.upateSubTaskById(sub2, 3);
        System.out.println("Эпик: " + epic.getStatus());

        System.out.println();

        System.out.println("Обновил задачу");
        task1.setName("Помыть всю посуду");
        task1.setStatus(Status.IN_PROGRESS);
        manager.upateTaskById(task1, 4);
        System.out.println("Задача: " + task1.getName()
                + ", статус: " + task1.getStatus());

        System.out.println();

        manager.getByIdEpic(epic.getId());
        manager.getIdTask(task1.getId());
        manager.getByIdSubTask(sub1.getId());

        System.out.println("История после 3-х просмотров:");
        System.out.println("\n" + manager.getHistory());

        System.out.println("\nЕще раз получил Эпик");
        manager.getByIdEpic(epic.getId());
        System.out.println("\n" + manager.getHistory());

        System.out.println("\nУдалил 2 подзадачи");
        manager.removeSubTaskById(sub1.getId());
        manager.removeEpicById(epic.getId());
        System.out.println("\n" + manager.getHistory());







        System.out.println("\n\nУдалил первую подзадачу");
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