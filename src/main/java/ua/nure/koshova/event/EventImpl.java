package ua.nure.koshova.event;

/**
 * The class representing the event. Contains the name of the event and the programStartTime of occurrence.
 *
 */
public class EventImpl implements Event {

    private String eventName;
    private String time;

    public String getEventName() {
        return eventName;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int compareTo(Event o) {
        return this.time.compareTo(o.getTime());
    }
}
