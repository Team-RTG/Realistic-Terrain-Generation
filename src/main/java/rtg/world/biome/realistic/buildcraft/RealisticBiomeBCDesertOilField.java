package rtg.world.biome.realistic.buildcraft;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.buildcraft.SurfaceBCDesertOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCDesertOilField;

public class RealisticBiomeBCDesertOilField extends RealisticBiomeBCBase {

    public RealisticBiomeBCDesertOilField(BiomeGenBase bcBiome, BiomeConfig config) {

        super(config,
            bcBiome, BiomeGenBase.river,
            new TerrainBCDesertOilField(),
            new SurfaceBCDesertOilField(config, bcBiome.topBlock, bcBiome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
