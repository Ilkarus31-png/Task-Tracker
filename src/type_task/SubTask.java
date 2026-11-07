package Task_Tracker.src.type_task;

public class SubTask extends Task{
    
    private final int epicId;


    public SubTask(String name, String sipattama, int id, int epicId) {
        super(name, sipattama, id);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
