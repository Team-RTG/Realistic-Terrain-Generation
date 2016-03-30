package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWasteland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWasteland;

public class RealisticBiomeBOPWasteland extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.wasteland.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPWasteland() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPWasteland(),
                new SurfaceBOPWasteland(config, topBlock, fillerBlock)
        );
    }
}
