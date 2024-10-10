/*    */ package com.tacz.guns.compat.cloth.widget;
/*    */ 
/*    */ import com.tacz.guns.client.renderer.crosshair.CrosshairType;
/*    */ import java.util.function.Function;
/*    */ import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.FormattedCharSequence;
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
/*    */ class null
/*    */   extends DropdownBoxEntry.DefaultSelectionCellCreator<CrosshairType>
/*    */ {
/*    */   null(Function<CrosshairType, Component> toTextFunction) {
/* 37 */     super(toTextFunction);
/*    */   }
/*    */   public DropdownBoxEntry.SelectionCellElement<CrosshairType> create(CrosshairType selection) {
/* 40 */     return (DropdownBoxEntry.SelectionCellElement<CrosshairType>)new DropdownBoxEntry.DefaultSelectionCellElement<CrosshairType>(selection, this.toTextFunction)
/*    */       {
/*    */         public void render(GuiGraphics graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
/* 43 */           this.rendering = true;
/* 44 */           this.x = x;
/* 45 */           this.y = y;
/* 46 */           this.width = width;
/* 47 */           this.height = height;
/* 48 */           boolean isHover = (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
/* 49 */           if (isHover) {
/* 50 */             graphics.m_280509_(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
/*    */           }
/* 52 */           FormattedCharSequence text = ((Component)this.toTextFunction.apply((CrosshairType)this.r)).m_7532_();
/* 53 */           int color = isHover ? 16777215 : 8947848;
/* 54 */           graphics.drawString((Minecraft.m_91087_()).f_91062_, text, (x + 6 + 18), (y + 6), color, false);
/*    */           
/* 56 */           ResourceLocation location = CrosshairType.getTextureLocation((CrosshairType)this.r);
/* 57 */           graphics.m_280163_(location, x + 4, y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCellHeight() {
/* 64 */     return 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCellWidth() {
/* 69 */     return 146;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDropBoxMaxHeight() {
/* 74 */     return getCellHeight() * (CrosshairType.values()).length;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\compat\cloth\widget\CrosshairDropdown$2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */