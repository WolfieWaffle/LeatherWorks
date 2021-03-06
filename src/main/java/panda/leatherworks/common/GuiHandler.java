package panda.leatherworks.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import panda.leatherworks.client.gui.GuiEnderPack;
import panda.leatherworks.client.gui.GuiPack;
import panda.leatherworks.client.gui.GuiTrunk;
import panda.leatherworks.common.tileentity.TileEntityTrunk;

public class GuiHandler implements IGuiHandler {
	public static final int PACK_GUI = 0;
	public static final int ENDER_PACK_GUI = 1;
	public static final int TRUNK_GUI = 2;

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

		if (id == PACK_GUI) {
			return new ContainerPack(player);
		}else
		if (id == ENDER_PACK_GUI) {
			return new ContainerChest(player.inventory,player.getInventoryEnderChest(),player);
		}else
		if(id == TRUNK_GUI){
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if (tileEntity instanceof TileEntityTrunk) {
				TileEntityTrunk tileEntityInventoryBasic = (TileEntityTrunk) tileEntity;
				return new InventoryTrunk(player.inventory, tileEntityInventoryBasic);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == PACK_GUI) {
			return new GuiPack(player,player.getHeldItem(EnumHand.MAIN_HAND));
		}else
		if (id == ENDER_PACK_GUI) {
			return new GuiEnderPack(player.inventory,player.getInventoryEnderChest() );
		}else
		if(id == TRUNK_GUI){
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if (tileEntity instanceof TileEntityTrunk) {
				TileEntityTrunk tileEntityInventoryBasic = (TileEntityTrunk) tileEntity;
				return new GuiTrunk(player.inventory, tileEntityInventoryBasic);
			}
		}
		return null;
	}
}
