package gcewing.sg;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class FeatureUnderDesertPyramid extends StructureComponent {

    StructureComponent base;
    
    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
    }
    
    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
    }
    
    public FeatureUnderDesertPyramid() {

    }
    
    public FeatureUnderDesertPyramid(StructureComponent base) {

    }
    
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox clip) {
        return true;
    }

}
