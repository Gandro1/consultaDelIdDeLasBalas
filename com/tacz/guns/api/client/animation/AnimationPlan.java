/*    */ package com.tacz.guns.api.client.animation;
/*    */ 
/*    */ public class AnimationPlan {
/*    */   public String animationName;
/*    */   public ObjectAnimation.PlayType playType;
/*    */   public float transitionTimeS;
/*    */   
/*    */   public AnimationPlan(String animationName, ObjectAnimation.PlayType playType, float transitionTimeS) {
/*  9 */     this.animationName = animationName;
/* 10 */     this.playType = playType;
/* 11 */     this.transitionTimeS = transitionTimeS;
/*    */   }
/*    */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\AnimationPlan.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */