package rtg.api.biome.vanilla.config;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaIcePlainsSpikes extends BiomeConfigVanillaBase
{
    public static final String decorationIceTreesId = "decorationIceTrees";
    public static final String decorationIceTreesName = "RTG Decoration: Ice Trees";
    
    public static final String decorationIceShrubsId = "decorationIceShrubs";
    public static final String decorationIceShrubsName = "RTG Decoration: Ice Shrubs";
    
    public static final String iceSpikeChanceId = "iceSpikeChance";
    public static final String iceSpikeChanceName = "Packed Ice Spike/Lake Chance";
    
    public BiomeConfigVanillaIcePlainsSpikes()
    {
        super("iceplainsspikes");
        
        this.addProperty(new BiomeConfigProperty(decorationIceTreesId, Type.BOOLEAN, decorationIceTreesName, "", false));
        this.addProperty(new BiomeConfigProperty(decorationIceShrubsId, Type.BOOLEAN, decorationIceShrubsName, "", false));
        this.addProperty(new BiomeConfigProperty(iceSpikeChanceId, Type.INTEGER, iceSpikeChanceName, "1/x chance that packed ice spikes/lakes will generate when they normally would." + Configuration.NEW_LINE + "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE + "Set to 0 to disable ice spikes." + Configuration.NEW_LINE, 1, 0, Integer.MAX_VALUE));
    }
}
