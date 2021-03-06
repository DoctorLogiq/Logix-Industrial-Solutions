package com.mods.logix.industrial.atlas;

import com.google.common.collect.ImmutableMap;
import com.mods.logix.API.block.LogixObjBlock;
import com.mods.logix.API.block.base.LogixBlock;
import com.mods.logix.API.block.base.LogixContainerBlock;
import com.mods.logix.industrial.LogixIndustrialSolutions;
import com.mods.logix.industrial.block.ConcreteBlock;
import com.mods.logix.industrial.block.RadiationSourceBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ForgeBlockStateV1;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static com.mods.logix.industrial.LogixIndustrialSolutions.ModID;
import static com.mods.logix.industrial.atlas.LogixIndustrialNames.NAME_BLOCK_CONCRETE;
import static com.mods.logix.industrial.atlas.LogixIndustrialNames.NAME_BLOCK_RADIATION_SOURCE;

@ObjectHolder(ModID)
@Mod.EventBusSubscriber(modid = ModID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LogixIndustrialBlocks
{
    //region Transformation Constants
    private static final TRSRTransformation THIRD_PERSON_BLOCK = ForgeBlockStateV1.Transforms.convert(0, 2.5f, 0, 75, 45, 0, 0.375f);
    @SuppressWarnings("deprecation")
    private static final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> BLOCK_TRANSFORMS = ImmutableMap.<ItemCameraTransforms.TransformType, TRSRTransformation>builder()
            .put(ItemCameraTransforms.TransformType.GUI, ForgeBlockStateV1.Transforms.convert(0, 0, 0, 30, 225, 0, 0.625f))
            .put(ItemCameraTransforms.TransformType.GROUND, ForgeBlockStateV1.Transforms.convert(0, 3, 0, 0, 0, 0, 0.25f))
            .put(ItemCameraTransforms.TransformType.FIXED, ForgeBlockStateV1.Transforms.convert(0, 0, 0, 0, 0, 0, 0.5f))
            .put(ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_BLOCK)
            .put(ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, ForgeBlockStateV1.Transforms.leftify(THIRD_PERSON_BLOCK))
            .put(ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND, ForgeBlockStateV1.Transforms.convert(0, 0, 0, 0, 45, 0, 0.4f))
            .put(ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, ForgeBlockStateV1.Transforms.convert(0, 0, 0, 0, 225, 0, 0.4f))
            .build();
    //endregion

    @ObjectHolder(NAME_BLOCK_CONCRETE)
    public static final LogixBlock CONCRETE_BLOCK = new ConcreteBlock();

    @ObjectHolder(NAME_BLOCK_RADIATION_SOURCE)
    public static final LogixBlock RADIATION_SOURCE_BLOCK = new RadiationSourceBlock();

    @SubscribeEvent
    static void onBlockRegistry(final RegistryEvent.Register<Block> event)
    {
        LogixIndustrialSolutions.logProcessStart("Registering Blocks");
        for (LogixBlock target : LogixBlock.getAllBlocksForRegistration())
        {
            LogixIndustrialSolutions.logProcessStep("registering: " + target.name);
            event.getRegistry().register(target);
        }
        for (LogixContainerBlock target : LogixContainerBlock.getAllBlocksForRegistration())
        {
            LogixIndustrialSolutions.logProcessStep("registering: " + target.name);
            event.getRegistry().register(target);
        }
        LogixIndustrialSolutions.logProcessEnd();
    }

    @SubscribeEvent
    static void onItemBlockRegistry(final RegistryEvent.Register<Item> event)
    {
        LogixIndustrialSolutions.logProcessStart("Registering BlockItems");
        for (LogixBlock target : LogixBlock.getAllBlocksForRegistration())
        {
            LogixIndustrialSolutions.logProcessStep("registering: " + target.name);
            event.getRegistry().register(target.createBlockItem());
        }
        for (LogixContainerBlock target : LogixContainerBlock.getAllBlocksForRegistration())
        {
            LogixIndustrialSolutions.logProcessStep("registering: " + target.name);
            event.getRegistry().register(target.createBlockItem());
        }
        LogixIndustrialSolutions.logProcessEnd();
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    static void onModelBakeEvent(ModelBakeEvent event) throws IllegalAccessException
    {
        LogixIndustrialSolutions.logProcessStart("Baking models");
        boolean noneFailed = true;
        for (LogixObjBlock target : LogixObjBlock.getAllObjBlocks())
        {
            LogixIndustrialSolutions.logProcessStep("Baking model for: " + target.name);
            LogixIndustrialSolutions.indent();
            boolean result = target.bakeModel(event, BLOCK_TRANSFORMS);
            LogixIndustrialSolutions.unindent();
            if (!result)
            {
                noneFailed = false;
                break;
            }
        }
        if (noneFailed) LogixIndustrialSolutions.logProcessEnd();
        else LogixIndustrialSolutions.logProcessEndedPrematurely();
    }

    @SubscribeEvent
    static void onPreTextureStitch(TextureStitchEvent.Pre event)
    {
        // TODO: Put texture names in OBJBlock and make this work for all LogixOBJBlocks
        LogixIndustrialSolutions.logProcessStart("Stitching textures");
        for (LogixObjBlock target : LogixObjBlock.getAllObjBlocks())
        {
            for (String texture : target.getTextureNames())
            {
                ResourceLocation resloc = ResourceLocation.tryCreate(ModID + ":block/" + texture);
                if (resloc != null)
                {
                    event.addSprite(resloc);
                    LogixIndustrialSolutions.logProcessStep("Added sprite '" + texture + "' for block '" + target.name + "'");
                }
                else
                {
                    LogixIndustrialSolutions.logProcessError("Failed to create ResourceLocation '" + texture + "' for block '" + target.name + "'");
                }
            }
        }
        LogixIndustrialSolutions.logProcessEnd();
    }
}