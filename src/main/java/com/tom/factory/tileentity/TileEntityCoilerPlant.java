package com.tom.factory.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import com.tom.api.energy.EnergyStorage;
import com.tom.apis.TomsModUtils;
import com.tom.core.CoreInit;
import com.tom.factory.block.PlateBlendingMachine;
import com.tom.recipes.handler.MachineCraftingHandler;
import com.tom.recipes.handler.MachineCraftingHandler.ItemStackChecker;

public class TileEntityCoilerPlant extends TileEntityMachineBase {
	private EnergyStorage energy = new EnergyStorage(20000, 100);
	private static final int MAX_PROCESS_TIME = 400;
	//private int maxProgress = 1;
	public int clientEnergy = 0;
	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 3 ? stack.getItem() == CoreInit.emptyWireCoil : true;
	}

	@Override
	public String getName() {
		return "coilerPlant";
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
		return (index == 0 || index == 3) && isItemValidForSlot(index, stack);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 1;
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
			if(energy.extractEnergy(30D, true) == 30D && canRun()){
				if(progress > 0){
					updateProgress();
				}else if(progress == 0){
					ItemStackChecker s = MachineCraftingHandler.getCoilerOutput(stack[0]);
					if(s != null && stack[3] != null && stack[3].getItem() == CoreInit.emptyWireCoil){
						if(stack[1] != null){
							if(TomsModUtils.areItemStacksEqual(stack[1], s.getStack(), true, true, false) && stack[1].stackSize + s.getStack().stackSize <= s.getStack().getMaxStackSize() && stack[0].stackSize >= s.getExtra()){
								if(stack[3].stackSize >= s.getStack().stackSize){
									stack[1].stackSize += s.getStack().stackSize;
									progress = -1;
									decrStackSize(0, s.getExtra());
									decrStackSize(3, s.getStack().stackSize);
								}
							}
						}else{
							if(stack[3].stackSize >= s.getStack().stackSize){
								progress = -1;
								stack[1] = s.getStack();
								decrStackSize(0, s.getExtra());
								decrStackSize(3, s.getStack().stackSize);
							}
						}
					}else{
						progress = -1;
					}
				}else{
					ItemStackChecker s = MachineCraftingHandler.getCoilerOutput(stack[0]);
					if(s != null && stack[3] != null && stack[3].getItem() == CoreInit.emptyWireCoil){
						if(stack[1] != null){
							if(TomsModUtils.areItemStacksEqual(stack[1], s.getStack(), true, true, false) && stack[1].stackSize + s.getStack().stackSize <= s.getStack().getMaxStackSize() && stack[0].stackSize >= s.getExtra()){
								progress = getMaxProgress();
							}
						}else{
							progress = getMaxProgress();
						}
					}
					TomsModUtils.setBlockStateWithCondition(worldObj, pos, PlateBlendingMachine.ACTIVE, progress > 0);
				}
			}else{
				TomsModUtils.setBlockStateWithCondition(worldObj, pos, PlateBlendingMachine.ACTIVE, false);
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
		int p = upgradeC + (upgradeC / 2) + 1;
		progress = Math.max(0, progress - p);
		energy.extractEnergy(1 * p, false);
	}

	@Override
	public EnergyStorage getEnergy() {
		return energy;
	}

	@Override
	public int getUpgradeSlot() {
		return 2;
	}

	@Override
	public int getMaxProcessTimeNormal() {
		return MAX_PROCESS_TIME;
	}
	@Override
	public ResourceLocation getFront() {
		return new ResourceLocation("tomsmodfactory:textures/blocks/coilerFront.png");
	}

	@Override
	public int[] getOutputSlots() {
		return new int[]{1};
	}

	@Override
	public int[] getInputSlots() {
		return new int[]{0, 3};
	}
}