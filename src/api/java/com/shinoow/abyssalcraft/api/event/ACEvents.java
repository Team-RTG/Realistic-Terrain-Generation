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
package com.shinoow.abyssalcraft.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;

/**
 * A class containing events used by AbyssalCraft.<br>
 * All events are fired on the {@link MinecraftForge#EVENT_BUS} if not stated otherwise.
 * 
 * @author shinoow
 *
 * @since 1.4
 */
public class ACEvents {

	/**
	 * ItemTransmutedEvent is fired when a player picks up an Item from the output slot of a Transmutator.<br>
	 * <br>
	 * {@link #transmuted} contains the Item found in the Transmutator output slot. <br>
	 * <br>
	 * This event is not {@link Cancelable}.
	 * <br>
	 * This event does not have a result. {@link HasResult}<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 * 
	 * @author shinoow
	 * 
	 * @since 1.4
	 */
	public static class ItemTransmutedEvent extends PlayerEvent {

		public final ItemStack transmuted;

		public ItemTransmutedEvent(EntityPlayer player, ItemStack crafting){
			super(player);
			transmuted = crafting;
		}
	}

	/**
	 * ItemCrystallizedEvent is fired when a player picks up an Item from the output slot of a Crystallizer.<br>
	 * <br>
	 * {@link #crystallized} contains the Item found in the Crystallizer output slot. <br>
	 * <br>
	 * This event is not {@link Cancelable}.
	 * <br>
	 * This event does not have a result. {@link HasResult}<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 * 
	 * @author shinoow
	 * 
	 * @since 1.4
	 */
	public static class ItemCrystallizedEvent extends PlayerEvent {

		public final ItemStack crystallized;

		public ItemCrystallizedEvent(EntityPlayer player, ItemStack crafting){
			super(player);
			crystallized = crafting;
		}
	}

	/**
	 * ItemEngravedEvent is fired when a player picks up an Item from the output slot of an Engraver.<br>
	 * <br>
	 * {@link #engraved} contains the Item found in the Engraver output slot. <br>
	 * <br>
	 * This event is not {@link Cancelable}.
	 * <br>
	 * This event does not have a result. {@link HasResult}<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 * 
	 * @author shinoow
	 * 
	 * @since 1.4
	 */
	public static class ItemEngravedEvent extends PlayerEvent {

		public final ItemStack engraved;

		public ItemEngravedEvent(EntityPlayer player, ItemStack crafting){
			super(player);
			engraved = crafting;
		}
	}

	/**
	 * ItemMaterializedEvent is fired when a player picks up an Item from the output slot of a Materializer.<br>
	 * <br>
	 * {@link #materialized} contains the Item found in the Materializer output slot. <br>
	 * <br>
	 * This event is not {@link Cancelable}.
	 * <br>
	 * This event does not have a result. {@link HasResult}<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 * 
	 * @author shinoow
	 * 
	 * @since 1.4
	 */
	public static class ItemMaterializedEvent extends PlayerEvent {

		public final ItemStack materialized;

		public ItemMaterializedEvent(EntityPlayer player, ItemStack crafting){
			super(player);
			materialized = crafting;
		}
	}

	/**
	 * RitualEvent is fired when Necronomicon Rituals are performed. <br>
	 * {@link RitualEvent.Pre} is fired before the Ritual is performed.<br>
	 * {@link RitualEvent.Post} is fired before the Ritual is completed. <br>
	 * <br>
	 * {@link #ritual} contains the ritual being performed. <br>
	 * {@link #world} contains the world at which this event is occurring. <br>
	 * {@link #x} contains the x-coordinate at which this event is occurring. <br>
	 * {@link #y} contains the y-coordinate at which this event is occurring. <br>
	 * {@link #z} contains the z-coordinate at which this event is occurring. <br>
	 * <br>
	 * Any child event of this event is {@link Cancelable}.
	 * <br>
	 * If cancelled, the ritual won't be performed/won't complete. <br>
	 * <br>
	 * This event does not have a result. {@link HasResult}<br>
	 * <br>
	 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
	 * 
	 * @author shinoow
	 * 
	 * @since 1.4
	 */
	public static class RitualEvent extends PlayerEvent {
		public final NecronomiconRitual ritual;
		public final World world;
		public final BlockPos pos;

		public RitualEvent(EntityPlayer player, NecronomiconRitual ritual, World world, BlockPos pos){
			super(player);
			this.ritual = ritual;
			this.world = world;
			this.pos = pos;
		}

		@Cancelable
		public static class Pre extends RitualEvent {

			public Pre(EntityPlayer player, NecronomiconRitual ritual, World world, BlockPos pos) {
				super(player, ritual, world, pos);
			}
		}

		@Cancelable
		public static class Post extends RitualEvent {

			public Post(EntityPlayer player, NecronomiconRitual ritual, World world, BlockPos pos) {
				super(player, ritual, world, pos);
			}
		}
	}
}
