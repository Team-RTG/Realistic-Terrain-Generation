package enhancedbiomes.blocks.tileentity;

import java.util.Iterator;
import java.util.List;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

public class TileEntitySaguaro extends TileEntity {
	public void updateEntity() {
		double d = 0.0625D;
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double)this.xCoord - d, (double)this.yCoord - d, (double)this.zCoord - d, (double)(this.xCoord + 1 + d), (double)(this.yCoord + 1 + d), (double)(this.zCoord + 1 + d));
        List list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
        Iterator iterator = list.iterator();
        EntityLivingBase entity;

        while (iterator.hasNext()) {
            entity = (EntityLivingBase)iterator.next();
            entity.attackEntityFrom(DamageSource.cactus, 1.0F);
        }
    }
}
