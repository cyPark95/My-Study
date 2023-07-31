import scheduler.LeastJob;
import scheduler.PriorityAllocation;
import scheduler.RoundRobin;
import scheduler.Scheduler;

public class SchedulerFactory {

    private SchedulerFactory() {
    }

    public static Scheduler create(int code) {
        return switch (code) {
            case 'R', 'r' -> new RoundRobin();
            case 'L', 'l' -> new LeastJob();
            case 'P', 'p' -> new PriorityAllocation();
            default -> null;
        };
    }
}
