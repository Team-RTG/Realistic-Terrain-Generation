package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungleEdge;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.VillageMaterial;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleEdge;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleEdge;

import java.util.Random;

public class RealisticBiomeVanillaJungleEdge extends RealisticBiomeVanillaBase
{
    
    public static  IBlockState topBlock = BiomeGenBase.jungleEdge.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.jungleEdge.fillerBlock;
    
    public RealisticBiomeVanillaJungleEdge(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.jungleEdge,
            BiomeGenBase.river,
            new TerrainVanillaJungleEdge(),
            new SurfaceVanillaJungleEdge(config, topBlock, fillerBlock));
        config.setVillageMaterial(VillageMaterial.Preset.JUNGLE);
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
    
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        
        if (this.config.getPropertyById(BiomeConfigVanillaJungleEdge.decorationLogsId).valueBoolean) {
        
            if (l > 0f && rand.nextInt(6) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                (new WorldGenLog(Blocks.log, 3, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }
    }
}
