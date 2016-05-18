package teamrtg.rtg.api.util;

import java.util.Arrays;

/**
 * @author topisani
 */
public class EnumUtils {
    public static String[] names(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
