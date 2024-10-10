package com.tacz.guns.api.client.animation.statemachine;

public interface AnimationState<T extends AnimationStateContext> {
  void update(T paramT);
  
  void entryAction(T paramT);
  
  void exitAction(T paramT);
  
  AnimationState<T> transition(T paramT, String paramString);
}


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\statemachine\AnimationState.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */