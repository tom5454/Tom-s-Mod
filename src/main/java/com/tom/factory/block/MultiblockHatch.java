package com.tom.factory.block;

import com.tom.api.block.BlockMultiblockPart;
import com.tom.api.tileentity.TileEntityTomsMod;
import com.tom.apis.TomsModUtils;
import com.tom.core.CoreInit;
import com.tom.factory.tileentity.TileEntityMBHatch;
import com.tom.handler.GuiHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MultiblockHatch extends BlockMultiblockPart {
	/*@SideOnly(Side.CLIENT)
	private IIcon out;*/
	protected MultiblockHatch(Material arg0) {
		super(arg0);
	}
	public MultiblockHatch(){
		this(Material.IRON);
	}
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityMBHatch();
	}
	/*public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side){
		TileEntityMBHatch te = (TileEntityMBHatch)world.getTileEntity(x, y, z);
		boolean input = te.input;
		if(!input){
			return this.out;
		}else{
			return this.blockIcon;
		}
	}
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister){
		this.out = iconregister.registerIcon("minecraft:mbHOut");
		this.blockIcon = iconregister.registerIcon("minecraft:mbH");
	}*/
	@Override
	public boolean onBlockActivated(World world, BlockPos pos,
			IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY,
			float hitZ) {
		if(!world.isRemote){
			TileEntity tilee = world.getTileEntity(pos);
			if(tilee instanceof TileEntityMBHatch){
				//TileEntityMBHatch te = (TileEntityMBHatch)tilee;
				if((heldItem != null) && CoreInit.isWrench(heldItem,player)){
					TomsModUtils.setBlockState(world, pos, state.withProperty(OUTPUT, !state.getValue(OUTPUT)));
					((TileEntityTomsMod) tilee).markBlockForUpdate(pos);
					return true;
				}else{
					player.openGui(CoreInit.modInstance, GuiHandler.GuiIDs.mbHatch.ordinal(), world, pos.getX(),pos.getY(),pos.getZ());
					//System.out.println("open");
					return true;
				}
			}else{
				return false;
			}
		}
		return true;
	}
	@Override
	public void breakBlockI(World world, int x, int y, int z, IBlockState block) {
	}
	public static final PropertyBool OUTPUT = PropertyBool.create("out");
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {OUTPUT});
	}
	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(OUTPUT, meta == 1);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{//System.out.println("getMeta");
		return state.getValue(OUTPUT) ? 1 : 0;
	}
	@Override
	public void onNeighborBlockChangeI(IBlockAccess world, int x, int y, int z, IBlockState b, Block block) {
		// TODO Auto-generated method stub

	}
}