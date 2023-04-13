//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.gui;

import net.minecraft.client.resources.*;
import net.minecraftcapes.config.*;
import net.minecraft.client.gui.*;
import net.minecraftcapes.player.*;
import net.minecraft.entity.player.*;
import net.minecraftcapes.events.*;

public class MenuScreen extends GuiScreen
{
    private String title;
    
    public void initGui() {
        int i = 0;
        this.title = I18n.format("category.minecraftcapes.gui", new Object[0]);
        this.addButton(new GuiButton(0, this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), 150, 20, this.getButtonString("Custom Capes", MinecraftCapesConfig.isCapeVisible())));
        ++i;
        this.addButton(new GuiButton(1, this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), 150, 20, this.getButtonString("Custom Ears", MinecraftCapesConfig.isEarsVisible())));
        ++i;
        ++i;
        this.addButton(new GuiButton(2, this.width / 2 - 75, this.height / 6 + 24 * (i >> 1), 150, 20, "Reload Profile"));
        ++i;
        this.addButton(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 24 * (i >> 1), 200, 20, I18n.format("gui.done", new Object[0])));
    }
    
    protected void actionPerformed(final GuiButton button) {
        if (button.enabled) {
            if (button.id == 0) {
                MinecraftCapesConfig.setCapeVisible(!MinecraftCapesConfig.isCapeVisible());
                button.displayString = this.getButtonString("Custom Capes", MinecraftCapesConfig.isCapeVisible());
            }
            else if (button.id == 1) {
                MinecraftCapesConfig.setEarsVisible(!MinecraftCapesConfig.isEarsVisible());
                button.displayString = this.getButtonString("Custom Ears", MinecraftCapesConfig.isEarsVisible());
            }
            else if (button.id == 2) {
                PlayerEventHandler.downloadProfile(PlayerHandler.getFromPlayer((EntityPlayer)this.mc.player));
            }
            else if (button.id == 3) {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    private String getButtonString(final String buttonText, final boolean value) {
        String onOff;
        if (value) {
            onOff = I18n.format("options.on", new Object[0]);
        }
        else {
            onOff = I18n.format("options.off", new Object[0]);
        }
        return buttonText + ": " + onOff;
    }
}
