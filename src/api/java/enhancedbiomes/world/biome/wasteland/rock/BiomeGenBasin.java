package enhancedbiomes.world.biome.wasteland.rock;

import java.util.List;
import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biome.base.BiomeGenRockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.*;

public class BiomeGenBasin extends BiomeGenRockBase
{
    public BiomeGenBasin(int par1)
    {
        super(par1);
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
        this.topBlock = Blocks.air;
        this.fillerBlock = Blocks.stone;
    }
}