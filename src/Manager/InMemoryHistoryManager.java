package Manager;

import TaskStructure.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    // Внутренний класс для узла списка
    private static class Node {
        Task task;
        Node prev;
        Node next;

        Node(Node prev, Task task, Node next) {
            this.prev = prev;
            this.task = task;
            this.next = next;
        }
    }

    // Голова и хвост списка
    private Node head;
    private Node tail;

    // Мапа для быстрого поиска узла по ID задачи
    private final Map<Integer, Node> historyMap = new HashMap<>();

    @Override
    public void add(Task task) {
        if (task == null) return;

        // Если задача уже была просмотрена, удаляем её из списка
        if (historyMap.containsKey(task.getId())) {
            removeNode(historyMap.get(task.getId()));
        }

        // Добавляем задачу в конец списка (linkLast)
        linkLast(task);
    }

    @Override
    public void remove(int id) {
        if (historyMap.containsKey(id)) {
            removeNode(historyMap.get(id));
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    // --- Внутренние методы для работы со списком ---

    private void linkLast(Task task) {
        Node oldTail = tail;
        Node newNode = new Node(oldTail, task, null);

        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }

        // Обязательно сохраняем узел в мапу
        historyMap.put(task.getId(), newNode);
    }

    private void removeNode(Node node) {
        if (node == null) return;

        Node prev = node.prev;
        Node next = node.next;

        // Перекидываем ссылки соседей
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        // Удаляем из мапы
        historyMap.remove(node.task.getId());
    }

    private List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Node current = head;
        while (current != null) {
            tasks.add(current.task);
            current = current.next;
        }
        return tasks;
    }
}