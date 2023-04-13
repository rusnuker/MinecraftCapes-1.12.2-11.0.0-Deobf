//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.proxy;

import net.minecraftcapes.*;

public class ServerProxy implements IProxy
{
    public void postInit() {
        MinecraftCapes.getLogger().error("MinecraftCapes has been loaded on server side. MinecraftCapes is a client only mod. No need to worry about this. You can delete the mod if you wish!");
    }
}
