package com.mods.logix.industrial;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(LogixIndustrialSolutions.ModID)
public class LogixIndustrialSolutions extends SecurityManager
{
    public static final String ModID = "logixindustrialsolutions";
    private static LogixIndustrialSolutions instance;

    public LogixIndustrialSolutions()
    {
        instance = this;
        /*
        MLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doServerStuff);
         */
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    public static LogixIndustrialSolutions getInstance()
    {
        return instance;
    }

    /* private void setup(final FMLCommonSetupEvent event)
    {

    }



    private void doServerStuff(FMLServerStartingEvent event)
    {

    } */

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        OBJLoader.INSTANCE.addDomain(ModID);
        logDebug("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    //region Logging
    private static final boolean DEBUG_ENABLED = true; // TODO: Set to false before release!
    private static final Logger LOGGER = LogManager.getLogger();
    private static int indentation = 0;
    private static String currentProcess = "";

    public static void logInfo(String message)
    {
        LOGGER.info(getIndentString() + message);
    }

    public static void logWarning(String message)
    {
        LOGGER.warn(getIndentString() + message);
    }

    public static void logError(String message)
    {
        LOGGER.error(getIndentString() + message);
    }

    public static void logDebug(String message)
    {
        if (!DEBUG_ENABLED) return;
        LOGGER.debug(getIndentString() + message);
    }

    public static void logInfo(String message, Object obj)
    {
        LOGGER.info(getIndentString() + message, obj);
    }

    public static void logWarning(String message, Object obj)
    {
        LOGGER.warn(getIndentString() + message, obj);
    }

    public static void logError(String message, Object obj)
    {
        LOGGER.error(getIndentString() + message, obj);
    }

    public static void logDebug(String message, Object obj)
    {
        if (!DEBUG_ENABLED) return;
        LOGGER.debug(getIndentString() + message, obj);
    }

    public static void logProcessStart(String process)
    {
        if (!DEBUG_ENABLED) return;
        currentProcess = process.toLowerCase();
        LOGGER.debug(getIndentString() + "Process started: " + currentProcess);
        indent();
    }

    public static void logProcessStep(String step)
    {
        if (!DEBUG_ENABLED) return;
        LOGGER.debug(getIndentString() + "- " + step);
    }

    public static void logProcessWarning(String error)
    {
        if (!DEBUG_ENABLED) return;
        LOGGER.debug(getIndentString() + "- WARN: " + error);
    }

    public static void logProcessError(String error)
    {
        if (!DEBUG_ENABLED) return;
        LOGGER.debug(getIndentString() + "- ERROR: " + error);
    }

    public static void logProcessEnd()
    {
        if (!DEBUG_ENABLED) return;
        assert currentProcess.length() > 0;
        unindent();
        LOGGER.debug(getIndentString() + "Process ended: " + currentProcess);
        currentProcess = "";
    }

    public static void logProcessEndedPrematurely()
    {
        if (!DEBUG_ENABLED) return;
        LOGGER.debug(getIndentString() + "ERROR: Process ended prematurely: " + currentProcess);
        unindent();
    }

    public static void indent()
    {
        indentation++;
    }

    public static void unindent()
    {
        indentation--;
        if (indentation < 0) indentation = 0;
    }

    public static void resetIndentation()
    {
        indentation = 0;
    }

    private static String getIndentString()
    {
        StringBuilder indent = new StringBuilder();
        if (indentation > 0)
        {
            for (int i = 0; i < indentation; i++)
                indent.append("  ");
        }
        return indent.toString();
    }
    //endregion

    //region Security

    public Class whoCalledHere()
    {
        return getClassContext()[3];
    }

    public Class whoCalledThere()
    {
        return getClassContext()[4];
    }
    //endregion

    /*
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo(ModID, "helloworld", () ->
        {
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event)
    {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    */
}