//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.player.render;

import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftcapes.config.*;
import net.minecraftcapes.player.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.*;
import net.minecraftcapes.*;
import net.minecraft.entity.*;

public class Deadmau5 implements LayerRenderer<AbstractClientPlayer>
{
    private final RenderPlayer playerRenderer;
    
    public Deadmau5(final RenderPlayer playerRendererIn) {
        this.playerRenderer = playerRendererIn;
    }
    
    public void doRenderLayer(final AbstractClientPlayer entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (!MinecraftCapesConfig.isEarsVisible()) {
            return;
        }
        final PlayerHandler playerHandler = PlayerHandler.getFromPlayer((EntityPlayer)entitylivingbaseIn);
        if (playerHandler.getEarLocation() != null && entitylivingbaseIn.hasSkin() && !entitylivingbaseIn.isInvisible()) {
            this.playerRenderer.bindTexture(playerHandler.getEarLocation());
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            if (MinecraftCapes.isLabyMod()) {
                for (int i = 0; i < 2; ++i) {
                    final float f = entitylivingbaseIn.prevRotationYaw + (entitylivingbaseIn.rotationYaw - entitylivingbaseIn.prevRotationYaw) * partialTicks - (entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks);
                    final float f2 = entitylivingbaseIn.prevRotationPitch + (entitylivingbaseIn.rotationPitch - entitylivingbaseIn.prevRotationPitch) * partialTicks;
                    GlStateManager.pushMatrix();
                    GlStateManager.rotate(f, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(f2, 1.0f, 0.0f, 0.0f);
                    GlStateManager.translate(0.375f * (i * 2 - 1), 0.0f, 0.0f);
                    GlStateManager.translate(0.0f, -0.375f, 0.0f);
                    GlStateManager.rotate(-f2, 1.0f, 0.0f, 0.0f);
                    GlStateManager.rotate(-f, 0.0f, 1.0f, 0.0f);
                    GlStateManager.scale(1.3333334f, 1.3333334f, 1.3333334f);
                    if (entitylivingbaseIn.isSneaking()) {
                        GlStateManager.translate(0.0f, 0.175f, 0.0f);
                    }
                    this.playerRenderer.getMainModel().renderDeadmau5Head(0.0625f);
                    GlStateManager.popMatrix();
                }
            }
            else {
                GlStateManager.pushMatrix();
                if (entitylivingbaseIn.isSneaking()) {
                    GlStateManager.translate(0.0f, 0.25f, 0.0f);
                }
                GlStateManager.scale(1.3333334f, 1.3333334f, 1.3333334f);
                this.playerRenderer.getMainModel().renderDeadmau5Head(0.0625f);
                GlStateManager.popMatrix();
            }
        }
    }
    
    public boolean shouldCombineTextures() {
        return true;
    }
}
