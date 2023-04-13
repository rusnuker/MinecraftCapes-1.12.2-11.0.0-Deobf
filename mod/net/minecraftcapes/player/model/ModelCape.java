//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.player.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

public class ModelCape extends ModelBase
{
    private ModelRenderer cape;
    
    public ModelCape() {
        (this.cape = new ModelRenderer((ModelBase)this, 0, 0)).setTextureSize(64, 32);
        this.cape.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1);
    }
    
    public void render(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.cape.render(scale);
    }
    
    public void setRotationAngles(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        final EntityPlayer livingEntity = (EntityPlayer)entityIn;
        if (livingEntity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty()) {
            if (livingEntity.isSneaking()) {
                this.cape.rotationPointZ = 1.4f;
                this.cape.rotationPointY = 1.85f;
            }
            else {
                this.cape.rotationPointZ = 0.0f;
                this.cape.rotationPointY = 0.0f;
            }
        }
        else if (livingEntity.isSneaking()) {
            this.cape.rotationPointZ = 0.3f;
            this.cape.rotationPointY = 0.8f;
        }
        else {
            this.cape.rotationPointZ = -1.1f;
            this.cape.rotationPointY = -0.85f;
        }
    }
}
