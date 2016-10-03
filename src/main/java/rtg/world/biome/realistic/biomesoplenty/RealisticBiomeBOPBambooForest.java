package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.BlockDirt;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBambooForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBambooForest;

public class RealisticBiomeBOPBambooForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.bamboo_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBambooForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPBambooForest(),
            new SurfaceBOPBambooForest(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), 0.15f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
