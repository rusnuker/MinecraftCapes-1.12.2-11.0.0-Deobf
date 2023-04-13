//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.events;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraftcapes.player.render.*;
import java.util.*;

public class MinecraftCapesPostInit
{
    public static void init() {
        Minecraft.getMinecraft().gameSettings.setModelPartEnabled(EnumPlayerModelParts.CAPE, true);
        for (final RenderPlayer render : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
            try {
                final List<LayerRenderer<?>> layerRenderers = (List<LayerRenderer<?>>)ObfuscationReflectionHelper.getPrivateValue((Class)RenderLivingBase.class, (Object)render, "layerRenderers");
                layerRenderers.removeIf(modelFeature -> modelFeature instanceof LayerElytra);
                layerRenderers.removeIf(modelFeature -> modelFeature instanceof LayerCape);
                ObfuscationReflectionHelper.setPrivateValue((Class)RenderLivingBase.class, (Object)render, (Object)layerRenderers, "layerRenderers");
                final ModelRenderer bipedDeadmau5Head = new ModelRenderer((ModelBase)render.getMainModel(), 0, 0);
                bipedDeadmau5Head.setTextureSize(14, 7);
                bipedDeadmau5Head.addBox(1.5f, -10.5f, -1.0f, 6, 6, 1, 0.0f);
                bipedDeadmau5Head.addBox(-7.5f, -10.5f, -1.0f, 6, 6, 1, 0.0f);
                bipedDeadmau5Head.setRotationPoint(0.0f, 0.0f, 0.0f);
                ObfuscationReflectionHelper.setPrivateValue((Class)ModelPlayer.class, (Object)render.getMainModel(), (Object)bipedDeadmau5Head, "bipedDeadmau5Head");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            render.addLayer((LayerRenderer)new CapeLayer(render));
            render.addLayer((LayerRenderer)new Deadmau5(render));
            render.addLayer((LayerRenderer)new ElytraLayer((RenderLivingBase<?>)render));
        }
    }
}
