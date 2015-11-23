package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.vanilla.SurfaceVanillaPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaPlains;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.plains.topBlock;
    public static Block fillerBlock = BiomeGenBase.plains.fillerBlock;
    
    public RealisticBiomeVanillaPlains()
    {
    
        super(
            BiomeGenBase.plains,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainVanillaPlains(),
            new SurfaceVanillaPlains(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone));
        
        this.setRealisticBiomeName("Vanilla Plains");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigVanilla.weightVanillaPlains;
        this.generateVillages = ConfigVanilla.villageVanillaPlains;
    }
}
