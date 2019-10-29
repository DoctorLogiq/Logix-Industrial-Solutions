package com.mods.logix.API.block.base;

import com.mods.logix.industrial.LogixIndustrialSolutions;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class LogixContainerBlock extends ContainerBlock
{
    private static final ArrayList<LogixContainerBlock> ALL_BLOCKS_FOR_REGISTRATION = new ArrayList<>();

    private final Properties properties;
    private final ItemGroup group;
    public final String modID;
    public final String name;

    /**
     * Creates a block which has a container.
     * @param modID The mod's ID
     * @param name The block's unlocalized name (e.g. "example_block")
     * @param group The creative tab in which the block can be found in
     * @param material The block's base material
     * @param colour The block's map colour
     * @param sound The block's step sound
     */
    public LogixContainerBlock(String modID, String name, ItemGroup group, Material material, MaterialColor colour, SoundType sound)
    {
        super(Properties.create(material, colour).sound(sound));

        this.modID = modID;
        this.name = name;
        this.group = group;
        this.properties = Properties.create(material, colour).sound(sound);

        setRegistryName(modID + ":" + name);
        ALL_BLOCKS_FOR_REGISTRATION.add(this);
    }

    /**
     * Creates a block which has a container.
     * @param modID The mod's ID
     * @param name The block's unlocalized name (e.g. "example_block")
     * @param group The creative tab in which the block can be found in
     * @param properties The block's properties — should at the very least define the {@link Material},
     * {@link MaterialColor} (map colour) and {@link SoundType} (step sound)
     */
    public LogixContainerBlock(String modID, String name, ItemGroup group, Properties properties)
    {
        super(properties);

        this.modID = modID;
        this.name = name;
        this.group = group;
        this.properties = properties;

        setRegistryName(modID + ":" + name);
        ALL_BLOCKS_FOR_REGISTRATION.add(this);
    }

    /**
     * Creates and returns a new {@link BlockItem}, which is put inside the correct {@link #group} (defined in the constructor).
     */
    public BlockItem createBlockItem()
    {
        BlockItem item = new BlockItem(this, new Item.Properties().group(group));
        // NOTE: This check should NEVER be hit!
        // TODO: Maybe use assert here?
        if (getRegistryName() == null)
        {
            LogixIndustrialSolutions.logError("Could not create a BlockItem because the registry name was null! Culprit: " + this.toString());
            return null;
        }
        item.setRegistryName(getRegistryName());
        return item;
    }

    /**
     * @return an <em>unmodifiable</em> list of all the blocks which need registering.
     */
    public static List<LogixContainerBlock> getAllBlocksForRegistration()
    {
        return Collections.unmodifiableList(ALL_BLOCKS_FOR_REGISTRATION);
    }
}