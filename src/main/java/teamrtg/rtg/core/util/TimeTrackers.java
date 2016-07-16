package teamrtg.rtg.core.util;

import teamrtg.rtg.api.util.TimeTracker;

/**
 * @author topisani
 */
public class TimeTrackers {
    public static final TimeTracker RTG_TERRAIN = TimeTracker.manager.tracker("RTG Terrain");
    public static final TimeTracker BIOME_LAYOUT_ACTIVITY = TimeTracker.manager.tracker("Biome Layout");
}
