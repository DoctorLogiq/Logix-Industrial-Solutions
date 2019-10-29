package com.mods.logix.API.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class LogixObjBlockWithTileEntity extends LogixObjBlock
{
    /**
     * Creates a block which uses a .OBJ model
     *
     * @param modID    The mod ID
     * @param name     <font color="orange">Must match the name of the corresponding .obj model file!</font>
     * @param group    Which creative tab to put this in
     * @param material The block's material
     * @param colour   The block's colour on maps
     * @param sound    The sound played when the block is stepped on
     */
    public LogixObjBlockWithTileEntity(String modID, String name, ItemGroup group, Material material, MaterialColor colour, SoundType sound)
    {
        super(modID, name, group, material, colour, sound);
    }

    /**
     * Creates a block which uses a .OBJ model
     *
     * @param modID      The mod ID
     * @param name       <font color="orange">Must match the name of the corresponding .obj model file!</font>
     * @param group      Which creative tab to put this in
     * @param properties The block's properties - should contain at least the material, map colour and step sound
     */
    public LogixObjBlockWithTileEntity(String modID, String name, ItemGroup group, Properties properties)
    {
        super(modID, name, group, properties);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState blockState, LivingEntity placer, ItemStack stack)
    {
        if (stack.hasDisplayName())
        {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof AbstractFurnaceTileEntity) // TODO: Change
            {
                ((AbstractFurnaceTileEntity) tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock())
        {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof AbstractFurnaceTileEntity)
            {
                InventoryHelper.dropInventoryItems(world, pos, (AbstractFurnaceTileEntity) tileEntity);
                world.updateComparatorOutputLevel(pos, this);
            }
            super.onReplaced(state, world, pos, newState, isMoving);
        }
    }

    /* TODO: Complete these if necessary (look at FurnaceBlock and AbstractFurnaceBlock)
    public boolean hasComparatorInputOverride(BlockState p_149740_1_) {
        return true;
    }

    public int getComparatorInputOverride(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
        return Container.calcRedstone(p_180641_2_.getTileEntity(p_180641_3_));
    }
    */
}