import Manager.Managers;
import Manager.TaskManager;
import TaskStructure.Epic;
import TaskStructure.Subtask;
import TaskStructure.Task;
import TaskStructure.TaskStatus;

public class Main {
    public static void main(String[] args) {
        // Используем утилитарный класс для получения менеджера
        TaskManager manager = Managers.getDefault();

        // 1. Создаем задачи
        Task task1 = new Task("TaskStructure.Task 1", "Desc", TaskStatus.NEW);
        Task task2 = new Task("TaskStructure.Task 2", "Desc", TaskStatus.NEW);
        manager.createTask(task1);
        manager.createTask(task2);

        Epic epic = new Epic("TaskStructure.Epic 1", "Desc");
        manager.createEpic(epic);

        Subtask sub1 = new Subtask("Sub 1", "Desc", TaskStatus.NEW, epic.getId());
        Subtask sub2 = new Subtask("Sub 2", "Desc", TaskStatus.NEW, epic.getId());
        Subtask sub3 = new Subtask("Sub 3", "Desc", TaskStatus.NEW, epic.getId());
        manager.createSubtask(sub1);
        manager.createSubtask(sub2);
        manager.createSubtask(sub3);

        // 2. Запрашиваем задачи в разном порядке
        System.out.println("=== ТЕСТ ИСТОРИИ ===");

        manager.getTaskById(task1.getId());     // История: [task1]
        manager.getEpicById(epic.getId());      // История: [task1, epic]
        manager.getSubtaskById(sub1.getId());   // История: [task1, epic, sub1]

        // Повторный запрос task1 (должен переместиться в конец)
        manager.getTaskById(task1.getId());     // История: [epic, sub1, task1]

        manager.getSubtaskById(sub1.getId());   // История: [epic, task1, sub1]

        // Вывод истории
        System.out.println("Текущая история просмотров:");
        for (Task t : manager.history()) {
            System.out.println(t.getName() + " (ID: " + t.getId() + ")");
        }

        // 3. Удаляем задачу, которая есть в истории
        System.out.println("\nУдаляем TaskStructure.Task 1...");
        manager.deleteTaskById(task1.getId());

        System.out.println("История после удаления TaskStructure.Task 1:");
        for (Task t : manager.history()) {
            System.out.println(t.getName() + " (ID: " + t.getId() + ")");
        }

        // 4. Удаляем эпик с подзадачами
        System.out.println("\nУдаляем Эпик и все его подзадачи...");
        manager.deleteEpicById(epic.getId());

        System.out.println("История после удаления Эпика:");
        for (Task t : manager.history()) {
            System.out.println(t.getName() + " (ID: " + t.getId() + ")");
        }
    }
}