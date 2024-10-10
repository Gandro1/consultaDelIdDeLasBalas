/*    */ package com.tacz.guns.item;
/*    */ 
/*    */ import com.tacz.guns.client.renderer.item.GunSmithTableItemRenderer;
/*    */ import com.tacz.guns.init.ModBlocks;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
/*    */ import net.minecraft.world.item.BlockItem;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraftforge.client.extensions.common.IClientItemExtensions;
/*    */ 
/*    */ public class GunSmithTableItem extends BlockItem {
/*    */   public GunSmithTableItem() {
/* 15 */     super((Block)ModBlocks.GUN_SMITH_TABLE.get(), (new Item.Properties()).m_41487_(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
/* 20 */     consumer.accept(new IClientItemExtensions()
/*    */         {
/*    */           public BlockEntityWithoutLevelRenderer getCustomRenderer() {
/* 23 */             Minecraft minecraft = Minecraft.m_91087_();
/* 24 */             return (BlockEntityWithoutLevelRenderer)new GunSmithTableItemRenderer(minecraft.m_167982_(), minecraft.m_167973_());
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\item\GunSmithTableItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */