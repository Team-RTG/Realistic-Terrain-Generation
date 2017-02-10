package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPChaparral;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPChaparral;

public class RealisticBiomeBOPChaparral extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.chaparral.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPChaparral(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPChaparral(),
            new SurfaceBOPChaparral(config, topBlock, fillerBlock, Blocks.sand.getDefaultState(), 26f, 0.35f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
