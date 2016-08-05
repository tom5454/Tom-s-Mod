package com.tom.factory.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

import com.tom.api.energy.EnergyStorage;
import com.tom.apis.TomsModUtils;
import com.tom.factory.FactoryInit;
import com.tom.factory.block.AlloySmelter;
import com.tom.recipes.handler.MachineCraftingHandler;
import com.tom.recipes.handler.MachineCraftingHandler.ItemStackChecker;

public class TileEntityAlloySmelter extends TileEntityMachineBase {
	private EnergyStorage energy = new EnergyStorage(10000, 100);
	private static final int[] SLOTS = new int[]{0,1,2};
	private static final int MAX_PROCESS_TIME = 300;
	//private int maxProgress = 1;
	public int clientEnergy = 0;
	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public String getName() {
		return "alloySmelter";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return index == 0 || index == 1;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 2;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.progress = compound.getInteger("progress");
		//this.maxProgress = compound.getInteger("maxProgress");
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("progress", progress);
		//compound.setInteger("mayProgress", maxProgress);
		return compound;
	}
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote){
			if(energy.extractEnergy(20D, true) == 20D){
				if(progress > 0){
					updateProgress();
				}else if(progress == 0){
					ItemStackChecker s = MachineCraftingHandler.getAlloySmelterOutput(stack[0], stack[1]);
					if(s != null){
						if(stack[2] != null){
							if(TomsModUtils.areItemStacksEqual(stack[2], s.getStack(), true, true, false) && stack[2].stackSize + s.getStack().stackSize <= s.getStack().getMaxStackSize() && stack[0].stackSize >= s.getExtra()){
								stack[2].stackSize += s.getStack().stackSize;
								progress = -1;
								decrStackSize(0, s.getExtra());
								decrStackSize(1, s.getExtra2());
							}
						}else{
							progress = -1;
							stack[2] = s.getStack();
							decrStackSize(0, s.getExtra());
							decrStackSize(1, s.getExtra2());
						}
					}else{
						progress = -1;
					}
				}else{
					ItemStackChecker s = MachineCraftingHandler.getAlloySmelterOutput(stack[0], stack[1]);
					if(s != null){
						if(stack[2] != null){
							if(TomsModUtils.areItemStacksEqual(stack[2], s.getStack(), true, true, false) && stack[2].stackSize + s.getStack().stackSize <= s.getStack().getMaxStackSize() && stack[0].stackSize >= s.getExtra()){
								progress = getMaxProgress();
							}
						}else{
							progress = getMaxProgress();
						}
					}
					TomsModUtils.setBlockStateWithCondition(worldObj, pos, AlloySmelter.ACTIVE, progress > 0);
				}
			}else{
				TomsModUtils.setBlockStateWithCondition(worldObj, pos, AlloySmelter.ACTIVE, false);
			}
		}
	}

	public int getClientEnergyStored() {
		return MathHelper.ceiling_double_int(energy.getEnergyStored());
	}

	public int getMaxEnergyStored() {
		return energy.getMaxEnergyStored();
	}
	private void updateProgress(){
		int upgradeC = getSpeedUpgradeCount();
		int p = upgradeC + 1 + (upgradeC / 2);
		progress = Math.max(0, progress - p);
		energy.extractEnergy(1D * p, false);
	}
	private int getSpeedUpgradeCount(){
		return stack[3] != null && stack[3].getItem() == FactoryInit.speedUpgrade ? stack[3].stackSize : 0;
	}

	@Override
	public EnergyStorage getEnergy() {
		return energy;
	}

	@Override
	public int getUpgradeSlot() {
		return 3;
	}

	@Override
	public int getMaxProcessTimeNormal() {
		return MAX_PROCESS_TIME;
	}
}