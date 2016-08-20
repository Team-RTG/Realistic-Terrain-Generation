package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCoralReef;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCoralReef;

public class RealisticBiomeBOPCoralReef extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.coral_reef.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPCoralReef(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPCoralReef(false, -10f, 0f, 0f, 0f, 30f),
            new SurfaceBOPCoralReef(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
