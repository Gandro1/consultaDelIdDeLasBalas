/*    */ package com.tacz.guns.init;
/*    */ 
/*    */ import net.minecraft.world.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.world.entity.ai.attributes.RangedAttribute;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ public class ModAttributes
/*    */ {
/* 11 */   public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, "tacz");
/*    */   
/* 13 */   public static final RegistryObject<Attribute> BULLET_RESISTANCE = ATTRIBUTES.register("tacz.bullet_resistance", () -> (new RangedAttribute("attribute.name.tacz.bullet_resistance", 0.0D, 0.0D, 1.0D)).m_22084_(true));
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\init\ModAttributes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */