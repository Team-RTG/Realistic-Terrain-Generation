package rtg.api;

import java.util.ArrayList;

import rtg.api.config.RTGConfig;

public class RTGAPI {

    public static final String NAME = "rtgapi";
    public static final String OWNER = "rtg";
    public static final String VERSION = "1.0.0";
    public static RTGConfig rtgConfig;
    public static String configPath;

    public ArrayList<Runnable> oneShotServerCloseActions;
    public ArrayList<Runnable> serverCloseActions;

    private static RTGAPI instance = null;

    public static RTGAPI getInstance() {

        if (instance == null) {
            instance = new RTGAPI();
        }

        return instance;
    }

    private RTGAPI() {
        oneShotServerCloseActions = new ArrayList<>();
        serverCloseActions = new ArrayList<>();
    }

    public void runOnServerClose(Runnable action) {
        this.serverCloseActions.add(action);
    }

    public void runOnNextServerCloseOnly(Runnable action) {
        this.serverCloseActions.add(action);
    }

    /*
     * This method is currently unused, but we're leaving it here for when we start
     * supporting multiple dimensions.
     */
    public static RTGConfig config(int dimension) {
        return rtgConfig;
    }

    public static RTGConfig config() {
        return config(0);
    }
}
