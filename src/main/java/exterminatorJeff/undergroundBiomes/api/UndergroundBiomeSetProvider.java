
package exterminatorJeff.undergroundBiomes.api;

/**
 *
 * @author Zeno410
 */
abstract public interface UndergroundBiomeSetProvider {
    // this function should return the new desired set
    // if null is returned that signals no changes to the existing set
     public UndergroundBiomeSet modifiedBiomeSet(int dimension, long worldSeed, UndergroundBiomeSet previous);
}
