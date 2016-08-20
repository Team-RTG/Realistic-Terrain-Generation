package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPHighland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPHighland;

public class RealisticBiomeBOPHighland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.highland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPHighland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPHighland(),
            new SurfaceBOPHighland(config, biome.topBlock, biome.fillerBlock)
        );

        this.generatesEmeralds = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
