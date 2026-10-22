package Task_Tracker.src.type_task;

import java.util.ArrayList;
import java.util.List;

public class Epic {
    private String name;
    private String sipattama;
    private final int id;
    private static int count_obj = 1;
    private Status statusTask = Status.NEW;

    private final List<Integer> subTaskIds = new ArrayList<>();

    public Epic(String name, String sipattama) {
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


    public int getId() {
        return id;
    }

    public Status getStatus() {
        return statusTask;
    }
    public void setStatus(Status status) {
        this.statusTask = status;
    }

    public List<Integer> getSubTaskIds () {
        return new ArrayList<>(subTaskIds);
    }

    public void addSubTaskId(int subTaskId) {
        subTaskIds.add(subTaskId);
    }

    public void removeSubTaskId(int subTaskId) {
        subTaskIds.remove(Integer.valueOf(subTaskId));
    }
}
