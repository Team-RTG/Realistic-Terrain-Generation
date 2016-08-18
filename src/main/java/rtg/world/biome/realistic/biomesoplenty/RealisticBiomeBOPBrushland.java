package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBrushland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBrushland;

public class RealisticBiomeBOPBrushland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.brushland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBrushland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPBrushland(),
            new SurfaceBOPBrushland(config, biome.topBlock, biome.fillerBlock, Blocks.SAND.getDefaultState(), 13f, 0.27f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
