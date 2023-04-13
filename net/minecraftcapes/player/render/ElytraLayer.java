//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.player.render;

import net.minecraft.util.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.entity.*;
import net.minecraftcapes.player.*;
import net.minecraftcapes.config.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.model.*;
import net.minecraft.item.*;

public class ElytraLayer implements LayerRenderer<EntityLivingBase>
{
    private static final ResourceLocation TEXTURE_ELYTRA;
    protected final RenderLivingBase<?> renderPlayer;
    private final ModelElytra modelElytra;
    
    public ElytraLayer(final RenderLivingBase<?> p_i47185_1_) {
        this.modelElytra = new ModelElytra();
        this.renderPlayer = p_i47185_1_;
    }
    
    public void doRenderLayer(final EntityLivingBase entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        final ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (itemstack.getItem() == Items.ELYTRA) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            final AbstractClientPlayer abstractclientplayerentity = (AbstractClientPlayer)entitylivingbaseIn;
            final PlayerHandler playerHandler = PlayerHandler.getFromPlayer((EntityPlayer)abstractclientplayerentity);
            if (playerHandler.getCapeLocation() != null && MinecraftCapesConfig.isCapeVisible()) {
                this.renderPlayer.bindTexture(playerHandler.getCapeLocation());
            }
            else if (abstractclientplayerentity.getLocationCape() != null && abstractclientplayerentity.isWearing(EnumPlayerModelParts.CAPE)) {
                this.renderPlayer.bindTexture(abstractclientplayerentity.getLocationCape());
            }
            else {
                this.renderPlayer.bindTexture(ElytraLayer.TEXTURE_ELYTRA);
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0f, 0.0f, 0.125f);
            this.modelElytra.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, (Entity)entitylivingbaseIn);
            this.modelElytra.render((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            if (itemstack.isItemEnchanted()) {
                LayerArmorBase.renderEnchantedGlint((RenderLivingBase)this.renderPlayer, entitylivingbaseIn, (ModelBase)this.modelElytra, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public boolean shouldCombineTextures() {
        return false;
    }
    
    static {
        TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");
    }
}
