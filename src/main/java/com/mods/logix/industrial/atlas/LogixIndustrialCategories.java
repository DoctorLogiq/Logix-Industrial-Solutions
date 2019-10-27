package com.mods.logix.industrial.atlas;

import net.minecraft.item.ItemGroup;

public enum LogixIndustrialCategories
{
    Decoratives(LogixIndustrialGroups.DECORATIVES);

    public final ItemGroup group;

    LogixIndustrialCategories(ItemGroup group)
    {
        this.group = group;
    }
}
