package TaskStructure;

public class Task {
    private int id;
    private String name;
    private String description;
    private TaskStatus status; // enum поле

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Конструктор для создания копии, для обновления
    public Task(int id, String name, String description, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setStatus(TaskStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "tasks.TaskStructure.Task{" + "id=" + id + ", name='" + name + ", status=" + status + '}';
    }
}