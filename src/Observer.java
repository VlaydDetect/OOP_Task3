import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Observer
public class Observer {
    private List<EventListener> listeners = new ArrayList<>();
    protected LinkedList<EventType> queue;

    public Observer() {}

    protected void registerListener(EventListener listener) {
        this.listeners.add(listener);
    }
}
