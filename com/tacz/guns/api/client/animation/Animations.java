/*     */ package com.tacz.guns.api.client.animation;
/*     */ import com.tacz.guns.api.client.animation.gltf.AccessorModel;
/*     */ import com.tacz.guns.api.client.animation.gltf.AnimationModel;
/*     */ import com.tacz.guns.api.client.animation.gltf.accessor.AccessorData;
/*     */ import com.tacz.guns.api.client.animation.gltf.accessor.AccessorFloatData;
/*     */ import com.tacz.guns.api.client.animation.interpolator.Interpolator;
/*     */ import com.tacz.guns.api.client.animation.interpolator.InterpolatorUtil;
/*     */ import com.tacz.guns.client.resource.pojo.animation.bedrock.AnimationBone;
/*     */ import com.tacz.guns.client.resource.pojo.animation.bedrock.AnimationKeyframes;
/*     */ import com.tacz.guns.client.resource.pojo.animation.bedrock.BedrockAnimation;
/*     */ import com.tacz.guns.client.resource.pojo.animation.bedrock.BedrockAnimationFile;
/*     */ import com.tacz.guns.client.resource.pojo.animation.bedrock.SoundEffectKeyframes;
/*     */ import com.tacz.guns.util.math.MathUtil;
/*     */ import it.unimi.dsi.fastutil.doubles.Double2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.doubles.Double2ObjectRBTreeMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import org.joml.Vector3f;
/*     */ import org.joml.Vector3fc;
/*     */ 
/*     */ public class Animations {
/*     */   public static AnimationController createControllerFromGltf(@Nonnull AnimationStructure structure, @Nonnull AnimationListenerSupplier supplier) {
/*  26 */     List<ObjectAnimation> prototypes = new ArrayList<>();
/*     */     
/*  28 */     List<AnimationModel> animationModels = structure.getAnimationModels();
/*  29 */     for (AnimationModel animationModel : animationModels) {
/*  30 */       ObjectAnimation animation = new ObjectAnimation(animationModel.getName());
/*     */ 
/*     */       
/*  33 */       List<AnimationModel.Channel> channelModels = animationModel.getChannels();
/*  34 */       for (AnimationModel.Channel channelModel : channelModels) {
/*  35 */         AccessorFloatData inputFloatData, outputFloatData; ObjectAnimationChannel channel = new ObjectAnimationChannel(ObjectAnimationChannel.ChannelType.valueOf(channelModel.path().toUpperCase(Locale.ENGLISH)));
/*  36 */         AnimationModel.Sampler sampler = channelModel.sampler();
/*     */ 
/*     */         
/*  39 */         AnimationModel.Interpolation interpolation = sampler.interpolation();
/*  40 */         NodeModel nodeModel = channelModel.nodeModel();
/*     */         
/*  42 */         if (channel.type.equals(ObjectAnimationChannel.ChannelType.ROTATION) && interpolation.equals(AnimationModel.Interpolation.LINEAR)) {
/*  43 */           channel.interpolator = InterpolatorUtil.fromInterpolation(InterpolatorUtil.InterpolatorType.SLERP);
/*     */         } else {
/*  45 */           channel.interpolator = InterpolatorUtil.fromInterpolation(InterpolatorUtil.InterpolatorType.valueOf(interpolation.name()));
/*     */         } 
/*  47 */         channel.node = nodeModel.getName();
/*     */ 
/*     */         
/*  50 */         AnimationListener animationListener = supplier.supplyListeners(channel.node, channel.type);
/*  51 */         if (animationListener == null) {
/*     */           continue;
/*     */         }
/*  54 */         float[] inverseValue = animationListener.initialValue();
/*  55 */         if (channel.type == ObjectAnimationChannel.ChannelType.ROTATION) {
/*  56 */           if (inverseValue.length == 3) {
/*  57 */             inverseValue = MathUtil.toQuaternion(inverseValue[0], inverseValue[1], inverseValue[2]);
/*     */           }
/*  59 */           inverseValue = MathUtil.inverseQuaternion(inverseValue);
/*  60 */         } else if (channel.type == ObjectAnimationChannel.ChannelType.TRANSLATION) {
/*  61 */           inverseValue[0] = -inverseValue[0];
/*  62 */           inverseValue[1] = -inverseValue[1];
/*  63 */           inverseValue[2] = -inverseValue[2];
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  68 */         AccessorModel input = sampler.input();
/*  69 */         AccessorData inputData = input.getAccessorData();
/*  70 */         if (inputData instanceof AccessorFloatData) { inputFloatData = (AccessorFloatData)inputData; }
/*  71 */         else { throw new IllegalArgumentException("Input data is not an AccessorFloatData, but " + inputData.getClass()); }
/*     */ 
/*     */         
/*  74 */         AccessorModel output = sampler.output();
/*  75 */         AccessorData outputData = output.getAccessorData();
/*  76 */         if (outputData instanceof AccessorFloatData) { outputFloatData = (AccessorFloatData)outputData; }
/*  77 */         else { throw new IllegalArgumentException("Output data is not an AccessorFloatData, but " + inputData.getClass()); }
/*     */         
/*  79 */         int numKeyElements = inputFloatData.getNumElements();
/*  80 */         int numValuesElements = outputFloatData.getTotalNumComponents() / numKeyElements;
/*  81 */         float[] keyframeTimeS = new float[numKeyElements];
/*  82 */         float[][] values = new float[numKeyElements][numValuesElements];
/*  83 */         for (int i = 0; i < numKeyElements; i++) {
/*  84 */           keyframeTimeS[i] = inputFloatData.get(i);
/*  85 */           for (int j = 0; j < numValuesElements; j++) {
/*  86 */             values[i][j] = outputFloatData.get(i * numValuesElements + j);
/*     */           }
/*  88 */           if (channel.type == ObjectAnimationChannel.ChannelType.ROTATION) {
/*  89 */             values[i] = MathUtil.toEulerAngles(values[i]);
/*  90 */             values[i] = MathUtil.toQuaternion(-values[i][0], -values[i][1], values[i][2]);
/*  91 */             values[i] = MathUtil.mulQuaternion(inverseValue, values[i]);
/*  92 */           } else if (channel.type == ObjectAnimationChannel.ChannelType.TRANSLATION) {
/*  93 */             values[i][0] = -values[i][0] + inverseValue[0];
/*  94 */             values[i][1] = -(-values[i][1] + inverseValue[1]);
/*  95 */             values[i][2] = values[i][2] + inverseValue[2];
/*     */           } 
/*     */         } 
/*  98 */         channel.content.keyframeTimeS = keyframeTimeS;
/*  99 */         channel.content.values = values;
/*     */ 
/*     */         
/* 102 */         channel.interpolator.compile(channel.content);
/*     */ 
/*     */         
/* 105 */         animation.addChannel(channel);
/*     */       } 
/*     */ 
/*     */       
/* 109 */       prototypes.add(animation);
/*     */     } 
/* 111 */     return new AnimationController(prototypes, supplier);
/*     */   }
/*     */   
/*     */   public static AnimationController createControllerFromBedrock(BedrockAnimationFile animationFile, AnimationListenerSupplier supplier) {
/* 115 */     return new AnimationController(createAnimationFromBedrock(animationFile), supplier);
/*     */   }
/*     */   @Nonnull
/*     */   public static List<ObjectAnimation> createAnimationFromBedrock(BedrockAnimationFile animationFile) {
/* 119 */     List<ObjectAnimation> result = new ArrayList<>();
/* 120 */     for (Map.Entry<String, BedrockAnimation> animationEntry : (Iterable<Map.Entry<String, BedrockAnimation>>)animationFile.getAnimations().entrySet()) {
/* 121 */       ObjectAnimation animation = new ObjectAnimation(animationEntry.getKey());
/* 122 */       BedrockAnimation bedrockAnimation = animationEntry.getValue();
/* 123 */       if (bedrockAnimation.getBones() != null) {
/* 124 */         for (Map.Entry<String, AnimationBone> boneEntry : (Iterable<Map.Entry<String, AnimationBone>>)bedrockAnimation.getBones().entrySet()) {
/* 125 */           AnimationBone bone = boneEntry.getValue();
/* 126 */           AnimationKeyframes translationKeyframes = bone.getPosition();
/* 127 */           AnimationKeyframes rotationKeyframes = bone.getRotation();
/* 128 */           AnimationKeyframes scaleKeyframes = bone.getScale();
/* 129 */           if (translationKeyframes != null) {
/* 130 */             ObjectAnimationChannel translationChannel = new ObjectAnimationChannel(ObjectAnimationChannel.ChannelType.TRANSLATION);
/* 131 */             translationChannel.node = boneEntry.getKey();
/* 132 */             translationChannel.interpolator = (Interpolator)new CustomInterpolator();
/*     */             
/* 134 */             writeBedrockTranslation(translationChannel, bone.getPosition());
/* 135 */             translationChannel.interpolator.compile(translationChannel.content);
/* 136 */             animation.addChannel(translationChannel);
/*     */           } 
/* 138 */           if (rotationKeyframes != null) {
/* 139 */             ObjectAnimationChannel rotationChannel = new ObjectAnimationChannel(ObjectAnimationChannel.ChannelType.ROTATION);
/* 140 */             rotationChannel.node = boneEntry.getKey();
/* 141 */             rotationChannel.interpolator = (Interpolator)new CustomInterpolator();
/*     */             
/* 143 */             writeBedrockRotation(rotationChannel, bone.getRotation());
/* 144 */             rotationChannel.interpolator.compile(rotationChannel.content);
/* 145 */             animation.addChannel(rotationChannel);
/*     */           } 
/* 147 */           if (scaleKeyframes != null) {
/* 148 */             ObjectAnimationChannel scaleChannel = new ObjectAnimationChannel(ObjectAnimationChannel.ChannelType.SCALE);
/* 149 */             scaleChannel.node = boneEntry.getKey();
/* 150 */             scaleChannel.interpolator = (Interpolator)new CustomInterpolator();
/*     */             
/* 152 */             writeBedrockScale(scaleChannel, bone.getScale());
/* 153 */             scaleChannel.interpolator.compile(scaleChannel.content);
/* 154 */             animation.addChannel(scaleChannel);
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 159 */       SoundEffectKeyframes soundEffectKeyframes = bedrockAnimation.getSoundEffects();
/* 160 */       if (soundEffectKeyframes != null) {
/* 161 */         ObjectAnimationSoundChannel soundChannel = new ObjectAnimationSoundChannel();
/* 162 */         soundChannel.content = new AnimationSoundChannelContent();
/* 163 */         int keyframeNum = soundEffectKeyframes.getKeyframes().size();
/* 164 */         soundChannel.content.keyframeTimeS = new double[keyframeNum];
/* 165 */         soundChannel.content.keyframeSoundName = new ResourceLocation[keyframeNum];
/* 166 */         int i = 0;
/* 167 */         for (ObjectBidirectionalIterator<Map.Entry<Double, ResourceLocation>> objectBidirectionalIterator = soundEffectKeyframes.getKeyframes().double2ObjectEntrySet().iterator(); objectBidirectionalIterator.hasNext(); ) { Map.Entry<Double, ResourceLocation> entry = objectBidirectionalIterator.next();
/* 168 */           soundChannel.content.keyframeTimeS[i] = ((Double)entry.getKey()).doubleValue();
/* 169 */           soundChannel.content.keyframeSoundName[i] = entry.getValue();
/* 170 */           i++; }
/*     */         
/* 172 */         animation.setSoundChannel(soundChannel);
/*     */       } 
/* 174 */       result.add(animation);
/*     */     } 
/* 176 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeBedrockTranslation(ObjectAnimationChannel animationChannel, AnimationKeyframes keyframes) {
/* 182 */     Double2ObjectRBTreeMap<AnimationKeyframes.Keyframe> keyframesMap = keyframes.getKeyframes();
/* 183 */     animationChannel.content.keyframeTimeS = new float[keyframesMap.size()];
/* 184 */     animationChannel.content.values = new float[keyframesMap.size()][];
/* 185 */     animationChannel.content.lerpModes = new AnimationChannelContent.LerpMode[keyframesMap.size()];
/* 186 */     int index = 0;
/* 187 */     for (ObjectBidirectionalIterator<Double2ObjectMap.Entry<AnimationKeyframes.Keyframe>> objectBidirectionalIterator = keyframesMap.double2ObjectEntrySet().iterator(); objectBidirectionalIterator.hasNext(); ) { Double2ObjectMap.Entry<AnimationKeyframes.Keyframe> entry = objectBidirectionalIterator.next();
/*     */       
/* 189 */       animationChannel.content.keyframeTimeS[index] = (float)entry.getDoubleKey();
/*     */       
/* 191 */       AnimationKeyframes.Keyframe keyframe = (AnimationKeyframes.Keyframe)entry.getValue();
/* 192 */       if (keyframe.pre() != null || keyframe.post() != null) {
/* 193 */         if (keyframe.pre() != null && keyframe.post() != null) {
/* 194 */           animationChannel.content.values[index] = new float[6];
/* 195 */           Vector3f pre = new Vector3f((Vector3fc)keyframe.pre());
/* 196 */           Vector3f post = new Vector3f((Vector3fc)keyframe.post());
/* 197 */           pre.mul(0.0625F, 0.0625F, 0.0625F);
/* 198 */           post.mul(0.0625F, 0.0625F, 0.0625F);
/* 199 */           readVector3fToArray(animationChannel.content.values[index], pre, 0);
/* 200 */           readVector3fToArray(animationChannel.content.values[index], post, 3);
/* 201 */         } else if (keyframe.pre() != null) {
/* 202 */           animationChannel.content.values[index] = new float[3];
/* 203 */           Vector3f pre = new Vector3f((Vector3fc)keyframe.pre());
/* 204 */           pre.mul(0.0625F, 0.0625F, 0.0625F);
/* 205 */           readVector3fToArray(animationChannel.content.values[index], pre, 0);
/*     */         } else {
/* 207 */           animationChannel.content.values[index] = new float[3];
/* 208 */           Vector3f post = new Vector3f((Vector3fc)keyframe.post());
/* 209 */           post.mul(0.0625F, 0.0625F, 0.0625F);
/* 210 */           readVector3fToArray(animationChannel.content.values[index], post, 0);
/*     */         } 
/* 212 */       } else if (keyframe.data() != null) {
/* 213 */         animationChannel.content.values[index] = new float[3];
/* 214 */         Vector3f data = new Vector3f((Vector3fc)keyframe.data());
/* 215 */         data.mul(0.0625F, 0.0625F, 0.0625F);
/* 216 */         readVector3fToArray(animationChannel.content.values[index], data, 0);
/*     */       } 
/*     */       
/* 219 */       String lerpModeName = keyframe.lerpMode();
/* 220 */       if (lerpModeName != null) {
/*     */         try {
/* 222 */           animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.valueOf(lerpModeName.toUpperCase(Locale.ENGLISH));
/* 223 */         } catch (IllegalArgumentException e) {
/* 224 */           animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.LINEAR;
/*     */         } 
/*     */       } else {
/* 227 */         animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.LINEAR;
/*     */       } 
/* 229 */       index++; }
/*     */   
/*     */   }
/*     */   
/*     */   private static void writeBedrockRotation(ObjectAnimationChannel animationChannel, AnimationKeyframes keyframes) {
/* 234 */     Double2ObjectRBTreeMap<AnimationKeyframes.Keyframe> keyframesMap = keyframes.getKeyframes();
/* 235 */     animationChannel.content.keyframeTimeS = new float[keyframesMap.size()];
/* 236 */     animationChannel.content.values = new float[keyframesMap.size()][];
/* 237 */     animationChannel.content.lerpModes = new AnimationChannelContent.LerpMode[keyframesMap.size()];
/* 238 */     int index = 0;
/* 239 */     for (ObjectBidirectionalIterator<Double2ObjectMap.Entry<AnimationKeyframes.Keyframe>> objectBidirectionalIterator = keyframesMap.double2ObjectEntrySet().iterator(); objectBidirectionalIterator.hasNext(); ) { Double2ObjectMap.Entry<AnimationKeyframes.Keyframe> entry = objectBidirectionalIterator.next();
/*     */       
/* 241 */       animationChannel.content.keyframeTimeS[index] = (float)entry.getDoubleKey();
/*     */       
/* 243 */       AnimationKeyframes.Keyframe keyframe = (AnimationKeyframes.Keyframe)entry.getValue();
/* 244 */       if (keyframe.pre() != null || keyframe.post() != null) {
/* 245 */         if (keyframe.pre() != null && keyframe.post() != null) {
/* 246 */           animationChannel.content.values[index] = new float[6];
/* 247 */           Vector3f pre = new Vector3f((Vector3fc)keyframe.pre());
/* 248 */           Vector3f post = new Vector3f((Vector3fc)keyframe.post());
/* 249 */           toAngle(pre);
/* 250 */           toAngle(post);
/* 251 */           animationChannel.content.values[index][0] = pre.x();
/* 252 */           animationChannel.content.values[index][1] = pre.y();
/* 253 */           animationChannel.content.values[index][2] = pre.z();
/* 254 */           animationChannel.content.values[index][3] = post.x();
/* 255 */           animationChannel.content.values[index][4] = post.y();
/* 256 */           animationChannel.content.values[index][5] = post.z();
/* 257 */         } else if (keyframe.pre() != null) {
/* 258 */           animationChannel.content.values[index] = new float[3];
/* 259 */           Vector3f pre = new Vector3f((Vector3fc)keyframe.pre());
/* 260 */           toAngle(pre);
/* 261 */           animationChannel.content.values[index][0] = pre.x();
/* 262 */           animationChannel.content.values[index][1] = pre.y();
/* 263 */           animationChannel.content.values[index][2] = pre.z();
/*     */         } else {
/* 265 */           animationChannel.content.values[index] = new float[3];
/* 266 */           Vector3f post = new Vector3f((Vector3fc)keyframe.post());
/* 267 */           toAngle(post);
/* 268 */           animationChannel.content.values[index][0] = post.x();
/* 269 */           animationChannel.content.values[index][1] = post.y();
/* 270 */           animationChannel.content.values[index][2] = post.z();
/*     */         } 
/* 272 */       } else if (keyframe.data() != null) {
/* 273 */         animationChannel.content.values[index] = new float[3];
/* 274 */         Vector3f data = new Vector3f((Vector3fc)keyframe.data());
/* 275 */         toAngle(data);
/* 276 */         animationChannel.content.values[index][0] = data.x();
/* 277 */         animationChannel.content.values[index][1] = data.y();
/* 278 */         animationChannel.content.values[index][2] = data.z();
/*     */       } 
/* 280 */       String lerpModeName = keyframe.lerpMode();
/* 281 */       if (lerpModeName != null) {
/* 282 */         if (lerpModeName.equals(AnimationChannelContent.LerpMode.CATMULLROM.name().toLowerCase())) {
/* 283 */           animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.CATMULLROM;
/*     */         } else {
/* 285 */           animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.LINEAR;
/*     */         } 
/*     */       } else {
/* 288 */         animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.LINEAR;
/*     */       } 
/* 290 */       index++; }
/*     */   
/*     */   }
/*     */   
/*     */   private static void writeBedrockScale(ObjectAnimationChannel animationChannel, AnimationKeyframes keyframes) {
/* 295 */     Double2ObjectRBTreeMap<AnimationKeyframes.Keyframe> keyframesMap = keyframes.getKeyframes();
/* 296 */     animationChannel.content.keyframeTimeS = new float[keyframesMap.size()];
/* 297 */     animationChannel.content.values = new float[keyframesMap.size()][];
/* 298 */     animationChannel.content.lerpModes = new AnimationChannelContent.LerpMode[keyframesMap.size()];
/* 299 */     int index = 0;
/* 300 */     for (ObjectBidirectionalIterator<Double2ObjectMap.Entry<AnimationKeyframes.Keyframe>> objectBidirectionalIterator = keyframesMap.double2ObjectEntrySet().iterator(); objectBidirectionalIterator.hasNext(); ) { Double2ObjectMap.Entry<AnimationKeyframes.Keyframe> entry = objectBidirectionalIterator.next();
/*     */       
/* 302 */       animationChannel.content.keyframeTimeS[index] = (float)entry.getDoubleKey();
/*     */       
/* 304 */       AnimationKeyframes.Keyframe keyframe = (AnimationKeyframes.Keyframe)entry.getValue();
/* 305 */       if (keyframe.pre() != null || keyframe.post() != null) {
/* 306 */         if (keyframe.pre() != null && keyframe.post() != null) {
/* 307 */           animationChannel.content.values[index] = new float[6];
/* 308 */           Vector3f pre = keyframe.pre();
/* 309 */           Vector3f post = keyframe.post();
/* 310 */           readVector3fToArray(animationChannel.content.values[index], pre, 0);
/* 311 */           readVector3fToArray(animationChannel.content.values[index], post, 3);
/* 312 */         } else if (keyframe.pre() != null) {
/* 313 */           animationChannel.content.values[index] = new float[3];
/* 314 */           Vector3f pre = keyframe.pre();
/* 315 */           readVector3fToArray(animationChannel.content.values[index], pre, 0);
/*     */         } else {
/* 317 */           animationChannel.content.values[index] = new float[3];
/* 318 */           Vector3f post = keyframe.post();
/* 319 */           readVector3fToArray(animationChannel.content.values[index], post, 0);
/*     */         } 
/* 321 */       } else if (keyframe.data() != null) {
/* 322 */         animationChannel.content.values[index] = new float[3];
/* 323 */         Vector3f data = keyframe.data();
/* 324 */         readVector3fToArray(animationChannel.content.values[index], data, 0);
/*     */       } 
/*     */       
/* 327 */       String lerpModeName = keyframe.lerpMode();
/* 328 */       if (lerpModeName != null) {
/*     */         try {
/* 330 */           animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.valueOf(lerpModeName.toUpperCase(Locale.ENGLISH));
/* 331 */         } catch (IllegalArgumentException e) {
/* 332 */           animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.LINEAR;
/*     */         } 
/*     */       } else {
/* 335 */         animationChannel.content.lerpModes[index] = AnimationChannelContent.LerpMode.LINEAR;
/*     */       } 
/* 337 */       index++; }
/*     */   
/*     */   }
/*     */   
/*     */   private static void toAngle(Vector3f vector3f) {
/* 342 */     vector3f.set((float)Math.toRadians(vector3f.x()), (float)Math.toRadians(vector3f.y()), (float)Math.toRadians(vector3f.z()));
/*     */   }
/*     */   
/*     */   private static void readVector3fToArray(float[] array, Vector3f vector3f, int offset) {
/* 346 */     array[offset] = vector3f.x();
/* 347 */     array[offset + 1] = vector3f.y();
/* 348 */     array[offset + 2] = vector3f.z();
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\Animations.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */