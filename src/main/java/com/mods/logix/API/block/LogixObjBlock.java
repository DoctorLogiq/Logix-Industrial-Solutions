package com.mods.logix.API.block;

import com.google.common.collect.ImmutableMap;
import com.mods.logix.API.block.base.LogixBlock;
import com.mods.logix.industrial.LogixIndustrialSolutions;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.BasicState;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.TRSRTransformation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class LogixObjBlock extends LogixBlock
{
    private static final ArrayList<LogixObjBlock> ALL_OBJ_BLOCKS = new ArrayList<>();

    /**
     * Creates a block which uses a .OBJ model
     * @param modID The mod ID
     * @param name <font color="orange">Must match the name of the corresponding .obj model file!</font>
     * @param group Which creative tab to put this in
     * @param material The block's material
     * @param colour The block's colour on maps
     * @param sound The sound played when the block is stepped on
     */
    public LogixObjBlock(String modID, String name, ItemGroup group, Material material, MaterialColor colour, SoundType sound)
    {
        super(modID, name, group, material, colour, sound);
        ALL_OBJ_BLOCKS.add(this);
    }

    /**
     * Creates a block which uses a .OBJ model
     * @param modID The mod ID
     * @param name <font color="orange">Must match the name of the corresponding .obj model file!</font>
     * @param group Which creative tab to put this in
     * @param properties The block's properties - should contain at least the material, map colour and step sound
     */
    public LogixObjBlock(String modID, String name, ItemGroup group, Properties properties)
    {
        super(modID, name, group, properties);
        ALL_OBJ_BLOCKS.add(this);
    }

    public abstract String[] getTextureNames();

    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, @Nullable EntityType<?> entityType)
    {
        return false;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean isVariableOpacity()
    {
        return true;
    }

    @SuppressWarnings("deprecation") // NOTE: Not actually deprecated!! Needed to make non-full-cubes stop occluding blocks behind them
    @Override
    public boolean isSolid(BlockState state)
    {
        return false;
    }

    // TODO: Do a pass on this to make sure all the logging is accurate
    // TODO: Protection?
    /**
     * Attempts to load and bake the corresponding .obj model, and put it into the registry.
     * <br/><br/>
     * <font color="orange">Note: The .obj model file is assumed to have the same name as the name passed
     * to the constructor for this block.</font>
     * @param event a reference to the current event
     * @param transformations the block transformations for the various different perspectives
     * @return true if the process was successful, false if it was not and model baking should cease
     */
    // NOTE: ItemCameraTransforms may not actually be deprecated, since it seems to affect the model's rendering!
    @SuppressWarnings("deprecation")
    public boolean bakeModel(final ModelBakeEvent event, final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transformations) throws IllegalAccessException
    {
        try
        {
            String modelName = this.modID + ":block/" + this.name + ".obj";
            LogixIndustrialSolutions.logProcessStep("Attempting to load the model '" + modelName + "'");
            IUnbakedModel unbaked = ModelLoaderRegistry.getModelOrMissing(new ResourceLocation(modelName));
            if (unbaked instanceof OBJModel)
            {
                LogixIndustrialSolutions.logProcessStep("Baking Block model");
                IBakedModel baked = unbaked.bake(event.getModelLoader(), ModelLoader.defaultTextureGetter(), new BasicState(unbaked.getDefaultState(), true), DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

                LogixIndustrialSolutions.logProcessStep("Adding to model registry");
                event.getModelRegistry().put(new ModelResourceLocation(this.modID + ":" + this.name, ""), baked);

                LogixIndustrialSolutions.logProcessStep("Baking inventory model for BlockItem");
                IBakedModel bakedInventory = unbaked.bake(event.getModelLoader(), ModelLoader.defaultTextureGetter(), new BasicState(unbaked.getDefaultState(), true), DefaultVertexFormats.ITEM);
                if (bakedInventory == null)
                {
                    LogixIndustrialSolutions.logError("The inventory model was somehow null after baking");
                    return false;
                }
                bakedInventory = new PerspectiveMapWrapper(bakedInventory, transformations);

                LogixIndustrialSolutions.logProcessStep("Adding to model registry");
                event.getModelRegistry().put(new ModelResourceLocation(this.modID + ":" + this.name, "inventory"), bakedInventory);
            }
            else
            {
                LogixIndustrialSolutions.logProcessError("Model is not an instance of OBJModel, it's a " + unbaked.toString());
                return false;
            }
        }
        catch (Exception ex)
        {
            // TODO: Exception logging function in main class?
            LogixIndustrialSolutions.logProcessError("Model baking failed due to an exception");
            if (ex.getMessage() != null)
                LogixIndustrialSolutions.logError("Message: " + ex.getMessage());
        }
        return true;
    }

    // TODO: This function is only needed for baking the models... protect it?
    /**
     * @return an <em>unmodifiable</em> list of all blocks which have corresponding .obj models
     */
    public static List<LogixObjBlock> getAllObjBlocks()
    {
        return Collections.unmodifiableList(ALL_OBJ_BLOCKS);
    }
}