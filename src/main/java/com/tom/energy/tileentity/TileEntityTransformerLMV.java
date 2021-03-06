package com.tom.energy.tileentity;

import static com.tom.lib.api.energy.EnergyType.*;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import com.tom.api.tileentity.ICustomMultimeterInformation;
import com.tom.api.tileentity.TileEntityTomsMod;
import com.tom.lib.api.energy.EnergyType;
import com.tom.lib.api.energy.IEnergyHandler;

import com.tom.energy.block.TransformerLM;

public class TileEntityTransformerLMV extends TileEntityTomsMod implements IEnergyHandler, ICustomMultimeterInformation {

	// private EnergyStorage energy = new EnergyStorage(10000);
	@Override
	public boolean canConnectEnergy(EnumFacing from, EnergyType type) {
		EnumFacing facing = this.getFacing();
		return (type == LV && from == facing.getOpposite()) || (type == MV && from == facing);
	}

	@Override
	public List<EnergyType> getValidEnergyTypes() {
		return MV.getList(LV);
	}

	@Override
	public double receiveEnergy(EnumFacing from, EnergyType type, double maxReceive, boolean simulate) {
		EnumFacing facing = this.getFacing();
		if (type == LV && from == facing.getOpposite() && getMode()) {
			/*TileEntity receiver = worldObj.getTileEntity(pos.offset(facing));
				if(receiver instanceof IEnergyReceiver) {
					//System.out.println("send");
					EnumFacing fOut = facing.getOpposite();
					IEnergyReceiver recv = (IEnergyReceiver)receiver;*/
			double maxLaser = MV.convertFrom(LV, maxReceive);
			double push = MV.pushEnergyTo(world, pos, facing.getOpposite(), maxLaser, 800, simulate);
			double ret = LV.convertFrom(MV, push);
			return ret;
			// }
		} else if (type == MV && from == facing && !getMode()) {
			// TileEntity receiver = worldObj.getTileEntity(pos.offset(facing));
			// if(receiver instanceof IEnergyReceiver) {
			// System.out.println("send");
			// EnumFacing fOut = facing.getOpposite();
			// IEnergyReceiver recv = (IEnergyReceiver)receiver;
			return MV.convertFrom(LV, LV.pushEnergyTo(world, pos, facing, LV.convertFrom(MV, maxReceive), 1600, simulate));
			/*}else{
					EnumFacing fOut = facing.getOpposite();
					IMultipartContainer container = MultipartHelper.getPartContainer(worldObj, pos.offset(facing));
					if (container == null) {
						return 0;
					}

					if (fOut != null) {
						ISlottedPart part = container.getPartInSlot(PartSlot.getFaceSlot(fOut));
						if (part instanceof IMicroblock.IFaceMicroblock && !((IMicroblock.IFaceMicroblock) part).isFaceHollow()) {
							return 0;
						}
					}

					ISlottedPart part = container.getPartInSlot(PartSlot.CENTER);
					try{
						if (part instanceof PartDuct<?>) {
							@SuppressWarnings("unchecked")
							IEnergyStorage recv = ((PartDuct<EnergyGrid>) part).getGrid().getData();
							double energyPushed = recv.receiveEnergy(Math.min(100, LV.convertFrom(LASER, maxReceive)), true);
							if(energyPushed > 0) {
								//System.out.println("push");
								this.markDirty();
								return recv.receiveEnergy(energyPushed, simulate);
							}
						} else {
							return 0;
						}
					}catch (ClassCastException e){
						return 0;
					}
				}*/
		}
		return 0;
	}

	@Override
	public double extractEnergy(EnumFacing from, EnergyType type, double maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public double getEnergyStored(EnumFacing from, EnergyType type) {
		return 0;
	}

	@Override
	public long getMaxEnergyStored(EnumFacing from, EnergyType type) {
		return 0;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// this.energy.writeToNBT(tag);
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		// this.energy.writeToNBT(tag);
	}

	public EnumFacing getFacing() {
		IBlockState state = world.getBlockState(pos);
		return state.getValue(TransformerLM.FACING);
	}

	public boolean getMode() {
		IBlockState state = world.getBlockState(pos);
		return state.getValue(TransformerLM.MODE);
	}

	@Override
	public List<ITextComponent> getInformation(List<ITextComponent> list) {
		list.add(new TextComponentTranslation("tomsMod.chat.step" + (getMode() ? "Up" : "Down")));
		return list;
	}
}
