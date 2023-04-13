//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.events;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.labymod.core.*;
import net.labymod.core_implementation.mc112.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import org.apache.commons.lang3.*;
import net.minecraftcapes.player.render.*;
import java.lang.reflect.*;

public class MinecraftCapesLabyModPostInit
{
    public static void init() {
        Minecraft.getMinecraft().gameSettings.setModelPartEnabled(EnumPlayerModelParts.CAPE, true);
        try {
            final Class<?> clazz = LabyModCore.getCoreAdapter().getClass();
            final Field field = clazz.getDeclaredField("renderPlayerImpl");
            field.setAccessible(true);
            field.set(LabyModCore.getCoreAdapter(), new RenderPlayerImplementation() {
                public LayerRenderer<?>[] getLayerRenderers(final RenderPlayer renderPlayer) {
                    LayerRenderer[] layers = super.getLayerRenderers(renderPlayer);
                    layers = (LayerRenderer[])ArrayUtils.add((Object[])layers, (Object)new CapeLayer(renderPlayer));
                    layers = (LayerRenderer[])ArrayUtils.add((Object[])layers, (Object)new Deadmau5(renderPlayer));
                    return (LayerRenderer<?>[])layers;
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
