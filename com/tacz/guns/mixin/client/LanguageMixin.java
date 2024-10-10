/*    */ package com.tacz.guns.mixin.client;
/*    */ 
/*    */ import com.tacz.guns.client.resource.ClientAssetManager;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.language.ClientLanguage;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({ClientLanguage.class})
/*    */ public class LanguageMixin
/*    */ {
/*    */   @Inject(method = {"getOrDefault(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getCustomLanguage(String key, String defaultValue, CallbackInfoReturnable<String> call) {
/* 17 */     String code = Minecraft.m_91087_().m_91102_().m_264236_();
/* 18 */     Map<String, String> languages = ClientAssetManager.INSTANCE.getLanguages(code);
/* 19 */     Map<String, String> alternative = ClientAssetManager.INSTANCE.getLanguages("en_us");
/* 20 */     if (languages != null && languages.containsKey(key)) {
/* 21 */       call.setReturnValue(languages.get(key));
/* 22 */     } else if (alternative != null && alternative.containsKey(key)) {
/* 23 */       call.setReturnValue(alternative.get(key));
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"has(Ljava/lang/String;)Z"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void hasCustomLanguage(String key, CallbackInfoReturnable<Boolean> call) {
/* 29 */     String code = Minecraft.m_91087_().m_91102_().m_264236_();
/* 30 */     Map<String, String> languages = ClientAssetManager.INSTANCE.getLanguages(code);
/* 31 */     Map<String, String> alternative = ClientAssetManager.INSTANCE.getLanguages("en_us");
/* 32 */     if (languages != null && languages.containsKey(key)) {
/* 33 */       call.setReturnValue(Boolean.valueOf(true));
/* 34 */     } else if (alternative != null && alternative.containsKey(key)) {
/* 35 */       call.setReturnValue(Boolean.valueOf(true));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\mixin\client\LanguageMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */