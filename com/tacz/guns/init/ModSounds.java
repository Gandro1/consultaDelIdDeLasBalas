/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class ModSounds
/*    */ {
/* 11 */   public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "tacz");
/*    */   
/* 13 */   public static final RegistryObject<SoundEvent> GUN = SOUNDS.register("gun", () -> SoundEvent.m_262824_(new ResourceLocation("tacz", "gun")));
/* 14 */   public static final RegistryObject<SoundEvent> TARGET_HIT = SOUNDS.register("target_block_hit", () -> SoundEvent.m_262824_(new ResourceLocation("tacz", "target_block_hit")));
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModSounds.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */