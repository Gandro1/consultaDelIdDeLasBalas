/*    */ package com.tacz.guns.resource.modifier.custom;
/*    */ 
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
/*    */ import com.tacz.guns.config.common.GunConfig;
/*    */ import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
/*    */ import com.tacz.guns.resource.pojo.data.attachment.Modifier;
/*    */ import it.unimi.dsi.fastutil.Pair;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SilenceJsonProperty
/*    */   extends JsonProperty<Pair<Modifier, Boolean>>
/*    */ {
/*    */   public SilenceJsonProperty(Pair<Modifier, Boolean> value) {
/* 68 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initComponents() {
/* 73 */     Pair<Modifier, Boolean> value = (Pair<Modifier, Boolean>)getValue();
/* 74 */     if (value != null) {
/* 75 */       int defaultDistance = ((Integer)GunConfig.DEFAULT_GUN_FIRE_SOUND_DISTANCE.get()).intValue();
/* 76 */       double eval = AttachmentPropertyManager.eval((Modifier)value.left(), defaultDistance);
/* 77 */       int distance = (int)Math.round(eval);
/* 78 */       if (distance > defaultDistance) {
/* 79 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.sound_distance.increase").m_130940_(ChatFormatting.RED));
/* 80 */       } else if (distance < defaultDistance) {
/* 81 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.sound_distance.increase").m_130940_(ChatFormatting.GREEN));
/*    */       } 
/* 83 */       if (((Boolean)value.right()).booleanValue())
/* 84 */         this.components.add(Component.m_237115_("tooltip.tacz.attachment.silence").m_130940_(ChatFormatting.GREEN)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\SilenceModifier$SilenceJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */