/*     */ package com.tacz.guns.resource.modifier.custom;
/*     */ 
/*     */ import com.tacz.guns.api.modifier.JsonProperty;
/*     */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*     */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdsJsonProperty
/*     */   extends JsonProperty<Modifier>
/*     */ {
/*     */   public AdsJsonProperty(Modifier value) {
/*  84 */     super(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initComponents() {
/*  89 */     Modifier value = (Modifier)getValue();
/*  90 */     float adsAddendTime = 0.0F;
/*  91 */     if (value != null) {
/*     */       
/*  93 */       double eval = AttachmentPropertyManager.eval(value, 0.2D);
/*  94 */       adsAddendTime = (float)(eval - 0.2D);
/*     */     } 
/*     */     
/*  97 */     if (adsAddendTime > 0.0F) {
/*  98 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.ads.increase").m_130940_(ChatFormatting.RED));
/*  99 */     } else if (adsAddendTime < 0.0F) {
/* 100 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.ads.decrease").m_130940_(ChatFormatting.GREEN));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\AdsModifier$AdsJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */