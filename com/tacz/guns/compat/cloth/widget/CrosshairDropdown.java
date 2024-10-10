/*    */ package com.tacz.guns.compat.cloth.widget;
/*    */ import com.tacz.guns.client.renderer.crosshair.CrosshairType;
/*    */ import java.util.function.Function;
/*    */ import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.FormattedCharSequence;
/*    */ 
/*    */ public class CrosshairDropdown {
/*    */   public static DropdownBoxEntry.SelectionTopCellElement<CrosshairType> of(CrosshairType type) {
/* 13 */     return (DropdownBoxEntry.SelectionTopCellElement<CrosshairType>)new DropdownBoxEntry.DefaultSelectionTopCellElement<CrosshairType>(type, name -> {
/*    */           for (CrosshairType crosshairType : CrosshairType.values()) {
/*    */             if (crosshairType.name().equals(name)) {
/*    */               return crosshairType;
/*    */             }
/*    */           } 
/*    */           return null;
/*    */         }id -> Component.m_237113_(id.toString()))
/*    */       {
/*    */         public void render(GuiGraphics graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
/* 23 */           this.textFieldWidget.m_252865_(x + 4);
/* 24 */           this.textFieldWidget.m_253211_(y + 6);
/* 25 */           this.textFieldWidget.m_93674_(width - 4 - 20);
/* 26 */           this.textFieldWidget.m_94186_(getParent().isEditable());
/* 27 */           this.textFieldWidget.m_94202_(getPreferredTextColor());
/* 28 */           this.textFieldWidget.m_88315_(graphics, mouseX, mouseY, delta);
/*    */           
/* 30 */           ResourceLocation location = CrosshairType.getTextureLocation((CrosshairType)this.value);
/* 31 */           graphics.m_280163_(location, x + width - 18, y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   public static DropdownBoxEntry.SelectionCellCreator<CrosshairType> of() {
/* 37 */     return (DropdownBoxEntry.SelectionCellCreator<CrosshairType>)new DropdownBoxEntry.DefaultSelectionCellCreator<CrosshairType>(i -> Component.m_237113_(i.name()))
/*    */       {
/*    */         public DropdownBoxEntry.SelectionCellElement<CrosshairType> create(CrosshairType selection) {
/* 40 */           return (DropdownBoxEntry.SelectionCellElement<CrosshairType>)new DropdownBoxEntry.DefaultSelectionCellElement<CrosshairType>(selection, this.toTextFunction)
/*    */             {
/*    */               public void render(GuiGraphics graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
/* 43 */                 this.rendering = true;
/* 44 */                 this.x = x;
/* 45 */                 this.y = y;
/* 46 */                 this.width = width;
/* 47 */                 this.height = height;
/* 48 */                 boolean isHover = (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
/* 49 */                 if (isHover) {
/* 50 */                   graphics.m_280509_(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
/*    */                 }
/* 52 */                 FormattedCharSequence text = ((Component)this.toTextFunction.apply((CrosshairType)this.r)).m_7532_();
/* 53 */                 int color = isHover ? 16777215 : 8947848;
/* 54 */                 graphics.drawString((Minecraft.m_91087_()).f_91062_, text, (x + 6 + 18), (y + 6), color, false);
/*    */                 
/* 56 */                 ResourceLocation location = CrosshairType.getTextureLocation((CrosshairType)this.r);
/* 57 */                 graphics.m_280163_(location, x + 4, y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
/*    */               }
/*    */             };
/*    */         }
/*    */ 
/*    */         
/*    */         public int getCellHeight() {
/* 64 */           return 20;
/*    */         }
/*    */ 
/*    */         
/*    */         public int getCellWidth() {
/* 69 */           return 146;
/*    */         }
/*    */ 
/*    */         
/*    */         public int getDropBoxMaxHeight() {
/* 74 */           return getCellHeight() * (CrosshairType.values()).length;
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\widget\CrosshairDropdown.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */