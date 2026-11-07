package Task_Tracker.src.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Task_Tracker.src.type_task.Epic;
import Task_Tracker.src.type_task.SubTask;
import Task_Tracker.src.type_task.Task;

public class InMemoryHistoryManager implements HistoryManager {

    public InMemoryHistoryManager () {

    }

    private static class Node {

        private Node prev;
        private Node next;
        Task task;

        public Node (Task task, Node prev, Node next) {
            this.task = task;
            this.prev = prev;
            this.next = next;
        }
    }

    private final HashMap<Integer, Node> nodeHashMap = new HashMap<>();
    private Node head;
    private Node tail;

    private void linkLast (Task task) {
        Node newNode = new Node (task, tail, null);

        if (tail == null) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    private void removeNode (Node node) {

        if (node == null) return;

        Node prev = node.prev;
        Node next = node.next;
        
        if (prev != null) {
            prev.next = next;
        }
        else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
        }
        else {
            tail = prev;
        }

        node.prev = null;
        node.next = null;
    }

    @Override
    public void addTask(Task task) {
        if (task == null) { return; }
        
        Node existingNode = nodeHashMap.get(task.getId());
        if (existingNode != null) {
            removeNode(existingNode);
        }

        linkLast(task);

        nodeHashMap.put(task.getId(), tail);
    }

    @Override
    public List<Task> getHistory () {
        List<Task> history = new ArrayList<>();

        Node current = head;

        while (current != null) {
            history.add(current.task);
            current = current.next;
        }
        
        return history;
    }

    @Override
    public void remove(int id) {
        Node node = nodeHashMap.remove(id);
        removeNode(node);
    }
    
}
