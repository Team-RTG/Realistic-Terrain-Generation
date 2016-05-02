package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavannaM;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.biome.deco.collection.DecoCollectionSavanna;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavannaM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavannaM;

public class RealisticBiomeVanillaSavannaM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.savanna;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaSavannaM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaSavannaM(),
            new SurfaceVanillaSavannaM(config, topBlock, fillerBlock)
        );
        this.noLakes=true;
        
        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionSavanna(this.config._boolean(BiomeConfigVanillaSavannaM.decorationLogsId)));
    }
}
