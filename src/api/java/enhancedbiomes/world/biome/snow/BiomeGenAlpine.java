package enhancedbiomes.world.biome.snow;

import java.util.Random;

import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAlpine extends BiomeGenSnowBase
{
    private WorldGenerator theWorldGenerator;

    public BiomeGenAlpine(int par1)
    {
        super(par1);
        this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.stone;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;

        for (var5 = 0; var5 < 7; ++var5)
        {
            var6 = par3 + par2Random.nextInt(16);
            var7 = par2Random.nextInt(64);
            var8 = par4 + par2Random.nextInt(16);
            this.theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
}
