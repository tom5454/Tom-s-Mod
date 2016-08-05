package com.tom.api.tileentity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import com.tom.api.ITileFluidHandler;
import com.tom.api.energy.EnergyType;
import com.tom.api.energy.EnergyType.EnergyHandlerNormal;
import com.tom.api.energy.EnergyType.EnergyHandlerProvider;
import com.tom.api.energy.EnergyType.EnergyHandlerReceiver;
import com.tom.api.energy.IEnergyHandler;
import com.tom.api.energy.IEnergyProvider;
import com.tom.api.energy.IEnergyReceiver;
import com.tom.api.energy.IEnergyStorageHandler;
import com.tom.api.energy.IEnergyStorageTile;

public class TileEntityTomsMod extends TileEntity implements ITickable {
	protected Map<EnumFacing, IItemHandler> itemHandlerSidedMap;
	protected Map<EnumFacing, IEnergyStorageHandler> energyHandlerMap;
	protected Map<EnumFacing, IFluidHandler> fluidHandlerMap;

	public TileEntityTomsMod() {
		//Initialize capabilities.
		itemHandlerSidedMap = new HashMap<EnumFacing, IItemHandler>();
		energyHandlerMap = new HashMap<EnumFacing, IEnergyStorageHandler>();
		fluidHandlerMap = new HashMap<EnumFacing, IFluidHandler>();
		if(this instanceof IInventory){
			if(canHaveInventory()){
				if(this instanceof ISidedInventory){
					ISidedInventory thisInv = (ISidedInventory) this;
					for(EnumFacing f : EnumFacing.VALUES){
						itemHandlerSidedMap.put(f, new SidedInvWrapper(thisInv, f));
					}
				}else{
					InvWrapper thisInv = new InvWrapper((IInventory) this);
					for(EnumFacing f : EnumFacing.VALUES){
						itemHandlerSidedMap.put(f, thisInv);
					}
				}
			}
		}
		if(this instanceof IEnergyHandler){
			IEnergyHandler thisHandler = (IEnergyHandler) this;
			for(EnumFacing f : EnumFacing.VALUES){
				energyHandlerMap.put(f, new EnergyHandlerNormal(thisHandler, f));
			}
		}else if(this instanceof IEnergyReceiver){
			IEnergyReceiver thisHandler = (IEnergyReceiver) this;
			for(EnumFacing f : EnumFacing.VALUES){
				energyHandlerMap.put(f, new EnergyHandlerReceiver(thisHandler, f));
			}
		}else if(this instanceof IEnergyProvider){
			IEnergyProvider thisHandler = (IEnergyProvider) this;
			for(EnumFacing f : EnumFacing.VALUES){
				energyHandlerMap.put(f, new EnergyHandlerProvider(thisHandler, f));
			}
		}
		if(this instanceof ITileFluidHandler){
			ITileFluidHandler thisFluid = (ITileFluidHandler) this;
			for(EnumFacing f : EnumFacing.VALUES){
				fluidHandlerMap.put(f, new FluidHandlerWrapper(f, thisFluid));
			}
		}
	}
	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		/*ByteBuf buf = Unpooled.buffer();
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());*/
		//return new FMLProxyPacket(new PacketBuffer(buf),TomsModAPIMain.Chanel2);
		return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
	}
	@Override
	public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		handleUpdateTag(pkt.getNbtCompound());
	}
	@Override
	public final void handleUpdateTag(final NBTTagCompound tag) {
		if(worldObj.isRemote)Minecraft.getMinecraft().addScheduledTask(new Runnable() {

			@Override
			public void run() {
				readFromPacket(tag);
			}
		});
	}
	@Override
	public final NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		writeToPacket(tag);
		return tag;
	}

	public void writeToPacket(NBTTagCompound tag){}
	public void readFromPacket(NBTTagCompound tag){}
	public void updateEntity(){}
	public void updateEntity(IBlockState currentState){}
	public void preUpdate(){}
	public void postUpdate(){}
	public boolean canHaveInventory(){return true;}

	@Override
	public final void update() {
		IBlockState state = worldObj.getBlockState(pos);
		if(state.getBlock() != Blocks.AIR){
			preUpdate();
			this.updateEntity();
			this.updateEntity(state);
			postUpdate();
		}
	}
	public final void markBlockForUpdate(BlockPos pos){
		markBlockForUpdate(worldObj, pos);
		//markDirty();
	}
	public static final void markBlockForUpdate(World world, BlockPos pos){
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
		if(world instanceof WorldServer){
			((WorldServer)world).getPlayerChunkMap().markBlockForUpdate(pos);
		}
	}
	public final void markBlockForUpdate(){
		markBlockForUpdate(pos);
	}
	@Override
	public final <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return this.<T>getInstance(itemHandlerSidedMap, facing, capability);
		}
		if (capability == EnergyType.ENERGY_HANDLER_CAPABILITY){
			return this.<T>getInstance(energyHandlerMap, facing, capability);
		}
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return this.<T>getInstance(fluidHandlerMap, facing, capability);
		}
		return super.getCapability(capability, facing);
	}
	@SuppressWarnings("unchecked")
	private final <T> T getInstance(Map<EnumFacing, ?> in, EnumFacing f, Capability<T> capability){
		try{
			return (T) (f == null ? in.get(EnumFacing.DOWN) : in.get(f));
		}catch(ClassCastException e){}
		return super.getCapability(capability, f);
	}
	@Override
	public final boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this instanceof IInventory) || (capability == EnergyType.ENERGY_HANDLER_CAPABILITY && this instanceof IEnergyStorageTile) || (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this instanceof ITileFluidHandler) || super.hasCapability(capability, facing);
	}
	@Override
	public final void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
	}
}