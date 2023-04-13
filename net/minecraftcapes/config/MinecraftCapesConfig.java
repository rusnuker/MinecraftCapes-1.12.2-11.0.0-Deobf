//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.config;

import com.google.gson.*;
import java.io.*;
import net.minecraft.client.*;
import java.nio.file.*;

public class MinecraftCapesConfig
{
    private static File runDirectory;
    private static Path configFile;
    private static ConfigValues config;
    
    public static void setCapeVisible(final boolean enabled) {
        MinecraftCapesConfig.config.capeVisible = enabled;
        saveConfig();
    }
    
    public static void setEarsVisible(final boolean enabled) {
        MinecraftCapesConfig.config.earsVisible = enabled;
        saveConfig();
    }
    
    public static boolean isCapeVisible() {
        return MinecraftCapesConfig.config.capeVisible;
    }
    
    public static boolean isEarsVisible() {
        return MinecraftCapesConfig.config.earsVisible;
    }
    
    public static void loadConfig() {
        try {
            if (!MinecraftCapesConfig.configFile.toFile().exists()) {
                final InputStream defaultConfigFile = MinecraftCapesConfig.class.getResourceAsStream("/assets/minecraftcapes/config.json");
                Files.copy(defaultConfigFile, MinecraftCapesConfig.configFile, new CopyOption[0]);
            }
            final Reader reader = new FileReader(MinecraftCapesConfig.configFile.toFile());
            MinecraftCapesConfig.config = (ConfigValues)new Gson().fromJson(reader, (Class)ConfigValues.class);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void saveConfig() {
        try {
            final Writer writer = new FileWriter(MinecraftCapesConfig.configFile.toFile());
            new Gson().toJson((Object)MinecraftCapesConfig.config, (Appendable)writer);
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ConfigValues getConfig() {
        return MinecraftCapesConfig.config;
    }
    
    static {
        MinecraftCapesConfig.runDirectory = Minecraft.getMinecraft().gameDir;
        MinecraftCapesConfig.configFile = Paths.get(MinecraftCapesConfig.runDirectory + "/config/minecraftcapes.json", new String[0]);
        MinecraftCapesConfig.config = null;
    }
    
    class ConfigValues
    {
        private boolean capeVisible;
        private boolean earsVisible;
        
        ConfigValues() {
            this.capeVisible = true;
            this.earsVisible = true;
        }
    }
}
