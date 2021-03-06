package com.tom.storage.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.tom.api.ITileFluidHandler;
import com.tom.api.block.BlockContainerTomsMod;
import com.tom.core.CoreInit;
import com.tom.storage.tileentity.TMTank;
import com.tom.util.TomsModUtils;

public abstract class BlockTankBase extends BlockContainerTomsMod {

	protected BlockTankBase() {
		super(Material.GLASS);
	}

	@Override
	public abstract TMTank createNewTileEntity(World worldIn, int meta);

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote && playerIn.isSneaking() && CoreInit.isWrench(playerIn, hand)) {
			TomsModUtils.breakBlock(worldIn, pos);
			return true;
		}
		TileEntity te = worldIn.getTileEntity(pos);
		boolean b = TomsModUtils.interactWithFluidHandler(((ITileFluidHandler) te).getTankOnSide(side), playerIn, hand);
		if(b && te instanceof TMTank)((TMTank)te).markBlockForUpdate();
		return b;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TMTank) {
			((TMTank) tile).readFromStackNBT(stack);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		if (stack.hasTagCompound()) {
			NBTTagCompound tag = stack.getTagCompound().getCompoundTag("BlockEntityTag").getCompoundTag("tank");
			if (!tag.hasKey("Empty")) {
				FluidStack s = FluidStack.loadFluidStackFromNBT(tag);
				if (s != null)
					tooltip.add(I18n.format("tomsMod.tooltip.fluidStored", I18n.format(s.getUnlocalizedName()), s.amount, getTankSize()));
			}
		}
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof TMTank) { return ((TMTank) tile).getComparatorValue(); }
		return 0;
	}

	public abstract int getTankSize();
}
