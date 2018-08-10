package rtg.api.util;

/**
 * This routine creates a 16x16 array for an ordered circular search
 *
 * @author Zeno410
 */
public class CircularSearchCreator {

    private boolean active = false;
    private int size;
    private float center;

    public int[] pattern(float maxRadius, int requestedSize) {
        if (active) {
            throw new RuntimeException();
        }
        active = true;
        size = requestedSize;
        center = (size - 1f) / 2f;
        int[] result = new int[size * size];
        boolean[] found = new boolean[size * size];
        int nextResult = 0;
        int smallerHalfSize = size / 2;
        int largerHalfSize = (size + 1) / 2;
        for (float radius = 0; radius < maxRadius; radius = radius + 0.01f) {
            // kind of a pain to go in a circle
            // so do a simple search in each quadrant
            // as long as the steps are really small everything will end up in order

            // upper right
            for (int y = 0; y < largerHalfSize; y++) {
                for (int x = smallerHalfSize; x < size; x++) {
                    int index = x * size + y;
                    if (found[index]) {
                        continue;// skip to next block; this is already in the patter
                    }
                    float distance = distanceFromCenter(x, y);
                    if (distance > radius) {
                        continue;// still too far; skip
                    }
                    //place in patter
                    result[nextResult++] = index;
                    found[index] = true;
                }
            }
            //lower right
            for (int x = size - 1; x >= smallerHalfSize; x--) {// out to in
                for (int y = largerHalfSize; y < size; y++) {
                    int index = x * size + y;
                    if (found[index]) {
                        continue;// skip to next block; this is already in the patter
                    }
                    float distance = distanceFromCenter(x, y);
                    if (distance > radius) {
                        continue;// still too far; skip
                    }
                    //place in patter
                    result[nextResult++] = index;
                    found[index] = true;
                }
            }
            //lower left
            for (int y = size - 1; y >= largerHalfSize - 1; y--) {// out to in
                for (int x = smallerHalfSize - 1; x > -1; x--) {
                    int index = x * size + y;
                    if (found[index]) {
                        continue;// skip to next block; this is already in the patter
                    }
                    float distance = distanceFromCenter(x, y);
                    if (distance > radius) {
                        continue;// still too far; skip
                    }
                    //place in pattern
                    result[nextResult++] = index;
                    found[index] = true;
                }
            }
            //upper left
            for (int x = 0; x < smallerHalfSize; x++) {// out to in
                for (int y = largerHalfSize - 1; y > -1; y--) {
                    int index = x * size + y;
                    if (found[index]) {
                        continue;// skip to next block; this is already in the patter
                    }
                    float distance = distanceFromCenter(x, y);
                    if (distance > radius) {
                        continue;// still too far; skip
                    }
                    //place in pattern
                    result[nextResult++] = index;
                    found[index] = true;
                }
            }
        }
        active = false;
        if (nextResult < result.length) {
            int[] newResult = new int[nextResult];
            System.arraycopy(result, 0, newResult, 0, nextResult);
            result = newResult;
        }
        return result;
    }

    private float distanceFromCenter(int x, int y) {
        return (float) Math.sqrt(MathUtils.pow2(x - this.center) + MathUtils.pow2(y - this.center));
    }
}
