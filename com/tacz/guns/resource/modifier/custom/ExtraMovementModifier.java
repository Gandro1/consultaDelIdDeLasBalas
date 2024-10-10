/*    */ package com.tacz.guns.resource.modifier.custom;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.tacz.guns.api.modifier.CacheValue;
/*    */ import com.tacz.guns.api.modifier.IAttachmentModifier;
/*    */ import com.tacz.guns.api.modifier.JsonProperty;
/*    */ import com.tacz.guns.resource.CommonGunPackLoader;
/*    */ import com.tacz.guns.resource.pojo.data.gun.GunData;
/*    */ import com.tacz.guns.resource.pojo.data.gun.MoveSpeed;
/*    */ import java.util.List;
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExtraMovementModifier
/*    */   implements IAttachmentModifier<MoveSpeed, MoveSpeed>
/*    */ {
/*    */   public static final String ID = "movement_speed";
/*    */   
/*    */   public String getId() {
/* 28 */     return "movement_speed";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonProperty<MoveSpeed> readJson(String json) {
/* 34 */     Data data = (Data)CommonGunPackLoader.GSON.fromJson(json, Data.class);
/* 35 */     MoveSpeed moveSpeed = data.getMoveSpeed();
/* 36 */     return new ExtraSpeedJsonProperty(moveSpeed);
/*    */   }
/*    */ 
/*    */   
/*    */   public CacheValue<MoveSpeed> initCache(ItemStack gunItem, GunData gunData) {
/* 41 */     return new CacheValue(gunData.getMoveSpeed());
/*    */   }
/*    */ 
/*    */   
/*    */   public void eval(List<MoveSpeed> modifiers, CacheValue<MoveSpeed> cache) {
/* 46 */     cache.setValue(MoveSpeed.of((MoveSpeed)cache.getValue(), modifiers));
/*    */   }
/*    */   
/*    */   public static class ExtraSpeedJsonProperty extends JsonProperty<MoveSpeed> {
/*    */     public ExtraSpeedJsonProperty(MoveSpeed value) {
/* 51 */       super(value);
/*    */     }
/*    */ 
/*    */     
/*    */     public void initComponents() {
/* 56 */       MoveSpeed speed = (MoveSpeed)getValue();
/* 57 */       if (speed == null)
/* 58 */         return;  resolveComponent(speed.getBaseMultiplier(), "movement_speed");
/* 59 */       resolveComponent(speed.getAimMultiplier(), "aim_speed");
/* 60 */       resolveComponent(speed.getReloadMultiplier(), "reload_speed");
/*    */     }
/*    */     
/*    */     private void resolveComponent(float amount, String key) {
/* 64 */       if (amount > 0.0F) {
/* 65 */         this.components.add(Component.m_237115_(String.format("tooltip.tacz.attachment.%s.increase", new Object[] { key })).m_130940_(ChatFormatting.GREEN));
/* 66 */       } else if (amount < 0.0F) {
/* 67 */         this.components.add(Component.m_237115_(String.format("tooltip.tacz.attachment.%s.decrease", new Object[] { key })).m_130940_(ChatFormatting.RED));
/*    */       } 
/*    */     } }
/*    */   
/*    */   public static class Data { @SerializedName("movement_speed")
/*    */     @Nullable
/* 73 */     private MoveSpeed moveSpeed = null;
/*    */ 
/*    */ 
/*    */     
/*    */     @Nullable
/*    */     public MoveSpeed getMoveSpeed() {
/* 79 */       return this.moveSpeed;
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\modifier\custom\ExtraMovementModifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */