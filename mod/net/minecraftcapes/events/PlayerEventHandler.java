//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.events;

import net.minecraftforge.event.entity.*;
import net.minecraft.entity.player.*;
import net.minecraftcapes.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftcapes.*;
import java.net.*;
import net.minecraft.client.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onPlayerJoin(final EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayer && event.getWorld().isRemote) {
            final EntityPlayer player = (EntityPlayer)event.getEntity();
            final PlayerHandler playerHandler = PlayerHandler.getFromPlayer(player);
            if (playerHandler == null || playerHandler.getHasInfo()) {
                return;
            }
            downloadProfile(playerHandler);
        }
    }
    
    public static void downloadProfile(final PlayerHandler playerHandler) {
        final URL url2;
        URL url;
        HttpURLConnection httpurlconnection;
        Reader reader;
        ProfileResult profileResult;
        final Thread playerDownload = new Thread(() -> {
            try {
                MinecraftCapes.getLogger().debug("Getting profile for {}", (Object)playerHandler.getPlayerUUID());
                new URL("https://minecraftcapes.net/profile/" + playerHandler.getPlayerUUID().toString().replace("-", ""));
                url = url2;
                httpurlconnection = (HttpURLConnection)url.openConnection(Minecraft.getMinecraft().getProxy());
                httpurlconnection.setDoInput(true);
                httpurlconnection.setDoOutput(false);
                httpurlconnection.connect();
                if (httpurlconnection.getResponseCode() / 100 == 2) {
                    reader = new InputStreamReader(httpurlconnection.getInputStream(), "UTF-8");
                    profileResult = (ProfileResult)new Gson().fromJson(reader, (Class)ProfileResult.class);
                    reader.close();
                    playerHandler.setHasInfo(true);
                    playerHandler.setHasCapeGlint(profileResult.capeGlint);
                    playerHandler.setUpsideDown(profileResult.upsideDown);
                    if (profileResult.textures.get("cape") != null) {
                        playerHandler.applyCape(profileResult.textures.get("cape"));
                    }
                    if (profileResult.textures.get("ears") != null) {
                        playerHandler.applyEars(profileResult.textures.get("ears"));
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return;
        });
        playerDownload.setDaemon(true);
        playerDownload.start();
    }
    
    class ProfileResult
    {
        private boolean capeGlint;
        private boolean upsideDown;
        private Map<String, String> textures;
        
        ProfileResult() {
            this.capeGlint = false;
            this.upsideDown = false;
            this.textures = null;
        }
    }
}
