package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMoor;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMoor;

public class RealisticBiomeBOPMoor extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.moor.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPMoor(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPMoor(68f, 75f, 16f),
            new SurfaceBOPMoor(config, BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState())
        );

        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
