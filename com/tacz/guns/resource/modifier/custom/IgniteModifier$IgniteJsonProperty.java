/*    */ package com.tacz.guns.resource.modifier.custom;
/*    */ 
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
/*    */ import com.tacz.guns.resource.pojo.data.gun.Ignite;
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
/*    */ public class IgniteJsonProperty
/*    */   extends JsonProperty<Ignite>
/*    */ {
/*    */   public IgniteJsonProperty(Ignite value) {
/* 57 */     super(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initComponents() {
/* 62 */     Ignite value = (Ignite)getValue();
/* 63 */     if (value == null) {
/*    */       return;
/*    */     }
/* 66 */     if (value.isIgniteEntity()) {
/* 67 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.ignite.entity").m_130940_(ChatFormatting.GREEN));
/*    */     }
/* 69 */     if (value.isIgniteBlock())
/* 70 */       this.components.add(Component.m_237115_("tooltip.tacz.attachment.ignite.block").m_130940_(ChatFormatting.GREEN)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\IgniteModifier$IgniteJsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */