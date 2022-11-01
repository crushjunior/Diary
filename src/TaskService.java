import java.time.LocalDateTime;
import java.util.*;
import java.util.Map;

public class TaskService {
    private final Map<Integer, Task> taskMap;

    public TaskService() {
        this.taskMap = new LinkedHashMap<>();
    }

    public void addTask(Task task) throws RuntimeException {

        if (!taskMap.containsKey(task.getId())) {
            taskMap.put(task.getId(), task);
        } else {
            throw new RuntimeException("Такая задача уже есть!");
        }
    }

    public void deleteTask(int id) throws RuntimeException {
        if (taskMap.containsKey(id)) {
            for (Map.Entry<Integer, Task> task : taskMap.entrySet()) {
                if (task.getKey().equals(id)) {
                    taskMap.remove(task);
                }
            }
            } else {
            throw new RuntimeException("Такой задачи в списке нет!");
        }
    }

    public void printTasksForDay(LocalDateTime date) {
        System.out.println("На " + date + " : ");
        for (Task value : taskMap.values()) {
            int sw = value.getTypeRepeat();
            switch (sw) {
                case 1:
                    if (value.getDateOfTask().equals(date)) {
                        System.out.println(value);
                    } break;
                case 2:
                    if (value.getDateOfTask().isBefore(date)) {
                        System.out.println(value);
                    } break;
                case 3:
                    if (value.getDateOfTask().isBefore(date) && value.getDateOfTask().getDayOfWeek() == date.getDayOfWeek()) {
                        System.out.println(value);
                    } break;
                case 4:
                    if (value.getDateOfTask().isBefore(date) && value.getDateOfTask().getDayOfMonth() == date.getDayOfMonth()) {
                        System.out.println(value);
                    } break;
                case 5:
                    if (value.getDateOfTask().isBefore(date) && value.getDateOfTask().getDayOfYear() == date.getDayOfYear()) {
                        System.out.println(value);
                    } break;
            }
        }
    }

    @Override
    public String toString() {
        return "TaskService{" +
                "taskMap=" + taskMap +
                '}';
    }
}
