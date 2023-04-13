//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.events;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftcapes.proxy.*;
import net.minecraft.client.*;
import net.minecraftcapes.gui.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Mod.EventBusSubscriber(modid = "minecraftcapes", value = { Side.CLIENT })
public class KeyHandlerEvent
{
    @SubscribeEvent
    public static void onKeyPress(final TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && ClientProxy.menuKey.isKeyDown()) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new MenuScreen());
        }
    }
}
