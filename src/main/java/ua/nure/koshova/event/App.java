package ua.nure.koshova.event;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 * The class that reproduces the chronological chain of events from the log.
 */
public class App {

    public static long programStartTime;

    /**
     * Read the file
     *
     * @param filename
     *
     */
    public static String readFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Converting the original programStartTime to milliseconds
     *
     * @param time
     *
     * @return programStartTime in milliseconds
     */
    public static long convertToMs(String time) {
        String resultStr = time.substring(time.indexOf(':') + 1);
        int seconds = Integer.parseInt(resultStr);
        int ms = seconds * 1000;
        return ms;
    }

    /**
     * Display event log at specified programStartTime
     *
     * @param events
     */
    public static void logEvents(Event[] events) {
        for (Event event : events) {
            long programExecutionTime = System.currentTimeMillis() - programStartTime;
            long eventOutputTime = convertToMs(event.getTime());
            while (programExecutionTime < eventOutputTime) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                programExecutionTime = System.currentTimeMillis() - programStartTime;
                eventOutputTime = convertToMs(event.getTime());
            }
            System.out.println(event.getEventName());
        }
    }


    public static void main(String[] args) {
        programStartTime = System.currentTimeMillis();
        Gson g = new Gson();
        String lines = readFile("events.json");
        Event[] events = g.fromJson(lines, EventImpl[].class);
        Arrays.sort(events);
        logEvents(events);
    }

}