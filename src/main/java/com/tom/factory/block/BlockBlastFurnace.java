package com.tom.factory.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.tom.api.block.BlockContainerTomsMod;
import com.tom.core.CoreInit;
import com.tom.factory.tileentity.TileEntityBlastFurnace;
import com.tom.handler.GuiHandler.GuiIDs;
import com.tom.util.TomsModUtils;

public class BlockBlastFurnace extends BlockContainerTomsMod {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyInteger STATE = PropertyInteger.create("state", 0, 2);

	public BlockBlastFurnace() {
		super(Material.ROCK);
		setHardness(6.6F);
		setResistance(45.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBlastFurnace();
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing f = TomsModUtils.getDirectionFacing(placer, false);
		return this.getDefaultState().withProperty(FACING, f).withProperty(STATE, 0);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING, STATE});
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return TomsModUtils.getBlockStateFromMeta(meta, STATE, FACING, getDefaultState(), 2);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {// System.out.println("getMeta");
		return TomsModUtils.getMetaFromState(state.getValue(FACING), state.getValue(STATE));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote)
			playerIn.openGui(CoreInit.modInstance, GuiIDs.blastFurnace.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
