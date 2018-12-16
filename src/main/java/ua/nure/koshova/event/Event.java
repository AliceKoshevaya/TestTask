package ua.nure.koshova.event;

public interface Event extends Comparable<Event> {

    public String getEventName();

    public String getTime();
}
