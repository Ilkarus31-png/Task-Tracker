package Task_Tracker.src.manager;

public class Managers {
    private Managers() {}

    public static ITaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
