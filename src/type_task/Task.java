package Task_Tracker.src.type_task;

public class Task {
    private String name;
    private String sipattama;
    private final int id;
    private static int count_obj = 1;
    private Status statusTask = Status.NEW;

    public Task(String name, String sipattama) {
        this.name = name;
        this.sipattama = sipattama;
        this.id = count_obj++;
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

    public Status getStatus() {
        return statusTask;
    }
    public void setStatus(Status status) {
        this.statusTask = status;
    }

    public int getId() {
        return id;
    }
}
