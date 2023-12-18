package rtg.world.gen;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;


/**
 * @author Zeno410
 */
public class MesaBiomeCombiner {

    public final int mesa = Biome.getIdForBiome(Biomes.MESA);
    public final int mesaBryce = Biome.getIdForBiome(Biomes.MUTATED_MESA);
    public final int mesaPlateau = Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK);
    public final int mesaPlateauF = Biome.getIdForBiome(Biomes.MESA_ROCK);
    public final int mesaPlateauM = Biome.getIdForBiome(Biomes.MUTATED_MESA_CLEAR_ROCK);
    public final int mesaPlateauFM = Biome.getIdForBiome(Biomes.MUTATED_MESA_ROCK);

    public void adjust(float[] result) {
        float mesaBorder = result[mesa];
        float bryceBorder = result[mesaBryce];
        float plateauBorder = result[mesaPlateau] + result[mesaPlateauM];
        float plateauFBorder = result[mesaPlateauF] + result[mesaPlateauFM];
        result[mesa] = 0;
        result[mesaPlateauM] = 0;
        result[mesaPlateauFM] = 0;
        if (plateauBorder > plateauFBorder) {
            result[mesaPlateau] = mesaBorder + plateauBorder + plateauFBorder;// + bryceBorder;
            result[mesaPlateauF] = 0;
        }
        else {
            result[mesaPlateau] = 0;
            result[mesaPlateauF] = mesaBorder + plateauBorder + plateauFBorder;// + bryceBorder;
        }
        if (bryceBorder > plateauBorder&& bryceBorder > plateauFBorder) {
            //result[mesaPlateau] = 0;
            //result[mesaPlateauF] = 0;
        	//result[mesaBryce] = mesaBorder + plateauBorder + plateauFBorder + bryceBorder;
        }
    }
}
