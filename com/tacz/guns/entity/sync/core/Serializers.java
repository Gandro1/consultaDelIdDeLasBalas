/*     */ package com.tacz.guns.entity.sync.core;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.nbt.ByteTag;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.nbt.DoubleTag;
/*     */ import net.minecraft.nbt.FloatTag;
/*     */ import net.minecraft.nbt.IntTag;
/*     */ import net.minecraft.nbt.LongTag;
/*     */ import net.minecraft.nbt.ShortTag;
/*     */ import net.minecraft.nbt.StringTag;
/*     */ import net.minecraft.nbt.Tag;
/*     */ import net.minecraft.network.FriendlyByteBuf;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ 
/*     */ public class Serializers
/*     */ {
/*  20 */   public static final IDataSerializer<Boolean> BOOLEAN = new IDataSerializer<Boolean>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, Boolean value) {
/*  23 */         buf.writeBoolean(value.booleanValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Boolean read(FriendlyByteBuf buf) {
/*  28 */         return Boolean.valueOf(buf.readBoolean());
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(Boolean value) {
/*  33 */         return (Tag)ByteTag.m_128273_(value.booleanValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Boolean read(Tag tag) {
/*  38 */         return Boolean.valueOf((((ByteTag)tag).m_7063_() != 0));
/*     */       }
/*     */     };
/*     */   
/*  42 */   public static final IDataSerializer<Byte> BYTE = new IDataSerializer<Byte>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, Byte value) {
/*  45 */         buf.writeByte(value.byteValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Byte read(FriendlyByteBuf buf) {
/*  50 */         return Byte.valueOf(buf.readByte());
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(Byte value) {
/*  55 */         return (Tag)ByteTag.m_128266_(value.byteValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Byte read(Tag tag) {
/*  60 */         return Byte.valueOf(((ByteTag)tag).m_7063_());
/*     */       }
/*     */     };
/*     */   
/*  64 */   public static final IDataSerializer<Short> SHORT = new IDataSerializer<Short>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, Short value) {
/*  67 */         buf.writeShort(value.shortValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Short read(FriendlyByteBuf buf) {
/*  72 */         return Short.valueOf(buf.readShort());
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(Short value) {
/*  77 */         return (Tag)ShortTag.m_129258_(value.shortValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Short read(Tag tag) {
/*  82 */         return Short.valueOf(((ShortTag)tag).m_7053_());
/*     */       }
/*     */     };
/*     */   
/*  86 */   public static final IDataSerializer<Integer> INTEGER = new IDataSerializer<Integer>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, Integer value) {
/*  89 */         buf.m_130130_(value.intValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Integer read(FriendlyByteBuf buf) {
/*  94 */         return Integer.valueOf(buf.m_130242_());
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(Integer value) {
/*  99 */         return (Tag)IntTag.m_128679_(value.intValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Integer read(Tag tag) {
/* 104 */         return Integer.valueOf(((IntTag)tag).m_7047_());
/*     */       }
/*     */     };
/*     */   
/* 108 */   public static final IDataSerializer<Long> LONG = new IDataSerializer<Long>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, Long value) {
/* 111 */         buf.writeLong(value.longValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Long read(FriendlyByteBuf buf) {
/* 116 */         return Long.valueOf(buf.readLong());
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(Long value) {
/* 121 */         return (Tag)LongTag.m_128882_(value.longValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Long read(Tag tag) {
/* 126 */         return Long.valueOf(((LongTag)tag).m_7046_());
/*     */       }
/*     */     };
/*     */   
/* 130 */   public static final IDataSerializer<Float> FLOAT = new IDataSerializer<Float>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, Float value) {
/* 133 */         buf.writeFloat(value.floatValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Float read(FriendlyByteBuf buf) {
/* 138 */         return Float.valueOf(buf.readFloat());
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(Float value) {
/* 143 */         return (Tag)FloatTag.m_128566_(value.floatValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Float read(Tag tag) {
/* 148 */         return Float.valueOf(((FloatTag)tag).m_7057_());
/*     */       }
/*     */     };
/*     */   
/* 152 */   public static final IDataSerializer<Double> DOUBLE = new IDataSerializer<Double>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, Double value) {
/* 155 */         buf.writeDouble(value.doubleValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Double read(FriendlyByteBuf buf) {
/* 160 */         return Double.valueOf(buf.readDouble());
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(Double value) {
/* 165 */         return (Tag)DoubleTag.m_128500_(value.doubleValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Double read(Tag tag) {
/* 170 */         return Double.valueOf(((DoubleTag)tag).m_7061_());
/*     */       }
/*     */     };
/*     */   
/* 174 */   public static final IDataSerializer<Character> CHARACTER = new IDataSerializer<Character>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, Character value) {
/* 177 */         buf.writeChar(value.charValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Character read(FriendlyByteBuf buf) {
/* 182 */         return Character.valueOf(buf.readChar());
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(Character value) {
/* 187 */         return (Tag)IntTag.m_128679_(value.charValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Character read(Tag tag) {
/* 192 */         return Character.valueOf((char)((IntTag)tag).m_7047_());
/*     */       }
/*     */     };
/*     */   
/* 196 */   public static final IDataSerializer<String> STRING = new IDataSerializer<String>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, String value) {
/* 199 */         buf.m_130070_(value);
/*     */       }
/*     */ 
/*     */       
/*     */       public String read(FriendlyByteBuf buf) {
/* 204 */         return buf.m_130277_();
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(String value) {
/* 209 */         return (Tag)StringTag.m_129297_(value);
/*     */       }
/*     */ 
/*     */       
/*     */       public String read(Tag tag) {
/* 214 */         return tag.m_7916_();
/*     */       }
/*     */     };
/*     */   
/* 218 */   public static final IDataSerializer<CompoundTag> TAG_COMPOUND = new IDataSerializer<CompoundTag>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, CompoundTag value) {
/* 221 */         buf.m_130079_(value);
/*     */       }
/*     */ 
/*     */       
/*     */       public CompoundTag read(FriendlyByteBuf buf) {
/* 226 */         return buf.m_130260_();
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(CompoundTag value) {
/* 231 */         return (Tag)value;
/*     */       }
/*     */ 
/*     */       
/*     */       public CompoundTag read(Tag tag) {
/* 236 */         return (CompoundTag)tag;
/*     */       }
/*     */     };
/*     */   
/* 240 */   public static final IDataSerializer<BlockPos> BLOCK_POS = new IDataSerializer<BlockPos>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, BlockPos value) {
/* 243 */         buf.m_130064_(value);
/*     */       }
/*     */ 
/*     */       
/*     */       public BlockPos read(FriendlyByteBuf buf) {
/* 248 */         return buf.m_130135_();
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(BlockPos value) {
/* 253 */         return (Tag)LongTag.m_128882_(value.m_121878_());
/*     */       }
/*     */ 
/*     */       
/*     */       public BlockPos read(Tag tag) {
/* 258 */         return BlockPos.m_122022_(((LongTag)tag).m_7046_());
/*     */       }
/*     */     };
/*     */   
/* 262 */   public static final IDataSerializer<UUID> UUID = new IDataSerializer<UUID>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, UUID value) {
/* 265 */         buf.m_130077_(value);
/*     */       }
/*     */ 
/*     */       
/*     */       public UUID read(FriendlyByteBuf buf) {
/* 270 */         return buf.m_130259_();
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(UUID value) {
/* 275 */         CompoundTag compound = new CompoundTag();
/* 276 */         compound.m_128356_("Most", value.getMostSignificantBits());
/* 277 */         compound.m_128356_("Least", value.getLeastSignificantBits());
/* 278 */         return (Tag)compound;
/*     */       }
/*     */ 
/*     */       
/*     */       public UUID read(Tag tag) {
/* 283 */         CompoundTag compound = (CompoundTag)tag;
/* 284 */         return new UUID(compound.m_128454_("Most"), compound.m_128454_("Least"));
/*     */       }
/*     */     };
/*     */   
/* 288 */   public static final IDataSerializer<ItemStack> ITEM_STACK = new IDataSerializer<ItemStack>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, ItemStack value) {
/* 291 */         buf.m_130055_(value);
/*     */       }
/*     */ 
/*     */       
/*     */       public ItemStack read(FriendlyByteBuf buf) {
/* 296 */         return buf.m_130267_();
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(ItemStack value) {
/* 301 */         return (Tag)value.m_41739_(new CompoundTag());
/*     */       }
/*     */ 
/*     */       
/*     */       public ItemStack read(Tag tag) {
/* 306 */         return ItemStack.m_41712_((CompoundTag)tag);
/*     */       }
/*     */     };
/*     */   
/* 310 */   public static final IDataSerializer<ResourceLocation> RESOURCE_LOCATION = new IDataSerializer<ResourceLocation>()
/*     */     {
/*     */       public void write(FriendlyByteBuf buf, ResourceLocation value) {
/* 313 */         buf.m_130085_(value);
/*     */       }
/*     */ 
/*     */       
/*     */       public ResourceLocation read(FriendlyByteBuf buf) {
/* 318 */         return buf.m_130281_();
/*     */       }
/*     */ 
/*     */       
/*     */       public Tag write(ResourceLocation value) {
/* 323 */         return (Tag)StringTag.m_129297_(value.toString());
/*     */       }
/*     */ 
/*     */       
/*     */       public ResourceLocation read(Tag tag) {
/* 328 */         return ResourceLocation.m_135820_(tag.m_7916_());
/*     */       }
/*     */     };
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\Serializers.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */