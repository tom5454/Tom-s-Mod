package com.tom.core.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.tom.api.block.IIconRegisterRequired;
import com.tom.core.CoreInit;

public class ItemCircuitDrawingPen extends Item implements IIconRegisterRequired{
	@Override
	public ItemCircuitDrawingPen setUnlocalizedName(String unlocalizedName) {
		super.setUnlocalizedName(unlocalizedName);
		return this;
	}
	public ItemCircuitDrawingPen() {
		setMaxStackSize(1);
		setCreativeTab(CoreInit.tabTomsModItems);
		setHasSubtypes(true);
	}
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return stack.getMetadata() == 0 ? true : false;
	}
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		if(hasContainerItem(itemStack)){
			return getDamaged();
		}
		return null;
	}
	public ItemStack getDamaged() {
		return new ItemStack(this, 1, 1);
	}
	@Override
	public void registerIcons() {
		CoreInit.registerRender(this, 0, "tomsmodcore:tm.pen");
		CoreInit.registerRender(this, 1, "tomsmodcore:tm.pen_empty");
	}
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + (stack.getMetadata() > 0 ? "_empty" : "");
	}
}
