package rtg.world.gen.structure;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;

import com.google.common.collect.Lists;

import rtg.config.rtg.ConfigRTG;
import rtg.util.Logger;

public class MapGenStrongholdRTG extends MapGenStronghold
{
    public final List<Biome> field_151546_e;
    /** is spawned false and set true once the defined BiomeGenBases were compared with the present ones */
    private boolean ranBiomeCheck;
    private ChunkCoordIntPair[] structureCoords;
    private double field_82671_h;
    private int field_82672_i;

    public MapGenStrongholdRTG()
    {
        int strongholdCount = ConfigRTG.strongholdCount;
        double strongholdDistance = (double)ConfigRTG.strongholdDistance;
        int strongholdSpread = ConfigRTG.strongholdSpread;

        this.structureCoords = new ChunkCoordIntPair[strongholdCount]; // Count (Vanilla = 3)
        this.field_82671_h = strongholdDistance; // Distance (Vanilla = 32.0D)
        this.field_82672_i = strongholdSpread; // Spread (Vanilla = 3)
        this.field_151546_e = Lists.<Biome>newArrayList();

        for (Biome biomegenbase : BiomeUtils.getRegisteredBiomes())
        {
            if (biomegenbase != null && biomegenbase.minHeight > 0.0F && !net.minecraftforge.common.BiomeManager.strongHoldBiomesBlackList.contains(biomegenbase))
            {
                this.field_151546_e.add(biomegenbase);
            }
        }
        for (Biome biome : net.minecraftforge.common.BiomeManager.strongHoldBiomes)
        {
            if (!this.field_151546_e.contains(biome))
            {
                this.field_151546_e.add(biome);
            }
        }
    }

    public MapGenStrongholdRTG(Map<String, String> p_i2068_1_)
    {
        this();

        for (Entry<String, String> entry : p_i2068_1_.entrySet())
        {
            if (((String)entry.getKey()).equals("distance"))
            {
                this.field_82671_h = MathHelper.parseDoubleWithDefaultAndMax((String)entry.getValue(), this.field_82671_h, 1.0D);
            }
            else if (((String)entry.getKey()).equals("count"))
            {
                this.structureCoords = new ChunkCoordIntPair[MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.structureCoords.length, 1)];
            }
            else if (((String)entry.getKey()).equals("spread"))
            {
                this.field_82672_i = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.field_82672_i, 1);
            }
        }
    }

    public String getStructureName()
    {
        return "Stronghold";
    }

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        if (!this.ranBiomeCheck)
        {
            Random random = new Random();
            random.setSeed(this.worldObj.getSeed());
            double d0 = random.nextDouble() * Math.PI * 2.0D;
            int i = 1;

            for (int j = 0; j < this.structureCoords.length; ++j)
            {
                double d1 = (1.25D * (double)i + random.nextDouble()) * this.field_82671_h * (double)i;
                int k = (int)Math.round(Math.cos(d0) * d1);
                int l = (int)Math.round(Math.sin(d0) * d1);
                BlockPos blockpos = this.worldObj.getWorldChunkManager().findBiomePosition((k << 4) + 8, (l << 4) + 8, 112, this.field_151546_e, random);

                if (blockpos != null)
                {
                    k = blockpos.getX() >> 4;
                    l = blockpos.getZ() >> 4;
                }

                this.structureCoords[j] = new ChunkCoordIntPair(k, l);
                d0 += (Math.PI * 2D) * (double)i / (double)this.field_82672_i;

                if (j == this.field_82672_i)
                {
                    i += 2 + random.nextInt(5);
                    this.field_82672_i += 1 + random.nextInt(2);
                }
            }

            this.ranBiomeCheck = true;
        }

        for (ChunkCoordIntPair chunkcoordintpair : this.structureCoords)
        {
            if (chunkX == chunkcoordintpair.chunkXPos && chunkZ == chunkcoordintpair.chunkZPos)
            {
                Logger.debug("Generated Stronghold at %d %d", chunkX * 16 + 8, chunkZ * 16 + 8);
                return true;
            }
        }

        return false;
    }

    protected List<BlockPos> getCoordList()
    {
        List<BlockPos> list = Lists.<BlockPos>newArrayList();

        for (ChunkCoordIntPair chunkcoordintpair : this.structureCoords)
        {
            if (chunkcoordintpair != null)
            {
                list.add(chunkcoordintpair.getCenterBlock(64));
            }
        }

        return list;
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        MapGenStrongholdRTG.Start mapgenstronghold$start;

        for (mapgenstronghold$start = new MapGenStrongholdRTG.Start(this.worldObj, this.rand, chunkX, chunkZ); mapgenstronghold$start.getComponents().isEmpty() || ((StructureStrongholdPieces.Stairs2)mapgenstronghold$start.getComponents().get(0)).strongholdPortalRoom == null; mapgenstronghold$start = new MapGenStrongholdRTG.Start(this.worldObj, this.rand, chunkX, chunkZ))
        {
            ;
        }

        return mapgenstronghold$start;
    }

    public static class Start extends StructureStart
    {
        public Start()
        {
        }

        public Start(World worldIn, Random p_i2067_2_, int p_i2067_3_, int p_i2067_4_)
        {
            super(p_i2067_3_, p_i2067_4_);
            StructureStrongholdPieces.prepareStructurePieces();
            StructureStrongholdPieces.Stairs2 structurestrongholdpieces$stairs2 = new StructureStrongholdPieces.Stairs2(0, p_i2067_2_, (p_i2067_3_ << 4) + 2, (p_i2067_4_ << 4) + 2);
            this.components.add(structurestrongholdpieces$stairs2);
            structurestrongholdpieces$stairs2.buildComponent(structurestrongholdpieces$stairs2, this.components, p_i2067_2_);
            List<StructureComponent> list = structurestrongholdpieces$stairs2.field_75026_c;

            while (!list.isEmpty())
            {
                int i = p_i2067_2_.nextInt(list.size());
                StructureComponent structurecomponent = (StructureComponent)list.remove(i);
                structurecomponent.buildComponent(structurestrongholdpieces$stairs2, this.components, p_i2067_2_);
            }

            this.updateBoundingBox();
            this.markAvailableHeight(worldIn, p_i2067_2_, 10);
        }
    }
}