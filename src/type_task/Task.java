package Task_Tracker.src.type_task;

public class Task {
    protected String name;
    protected String sipattama;
    protected int id;
    protected Status statusTask = Status.NEW;

    public Task(String name, String sipattama, int id) {
        this.name = name;
        this.sipattama = sipattama;
        this.id = id;
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
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Задача: %s, id: %d, описание: %s, статус: %s",
                            getName(), getId(), getSipattama(), getStatus());
    }
}
