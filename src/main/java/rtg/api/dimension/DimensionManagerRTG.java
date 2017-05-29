package rtg.api.dimension;

import java.util.ArrayList;

/**
 * Created by WhichOnesPink on 28/05/2017.
 */
public class DimensionManagerRTG {

    public static final int OVERWORLD = 0;
    public static ArrayList<Integer> rtgDimensions = new ArrayList<Integer>(){};

    public static void addRTGDimension(int dimId) {

        if (!rtgDimensions.contains(dimId)) {
            rtgDimensions.add(dimId);
        }
    }

    public static boolean isValidDimension(int dimId) {
        return rtgDimensions.contains(dimId);
    }
}
