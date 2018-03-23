package rtg.api.dimension;

import java.util.Set;

import com.google.common.collect.Sets;

import rtg.api.util.UtilityClass;

/**
 * Created by WhichOnesPink on 28/05/2017.
 */
// TODO: [1.12] It's not likely that we will need to manage dimentions, a simple dimension type blacklist should be fine. This class should be removed.
@UtilityClass
public final class DimensionManagerRTG
{
    private DimensionManagerRTG() {}
    private static Set<Integer> rtgDimensions = Sets.newHashSet();
    public static void addRTGDimension(int dimId) { rtgDimensions.add(dimId); }
    public static boolean isValidDimension(int dimId) { return rtgDimensions.contains(dimId); }
}
