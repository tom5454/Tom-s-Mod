package com.tom.core.item;

import com.tom.api.item.IFuelRod;
import com.tom.core.CoreInit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UraniumRod extends Item implements IFuelRod{

	@Override
	public int getAmount(ItemStack is) {
		return 10000;
	}

	@Override
	public ItemStack getReturnStack(ItemStack is) {
		return new ItemStack(CoreInit.dUraniumRod);
	}

}