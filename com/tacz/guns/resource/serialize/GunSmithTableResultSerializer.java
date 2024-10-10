/*    */ package com.tacz.guns.resource.serialize;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.tacz.guns.api.TimelessAPI;
/*    */ import com.tacz.guns.api.item.builder.AttachmentItemBuilder;
/*    */ import com.tacz.guns.api.item.builder.GunItemBuilder;
/*    */ import com.tacz.guns.crafting.GunSmithTableResult;
/*    */ import com.tacz.guns.resource.index.CommonAttachmentIndex;
/*    */ import com.tacz.guns.resource.index.CommonGunIndex;
/*    */ import com.tacz.guns.resource.pojo.data.recipe.GunResult;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.EnumMap;
/*    */ import java.util.Locale;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.nbt.Tag;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.GsonHelper;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraftforge.common.crafting.CraftingHelper;
/*    */ 
/*    */ public class GunSmithTableResultSerializer implements JsonDeserializer<GunSmithTableResult> {
/*    */   public GunSmithTableResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 25 */     if (json.isJsonObject()) {
/* 26 */       GunSmithTableResult result; JsonObject jsonObject = json.getAsJsonObject();
/* 27 */       ResourceLocation id = new ResourceLocation(GsonHelper.m_13906_(jsonObject, "id"));
/* 28 */       String typeName = GsonHelper.m_13906_(jsonObject, "type");
/* 29 */       int count = 1;
/* 30 */       if (jsonObject.has("count")) {
/* 31 */         count = Math.max(GsonHelper.m_13927_(jsonObject, "count"), 1);
/*    */       }
/*    */ 
/*    */       
/* 35 */       switch (typeName) { case "gun":
/* 36 */           result = getGunStack(id, count, jsonObject); break;
/* 37 */         case "ammo": result = getAmmoStack(id, count); break;
/* 38 */         case "attachment": result = getAttachmentStack(id, count); break;
/*    */         default:
/* 40 */           return new GunSmithTableResult(ItemStack.f_41583_, ""); }
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 45 */       if (jsonObject.has("nbt")) {
/* 46 */         CompoundTag extraTag = CraftingHelper.getNBT(jsonObject.get("nbt"));
/* 47 */         CompoundTag itemTag = result.getResult().m_41784_();
/* 48 */         for (String key : extraTag.m_128431_()) {
/* 49 */           Tag tag = extraTag.m_128423_(key);
/* 50 */           if (tag != null) {
/* 51 */             itemTag.m_128365_(key, tag);
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 56 */       return result;
/*    */     } 
/* 58 */     return new GunSmithTableResult(ItemStack.f_41583_, "");
/*    */   }
/*    */   
/*    */   private GunSmithTableResult getGunStack(ResourceLocation id, int count, JsonObject extraData) {
/* 62 */     GunResult gunResult = (GunResult)CommonGunPackLoader.GSON.fromJson((JsonElement)extraData, GunResult.class);
/* 63 */     int ammoCount = Math.max(0, gunResult.getAmmoCount());
/* 64 */     EnumMap<AttachmentType, ResourceLocation> attachments = gunResult.getAttachments();
/*    */     
/* 66 */     return TimelessAPI.getCommonGunIndex(id).map(gunIndex -> {
/*    */           ItemStack itemStack = GunItemBuilder.create().setCount(count).setId(id).setAmmoCount(ammoCount).setAmmoInBarrel(false).putAllAttachment(attachments).setFireMode(gunIndex.getGunData().getFireModeSet().get(0)).build();
/*    */ 
/*    */ 
/*    */           
/*    */           String group = gunIndex.getType();
/*    */ 
/*    */ 
/*    */           
/*    */           return new GunSmithTableResult(itemStack, group);
/* 76 */         }).orElse(new GunSmithTableResult(ItemStack.f_41583_, ""));
/*    */   }
/*    */   
/*    */   private GunSmithTableResult getAmmoStack(ResourceLocation id, int count) {
/* 80 */     return new GunSmithTableResult(AmmoItemBuilder.create().setCount(count).setId(id).build(), "ammo");
/*    */   }
/*    */   
/*    */   private GunSmithTableResult getAttachmentStack(ResourceLocation id, int count) {
/* 84 */     return TimelessAPI.getCommonAttachmentIndex(id).map(attachmentIndex -> {
/*    */           ItemStack itemStack = AttachmentItemBuilder.create().setCount(count).setId(id).build();
/*    */           String group = attachmentIndex.getType().name().toLowerCase(Locale.US);
/*    */           return new GunSmithTableResult(itemStack, group);
/* 88 */         }).orElse(new GunSmithTableResult(ItemStack.f_41583_, ""));
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\resource\serialize\GunSmithTableResultSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */