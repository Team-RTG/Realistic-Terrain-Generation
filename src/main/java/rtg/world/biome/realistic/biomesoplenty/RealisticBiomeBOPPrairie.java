package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPPrairie;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPPrairie;

public class RealisticBiomeBOPPrairie extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.prairie.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPPrairie() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPPrairie(65f, 80f, 25f),
                new SurfaceBOPPrairie(config, topBlock, fillerBlock)
        );
    }
}
