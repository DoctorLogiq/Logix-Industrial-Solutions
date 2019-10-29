package com.mods.logix.API.utility;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class LogixRegistryHelper
{
    public static <T extends IForgeRegistryEntry<T>> T setup(String modID, final T entry, final String name)
    {
        return setup(entry, new ResourceLocation(modID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName)
    {
        entry.setRegistryName(registryName);
        return entry;
    }
}