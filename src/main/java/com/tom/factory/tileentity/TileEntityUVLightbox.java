package com.tom.factory.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import com.tom.api.energy.EnergyStorage;
import com.tom.apis.TomsModUtils;
import com.tom.core.TMResource.CraftingMaterial;
import com.tom.factory.block.BlockCrusher;

public class TileEntityUVLightbox extends TileEntityMachineBase {
	private EnergyStorage energy = new EnergyStorage(10000, 100);
	public int clientEnergy = 0;
	private int maxProgress = 0;

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return index == 0;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 1;
	}

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
		return "uvLightBox";
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
		return 2;
	}
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.progress = compound.getInteger("progress");
		this.maxProgress = compound.getInteger("maxProgress");
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("progress", progress);
		compound.setInteger("maxProgress", maxProgress);
		return compound;
	}
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote){
			if(energy.extractEnergy(20D, true) == 20D && canRun()){
				if(progress > 0){
					updateProgress();
				}else if(progress == 0){
					ItemStack s = getRecipe();
					if(s != null){
						if(stack[1] != null){
							if(TomsModUtils.areItemStacksEqual(stack[1], s, true, true, false) && stack[1].stackSize + s.stackSize <= s.getMaxStackSize() && stack[0].stackSize >= 1){
								stack[1].stackSize += s.stackSize;
								progress = -1;
								decrStackSize(0, 1);
							}
						}else{
							progress = -1;
							stack[1] = s.copy();
							decrStackSize(0, 1);
						}
					}else{
						progress = -1;
					}
				}else{
					ItemStack s = getRecipe();
					if(s != null){
						if(stack[1] != null){
							if(TomsModUtils.areItemStacksEqual(stack[1], s, true, true, false) && stack[1].stackSize + s.stackSize <= s.getMaxStackSize() && stack[0].stackSize >= 1){
								progress = maxProgress = getTime();
							}
						}else{
							progress = maxProgress = getTime();
						}
					}
					TomsModUtils.setBlockStateWithCondition(worldObj, pos, BlockCrusher.ACTIVE, progress > 0);
				}
			}else{
				TomsModUtils.setBlockStateWithCondition(worldObj, pos, BlockCrusher.ACTIVE, false);
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
		int p = upgradeC + MathHelper.floor_double(10 * (getMaxProcessTimeNormal() / TYPE_MULTIPLIER_SPEED[getType()])) + (upgradeC / 2);
		progress = Math.max(0, progress - p);
		energy.extractEnergy(0.1D * p, false);
	}
	private ItemStack getRecipe(){
		int lvl = 2 - getType();
		if(stack[2] != null && CraftingMaterial.equals(stack[2].getItem())){
			if(CraftingMaterial.BLUEPRINT_BASIC_CIRCUIT.equals(stack[2]) && CraftingMaterial.PHOTOACTIVE_BASIC_CIRCUIT_PLATE.equals(stack[0])){
				return CraftingMaterial.RAW_BASIC_CIRCUIT_PANEL.getStackNormal();
			}else if(CraftingMaterial.BLUEPRINT_NORMAL_CIRCUIT.equals(stack[2]) && CraftingMaterial.PHOTOACTIVE_BASIC_CIRCUIT_PLATE.equals(stack[0])){
				return CraftingMaterial.RAW_NORMAL_CIRCUIT_PANEL.getStackNormal();
			}else if(CraftingMaterial.BLUEPRINT_ADVANCED_CIRCUIT.equals(stack[2]) && CraftingMaterial.PHOTOACTIVE_ADVANCED_CIRCUIT_PLATE.equals(stack[0])){
				return lvl > 0 ? CraftingMaterial.RAW_ADVANCED_CIRCUIT_PANEL.getStackNormal() : null;
			}else if(CraftingMaterial.BLUEPRINT_ELITE_CIRCUIT.equals(stack[2]) && CraftingMaterial.PHOTOACTIVE_ADVANCED_CIRCUIT_PLATE.equals(stack[0])){
				return lvl > 1 ? CraftingMaterial.RAW_ELITE_CIRCUIT_PANEL.getStackNormal() : null;
			}
		}
		return null;
	}
	private int getTime(){
		if(stack[2] != null && CraftingMaterial.equals(stack[2].getItem())){
			if(CraftingMaterial.BLUEPRINT_BASIC_CIRCUIT.equals(stack[2]) && CraftingMaterial.PHOTOACTIVE_BASIC_CIRCUIT_PLATE.equals(stack[0])){
				return 500;
			}else if(CraftingMaterial.BLUEPRINT_NORMAL_CIRCUIT.equals(stack[2]) && CraftingMaterial.PHOTOACTIVE_BASIC_CIRCUIT_PLATE.equals(stack[0])){
				return 1000;
			}else if(CraftingMaterial.BLUEPRINT_ADVANCED_CIRCUIT.equals(stack[2]) && CraftingMaterial.PHOTOACTIVE_ADVANCED_CIRCUIT_PLATE.equals(stack[0])){
				return 1600;
			}else if(CraftingMaterial.BLUEPRINT_ELITE_CIRCUIT.equals(stack[2]) && CraftingMaterial.PHOTOACTIVE_ADVANCED_CIRCUIT_PLATE.equals(stack[0])){
				return 2500;
			}
		}
		return 0;
	}
	@Override
	public int getField(int id) {
		return id == 1 ? maxProgress : super.getField(id);
	}
	@Override
	public ResourceLocation getFront() {
		return new ResourceLocation("tomsmodfactory:textures/blocks/uvBox.png");
	}

	@Override
	public int[] getOutputSlots() {
		return new int[]{1};
	}

	@Override
	public int[] getInputSlots() {
		return new int[]{0};
	}
}
