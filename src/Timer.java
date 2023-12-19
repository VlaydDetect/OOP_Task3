import java.util.ArrayList;
import java.util.List;

// Observer
public class Timer extends Thread {
    private int workDays;
    private int workHours;
    private int stepDelay;
    private int hoursStep;

    private int currentDay;
    private int currentHour;

    private List<TimerListener> listeners = new ArrayList<>();

    public Timer() {}

    protected void init(int workDays, int workHours, int stepDelay, int hoursStep) {
        this.workDays = workDays;
        this.workHours = workHours;
        this.stepDelay = stepDelay;
        this.hoursStep = hoursStep;
        this.currentDay = 0;
        this.currentHour = 0;
        EventEmitter.emitEvent(EventType.SupermarketOpening, null, null);
    }

    @Override
    public void run() {
//        while (currentDay <= workDays) {
//            if (currentHour >= startHour && currentHour <= finishHour) {
//                currentHour += hoursStep;
//                try {
//                    Thread.sleep(stepDelay * 1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                currentDay++;
//                currentHour = startHour;
//                notifyListeners();
//            }
//        }

//        while (currentHour <= finishHour) {
//            currentHour += hoursStep;
//            try {
//                Thread.sleep(stepDelay * 1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            notifyListeners();
//        }

        if (currentHour <= workHours) {
            currentHour += hoursStep;
            try {
                sleep(stepDelay * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyListeners();
        } else {
            interrupt();
            EventEmitter.emitEvent(EventType.SupermarketClosing, null, null);
        }
    }

    protected void registerListener(TimerListener listener) {
        this.listeners.add(listener);
    }

    private void notifyListeners() {
        this.listeners.forEach(l -> l.dataChanged(currentDay));
    }
}
