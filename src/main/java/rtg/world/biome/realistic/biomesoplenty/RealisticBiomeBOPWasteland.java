package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWasteland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWasteland;

public class RealisticBiomeBOPWasteland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.wasteland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPWasteland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPWasteland(),
            new SurfaceBOPWasteland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
