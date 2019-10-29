package com.mods.logix.industrial.block;

import com.mods.logix.API.block.LogixObjBlockWithTileEntity;
import com.mods.logix.industrial.atlas.LogixIndustrialTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import static com.mods.logix.industrial.LogixIndustrialSolutions.ModID;
import static com.mods.logix.industrial.atlas.LogixIndustrialGroups.DECORATIVES;
import static com.mods.logix.industrial.atlas.LogixIndustrialNames.NAME_BLOCK_RADIATION_SOURCE;

public class RadiationSourceBlock extends LogixObjBlockWithTileEntity
{
    public RadiationSourceBlock()
    {
        super(ModID, NAME_BLOCK_RADIATION_SOURCE, DECORATIVES, Material.IRON, MaterialColor.IRON, SoundType.METAL);
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return LogixIndustrialTileEntities.RADIATION_SOURCE.create();
    }

    @Override
    public String[] getTextureNames()
    {
        return new String[]{ "radiation_source_block" };
    }
}