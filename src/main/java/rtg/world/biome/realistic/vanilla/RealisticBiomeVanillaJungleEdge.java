package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleEdge;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleEdge;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaJungleEdge extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.jungleEdge.topBlock;
    public static Block fillerBlock = BiomeGenBase.jungleEdge.fillerBlock;
    
    public RealisticBiomeVanillaJungleEdge(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.jungleEdge,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainVanillaJungleEdge(),
            new SurfaceVanillaJungleEdge(topBlock, fillerBlock));
        
        this.config = config;
        this.biomeWeight = ConfigVanilla.weightVanillaJungleEdge;
        this.generateVillages = ConfigVanilla.villageVanillaJungleEdge;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        
        if (l > 0f && rand.nextInt(6) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            (new WorldGenLog(Blocks.log, 3, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
        }
    }
}
