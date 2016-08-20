
package rtg.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import rtg.RTG;

/**
 * A simple utility to track time spent in various procedures.
 * Crashes in re-entrant procedures, which is desired
 * That would require a much more complex system.
 * @author Zeno410
 */
public class TimeTracker {

    //private boolean started;
    private boolean stopped;
    private int depth;
    private int maxDepth;
    private long startTime;
    private long stopTime;
    private long totalOn = 0;
    private long totalOff = 0;
    public static final Manager manager = new Manager();

    public String report () {
        return new String(" on proportion "+((float)totalOn/(float)(totalOn+totalOff+1))+ " max depth " +
                maxDepth);
    }

    public void start() {
        //if (started) throw new RuntimeException();
        startTime = System.currentTimeMillis();
        //started = true;
        depth++;
        if (depth>maxDepth) maxDepth++;
        if (stopped) {
            totalOff += startTime - stopTime;
            stopped = false;
        }
    }

    public void stop() {
        //if (!started) throw new RuntimeException();
        depth--;
        if (depth == 0) {
            stopTime = System.currentTimeMillis();
            stopped = true;
            totalOn += stopTime - startTime;
        }
        //started = false;
    }

    public static class Manager {
        private HashMap<String,TimeTracker> trackers = new HashMap<String,TimeTracker>();
        private Manager() {
            RTG.instance.runOnServerClose(runReport());
        }

        private Runnable runReport() {
            return new Runnable() {
                public void run() {
                    report();
                }
            };
        }

        private TimeTracker tracker(String name) {
            TimeTracker result = trackers.get(name);
            if (result == null){
                result = new TimeTracker();
                trackers.put(name, result);
            }
            return result;
        }

        public void start(String name) {
            tracker(name).start();
        }

        public void stop(String name) {
            tracker(name).stop();
        }

        public void report() {
            if (trackers.size()<1) return;
            StringWriter output = StringWriter.from("TimeUsage.txt");
            for (String name: trackers.keySet()) {
                output.accept(name + " " + trackers.get(name).report());
            }
            output.done();
            //trackers.clear();
        }
    }

}

class StringWriter {
    BufferedWriter output;
    boolean started = false;

    /** Creates a new instance of StringWriter */
    public StringWriter(File file) throws IOException {
        output = new BufferedWriter(new FileWriter(file));
    }

    public static StringWriter from(File file) {
        try{ return new StringWriter(file);}
        catch (IOException e) {throw new RuntimeException();}
    }

    public static StringWriter from(String fileName) {
        return StringWriter.from(new File(fileName));
    }

    public void accept(String written) {
        try {
            if (started) output.write('\r');
            started = true;
            output.write(written);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void done() {
        try {
            output.flush();
            output.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}