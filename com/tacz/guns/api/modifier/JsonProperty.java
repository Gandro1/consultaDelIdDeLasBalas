/*    */ package com.tacz.guns.api.modifier;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.network.chat.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class JsonProperty<T>
/*    */ {
/* 15 */   protected List<Component> components = Lists.newArrayList();
/*    */ 
/*    */   
/*    */   public JsonProperty(@Nullable T value) {
/* 19 */     this.value = value;
/*    */   } @Nullable
/*    */   private T value;
/*    */   @Nullable
/*    */   public T getValue() {
/* 24 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(@Nullable T value) {
/* 28 */     this.value = value;
/*    */   }
/*    */   
/*    */   public List<Component> getComponents() {
/* 32 */     return this.components;
/*    */   }
/*    */   
/*    */   public abstract void initComponents();
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\modifier\JsonProperty.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */