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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BulletSpeedJsonProperty
/*     */   extends JsonProperty<Modifier>
/*     */ {
/*     */   public BulletSpeedJsonProperty(Modifier value) {
/*  91 */     super(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initComponents() {
/*  96 */     Modifier ammoSpeed = (Modifier)getValue();
/*  97 */     if (ammoSpeed != null) {
/*  98 */       double eval = AttachmentPropertyManager.eval(ammoSpeed, 300.0D);
/*  99 */       if (eval > 300.0D) {
/* 100 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.ammo_speed.increase").m_130940_(ChatFormatting.GREEN));
/* 101 */       } else if (eval < 300.0D) {
/* 102 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.ammo_speed.decrease").m_130940_(ChatFormatting.RED));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\AmmoSpeedModifier$BulletSpeedJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */