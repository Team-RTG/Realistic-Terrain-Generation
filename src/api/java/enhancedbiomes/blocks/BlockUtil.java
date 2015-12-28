package enhancedbiomes.blocks;

import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.gen.WorldGenSaguaro;
import enhancedbiomes.world.gen.geometry.WorldGenCone;
import enhancedbiomes.world.gen.geometry.WorldGenConeVariation;
import enhancedbiomes.world.gen.geometry.WorldGenSphere;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCactus;

public class BlockUtil extends Block
{
	public static int x1;
	public static int y1;
	public static int z1;
	
	public static int x2;
	public static int y2;
	public static int z2;
	
	public static int x3;
	public static int y3;
	public static int z3;
	
	public static int a = 0;
	
	public BlockUtil()
    {
        super(Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {    
    	System.out.println("Util block activated");	
    	System.out.println("x1 = " + x1 + ", y1 = " + y1 + ", z1 = " + z1);	
    	System.out.println("x2 = " + x2 + ", y2 = " + y2 + ", z2 = " + z2);	
    	System.out.println("x3 = " + x3 + ", y3 = " + y3 + ", z3 = " + z3);	
    	a++;
    	if(a == 2) {x1 = x; y1 = y; z1 = z;}
    	else if(a == 4) {x2 = x; y2 = y; z2 = z;}
    	else if(a == 6) {x3 = x; y3 = y; z3 = z;}
    	else if(a > 6) {
    		int xmin = x2 - x1, ymin = y2 - y1, zmin = z2 - z1, xmax = x3 - x1, ymax = y3 - y1, zmax = z3 - z1;
    		for(int xpos = xmin; xpos <= xmax; xpos++) {
    			for(int ypos = ymin; ypos <= ymax; ypos++) {
    				for(int zpos = zmin; zpos <= zmax; zpos++) {
    	    			Block block = par1World.getBlock(x1 + xpos, y1 + ypos, z1 + zpos);
    	    			int meta = par1World.getBlockMetadata(x1 + xpos, y1 + ypos, z1 + zpos);
    	    			System.err.println("this.placeBlockAtCurrentPosition(p_74875_1_, " + block.getUnlocalizedName() + ", " + meta + ", " + xpos + ", " + ypos + ", " + zpos + ", " + "p_74875_3_);");
    	    		}
        		}
    		}
    	}
    	//new WorldGenSaguaro().generate(par1World, par1World.rand, x, y, z);
    	//TreeGen.kapok(par1World.rand).generate(par1World, par1World.rand, x + 2, y, z);
    	/*int height = 22;
    	new WorldGenCone(EnhancedBiomesBlocks.leavesSpruce, 2, 4.25D, 7).generate(par1World, par1World.rand, x, y + height - 5, z);
    	new WorldGenCone(EnhancedBiomesBlocks.leavesSpruce, 2, 4.25D, 7).generate(par1World, par1World.rand, x, y + height - 8, z);
    	new WorldGenCone(EnhancedBiomesBlocks.leavesSpruce, 2, 4.25D, 7).generate(par1World, par1World.rand, x, y + height - 11, z);
    	new WorldGenCone(EnhancedBiomesBlocks.leavesSpruce, 2, 4.25D, 7).generate(par1World, par1World.rand, x, y + height - 14, z);
    	new WorldGenCone(EnhancedBiomesBlocks.logSpruce, 2, 1.25D, height).generate(par1World, par1World.rand, x, y, z);*/
    	return true;
    }
}
