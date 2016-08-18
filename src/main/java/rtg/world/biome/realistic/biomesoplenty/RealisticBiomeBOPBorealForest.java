package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBorealForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBorealForest;

public class RealisticBiomeBOPBorealForest extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.boreal_forest.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPBorealForest(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPBorealForest(),
            new SurfaceBOPBorealForest(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.15f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
