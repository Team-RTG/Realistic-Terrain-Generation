package com.sdj64.highlands.block;

import com.sdj64.highlands.References;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class HighlandsBlocks {

	public static final int NUM_TREE_TYPES = 7;
	public static final int NUM_PLANTS = 9;
	
	public static final CreativeTabs tabHighlands = new CreativeTabs("highlands")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(Blocks.sapling);
        }
    };
	
    //tree blocks
	public static Block[] planks;
	public static Block[] woods;
	public static Block[] leaves;
	public static Block[] saplings;
	
	//wood products
	public static Block[] doors;
	public static Block[] fences;
	public static Block[] slabs;
	public static Block[] doubleSlabs;
	public static Block[] stairs;
	
	//plants
	public static Block[] plants;
	
	public static void constructBlocks()
	{
		//initialize EnumType meta lookup
		EnumTypeTree.ASPEN.setMetaLookup();
		EnumTypeTree.POPLAR.setMetaLookup();
		EnumTypeTree.EUCA.setMetaLookup();
		EnumTypeTree.PALM.setMetaLookup();
		EnumTypeTree.FIR.setMetaLookup();
		EnumTypeTree.REDWOOD.setMetaLookup();
		EnumTypeTree.BAMBOO.setMetaLookup();
		
		EnumTypePlant.BLUEFLOWER.setMetaLookup();
		EnumTypePlant.CATTAIL.setMetaLookup();
		EnumTypePlant.COTTON.setMetaLookup();
		EnumTypePlant.BLUEBERRYBUSH.setMetaLookup();
		EnumTypePlant.RASPBERRYBUSH.setMetaLookup();
		EnumTypePlant.THORNBUSH.setMetaLookup();
		EnumTypePlant.LAVENDER.setMetaLookup();
		EnumTypePlant.GREENLEAF.setMetaLookup();
		EnumTypePlant.DUNEGRASS.setMetaLookup();
		
		//initialize arrays
		planks = new Block[NUM_TREE_TYPES];
		woods = new Block[NUM_TREE_TYPES];
		leaves = new Block[NUM_TREE_TYPES];
		saplings = new Block[NUM_TREE_TYPES];
		doors = new Block[NUM_TREE_TYPES];
		fences = new Block[NUM_TREE_TYPES];
		slabs = new Block[NUM_TREE_TYPES];
		doubleSlabs = new Block[NUM_TREE_TYPES];
		stairs = new Block[NUM_TREE_TYPES];
		
		plants = new Block[NUM_PLANTS];
		
		//initialize blocks within arrays
		for(int i = 0; i < NUM_TREE_TYPES; i++)
		{
			planks[i] = new BlockHighlandsPlanks(EnumTypeTree.META_LOOKUP[i], References.MOD_ID + "_" + EnumTypeTree.META_LOOKUP[i].getName());
			woods[i] = new BlockHighlandsLog(EnumTypeTree.META_LOOKUP[i], References.MOD_ID + "_" + EnumTypeTree.META_LOOKUP[i].getName());
			leaves[i] = new BlockHighlandsLeaves(EnumTypeTree.META_LOOKUP[i], References.MOD_ID + "_" + EnumTypeTree.META_LOOKUP[i].getName());
			saplings[i] = new BlockHighlandsSapling(EnumTypeTree.META_LOOKUP[i], References.MOD_ID + "_" + EnumTypeTree.META_LOOKUP[i].getName());
			
			
			GameRegistry.registerBlock(planks[i], planks[i].getUnlocalizedName().substring(15));
			GameRegistry.registerBlock(woods[i], woods[i].getUnlocalizedName().substring(15));
			GameRegistry.registerBlock(leaves[i], leaves[i].getUnlocalizedName().substring(15));
			GameRegistry.registerBlock(saplings[i], saplings[i].getUnlocalizedName().substring(15));
			
			OreDictionary.registerOre("logWood", woods[i]);
			OreDictionary.registerOre("plankWood", planks[i]);
			OreDictionary.registerOre("treeLeaves", leaves[i]);
			OreDictionary.registerOre("treeSapling", saplings[i]);
			
			Blocks.fire.setFireInfo(leaves[i], 30, 60);
			Blocks.fire.setFireInfo(planks[i], 5, 20);
			Blocks.fire.setFireInfo(woods[i], 5, 5);
		}

		for(int i = 0; i < NUM_PLANTS; i++){
			plants[i] = new BlockHighlandsPlant(EnumTypePlant.META_LOOKUP[i].name);
			
			GameRegistry.registerBlock(plants[i], plants[i].getUnlocalizedName().substring(15));
			
			Blocks.fire.setFireInfo(plants[i], 60, 100);
		}
		((BlockHighlandsPlant)plants[EnumTypePlant.THORNBUSH.meta]).thornbush = true;
	}
	
	public static void registerRenders()
	{
		for(int i = 0; i < NUM_TREE_TYPES; i++)
		{
			registerRender(planks[i]);
			registerRender(woods[i]);
			registerRender(leaves[i]);
			registerRender(saplings[i]);
		}
		
		for(int i = 0; i < NUM_PLANTS; i++){
			registerRender(plants[i]);
		}
	}
	
	private static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, 
				new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(15), "inventory"));
		/*
		if(block instanceof BlockHighlandsLeaves){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, 
					new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(15), "check_decay=false,decayable=false"));
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, 
					new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(15), "check_decay=true,decayable=false"));
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, 
					new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(15), "check_decay=false,decayable=true"));
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, 
					new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(15), "check_decay=true,decayable=true"));
		}
		if(block instanceof BlockHighlandsSapling){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, 
					new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(15), "stage=0"));
		}
		*/
	}
	
	
	
	
	public static enum EnumTypeTree implements IStringSerializable
    {
        ASPEN(0, "aspen"),
        POPLAR(1, "poplar"),
        EUCA(2, "eucalyptus"),
        PALM(3, "palm"),
        FIR(4, "fir"),
        REDWOOD(5, "redwood"),
		BAMBOO(6, "bamboo");
        private static final EnumTypeTree[] META_LOOKUP = new EnumTypeTree[values().length];
        private final int meta;
        private final String name;

        private EnumTypeTree(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }
        
        public void setMetaLookup(){ EnumTypeTree.META_LOOKUP[this.meta] = this;}

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
	
	public static enum EnumTypePlant implements IStringSerializable
    {
        BLUEFLOWER(0, "blue_flower"),
        CATTAIL(1, "cattail"),
        COTTON(2, "cotton"),
        RASPBERRYBUSH(3, "raspberry_bush"),
        BLUEBERRYBUSH(4, "blueberry_bush"),
        THORNBUSH(5, "thorn_bush"),
        LAVENDER(6, "lavender"),
        GREENLEAF(7, "green_leaf"),
		DUNEGRASS(8, "dune_grass");
        private static final EnumTypePlant[] META_LOOKUP = new EnumTypePlant[values().length];
        private final int meta;
        private final String name;

        private EnumTypePlant(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }
        
        public void setMetaLookup(){ EnumTypePlant.META_LOOKUP[this.meta] = this;}

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
