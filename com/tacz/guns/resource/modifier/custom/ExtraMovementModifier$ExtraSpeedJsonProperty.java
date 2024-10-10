/*    */ package com.tacz.guns.resource.modifier.custom;
/*    */ 
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
/*    */ import com.tacz.guns.resource.pojo.data.gun.MoveSpeed;
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
/*    */ public class ExtraSpeedJsonProperty
/*    */   extends JsonProperty<MoveSpeed>
/*    */ {
/*    */   public ExtraSpeedJsonProperty(MoveSpeed value) {
/* 51 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initComponents() {
/* 56 */     MoveSpeed speed = (MoveSpeed)getValue();
/* 57 */     if (speed == null)
/* 58 */       return;  resolveComponent(speed.getBaseMultiplier(), "movement_speed");
/* 59 */     resolveComponent(speed.getAimMultiplier(), "aim_speed");
/* 60 */     resolveComponent(speed.getReloadMultiplier(), "reload_speed");
/*    */   }
/*    */   
/*    */   private void resolveComponent(float amount, String key) {
/* 64 */     if (amount > 0.0F) {
/* 65 */       this.components.add(Component.m_237115_(String.format("tooltip.tacz.attachment.%s.increase", new Object[] { key })).m_130940_(ChatFormatting.GREEN));
/* 66 */     } else if (amount < 0.0F) {
/* 67 */       this.components.add(Component.m_237115_(String.format("tooltip.tacz.attachment.%s.decrease", new Object[] { key })).m_130940_(ChatFormatting.RED));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\ExtraMovementModifier$ExtraSpeedJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */