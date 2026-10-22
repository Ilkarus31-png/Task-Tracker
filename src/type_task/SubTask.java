package Task_Tracker.src.type_task;

public class SubTask {
    private String name;
    private String sipattama;
    private final int id;
    private final int epicId;
    private static int count_obj = 1;
    private Status statusTask = Status.NEW;

    public SubTask(String name, String sipattama, int epicId) {
        this.name = name;
        this.sipattama = sipattama;
        this.id = count_obj++;
        this.epicId = epicId;
    }

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getSipattama() {
        return sipattama;
    }
    public void setSipattama(String sipattama) {
        this.sipattama = sipattama;
    }


    public int getId() {
        return id;
    }

    public Status getStatus() {
        return statusTask;
    }
    public void setStatus(Status status) {
        this.statusTask = status;
    }

    public int getEpicId() {
        return epicId;
    }
}
