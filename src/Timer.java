import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// Observer
public class Timer extends Thread {
    private int workDays;
    private int startHour;
    private int finishHour;
    private int stepDelay;
    private int hoursStep;

    private int currentDay;
    private int currentHour;

    private List<TimerListener> listeners = new ArrayList<>();

    public Timer(int workDays, int startHour, int finishHour, int stepDelay, int hoursStep) {
        this.workDays = workDays;
        this.startHour = startHour;
        this.finishHour = finishHour;
        this.stepDelay = stepDelay;
        this.hoursStep = hoursStep;
        this.currentDay = 0;
        this.currentHour = startHour;
    }

    @Override
    public void run() {
        while (currentDay <= workDays) {
            if (currentHour >= startHour && currentHour <= finishHour) {
                currentHour += hoursStep;
                try {
                    Thread.sleep(stepDelay * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                currentDay++;
                currentHour = startHour;
                notifyListeners();
            }
        }
    }

    protected void registerListener(TimerListener listener) {
        this.listeners.add(listener);
    }

    private void notifyListeners() {
        this.listeners.forEach(l -> l.dataChanged(currentDay));
    }
}
