package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOvergrownCliffs;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOvergrownCliffs;

public class RealisticBiomeBOPOvergrownCliffs extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.overgrown_cliffs.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPOvergrownCliffs(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPOvergrownCliffs(300f, 100f, 0f),
            new SurfaceBOPOvergrownCliffs(config, biome.topBlock, biome.fillerBlock, 0.95f)
        );

        this.generatesEmeralds = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
