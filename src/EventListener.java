public interface EventListener {
    void onEvent(EventType eventType);
    void dataChanged(int newDay);
}
