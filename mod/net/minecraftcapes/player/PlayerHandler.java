//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "F:\rusherhack"!

//Decompiled by Procyon!

package net.minecraftcapes.player;

import java.util.*;
import net.minecraft.entity.player.*;
import org.apache.commons.codec.binary.*;
import javax.imageio.*;
import net.minecraftcapes.*;
import java.io.*;
import it.unimi.dsi.fastutil.ints.*;
import java.awt.image.*;
import net.minecraft.util.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.texture.*;

public class PlayerHandler
{
    private static HashMap<UUID, PlayerHandler> instances;
    private boolean hasStaticCape;
    private boolean hasEars;
    private boolean hasAnimatedCape;
    private Boolean showCape;
    private Boolean hasCapeGlint;
    private boolean upsideDown;
    private Boolean hasInfo;
    private UUID playerUUID;
    private Int2ObjectMap<BufferedImage> animatedCape;
    private long lastFrameTime;
    private int lastFrame;
    private int capeInterval;
    
    public PlayerHandler(final EntityPlayer player) {
        this.hasStaticCape = false;
        this.hasEars = false;
        this.hasAnimatedCape = false;
        this.showCape = true;
        this.hasCapeGlint = false;
        this.upsideDown = false;
        this.hasInfo = false;
        this.lastFrameTime = 0L;
        this.lastFrame = 0;
        this.capeInterval = 100;
        this.playerUUID = player.getUniqueID();
        PlayerHandler.instances.put(this.playerUUID, this);
    }
    
    public static PlayerHandler getFromPlayer(final EntityPlayer player) {
        final PlayerHandler playerHandler = PlayerHandler.instances.get(player.getUniqueID());
        return (playerHandler == null) ? new PlayerHandler(player) : playerHandler;
    }
    
    private BufferedImage readTexture(final String textureBase64) {
        try {
            final byte[] imgBytes = Base64.decodeBase64(textureBase64);
            final ByteArrayInputStream bias = new ByteArrayInputStream(imgBytes);
            return ImageIO.read(bias);
        }
        catch (IOException e) {
            MinecraftCapes.getLogger().error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public void applyCape(final String cape) {
        final BufferedImage capeImage = this.readTexture(cape);
        if (capeImage.getHeight() != capeImage.getWidth() / 2) {
            final Int2ObjectMap<BufferedImage> animatedCape = (Int2ObjectMap<BufferedImage>)new Int2ObjectOpenHashMap();
            for (int totalFrames = capeImage.getHeight() / (capeImage.getWidth() / 2), currentFrame = 0; currentFrame < totalFrames; ++currentFrame) {
                final BufferedImage frame = new BufferedImage(capeImage.getWidth(), capeImage.getWidth() / 2, 2);
                final Graphics frameGraphics = frame.getGraphics();
                frameGraphics.drawImage(capeImage, 0, 0, capeImage.getWidth(), capeImage.getWidth() / 2, 0, currentFrame * (capeImage.getWidth() / 2), capeImage.getWidth(), (currentFrame + 1) * (capeImage.getWidth() / 2), null);
                frameGraphics.dispose();
                animatedCape.put(currentFrame, (Object)frame);
            }
            this.setAnimatedCape(animatedCape);
            MinecraftCapes.getLogger().debug("Animated cape loaded for {}", (Object)this.playerUUID);
        }
        else {
            int imageWidth = 64;
            int imageHeight = 32;
            for (int srcWidth = capeImage.getWidth(), srcHeight = capeImage.getHeight(); imageWidth < srcWidth || imageHeight < srcHeight; imageWidth *= 2, imageHeight *= 2) {}
            final BufferedImage imgNew = new BufferedImage(imageWidth, imageHeight, 2);
            final Graphics g = imgNew.getGraphics();
            g.drawImage(capeImage, 0, 0, null);
            g.dispose();
            this.applyTexture(new ResourceLocation("minecraftcapes", "capes/" + this.playerUUID), imgNew);
            this.setHasStaticCape(true);
            MinecraftCapes.getLogger().debug("Static cape loaded for {}", (Object)this.playerUUID);
        }
    }
    
    public void applyEars(final String ears) {
        BufferedImage earImage;
        if (MinecraftCapes.isLabyMod()) {
            final BufferedImage oldImage = this.readTexture(ears);
            earImage = new BufferedImage(64, 64, 2);
            final Graphics g = earImage.getGraphics();
            g.drawImage(oldImage, 24, 0, null);
            g.dispose();
        }
        else {
            earImage = this.readTexture(ears);
        }
        this.applyTexture(new ResourceLocation("minecraftcapes", "ears/" + this.playerUUID), earImage);
        this.setHasEars(true);
    }
    
    public void setAnimatedCape(final Int2ObjectMap<BufferedImage> animatedCape) {
        MinecraftCapes.getLogger().debug("Setting animated cape for {}", (Object)this.playerUUID);
        this.animatedCape = animatedCape;
        this.setHasAnimatedCape(true);
        this.loadFramesToResource();
    }
    
    private void loadFramesToResource() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ldc_w           "Loading resources to memory for {}"
        //     6: aload_0         /* this */
        //     7: getfield        net/minecraftcapes/player/PlayerHandler.playerUUID:Ljava/util/UUID;
        //    10: invokeinterface org/apache/logging/log4j/Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V
        //    15: aload_0         /* this */
        //    16: invokevirtual   net/minecraftcapes/player/PlayerHandler.getAnimatedCape:()Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;
        //    19: aload_0         /* this */
        //    20: invokedynamic   BootstrapMethod #0, accept:(Lnet/minecraftcapes/player/PlayerHandler;)Ljava/util/function/BiConsumer;
        //    25: invokeinterface it/unimi/dsi/fastutil/ints/Int2ObjectMap.forEach:(Ljava/util/function/BiConsumer;)V
        //    30: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private ResourceLocation getFrame() {
        final long time = System.currentTimeMillis();
        if (time > this.lastFrameTime + this.capeInterval) {
            final int currentFrameNo = (this.lastFrame + 1 > this.getAnimatedCape().size() - 1) ? 0 : (this.lastFrame + 1);
            this.lastFrame = currentFrameNo;
            this.lastFrameTime = time;
            return new ResourceLocation("minecraftcapes", String.format("capes/%s/%d", this.playerUUID, currentFrameNo));
        }
        return new ResourceLocation("minecraftcapes", String.format("capes/%s/%d", this.playerUUID, this.lastFrame));
    }
    
    public ResourceLocation getCapeLocation() {
        return this.hasStaticCape ? new ResourceLocation("minecraftcapes", "capes/" + this.playerUUID) : (this.hasAnimatedCape ? this.getFrame() : null);
    }
    
    public ResourceLocation getEarLocation() {
        return this.hasEars ? new ResourceLocation("minecraftcapes", "ears/" + this.playerUUID) : null;
    }
    
    private void applyTexture(final ResourceLocation resourceLocation, final BufferedImage BufferedImage) {
        Minecraft.getMinecraft().addScheduledTask(() -> Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, (ITextureObject)new DynamicTexture(BufferedImage)));
    }
    
