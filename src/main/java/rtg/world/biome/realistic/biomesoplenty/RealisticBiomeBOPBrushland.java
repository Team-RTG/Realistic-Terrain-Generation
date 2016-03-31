package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBrushland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBrushland;

public class RealisticBiomeBOPBrushland extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.brushland.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPBrushland() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPBrushland(),
                new SurfaceBOPBrushland(config, topBlock, fillerBlock, Blocks.sand.getDefaultState(), 13f, 0.27f)
        );
    }
}
