package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWetland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWetland;

public class RealisticBiomeBOPWetland extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.wetland.get();

    public static IBlockState topBlock = BOPBlocks.grass.getDefaultState();
    public static IBlockState fillerBlock = BOPBlocks.dirt.getDefaultState();

    public RealisticBiomeBOPWetland(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPWetland(),
            new SurfaceBOPWetland(config, topBlock, fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
