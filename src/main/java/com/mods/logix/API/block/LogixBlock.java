package com.mods.logix.API.block;

import com.mods.logix.industrial.LogixIndustrialSolutions;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class LogixBlock extends Block
{
    private static final ArrayList<LogixBlock> ALL_BLOCKS = new ArrayList<>();

    private final Properties properties;
    private final ItemGroup group;
    public final String modID;
    public final String name;

    public LogixBlock(String modID, String name, ItemGroup group, Material material, MaterialColor colour, SoundType sound)
    {
        super(Properties.create(material, colour).sound(sound));

        this.modID = modID;
        this.name = name;
        this.group = group;
        this.properties = Properties.create(material, colour).sound(sound);

        setRegistryName(modID + ":" + name);
        ALL_BLOCKS.add(this);
    }

    public LogixBlock(String modID, String name, ItemGroup group, Properties properties)
    {
        super(properties);

        this.modID = modID;
        this.name = name;
        this.group = group;
        this.properties = properties;

        setRegistryName(modID + ":" + name);
        ALL_BLOCKS.add(this);
    }

    public BlockItem createBlockItem()
    {
        BlockItem item = new BlockItem(this, new Item.Properties().group(group));
        if (getRegistryName() == null)
        {
            LogixIndustrialSolutions.logError("Could not create a BlockItem because the registry name was null! Culprit: " + this.toString());
            return null;
        }
        item.setRegistryName(getRegistryName());
        return item;
    }

    public static List<LogixBlock> getAllBlocksForRegistration()
    {
        return Collections.unmodifiableList(ALL_BLOCKS);
    }
}