
package rtg.api.util;

/**
 * Convenience class for bayesian statistical manipulations
 * @author Zeno410
 */
public class Bayesian {
    
    public static final float adjustment(float probability, float multiplier) {
        // returns the original probability adjusted for the multiplier to the confidence ratio
        // useful for computationally cheap remappings within [0,1]
        if (probability >= 1) return probability;
        if (probability <= 0) return probability;
        float newConfidence = probability*multiplier/(1f-probability);
        return newConfidence/(1f+newConfidence);
    }

}
