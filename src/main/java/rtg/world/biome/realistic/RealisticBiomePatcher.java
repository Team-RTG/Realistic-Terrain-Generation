package rtg.world.biome.realistic;

import rtg.config.rtg.ConfigRTG;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomePatcher
{
    
    private int patchBiomeId;
    private RealisticBiomeBase realisticBiome;
    private BiomeGenBase baseBiome;
    
    private int singleBiomeId;
    private RealisticBiomeBase singleRealisticBiome;
    private BiomeGenBase singleBaseBiome;    
    
    public RealisticBiomePatcher()
    {
        this.patchBiomeId = ConfigRTG.patchBiomeId;
        
        if (this.patchBiomeId > -1) {
            
            try {
                this.realisticBiome = RealisticBiomeBase.getBiome(this.patchBiomeId);
            }
            catch (Exception e) {
                throw new RuntimeException("Realistic patch biome " + this.patchBiomeId + " not found. Please make sure this biome is enabled.");
            }
            
            try {
                this.baseBiome = realisticBiome.baseBiome;
            }
            catch (Exception e) {
                throw new RuntimeException("Base patch biome " + this.patchBiomeId + " not found. Please make sure this biome is enabled.");
            }
        }
        
        this.singleBiomeId = (int)ConfigRTG.singleBiomeId;
        
        if (this.isSingleBiomeWorld()) {
            
            this.singleRealisticBiome = RealisticBiomeBase.getBiome(this.singleBiomeId);
            this.singleBaseBiome = singleRealisticBiome.baseBiome;
        }
    }
    
    public RealisticBiomeBase getPatchedRealisticBiome(String exceptionMessage)
    {
        if (this.patchBiomeId < 0) {
            throw new RuntimeException(exceptionMessage);
        }
        else {
            
            if (this.realisticBiome == null) throw new RuntimeException("Problem patching realistic biome.");
            
            return this.realisticBiome;
        }
    }
    
    public BiomeGenBase getPatchedBaseBiome(String exceptionMessage)
    {
        if (this.patchBiomeId < 0) {
            throw new RuntimeException(exceptionMessage);
        }
        else {
            
            if (this.baseBiome == null) throw new RuntimeException("Problem patching base biome.");
            
            return this.baseBiome;
        }
    }
    
    public RealisticBiomeBase getSingleRealisticBiome()
    {
        return this.singleRealisticBiome;
    }
    
    public BiomeGenBase getSingleBaseBiome()
    {
        return this.singleBaseBiome;
    }
    
    public boolean isSingleBiomeWorld()
    {
        return (this.singleBiomeId >= 0) ? true : false;
    }
}
