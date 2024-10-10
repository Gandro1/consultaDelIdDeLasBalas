/*    */ package com.tacz.guns.entity.sync.core;
/*    */ 
/*    */ public final class SyncedClassKey<E extends Entity> extends Record {
/*    */   private final Class<E> entityClass;
/*    */   private final ResourceLocation id;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/tacz/guns/entity/sync/core/SyncedClassKey;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/tacz/guns/entity/sync/core/SyncedClassKey;
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	7	0	this	Lcom/tacz/guns/entity/sync/core/SyncedClassKey<TE;>;
/*    */   }
/*    */   
/* 11 */   public SyncedClassKey(Class<E> entityClass, ResourceLocation id) { this.entityClass = entityClass; this.id = id; } public Class<E> entityClass() { return this.entityClass; } public ResourceLocation id() { return this.id; }
/* 12 */    public static final SyncedClassKey<LivingEntity> LIVING_ENTITY = new SyncedClassKey((Class)LivingEntity.class, new ResourceLocation("living_entity"));
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 16 */     if (this == o) {
/* 17 */       return true;
/*    */     }
/* 19 */     if (o == null || getClass() != o.getClass()) {
/* 20 */       return false;
/*    */     }
/* 22 */     SyncedClassKey<?> that = (SyncedClassKey)o;
/* 23 */     return this.entityClass.getName().equals(that.entityClass.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 28 */     return this.entityClass.getName().hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\entity\sync\core\SyncedClassKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */