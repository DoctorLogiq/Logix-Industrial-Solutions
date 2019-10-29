package com.mods.logix.industrial.block;

import com.mods.logix.API.block.LogixObjBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import static com.mods.logix.industrial.LogixIndustrialSolutions.ModID;
import static com.mods.logix.industrial.atlas.LogixIndustrialGroups.DECORATIVES;
import static com.mods.logix.industrial.atlas.LogixIndustrialNames.NAME_BLOCK_RADIATION_SOURCE;

public class RadiationSourceBlock extends LogixObjBlock
{
    public RadiationSourceBlock()
    {
        super(ModID, NAME_BLOCK_RADIATION_SOURCE, DECORATIVES, Material.IRON, MaterialColor.IRON, SoundType.METAL);
    }
}