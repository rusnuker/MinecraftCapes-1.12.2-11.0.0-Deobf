//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.proxy;

import net.minecraft.client.settings.*;
import net.minecraftcapes.config.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.common.*;
import net.minecraftcapes.*;
import net.minecraftcapes.events.*;

public class ClientProxy implements IProxy
{
    public static final KeyBinding menuKey;
    
    @Override
    public void init() {
        MinecraftCapesConfig.loadConfig();
        ClientRegistry.registerKeyBinding(ClientProxy.menuKey);
    }
    
    @Override
    public void postInit() {
        MinecraftForge.EVENT_BUS.register((Object)new KeyHandlerEvent());
        MinecraftForge.EVENT_BUS.register((Object)new PlayerEventHandler());
        try {
            Class.forName("net.labymod.core.LabyModCore");
            MinecraftCapes.getLogger().debug("Starting in LabyMod Mode");
            MinecraftCapes.setLabyMod(true);
            MinecraftCapesLabyModPostInit.init();
        }
        catch (ClassNotFoundException e) {
            MinecraftCapes.getLogger().debug("Starting in Forge Mode");
            MinecraftCapesPostInit.init();
        }
    }
    
    static {
        menuKey = new KeyBinding("key.minecraftcapes.gui", 36, "category.minecraftcapes.gui");
    }
}
