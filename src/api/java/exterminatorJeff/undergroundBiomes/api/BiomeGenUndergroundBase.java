package exterminatorJeff.undergroundBiomes.api;

public class BiomeGenUndergroundBase {
    
    
    public String biomeName = "";
    
    public final int biomeID;
    
    public boolean hasStrata = false;
    
    public StrataLayer[] strata;
    
    public final PerlinNoiseGenerator strataNoise;
    
    //public int fillerBlock = 0;
    //public byte fillerBlockMetadata;

    public final UBStoneCodes fillerBlockCodes;
    
    public BiomeGenUndergroundBase(int ID, NamedBlock filler, int metadataValue, BiomeGenUndergroundBase [] biomeList){
        this.biomeID = ID;
        this.fillerBlockCodes = new UBStoneCodes(filler,metadataValue);
        strataNoise = new PerlinNoiseGenerator(1);
        biomeList[ID] = this;
    }

    public BiomeGenUndergroundBase(int ID, NamedBlock filler, int metadataValue, BiomeGenUndergroundBase [] biomeList,
            StrataLayer [] strataLayers){
        this.biomeID = ID;
        this.fillerBlockCodes = new UBStoneCodes(filler,metadataValue);
        strataNoise = new PerlinNoiseGenerator(1);
        biomeList[ID] = this;
        AddStrataLayers(strataLayers);
    }
    
    public BiomeGenUndergroundBase AddStrataLayers(StrataLayer[] strata){
        hasStrata = true;
        this.strata = strata;    
        return this;
    }
    
    public UBStoneCodes getStrataBlockAtLayer(int y){
        for(int i = 0; i < strata.length; i++){
            if(strata[i].valueIsInLayer(y) == true){
                return strata[i].codes;
            }
        }
        return fillerBlockCodes;
    }
    
    public BiomeGenUndergroundBase setName(String name){
        this.biomeName = name;
        return this;
    }
    
}


