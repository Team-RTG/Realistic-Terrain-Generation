package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaiga;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaiga;

public class RealisticBiomeVanillaColdTaiga extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.coldTaiga.topBlock;
    public static Block fillerBlock = BiomeGenBase.coldTaiga.fillerBlock;
    
    public RealisticBiomeVanillaColdTaiga(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.coldTaiga,
            BiomeGenBase.frozenRiver,
            new TerrainVanillaColdTaiga(),
            new SurfaceVanillaColdTaiga(config, topBlock, fillerBlock)
        );
        
        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaColdTaiga.decorationLogsId), 8f));
    }
}
