package rtg.world.gen.structure;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import rtg.config.rtg.ConfigRTG;
import rtg.village.*;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces.Road;

public class MapGenVillageRTG extends MapGenVillage
{

    public static final StructureVillagePiecesRTG StructureVillagePieces = null;

	public static List villageSpawnBiomes = BiomeBase.arrVillageBiomes;
    
    private int terrainType = ConfigRTG.villageSize;
    private int field_82665_g;
    private int field_82666_h;

    public MapGenVillageRTG()
    {
        int minDistanceVillages = ConfigRTG.minDistanceVillages;
        int maxDistanceVillages = ConfigRTG.maxDistanceVillages;
        
        minDistanceVillages = (minDistanceVillages > maxDistanceVillages) ? maxDistanceVillages : minDistanceVillages;
        
        this.field_82665_g = maxDistanceVillages;
        this.field_82666_h = minDistanceVillages;
    }

    public MapGenVillageRTG(Map par1Map)
    {
        this();
        Iterator iterator = par1Map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("size"))
            {
                this.terrainType = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.terrainType, 0);
            }
            else if (((String)entry.getKey()).equals("distance"))
            {
                this.field_82665_g = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.field_82665_g, this.field_82666_h + 1);
            }
        }
    }

    public String func_143025_a()
    {
        return "Village";
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        if (villageSpawnBiomes.size() < 1) {
            return false;
        }
        
        WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) worldObj.getWorldChunkManager();
        int worldX = par1 * 16;
        int worldZ = par2 * 16;
        RealisticBiomeBase realisticBiome = cmr.getBiomeDataAt(worldX + 16, worldZ + 16);
        BiomeGenBase biome = realisticBiome.baseBiome;
        boolean canSpawnVillage = false;
        
        int k = par1;
        int l = par2;

        if (par1 < 0)
        {
            par1 -= this.field_82665_g - 1;
        }

        if (par2 < 0)
        {
            par2 -= this.field_82665_g - 1;
        }

        int i1 = par1 / this.field_82665_g;
        int j1 = par2 / this.field_82665_g;
        Random random = this.worldObj.setRandomSeed(i1, j1, 10387312);
        i1 *= this.field_82665_g;
        j1 *= this.field_82665_g;
        i1 += random.nextInt(this.field_82665_g - this.field_82666_h);
        j1 += random.nextInt(this.field_82665_g - this.field_82666_h);

        if (k == i1 && l == j1)
        {
            for (int intBiomeIndex = 0; intBiomeIndex < villageSpawnBiomes.size(); intBiomeIndex++) {
                
                if (villageSpawnBiomes.get(intBiomeIndex) == biome) {
                    canSpawnVillage = true;
                    break;
                }
            }
        }

        return canSpawnVillage;
    }

    protected StructureStart getStructureStart(int par1, int par2) {
		return new MapGenVillageRTG.Start(this.worldObj, this.rand, par1, par2, this.terrainType);
    }
    
    public static class Start extends StructureStart
    {
    	/** well ... thats what it does */
    	private boolean hasMoreThanTwoComponents;

    	public Start() {}

    	public Start(World p_i2092_1_, Random p_i2092_2_, int p_i2092_3_, int p_i2092_4_, int p_i2092_5_)
    	{
    		super(p_i2092_3_, p_i2092_4_);
    		List list = VillagePieceSelection.getStructureVillageWeightedPieceList(p_i2092_2_, p_i2092_5_, null);
    		StructureVillagePiecesRTG.Start start = new StructureVillagePiecesRTG.Start(p_i2092_1_.getWorldChunkManager(), 0, p_i2092_2_, (p_i2092_3_ << 4) + 2, (p_i2092_4_ << 4) + 2, list, p_i2092_5_);
    		this.components.add(start);
    		start.buildComponent(start, this.components, p_i2092_2_);
    		List list1 = start.field_74930_j;
    		List list2 = start.field_74932_i;
    		int l;

    		while (!list1.isEmpty() || !list2.isEmpty())
    		{
    			StructureComponent structurecomponent;

    			if (list1.isEmpty())
    			{
    				l = p_i2092_2_.nextInt(list2.size());
    				structurecomponent = (StructureComponent)list2.remove(l);
    				structurecomponent.buildComponent(start, this.components, p_i2092_2_);
    			}
    			else
    			{
    				l = p_i2092_2_.nextInt(list1.size());
    				structurecomponent = (StructureComponent)list1.remove(l);
    				structurecomponent.buildComponent(start, this.components, p_i2092_2_);
    			}
    		}

    		this.updateBoundingBox();
    		l = 0;
    		Iterator iterator = this.components.iterator();

    		while (iterator.hasNext())
    		{
    			StructureComponent structurecomponent1 = (StructureComponent)iterator.next();

    			if (!(structurecomponent1 instanceof Road))
    			{
    				++l;
    			}
    		}

    		this.hasMoreThanTwoComponents = l > 2;
    	}

    	/**
    	 * currently only defined for Villages, returns true if Village has more than 2 non-road components
    	 */
    	 public boolean isSizeableStructure()
    	{
    		 return this.hasMoreThanTwoComponents;
    	}

    	public void func_143022_a(NBTTagCompound p_143022_1_)
    	{
    		super.func_143022_a(p_143022_1_);
    		p_143022_1_.setBoolean("Valid", this.hasMoreThanTwoComponents);
    	}

    	public void func_143017_b(NBTTagCompound p_143017_1_)
    	{
    		super.func_143017_b(p_143017_1_);
    		this.hasMoreThanTwoComponents = p_143017_1_.getBoolean("Valid");
    	}
    }
}