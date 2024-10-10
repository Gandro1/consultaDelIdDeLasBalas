/*     */ package com.tacz.guns.client.particle;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.VertexConsumer;
/*     */ import com.tacz.guns.api.TimelessAPI;
/*     */ import com.tacz.guns.client.resource.index.ClientAmmoIndex;
/*     */ import com.tacz.guns.client.resource.index.ClientGunIndex;
/*     */ import com.tacz.guns.config.client.RenderConfig;
/*     */ import com.tacz.guns.init.ModBlocks;
/*     */ import com.tacz.guns.particles.BulletHoleOption;
/*     */ import net.minecraft.client.Camera;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.particle.ParticleProvider;
/*     */ import net.minecraft.client.particle.ParticleRenderType;
/*     */ import net.minecraft.client.particle.TextureSheetParticle;
/*     */ import net.minecraft.client.renderer.LightTexture;
/*     */ import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.particles.ParticleOptions;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.inventory.InventoryMenu;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Block;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.joml.Quaternionf;
/*     */ import org.joml.Quaternionfc;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ public class BulletHoleParticle extends TextureSheetParticle {
/*     */   private final Direction direction;
/*     */   private final BlockPos pos;
/*     */   
/*     */   public BulletHoleParticle(ClientLevel world, double x, double y, double z, Direction direction, BlockPos pos, String ammoId, String gunId) {
/*  42 */     super(world, x, y, z);
/*  43 */     m_108337_(getSprite(pos));
/*  44 */     this.direction = direction;
/*  45 */     this.pos = pos;
/*  46 */     this.f_107225_ = getLifetimeFromConfig(world);
/*  47 */     this.f_107219_ = false;
/*  48 */     this.f_107226_ = 0.0F;
/*  49 */     this.f_107663_ = 0.05F;
/*     */ 
/*     */     
/*  52 */     BlockState state = world.m_8055_(pos);
/*  53 */     if (world.m_8055_(pos).m_60795_() || state.m_60713_((Block)ModBlocks.TARGET.get())) {
/*  54 */       m_107274_();
/*     */     }
/*  56 */     TimelessAPI.getClientGunIndex(new ResourceLocation(gunId)).ifPresent(gunIndex -> {
/*     */           float[] gunTracerColor = gunIndex.getTracerColor();
/*     */ 
/*     */           
/*     */           if (gunTracerColor != null) {
/*     */             this.f_107227_ = gunTracerColor[0];
/*     */             
/*     */             this.f_107228_ = gunTracerColor[1];
/*     */             
/*     */             this.f_107229_ = gunTracerColor[2];
/*     */           } else {
/*     */             TimelessAPI.getClientAmmoIndex(new ResourceLocation(ammoId)).ifPresent(());
/*     */           } 
/*     */         });
/*     */     
/*  71 */     this.f_107230_ = 0.9F;
/*     */   }
/*     */   private int uOffset; private int vOffset; private float textureDensity;
/*     */   private int getLifetimeFromConfig(ClientLevel world) {
/*  75 */     int configLife = ((Integer)RenderConfig.BULLET_HOLE_PARTICLE_LIFE.get()).intValue();
/*  76 */     if (configLife <= 1) {
/*  77 */       return configLife;
/*     */     }
/*  79 */     return configLife + world.f_46441_.m_188503_(configLife / 2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m_108337_(TextureAtlasSprite sprite) {
/*  84 */     super.m_108337_(sprite);
/*  85 */     this.uOffset = this.f_107223_.m_188503_(16);
/*  86 */     this.vOffset = this.f_107223_.m_188503_(16);
/*     */     
/*  88 */     this.textureDensity = (sprite.m_118410_() - sprite.m_118409_()) / 16.0F;
/*     */   }
/*     */   
/*     */   private TextureAtlasSprite getSprite(BlockPos pos) {
/*  92 */     Minecraft minecraft = Minecraft.m_91087_();
/*  93 */     ClientLevel clientLevel = minecraft.f_91073_;
/*  94 */     if (clientLevel != null) {
/*  95 */       BlockState state = clientLevel.m_8055_(pos);
/*  96 */       return Minecraft.m_91087_().m_91289_().m_110907_().getTexture(state, (Level)clientLevel, pos);
/*     */     } 
/*  98 */     return Minecraft.m_91087_().m_91258_(InventoryMenu.f_39692_).apply(MissingTextureAtlasSprite.m_118071_());
/*     */   }
/*     */ 
/*     */   
/*     */   protected float m_5970_() {
/* 103 */     return this.f_108321_.m_118409_() + this.uOffset * this.textureDensity;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float m_5951_() {
/* 108 */     return this.f_108321_.m_118411_() + this.vOffset * this.textureDensity;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float m_5952_() {
/* 113 */     return m_5970_() + this.textureDensity;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float m_5950_() {
/* 118 */     return m_5951_() + this.textureDensity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_5989_() {
/* 123 */     super.m_5989_();
/* 124 */     if (this.f_107208_.m_8055_(this.pos).m_60795_()) {
/* 125 */       m_107274_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void m_5744_(VertexConsumer buffer, Camera renderInfo, float partialTicks) {
/* 131 */     Vec3 view = renderInfo.m_90583_();
/* 132 */     float particleX = (float)(Mth.m_14139_(partialTicks, this.f_107209_, this.f_107212_) - view.m_7096_());
/* 133 */     float particleY = (float)(Mth.m_14139_(partialTicks, this.f_107210_, this.f_107213_) - view.m_7098_());
/* 134 */     float particleZ = (float)(Mth.m_14139_(partialTicks, this.f_107211_, this.f_107214_) - view.m_7094_());
/* 135 */     Quaternionf quaternion = this.direction.m_253075_();
/* 136 */     Vector3f[] points = { new Vector3f(-1.0F, 0.01F, -1.0F), new Vector3f(-1.0F, 0.01F, 1.0F), new Vector3f(1.0F, 0.01F, 1.0F), new Vector3f(1.0F, 0.01F, -1.0F) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     float scale = m_5902_(partialTicks);
/*     */     
/* 145 */     for (int i = 0; i < 4; i++) {
/* 146 */       Vector3f vector3f = points[i];
/* 147 */       vector3f.rotate((Quaternionfc)quaternion);
/* 148 */       vector3f.mul(scale);
/* 149 */       vector3f.add(particleX, particleY, particleZ);
/*     */     } 
/*     */ 
/*     */     
/* 153 */     float u0 = m_5970_();
/* 154 */     float u1 = m_5952_();
/* 155 */     float v0 = m_5951_();
/* 156 */     float v1 = m_5950_();
/*     */ 
/*     */     
/* 159 */     int light = Math.max(15 - this.f_107224_ / 2, 0);
/* 160 */     int lightColor = LightTexture.m_109885_(light, light);
/*     */ 
/*     */     
/* 163 */     float colorPercent = light / 15.0F;
/* 164 */     float red = this.f_107227_ * colorPercent;
/* 165 */     float green = this.f_107228_ * colorPercent;
/* 166 */     float blue = this.f_107229_ * colorPercent;
/*     */ 
/*     */     
/* 169 */     double threshold = ((Double)RenderConfig.BULLET_HOLE_PARTICLE_FADE_THRESHOLD.get()).doubleValue() * this.f_107225_;
/* 170 */     float fade = 1.0F - (float)(Math.max(this.f_107224_ - threshold, 0.0D) / (this.f_107225_ - threshold));
/* 171 */     float alphaFade = this.f_107230_ * fade;
/*     */     
/* 173 */     buffer.m_5483_(points[0].x(), points[0].y(), points[0].z()).m_7421_(u1, v1).m_85950_(red, green, blue, alphaFade).m_85969_(lightColor).m_5752_();
/* 174 */     buffer.m_5483_(points[1].x(), points[1].y(), points[1].z()).m_7421_(u1, v0).m_85950_(red, green, blue, alphaFade).m_85969_(lightColor).m_5752_();
/* 175 */     buffer.m_5483_(points[2].x(), points[2].y(), points[2].z()).m_7421_(u0, v0).m_85950_(red, green, blue, alphaFade).m_85969_(lightColor).m_5752_();
/* 176 */     buffer.m_5483_(points[3].x(), points[3].y(), points[3].z()).m_7421_(u0, v1).m_85950_(red, green, blue, alphaFade).m_85969_(lightColor).m_5752_();
/*     */   }
/*     */ 
/*     */   
/*     */   public ParticleRenderType m_7556_() {
/* 181 */     return ParticleRenderType.f_107429_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static class Provider
/*     */     implements ParticleProvider<BulletHoleOption>
/*     */   {
/*     */     public BulletHoleParticle createParticle(@NotNull BulletHoleOption option, @NotNull ClientLevel world, double x, double y, double z, double pXSpeed, double pYSpeed, double pZSpeed) {
/* 191 */       BulletHoleParticle particle = new BulletHoleParticle(world, x, y, z, option.getDirection(), option.getPos(), option.getAmmoId(), option.getGunId());
/* 192 */       return particle;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\client\particle\BulletHoleParticle.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */