package rtg.world.biome.realistic.vanilla;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBeach;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPalm;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBeach;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;

public class RealisticBiomeVanillaBeach extends RealisticBiomeVanillaBase {
    
    public static Block topBlock = BiomeGenBase.beach.topBlock;
    public static Block fillerBlock = BiomeGenBase.beach.fillerBlock;
    
    public RealisticBiomeVanillaBeach(BiomeConfig config)
    {
        super(config, 
            BiomeGenBase.beach,
            BiomeGenBase.river,
            new TerrainVanillaBeach(),
            new SurfaceVanillaBeach(config, topBlock, fillerBlock, topBlock, fillerBlock, (byte) 0, 1));
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        if (this.config.getPropertyById(BiomeConfigVanillaBeach.decorationPalmTreesId).valueBoolean) {
            
            if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
                
                if (rand.nextInt((int) (4f / strength)) == 0) {
                    
                    int j6 = chunkX + rand.nextInt(16) + 8;
                    int k10 = chunkY + rand.nextInt(16) + 8;
                    int z52 = world.getHeightValue(j6, k10);
                    
                    if (z52 < 80) {
                        WorldGenerator worldgenerator = new WorldGenTreeRTGPalm();
                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                        worldgenerator.generate(world, rand, j6, z52, k10);
                    }
                }
            }
        }
    }
    
}
