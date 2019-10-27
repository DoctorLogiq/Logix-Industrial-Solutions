package com.mods.logix.industrial.block;

import com.mods.logix.API.block.LogixObjBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import static com.mods.logix.industrial.LogixIndustrialSolutions.ModID;
import static com.mods.logix.industrial.atlas.LogixIndustrialNames.NAME_BLOCK_GENERIC;
import static com.mods.logix.industrial.groups.LogixIndustrialGroups.DECORATIVES;

public class GenericBlock extends LogixObjBlock
{
    public GenericBlock()
    {
        super(ModID, NAME_BLOCK_GENERIC, DECORATIVES, Material.IRON, MaterialColor.IRON, SoundType.METAL);
    }
}
