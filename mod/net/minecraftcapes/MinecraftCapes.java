//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes;

import net.minecraftcapes.proxy.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.*;

@Mod(modid = "minecraftcapes", name = "MinecraftCapes Mod", version = "11.0.0", acceptedMinecraftVersions = "1.12.2")
public class MinecraftCapes
{
    public static final String MODID = "minecraftcapes";
    private static final Logger logger;
    private static boolean isLabyMod;
    @SidedProxy(clientSide = "net.minecraftcapes.proxy.ClientProxy", serverSide = "net.minecraftcapes.proxy.ServerProxy")
    private static IProxy proxy;
    
    @Mod.EventHandler
    public static void preInit(final FMLPostInitializationEvent event) {
        MinecraftCapes.proxy.init();
    }
    
    @Mod.EventHandler
    public static void postInit(final FMLPostInitializationEvent event) {
        MinecraftCapes.proxy.postInit();
    }
    
    public static Logger getLogger() {
        return MinecraftCapes.logger;
    }
    
    public static boolean isLabyMod() {
        return MinecraftCapes.isLabyMod;
    }
    
    public static void setLabyMod(final boolean isLabyMod) {
        MinecraftCapes.isLabyMod = isLabyMod;
    }
    
    static {
        logger = LogManager.getLogger();
        MinecraftCapes.isLabyMod = false;
    }
}
