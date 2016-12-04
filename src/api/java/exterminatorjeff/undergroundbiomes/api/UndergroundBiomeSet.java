package exterminatorjeff.undergroundbiomes.api;
/**
 *
 * @author Zeno410
 */
abstract public class UndergroundBiomeSet {
    public final StrataLayer [] [] strataLayers ;

    public final UBBiome[] biomeList = new UBBiome[256];

    public UndergroundBiomeSet(StrataLayer [] [] strataLayers) {
        this.strataLayers = strataLayers;
    }

    abstract public UBBiome [] allowedBiomes();
    
    public UBBiome [] biomesByID() {
        UBBiome [] allowedBiomes = allowedBiomes();
        int size  = 0;
        for (int i = 0; i < allowedBiomes.length; i++) {
            size = Math.max(size,allowedBiomes[i].ID);
        }
        UBBiome [] result = new UBBiome[size+1];
        for (int i = 0; i < allowedBiomes.length; i++) {
            result[allowedBiomes[i].ID] = allowedBiomes[i];
        }
        return result;
    }
}