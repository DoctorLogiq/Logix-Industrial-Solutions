package com.mods.logix.industrial.groups;

import com.mods.logix.industrial.registry.LogixIndustrialBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LogixIndustrialGroups
{
    public static final ItemGroup DECORATIVES = new ItemGroup("logixindustrialdecorative")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(LogixIndustrialBlocks.generic_block);
        }
    };
}
