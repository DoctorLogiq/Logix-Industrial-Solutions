package com.mods.logix.industrial.tileentity;

import com.mods.logix.industrial.LogixIndustrialSolutions;
import com.mods.logix.industrial.atlas.LogixIndustrialTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class RadiationSourceTileEntity extends TileEntity implements ITickableTileEntity
{
    @SuppressWarnings("ConstantConditions")
    public RadiationSourceTileEntity()
    {
        super(LogixIndustrialTileEntities.RADIATION_SOURCE);
    }

    private int counter = 0;

    @Override
    public void tick()
    {
        if (counter++ == 20)
        {
            // TODO: Remove!
            LogixIndustrialSolutions.logDebug("Test");
            counter = 0;
        }
    }
}