    @Override
    public String toString() {
        return "PlayerHandler{hasStaticCape=" + this.hasStaticCape + ", hasEars=" + this.hasEars + ", hasAnimatedCape=" + this.hasAnimatedCape + ", hasCapeGlint=" + this.hasCapeGlint + ", upsideDown=" + this.upsideDown + ", hasInfo=" + this.hasInfo + ", playerUUID=" + this.playerUUID + ", animatedCape=" + this.animatedCape + ", lastFrameTime=" + this.lastFrameTime + ", lastFrame=" + this.lastFrame + ", capeInterval=" + this.capeInterval + '}';
    }
    
    public void setHasStaticCape(final boolean hasStaticCape) {
        this.hasStaticCape = hasStaticCape;
    }
    
    public void setHasEars(final boolean hasEars) {
        this.hasEars = hasEars;
    }
    
    public void setHasAnimatedCape(final boolean hasAnimatedCape) {
        this.hasAnimatedCape = hasAnimatedCape;
    }
    
    public Boolean getShowCape() {
        return this.showCape;
    }
    
    public void setShowCape(final Boolean showCape) {
        this.showCape = showCape;
    }
    
    public Boolean getHasCapeGlint() {
        return this.hasCapeGlint;
    }
    
    public void setHasCapeGlint(final Boolean hasCapeGlint) {
        this.hasCapeGlint = hasCapeGlint;
    }
    
    public boolean isUpsideDown() {
        return this.upsideDown;
    }
    
    public void setUpsideDown(final boolean upsideDown) {
        this.upsideDown = upsideDown;
    }
    
    public Boolean getHasInfo() {
        return this.hasInfo;
    }
    
    public void setHasInfo(final Boolean hasInfo) {
        this.hasInfo = hasInfo;
    }
    
    public void setPlayerUUID(final UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    
    public UUID getPlayerUUID() {
        return this.playerUUID;
    }
    
    public Int2ObjectMap<BufferedImage> getAnimatedCape() {
        return this.animatedCape;
    }
    
    static {
        PlayerHandler.instances = new HashMap<UUID, PlayerHandler>();
    }
}
