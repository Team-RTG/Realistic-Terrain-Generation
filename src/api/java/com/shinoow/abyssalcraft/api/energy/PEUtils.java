/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2016 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.api.energy;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum.AmplifierType;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;
import com.shinoow.abyssalcraft.api.entity.EntityUtil;

/**
 * Backbone for handling interactions between<br>
 * various PE mechanics
 * @author shinoow
 *
 */
public class PEUtils {

	/**
	 * Attempts to transfer PE from a Manipulator to nearby Players
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @param manipulator PE Manipulator
	 * @param range Transfer Range
	 */
	public static void transferPEToNearbyPlayers(World world, BlockPos pos, IEnergyManipulator manipulator, int range){

		int xp = pos.getX();
		int yp = pos.getY();
		int zp = pos.getZ();

		List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)).expand(range, range, range));

		for(EntityPlayer player : players)
			if(EntityUtil.hasNecronomicon(player)){
				ItemStack item = player.getHeldItem(EnumHand.MAIN_HAND);
				ItemStack item1 = player.getHeldItem(EnumHand.OFF_HAND);
				if(item != null && item.getItem() instanceof IEnergyTransporterItem ||
						item1 != null && item1.getItem() instanceof IEnergyTransporterItem){
					if(!world.isRemote){
						transferPEToStack(item, manipulator);
						transferPEToStack(item1, manipulator);
					}
					Vec3d vec = new Vec3d(xp, yp, zp);
					Vec3d t = new Vec3d(player.posX, player.posY, player.posZ);
					double vx = vec.xCoord > t.xCoord ? vec.xCoord - t.xCoord : vec.xCoord < t.xCoord ? t.xCoord - vec.xCoord : 0;
					double vy = vec.yCoord > t.yCoord ? vec.yCoord - t.yCoord : vec.yCoord < t.yCoord ? t.yCoord - vec.yCoord : 0;
					double vz = vec.zCoord > t.zCoord ? vec.zCoord - t.zCoord : vec.zCoord < t.zCoord ? t.zCoord - vec.zCoord : 0;

					for(int i = 1; i < 11; i++){
						Vec3d v = new Vec3d(vec.xCoord > t.xCoord ? vec.xCoord - vx/i : vec.xCoord < t.xCoord ? vec.xCoord + vx/i : t.xCoord,
								vec.yCoord > t.yCoord ? vec.yCoord - vy/i : vec.yCoord < t.yCoord ? vec.yCoord + vy/i : t.yCoord,
										vec.zCoord > t.zCoord ? vec.zCoord - vz/i : vec.zCoord < t.zCoord ? vec.zCoord + vz/i : t.zCoord);
						Vec3d v2 = new Vec3d(vec.xCoord > t.xCoord ? t.xCoord + vx/i : vec.xCoord < t.xCoord ? t.xCoord - vx/i : t.xCoord,
								vec.yCoord > t.yCoord ? t.yCoord + vy/i : vec.yCoord < t.yCoord ? t.yCoord - vy/i : t.yCoord,
										vec.zCoord > t.zCoord ? t.zCoord + vz/i : vec.zCoord < t.zCoord ? t.zCoord - vz/i : t.zCoord);
						for(double d = 0; d < 0.1; d += 0.05){
							int x = vec.xCoord > t.xCoord ? -1 : vec.xCoord < t.xCoord ? 1 : 0;
							int y = vec.yCoord > t.yCoord ? -1 : vec.yCoord < t.yCoord ? 1 : 0;
							int z = vec.zCoord > t.zCoord ? -1 : vec.zCoord < t.zCoord ? 1 : 0;
							AbyssalCraftAPI.getInternalMethodHandler().spawnParticle("PEStream", world, v.xCoord + 0.5 + x*d, v.yCoord + 0.5 + y*d, v.zCoord + 0.5 + z*d, 0,0,0);
							AbyssalCraftAPI.getInternalMethodHandler().spawnParticle("PEStream", world, v2.xCoord + 0.5 + x*d, v2.yCoord + 0.5 + y*d, v2.zCoord + 0.5 + z*d, 0,0,0);
						}
					}
				}
			}
	}

	/**
	 * Shortcut method for Transferring PE to an ItemStack
	 * @param stack ItemStack to potentially transfer PE to
	 * @param manipulator PE Manipulator to transfer said PE
	 */
	private static void transferPEToStack(ItemStack stack, IEnergyManipulator manipulator){
		if(stack != null && stack.getItem() instanceof IEnergyTransporterItem)
			if(((IEnergyTransporterItem) stack.getItem()).canAcceptPEExternally(stack)){
				((IEnergyTransporterItem) stack.getItem()).addEnergy(stack, manipulator.getEnergyQuanta());
				manipulator.addTolerance(manipulator.isActive() ? 4 : 2);
			}
	}

	/**
	 * Attempts to transfer PE from a Manipulator to nearby Collectors
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @param manipulator PE Manipulator
	 * @param boost Any range boost applied to the manipulator
	 */
	public static void transferPEToCollectors(World world, BlockPos pos, IEnergyManipulator manipulator, int boost){

		int xp = pos.getX();
		int yp = pos.getY();
		int zp = pos.getZ();

		List<TileEntity> collectors = Lists.newArrayList();

		for(int x = -1*(3+boost); x <= 3+boost; x++)
			for(int y = 0; y <= getRangeAmplifiers(world, pos); y++)
				for(int z = -1*(3+boost); z <= 3+boost; z++)
					if(x < -2 || x > 2 || z < -2 || z > 2)
						if(isCollector(world.getTileEntity(new BlockPos(xp + x, yp - y, zp + z))) && collectors.size() < 20)
							collectors.add(world.getTileEntity(new BlockPos(xp + x, yp - y, zp + z)));

		for(TileEntity tile : collectors)
			if(checkForAdjacentCollectors(world, tile.getPos()))
				if(world.rand.nextInt(120-(int)(20 * manipulator.getAmplifier(AmplifierType.DURATION))) == 0)
					if(((IEnergyCollector) tile).canAcceptPE()){
						if(!world.isRemote){
							((IEnergyCollector) tile).addEnergy(manipulator.getEnergyQuanta());
							manipulator.addTolerance(manipulator.isActive() ? 2 : 1);
						}
						Vec3d vec = new Vec3d(xp, yp, zp);
						Vec3d t = new Vec3d(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ());
						double vx = vec.xCoord > t.xCoord ? vec.xCoord - t.xCoord : vec.xCoord < t.xCoord ? t.xCoord - vec.xCoord : 0;
						double vy = vec.yCoord > t.yCoord ? vec.yCoord - t.yCoord : vec.yCoord < t.yCoord ? t.yCoord - vec.yCoord : 0;
						double vz = vec.zCoord > t.zCoord ? vec.zCoord - t.zCoord : vec.zCoord < t.zCoord ? t.zCoord - vec.zCoord : 0;

						for(int i = 1; i < 11; i++){
							Vec3d v = new Vec3d(vec.xCoord > t.xCoord ? vec.xCoord - vx/i : vec.xCoord < t.xCoord ? vec.xCoord + vx/i : t.xCoord,
									vec.yCoord > t.yCoord ? vec.yCoord - vy/i : vec.yCoord < t.yCoord ? vec.yCoord + vy/i : t.yCoord,
											vec.zCoord > t.zCoord ? vec.zCoord - vz/i : vec.zCoord < t.zCoord ? vec.zCoord + vz/i : t.zCoord);
							Vec3d v2 = new Vec3d(vec.xCoord > t.xCoord ? t.xCoord + vx/i : vec.xCoord < t.xCoord ? t.xCoord - vx/i : t.xCoord,
									vec.yCoord > t.yCoord ? t.yCoord + vy/i : vec.yCoord < t.yCoord ? t.yCoord - vy/i : t.yCoord,
											vec.zCoord > t.zCoord ? t.zCoord + vz/i : vec.zCoord < t.zCoord ? t.zCoord - vz/i : t.zCoord);
							for(double d = 0; d < 0.1; d += 0.05){
								int x = vec.xCoord > t.xCoord ? -1 : vec.xCoord < t.xCoord ? 1 : 0;
								int y = vec.yCoord > t.yCoord ? -1 : vec.yCoord < t.yCoord ? 1 : 0;
								int z = vec.zCoord > t.zCoord ? -1 : vec.zCoord < t.zCoord ? 1 : 0;
								AbyssalCraftAPI.getInternalMethodHandler().spawnParticle("PEStream", world, v.xCoord + 0.5 + x*d, v.yCoord + 0.5 + y*d, v.zCoord + 0.5 + z*d, 0,0,0);
								AbyssalCraftAPI.getInternalMethodHandler().spawnParticle("PEStream", world, v2.xCoord + 0.5 + x*d, v2.yCoord + 0.5 + y*d, v2.zCoord + 0.5 + z*d, 0,0,0);
							}
						}
					}
	}

	/**
	 * Utility method for clearing the active Deity and Amplifier<br>
	 * when a PE Manipulator no longer is active. Should be called in<br>
	 * update() ({@link ITickable}) whenever the manipulator isn't active<br>
	 * to ensure the data is properly erased when it should be.
	 * @param manipulator PE Manipulator to reset
	 */
	public static void clearManipulatorData(IEnergyManipulator manipulator){
		if(manipulator.getActiveAmplifier() != null || manipulator.getActiveDeity() != null){
			NBTTagCompound tag = new NBTTagCompound();
			((TileEntity) manipulator).writeToNBT(tag);
			tag.setString("ActiveDeity", "");
			tag.setString("ActiveAmplifier", "");
			manipulator.setActiveDeity(null);
			manipulator.setActiveAmplifier(null);
			((TileEntity) manipulator).readFromNBT(tag);
		}
	}

	/**
	 * Utility method for reading specific NBT data in a PE Manipulator.<br>
	 * Call in your TE's readFromNBT!
	 * @param manipulator PE Manipulator
	 * @param compound NBT Tag Compound
	 */
	public static void readManipulatorNBT(IEnergyManipulator manipulator, NBTTagCompound compound){
		if(compound.hasKey("ActiveDeity") && !compound.getString("ActiveDeity").equals(""))
			manipulator.setActiveDeity(DeityType.valueOf(compound.getString("ActiveDeity")));
		if(compound.hasKey("ActiveAmplifier") && !compound.getString("ActiveAmplifier").equals(""))
			manipulator.setActiveAmplifier(AmplifierType.valueOf(compound.getString("ActiveAmplifier")));
	}

	/**
	 * Utility method for writing specific NBT data in a PE Manipulator.<br>
	 * Call in your TE's writeToNBT!
	 * @param manipulator PE Manipulator
	 * @param compound NBT Tag Compound
	 */
	public static void writeManipulatorNBT(IEnergyManipulator manipulator, NBTTagCompound compound){
		if(manipulator.getActiveDeity() != null)
			compound.setString("ActiveDeity", manipulator.getActiveDeity().name());
		else compound.setString("ActiveDeity", "");
		if(manipulator.getActiveAmplifier() != null)
			compound.setString("ActiveAmplifier", manipulator.getActiveAmplifier().name());
		else compound.setString("ActiveAmplifier", "");
	}

	/**
	 * Checks for any range amplifying blocks below a specific BlockPos
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @return A number between 0 and 2, representing the amount of range amplifiers below this BlockPos
	 */
	public static int getRangeAmplifiers(World world, BlockPos pos){
		Block block1 = world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock();
		Block block2 = world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 2, pos.getZ())).getBlock();
		int num = 0;
		if(block1 != null && block1 instanceof IEnergyAmplifier &&
				((IEnergyAmplifier) block1).getAmplifierType() == AmplifierType.RANGE)
			num = 1;
		if(block1 != null && block1 instanceof IEnergyAmplifier &&
				((IEnergyAmplifier) block1).getAmplifierType() == AmplifierType.RANGE
				&& block2 != null && block2 instanceof IEnergyAmplifier &&
				((IEnergyAmplifier) block2).getAmplifierType() == AmplifierType.RANGE)
			num = 2;
		return num;
	}

	/**
	 * Checks if a TileEntity is a PE Collector (extends IEnergyCollector)
	 * @param tile TileEntity to check
	 * @return True if the TileEntity is a PE Collector, otherwise false
	 */
	public static boolean isCollector(TileEntity tile){
		if(tile != null) return tile instanceof IEnergyCollector;
		return false;
	}

	/**
	 * Checks if a TileEntity is a PE Manipulator (extends IEnergyManipulator)
	 * @param tile TileEntity to check
	 * @return True if the TileEntity is a PE Manipulator, otherwise false
	 */
	public static boolean isManipulator(TileEntity tile){
		if(tile != null) return tile instanceof IEnergyManipulator;
		return false;
	}

	/**
	 * Checks if a TileEntity is a PE Container (extends IEnergyContainer)
	 * @param tile TileEntity to check
	 * @return True if the TileEntity is a PE Collector, otherwise false
	 */
	public static boolean isContainer(TileEntity tile){
		if(tile != null) return tile instanceof IEnergyContainer;
		return false;
	}

	/**
	 * Checks that the BlockPos has no adjacent PE Collectors
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @return True if the BlockPos has no adjacent PE Collectors, otherwise false
	 */
	public static boolean checkForAdjacentCollectors(World world, BlockPos pos){
		for(EnumFacing face : EnumFacing.values())
			if(isCollector(world.getTileEntity(pos.offset(face))))
				return false;
		return true;
	}

	/**
	 * Checks that the BlockPos has no adjacent PE Manipulators
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @return True if the BlockPos has no adjacent PE Manipulators, otherwise false
	 */
	public static boolean checkForAdjacentManipulators(World world, BlockPos pos){
		for(EnumFacing face : EnumFacing.values())
			if(isManipulator(world.getTileEntity(pos.offset(face))))
				return false;
		if(isManipulator(world.getTileEntity(pos.up(2)))) //Might remove this extra check
			return false;
		if(isManipulator(world.getTileEntity(pos.down(2)))) // ^
			return false;
		return true;
	}

	/**
	 * Checks for anything accepting PE in the given direction
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @param face Direction to check
	 * @param dist Transport distance
	 * @return True if a PE Container is found, otherwise false
	 */
	public static boolean canTransfer(World world, BlockPos pos, EnumFacing face, int dist){

		for(int i = 1; i < dist+1; i++){
			if(world.getBlockState(pos.offset(face, i)).getBlock().isFullCube(world.getBlockState(pos.offset(face, i))) && !world.isAirBlock(pos.offset(face, i))) return false;
			if(isContainer(world.getTileEntity(pos.offset(face, i))))
				return ((IEnergyContainer) world.getTileEntity(pos.offset(face, i))).canAcceptPE();
		}
		return false;
	}

	/**
	 * Looks for a PE Collector in the given direction
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @param face Direction to check
	 * @param dist Transport distance
	 * @return A PE Collector if one is found, otherwise null
	 */
	public static IEnergyCollector getCollector(World world, BlockPos pos, EnumFacing face, int dist){
		for(int i = 1; i < dist+1; i++)
			if(isCollector(world.getTileEntity(pos.offset(face, i))))
				return (IEnergyCollector) world.getTileEntity(pos.offset(face, i));
		return null;
	}

	/**
	 * Looks for a PE Container in the given distance
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @param face Direction to check
	 * @param dist Transport distance
	 * @return A TileEntity that can accept PE, otherwise null
	 */
	public static IEnergyContainer getContainer(World world, BlockPos pos, EnumFacing face, int dist){
		for(int i = 1; i < dist+1; i++)
			if(isContainer(world.getTileEntity(pos.offset(face, i))))
				return (IEnergyContainer) world.getTileEntity(pos.offset(face, i));
		return null;
	}

	/**
	 * Attempts to collect PE from nearby PE Collectors
	 * @param relay PE Relay
	 * @param world Current World
	 * @param pos Current BlockPos
	 * @param face Direction to collect from
	 * @param amount Amount of PE to drain
	 */
	public static void collectNearbyPE(IEnergyTransporter relay, World world, BlockPos pos, EnumFacing face, float amount){
		if(relay.canAcceptPE()){
			IEnergyCollector collector = getCollector(world, pos, face, 1);
			if(collector != null && collector.canTransferPE())
				if(!world.isRemote)
					relay.addEnergy(collector.consumeEnergy(amount));

		}
	}
}