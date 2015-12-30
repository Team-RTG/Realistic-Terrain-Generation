
package rtg.util;

import exterminatorJeff.undergroundBiomes.api.UBAPIHook;
import rtg.RTG;
import exterminatorJeff.undergroundBiomes.api.UBStrataColumn;

/**
 *
 * @author Zeno410
 */
    public class UBColumnCache {
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
            column = UBAPIHook.ubAPIHook.dimensionalStrataColumnProvider.ubStrataColumnProvider(0).strataColumn(x, y);
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
    }