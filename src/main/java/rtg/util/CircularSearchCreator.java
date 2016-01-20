
package rtg.util;

/**
 * This routine creates a 16x16 array for an ordered circular search
 * @author Zeno410
 */
public class CircularSearchCreator {

    public int[] pattern() {
        int [] result = new int[256];
        boolean [] found = new boolean[256];
        int nextResult = 0;
        for (float radius = 0; radius<12; radius = radius + 0.01f) {
            // kind of a pain to go in a circle
            // so do a simple search in each quadrant
            // as long as the steps are really small everything will end up in order

            // upper right
            for (int y = 0;y<8;y++) {
                for (int x = 8;x<16;x++) {
                    int index = (int)x*16+y;
                    if (found[index]) continue;// skip to next block; this is already in the patter
                    float distance = distanceFromCenter(x,y);
                    if (distance>radius) continue;// still too far; skip
                    //place in patter
                    result[nextResult++]=index;
                    found[index] = true;
                }
            }
            //lower right
            for (int x = 15;x>7;x--) {// out to in
                for (int y = 8;y<16;y++) {
                    int index = (int)x*16+y;
                    if (found[index]) continue;// skip to next block; this is already in the patter
                    float distance = distanceFromCenter(x,y);
                    if (distance>radius) continue;// still too far; skip
                    //place in patter
                    result[nextResult++]=index;
                    found[index] = true;
                }
            }
            //lower left
            for (int y = 15;y>7;y--) {// out to in
                for (int x = 7;x>-1;x--) {
                    int index = (int)x*16+y;
                    if (found[index]) continue;// skip to next block; this is already in the patter
                    float distance = distanceFromCenter(x,y);
                    if (distance>radius) continue;// still too far; skip
                    //place in patter
                    result[nextResult++]=index;
                    found[index] = true;
                }
            }
            //upper left
            for (int x = 0;x<8;x++) {// out to in
                for (int y = 7;y>-1;y--) {
                    int index = (int)x*16+y;
                    if (found[index]) continue;// skip to next block; this is already in the patter
                    float distance = distanceFromCenter(x,y);
                    if (distance>radius) continue;// still too far; skip
                    //place in patter
                    result[nextResult++]=index;
                    found[index] = true;
                }
            }
        }
        if (nextResult != 256) throw new RuntimeException("total "+nextResult);
        return result;
    }

    float distanceFromCenter(int x, int y) {
        float xDist = ((float)x-7.5f);
        float yDist = ((float)y-7.5f);
        return (float)Math.sqrt(xDist*xDist+yDist*yDist);
    }
}
