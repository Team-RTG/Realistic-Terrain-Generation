package rtg.world.biome.realistic.arsmagica;

import rtg.config.arsmagica.ConfigAM;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.arsmagica.SurfaceAMWitchwoodForest;
import rtg.world.gen.terrain.arsmagica.TerrainAMWitchwoodForest;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeAMWitchwoodForest extends RealisticBiomeAMBase
{
    
    public RealisticBiomeAMWitchwoodForest(BiomeGenBase amBiome)
    {
    
        super(
            amBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainAMWitchwoodForest(),
            new SurfaceAMWitchwoodForest(amBiome.topBlock, amBiome.fillerBlock, Blocks.stone, Blocks.cobblestone));
        
        this.setRealisticBiomeName("Ars Magica Witchwood Forest");
        this.biomeCategory = BiomeCategory.NORMAL;
        this.biomeWeight = ConfigAM.weightAMWitchwoodForest;
    }
}
