package rtg.world.gen.structure;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.StructureMineshaftStart;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenMineshaftRTG extends MapGenMineshaft
{
    private double field_82673_e = 0.004D;
    private static final String __OBFID = "CL_00000443";

    public MapGenMineshaftRTG() {}

    public String func_143025_a()
    {
        return "Mineshaft";
    }

    public MapGenMineshaftRTG(Map p_i2034_1_)
    {
        //FMLLog.log(Level.INFO, "RTG MapGenMineshaftRTG");
        
        Iterator iterator = p_i2034_1_.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("chance"))
            {
                this.field_82673_e = MathHelper.parseDoubleWithDefault((String)entry.getValue(), this.field_82673_e);
            }
        }
    }

    protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_)
    {
        return this.rand.nextDouble() < this.field_82673_e && this.rand.nextInt(80) < Math.max(Math.abs(p_75047_1_), Math.abs(p_75047_2_));
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        //FMLLog.log(Level.INFO, "RTG getStructureStart");
        return new Start(worldObj, rand, chunkX, chunkZ);
    }
    
    public static class Start extends StructureMineshaftStart {
        public Start() {
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ) {
            
            super(worldIn, random, chunkX, chunkZ);
            
            //FMLLog.log(Level.INFO, "RTG MapGen Started");

        }
    }
}