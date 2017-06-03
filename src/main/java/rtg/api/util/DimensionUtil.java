package rtg.api.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import net.minecraft.world.DimensionType;

import net.minecraftforge.common.DimensionManager;

/**
 * Created by WhichOnesPink on 28/05/2017.
 */
public class DimensionUtil {

    private Hashtable<Integer, DimensionType> registeredDimensions;

    public DimensionUtil() {

        try {
            Field field = DimensionManager.class.getDeclaredField("dimensions");
            field.setAccessible(true);

            try {
                registeredDimensions = (Hashtable<Integer, DimensionType>) field.get(null);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("Could not get dimensions.");
            }
        }
        catch (NoSuchFieldException e) {
            throw new RuntimeException("No dimensions? Hmmmm.");
        }

    }

    public int[] getRegisteredDimensions()
    {
        int[] ret = new int[registeredDimensions.size()];
        int x = 0;
        for (Map.Entry<Integer, DimensionType> ent : registeredDimensions.entrySet())
        {
            ret[x++] = ent.getKey();
        }

        return Arrays.copyOf(ret, x);
    }
}
