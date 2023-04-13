//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.player.render;

import net.minecraft.client.entity.*;
import net.minecraftcapes.player.model.*;
import net.minecraftcapes.config.*;
import net.minecraftcapes.player.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import net.minecraft.client.model.*;
import net.minecraft.item.*;

public class CapeLayer implements LayerRenderer<AbstractClientPlayer>
{
    private final RenderPlayer renderPlayer;
    private final ModelCape modelCape;
    
    public CapeLayer(final RenderPlayer playerRendererIn) {
        this.modelCape = new ModelCape();
        this.renderPlayer = playerRendererIn;
    }
    
    public void doRenderLayer(final AbstractClientPlayer entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (!MinecraftCapesConfig.isCapeVisible() && entitylivingbaseIn.getLocationCape() == null) {
            return;
        }
        final PlayerHandler playerHandler = PlayerHandler.getFromPlayer((EntityPlayer)entitylivingbaseIn);
        if (playerHandler.getShowCape() && !entitylivingbaseIn.isInvisible() && (entitylivingbaseIn.getLocationCape() != null || playerHandler.getCapeLocation() != null)) {
            final ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if (itemstack.getItem() != Items.ELYTRA) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                if (MinecraftCapesConfig.isCapeVisible() && playerHandler.getCapeLocation() != null) {
                    this.renderPlayer.bindTexture(playerHandler.getCapeLocation());
                }
                else {
                    this.renderPlayer.bindTexture(entitylivingbaseIn.getLocationCape());
                }
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0f, 0.0f, 0.125f);
                final double d0 = entitylivingbaseIn.prevChasingPosX + (entitylivingbaseIn.chasingPosX - entitylivingbaseIn.prevChasingPosX) * partialTicks - (entitylivingbaseIn.prevPosX + (entitylivingbaseIn.posX - entitylivingbaseIn.prevPosX) * partialTicks);
                final double d2 = entitylivingbaseIn.prevChasingPosY + (entitylivingbaseIn.chasingPosY - entitylivingbaseIn.prevChasingPosY) * partialTicks - (entitylivingbaseIn.prevPosY + (entitylivingbaseIn.posY - entitylivingbaseIn.prevPosY) * partialTicks);
                final double d3 = entitylivingbaseIn.prevChasingPosZ + (entitylivingbaseIn.chasingPosZ - entitylivingbaseIn.prevChasingPosZ) * partialTicks - (entitylivingbaseIn.prevPosZ + (entitylivingbaseIn.posZ - entitylivingbaseIn.prevPosZ) * partialTicks);
                final float f = entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks;
                final double d4 = MathHelper.sin(f * 0.017453292f);
                final double d5 = -MathHelper.cos(f * 0.017453292f);
                float f2 = (float)d2 * 10.0f;
                f2 = MathHelper.clamp(f2, -6.0f, 32.0f);
                float f3 = (float)(d0 * d4 + d3 * d5) * 100.0f;
                final float f4 = (float)(d0 * d5 - d3 * d4) * 100.0f;
                if (f3 < 0.0f) {
                    f3 = 0.0f;
                }
                final float f5 = entitylivingbaseIn.prevCameraYaw + (entitylivingbaseIn.cameraYaw - entitylivingbaseIn.prevCameraYaw) * partialTicks;
                f2 += MathHelper.sin((entitylivingbaseIn.prevDistanceWalkedModified + (entitylivingbaseIn.distanceWalkedModified - entitylivingbaseIn.prevDistanceWalkedModified) * partialTicks) * 6.0f) * 32.0f * f5;
                if (entitylivingbaseIn.isSneaking()) {
                    f2 += 25.0f;
                }
                GlStateManager.rotate(6.0f + f3 / 2.0f + f2, 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(f4 / 2.0f, 0.0f, 0.0f, 1.0f);
                GlStateManager.rotate(-f4 / 2.0f, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
                this.modelCape.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, (Entity)entitylivingbaseIn);
                this.modelCape.render((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                if (playerHandler.getHasCapeGlint() && MinecraftCapesConfig.isCapeVisible() && playerHandler.getCapeLocation() != null) {
                    LayerArmorBase.renderEnchantedGlint((RenderLivingBase)this.renderPlayer, (EntityLivingBase)entitylivingbaseIn, (ModelBase)this.modelCape, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                }
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }
    
    public boolean shouldCombineTextures() {
        return false;
    }
}
