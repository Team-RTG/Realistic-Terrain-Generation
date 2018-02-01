
package rtg.util;

//import exterminatorjeff.undergroundbiomes.api.UBStrataColumn;

//import static exterminatorjeff.undergroundbiomes.api.API.STRATA_COLUMN_PROVIDER;

import rtg.RTG;

/**
 *
 * @author Zeno410
 */
public class UBColumnCache {
/*
    private UBStrataColumn column;
    private int cachedX = Integer.MIN_VALUE;
    private int cachedY = Integer.MIN_VALUE;

    public UBColumnCache() {
        RTG.instance.runOnServerClose(onClose());
    }

    public UBStrataColumn column(int x, int y) {
        if (x == cachedX) {
            if (y == cachedY) {
                return column;
            }
        }
        column = STRATA_COLUMN_PROVIDER.ubStrataColumnProvider(0).strataColumn(x, y);
        cachedX = x;
        cachedY = y;
        return column;
    }

    private Runnable onClose() {
        return new Runnable() {
            public void run() {
                cachedX = Integer.MIN_VALUE;
                cachedY = Integer.MIN_VALUE;
            }
        };
    }
*/
}