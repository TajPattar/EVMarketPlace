package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a log of alarm system events.
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
// Represents a log of alarm system events
public class EventLog implements Iterable<Event> {
    /** the only EventLog in the system (Singleton Design Pattern) */
    private static EventLog theLog;
    private Collection<Event> events;

    /**
     * Prevent external construction.
     * (Singleton Design Pattern).
     */
    //EFFECTS: Constructs the Log Event
    private EventLog() {
        events = new ArrayList<Event>();
    }

    /**
     * Gets instance of EventLog - creates it
     * if it doesn't already exist.
     * (Singleton Design Pattern)
     * @return  instance of EventLog
     */
    // EFFECTS: Gets Instance of Event log and creates if not already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();

        }
        return theLog;
    }

    /**
     * Adds an event to the event log.
     * @param e the event to be added
     */
    // EFFECTS: Adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    /**
     * Clears the event log and logs the event.
     */
    // EFFECTS: clears the event log and logs event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    //EFFECTS: sets up the iterator for Event
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}