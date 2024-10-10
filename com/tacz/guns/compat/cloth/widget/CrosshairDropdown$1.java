/*    */ package com.tacz.guns.compat.cloth.widget;
/*    */ 
/*    */ import com.tacz.guns.client.renderer.crosshair.CrosshairType;
/*    */ import java.util.function.Function;
/*    */ import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends DropdownBoxEntry.DefaultSelectionTopCellElement<CrosshairType>
/*    */ {
/*    */   null(CrosshairType value, Function<String, CrosshairType> toObjectFunction, Function<CrosshairType, Component> toTextFunction) {
/* 20 */     super(value, toObjectFunction, toTextFunction);
/*    */   }
/*    */   public void render(GuiGraphics graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
/* 23 */     this.textFieldWidget.m_252865_(x + 4);
/* 24 */     this.textFieldWidget.m_253211_(y + 6);
/* 25 */     this.textFieldWidget.m_93674_(width - 4 - 20);
/* 26 */     this.textFieldWidget.m_94186_(getParent().isEditable());
/* 27 */     this.textFieldWidget.m_94202_(getPreferredTextColor());
/* 28 */     this.textFieldWidget.m_88315_(graphics, mouseX, mouseY, delta);
/*    */     
/* 30 */     ResourceLocation location = CrosshairType.getTextureLocation((CrosshairType)this.value);
/* 31 */     graphics.m_280163_(location, x + width - 18, y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\widget\CrosshairDropdown$1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */