package com.mods.logix.industrial.atlas;

import com.mods.logix.industrial.LogixIndustrialSolutions;
import com.mods.logix.industrial.tileentity.RadiationSourceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static com.mods.logix.industrial.LogixIndustrialSolutions.ModID;

@ObjectHolder(LogixIndustrialSolutions.ModID)
@Mod.EventBusSubscriber(modid = ModID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LogixIndustrialTileEntities
{
    public static final TileEntityType<RadiationSourceTileEntity> RADIATION_SOURCE = null;

    @SubscribeEvent
    static void onTileEntityRegistry(RegistryEvent.Register<TileEntityType<?>> event)
    {
        LogixIndustrialSolutions.logProcessStart("Registering TileEntities");
        event.getRegistry().registerAll(
                TypeGenerator.generateRadiationSourceTileEntityType(LogixIndustrialBlocks.RADIATION_SOURCE_BLOCK)
        );
        LogixIndustrialSolutions.logProcessEnd();
    }

    @SuppressWarnings("ConstantConditions") // NOTE: .build() can take null, it's not ACTUALLY annotated with @NotNull like the inspection suggests!
    private static class TypeGenerator
    {
        static TileEntityType generateRadiationSourceTileEntityType(Block... blocks)
        {
            LogixIndustrialSolutions.logProcessStep("radiation_source");
            return TileEntityType.Builder
                    .create(RadiationSourceTileEntity::new, blocks)
                    .build(null)
                    .setRegistryName(ModID, "radiation_source");
        }
    }
}