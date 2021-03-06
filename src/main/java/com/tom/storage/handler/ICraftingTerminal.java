package com.tom.storage.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;

import com.tom.storage.tileentity.inventory.ContainerCraftingTerminal;
import com.tom.storage.tileentity.inventory.ContainerCraftingTerminal.SlotTerminalCrafting;

public interface ICraftingTerminal extends ITerminal {
	IInventory getCraftingInv();

	InventoryCraftResult getCraftResult();

	void craft(EntityPlayer playerIn, ContainerCraftingTerminal containerCraftingTerminal, SlotTerminalCrafting slot);
}