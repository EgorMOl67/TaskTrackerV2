package TaskStructure;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    // Эпик хранит только ID подзадач
    private List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description) {
        // По умолчанию - статус NEW
        super(name, description, TaskStatus.NEW);
    }

    public Epic(int id, String name, String description, TaskStatus status) {
        super(id, name, description, status);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId(int subtaskId) {
        subtaskIds.add(subtaskId);
    }
    public void removeSubtaskId(int subtaskId) {
        subtaskIds.remove(Integer.valueOf(subtaskId));
    }

    @Override
    public String toString() {
        return "tasks.TaskStructure.Epic{" + "id=" + getId() + ", name='" + getName() + ", status=" + getStatus() + ", subtasks=" + subtaskIds + '}';
    }
}