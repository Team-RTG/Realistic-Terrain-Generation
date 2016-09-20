package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPAlps;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPAlps;

public class RealisticBiomeBOPAlps extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.alps.get();
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBOPAlps(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPAlps(),
            new SurfaceBOPAlps(config, biome.topBlock, biome.fillerBlock, 0.45f)
        );

        this.generatesEmeralds = true;
        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
