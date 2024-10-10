/*     */ package com.tacz.guns.api.client.animation.gltf;
/*     */ import com.tacz.guns.api.client.animation.gltf.accessor.AccessorData;
/*     */ import com.tacz.guns.api.client.animation.gltf.accessor.AccessorDatas;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.Accessor;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.AccessorSparse;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.AccessorSparseIndices;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.AccessorSparseValues;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.Animation;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.AnimationChannel;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.Buffer;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.BufferView;
/*     */ import com.tacz.guns.client.resource.pojo.animation.gltf.Node;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class AnimationStructure {
/*  18 */   private final List<AccessorModel> accessorModels = new ArrayList<>();
/*  19 */   private final List<AnimationModel> animationModels = new ArrayList<>();
/*  20 */   private final List<BufferModel> bufferModels = new ArrayList<>();
/*  21 */   private final List<BufferViewModel> bufferViewModels = new ArrayList<>();
/*  22 */   private final List<NodeModel> nodeModels = new ArrayList<>();
/*     */   private final RawAnimationStructure gltf;
/*     */   
/*     */   public AnimationStructure(RawAnimationStructure asset) {
/*  26 */     this.gltf = asset;
/*  27 */     createAccessorModels();
/*  28 */     createAnimationModels();
/*  29 */     createBufferModels();
/*  30 */     createBufferViewModels();
/*  31 */     createNodeModels();
/*     */     
/*  33 */     initBufferModels();
/*  34 */     initBufferViewModels();
/*  35 */     initAccessorModels();
/*  36 */     initAnimationModels();
/*  37 */     initNodeModels();
/*     */   }
/*     */   
/*     */   private static float[] clone(float[] array) {
/*  41 */     if (array == null) {
/*  42 */       return null;
/*     */     }
/*  44 */     return (float[])array.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   private static BufferViewModel createBufferViewModel(String uriString, ByteBuffer bufferData) {
/*  49 */     BufferModel bufferModel = new BufferModel();
/*  50 */     bufferModel.setUri(uriString);
/*  51 */     bufferModel.setBufferData(bufferData);
/*     */     
/*  53 */     BufferViewModel bufferViewModel = new BufferViewModel(null);
/*     */     
/*  55 */     bufferViewModel.setByteOffset(0);
/*  56 */     bufferViewModel.setByteLength(bufferData.capacity());
/*  57 */     bufferViewModel.setBufferModel(bufferModel);
/*     */     
/*  59 */     return bufferViewModel;
/*     */   }
/*     */ 
/*     */   
/*     */   private static BufferViewModel createBufferViewModel(BufferView bufferView) {
/*  64 */     int byteOffset = (bufferView.getByteOffset() == null) ? 0 : bufferView.getByteOffset().intValue();
/*  65 */     int byteLength = bufferView.getByteLength().intValue();
/*  66 */     Integer byteStride = bufferView.getByteStride();
/*  67 */     Integer target = bufferView.getTarget();
/*  68 */     BufferViewModel bufferViewModel = new BufferViewModel(target);
/*     */     
/*  70 */     bufferViewModel.setByteOffset(byteOffset);
/*  71 */     bufferViewModel.setByteLength(byteLength);
/*  72 */     bufferViewModel.setByteStride(byteStride);
/*  73 */     return bufferViewModel;
/*     */   }
/*     */   
/*     */   private static boolean isDataUriString(String uriString) {
/*  77 */     if (uriString == null) {
/*  78 */       return false;
/*     */     }
/*     */     try {
/*  81 */       URI uri = new URI(uriString);
/*  82 */       return isDataUri(uri);
/*  83 */     } catch (URISyntaxException e) {
/*  84 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean isDataUri(URI uri) {
/*  89 */     return "data".equalsIgnoreCase(uri.getScheme());
/*     */   }
/*     */   
/*     */   public static byte[] readDataUri(String uriString) {
/*  93 */     String encoding = "base64,";
/*  94 */     int encodingIndex = uriString.indexOf(encoding);
/*  95 */     if (encodingIndex < 0) {
/*  96 */       throw new IllegalArgumentException("The given URI string is not a base64 encoded data URI string: " + uriString);
/*     */     }
/*     */ 
/*     */     
/* 100 */     int contentStartIndex = encodingIndex + encoding.length();
/* 101 */     return Base64.getDecoder().decode(uriString
/* 102 */         .substring(contentStartIndex));
/*     */   }
/*     */   
/*     */   public List<BufferModel> getBufferModels() {
/* 106 */     return Collections.unmodifiableList(this.bufferModels);
/*     */   }
/*     */   
/*     */   public List<AccessorModel> getAccessorModels() {
/* 110 */     return this.accessorModels;
/*     */   }
/*     */   
/*     */   public List<AnimationModel> getAnimationModels() {
/* 114 */     return this.animationModels;
/*     */   }
/*     */   
/*     */   public List<BufferViewModel> getBufferViewModels() {
/* 118 */     return this.bufferViewModels;
/*     */   }
/*     */   
/*     */   public List<NodeModel> getNodeModels() {
/* 122 */     return this.nodeModels;
/*     */   }
/*     */   
/*     */   private void createBufferModels() {
/* 126 */     List<Buffer> buffers = (this.gltf.getBuffers() == null) ? Collections.<Buffer>emptyList() : this.gltf.getBuffers();
/* 127 */     for (int i = 0; i < buffers.size(); i++) {
/* 128 */       Buffer buffer = buffers.get(i);
/* 129 */       BufferModel bufferModel = new BufferModel();
/* 130 */       bufferModel.setUri(buffer.getUri());
/* 131 */       this.bufferModels.add(bufferModel);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initBufferModels() {
/* 136 */     List<Buffer> buffers = (this.gltf.getBuffers() == null) ? Collections.<Buffer>emptyList() : this.gltf.getBuffers();
/* 137 */     for (int i = 0; i < buffers.size(); i++) {
/* 138 */       Buffer buffer = buffers.get(i);
/* 139 */       BufferModel bufferModel = this.bufferModels.get(i);
/*     */       
/* 141 */       String uri = buffer.getUri();
/* 142 */       if (isDataUriString(uri)) {
/* 143 */         byte[] data = readDataUri(uri);
/* 144 */         ByteBuffer bufferData = Buffers.create(data);
/* 145 */         bufferModel.setBufferData(bufferData);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void createAccessorModels() {
/* 152 */     List<Accessor> accessors = (this.gltf.getAccessors() == null) ? Collections.<Accessor>emptyList() : this.gltf.getAccessors();
/* 153 */     for (Accessor accessor : accessors) {
/* 154 */       Integer componentType = accessor.getComponentType();
/* 155 */       Integer count = accessor.getCount();
/* 156 */       ElementType elementType = ElementType.forString(accessor.getType());
/*     */       
/* 158 */       AccessorModel accessorModel = new AccessorModel(componentType.intValue(), count.intValue(), elementType);
/* 159 */       this.accessorModels.add(accessorModel);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initAccessorModels() {
/* 164 */     List<Accessor> accessors = (this.gltf.getAccessors() == null) ? Collections.<Accessor>emptyList() : this.gltf.getAccessors();
/* 165 */     for (int i = 0; i < accessors.size(); i++) {
/* 166 */       Accessor accessor = accessors.get(i);
/* 167 */       AccessorModel accessorModel = this.accessorModels.get(i);
/*     */       
/* 169 */       int byteOffset = (accessor.getByteOffset() == null) ? 0 : accessor.getByteOffset().intValue();
/* 170 */       accessorModel.setByteOffset(byteOffset);
/*     */       
/* 172 */       AccessorSparse accessorSparse = accessor.getSparse();
/* 173 */       if (accessorSparse == null) {
/* 174 */         initDenseAccessorModel(i, accessor, accessorModel);
/*     */       } else {
/* 176 */         initSparseAccessorModel(i, accessor, accessorModel);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createAnimationModels() {
/* 182 */     List<Animation> animations = (this.gltf.getAnimations() == null) ? Collections.<Animation>emptyList() : this.gltf.getAnimations();
/* 183 */     for (int i = 0; i < animations.size(); i++) {
/* 184 */       this.animationModels.add(new AnimationModel());
/*     */     }
/*     */   }
/*     */   
/*     */   private void initAnimationModels() {
/* 189 */     List<Animation> animations = (this.gltf.getAnimations() == null) ? Collections.<Animation>emptyList() : this.gltf.getAnimations();
/*     */     
/* 191 */     for (int i = 0; i < animations.size(); i++) {
/* 192 */       Animation animation = animations.get(i);
/* 193 */       AnimationModel animationModel = this.animationModels.get(i);
/* 194 */       animationModel.setName(animation.getName());
/*     */       
/* 196 */       List<AnimationChannel> channels = animation.getChannels();
/* 197 */       for (AnimationChannel animationChannel : channels) {
/* 198 */         AnimationModel.Channel channel = createChannel(animation, animationChannel);
/* 199 */         animationModel.addChannel(channel);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createBufferViewModels() {
/* 205 */     List<BufferView> bufferViews = (this.gltf.getBufferViews() == null) ? Collections.<BufferView>emptyList() : this.gltf.getBufferViews();
/*     */     
/* 207 */     for (BufferView bufferView : bufferViews) {
/*     */       
/* 209 */       BufferViewModel bufferViewModel = createBufferViewModel(bufferView);
/* 210 */       this.bufferViewModels.add(bufferViewModel);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initBufferViewModels() {
/* 215 */     List<BufferView> bufferViews = (this.gltf.getBufferViews() == null) ? Collections.<BufferView>emptyList() : this.gltf.getBufferViews();
/*     */     
/* 217 */     for (int i = 0; i < bufferViews.size(); i++) {
/* 218 */       BufferView bufferView = bufferViews.get(i);
/*     */       
/* 220 */       BufferViewModel bufferViewModel = this.bufferViewModels.get(i);
/*     */       
/* 222 */       int bufferIndex = bufferView.getBuffer().intValue();
/* 223 */       BufferModel bufferModel = this.bufferModels.get(bufferIndex);
/* 224 */       bufferViewModel.setBufferModel(bufferModel);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createNodeModels() {
/* 229 */     List<Node> nodes = (this.gltf.getNodes() == null) ? Collections.<Node>emptyList() : this.gltf.getNodes();
/* 230 */     for (int i = 0; i < nodes.size(); i++) {
/* 231 */       this.nodeModels.add(new NodeModel());
/*     */     }
/*     */   }
/*     */   
/*     */   private void initNodeModels() {
/* 236 */     List<Node> nodes = (this.gltf.getNodes() == null) ? Collections.<Node>emptyList() : this.gltf.getNodes();
/* 237 */     for (int i = 0; i < nodes.size(); i++) {
/* 238 */       Node node = nodes.get(i);
/*     */       
/* 240 */       NodeModel nodeModel = this.nodeModels.get(i);
/* 241 */       nodeModel.setName(node.getName());
/*     */       
/* 243 */       List<Integer> childIndices = (node.getChildren() == null) ? Collections.<Integer>emptyList() : node.getChildren();
/*     */       
/* 245 */       for (Integer childIndex : childIndices) {
/* 246 */         NodeModel child = this.nodeModels.get(childIndex.intValue());
/* 247 */         nodeModel.addChild(child);
/*     */       } 
/*     */       
/* 250 */       float[] matrix = node.getMatrix();
/* 251 */       float[] translation = node.getTranslation();
/* 252 */       float[] rotation = node.getRotation();
/* 253 */       float[] scale = node.getScale();
/* 254 */       nodeModel.setMatrix(clone(matrix));
/* 255 */       nodeModel.setTranslation(clone(translation));
/* 256 */       nodeModel.setRotation(clone(rotation));
/* 257 */       nodeModel.setScale(clone(scale));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private AnimationModel.Channel createChannel(Animation animation, AnimationChannel animationChannel) {
/* 263 */     List<AnimationSampler> samplers = animation.getSamplers();
/*     */     
/* 265 */     int samplerIndex = animationChannel.getSampler().intValue();
/* 266 */     AnimationSampler animationSampler = samplers.get(samplerIndex);
/*     */     
/* 268 */     int inputAccessorIndex = animationSampler.getInput().intValue();
/*     */     
/* 270 */     AccessorModel inputAccessorModel = this.accessorModels.get(inputAccessorIndex);
/*     */     
/* 272 */     int outputAccessorIndex = animationSampler.getOutput().intValue();
/*     */     
/* 274 */     AccessorModel outputAccessorModel = this.accessorModels.get(outputAccessorIndex);
/*     */ 
/*     */     
/* 277 */     String interpolationString = animationSampler.getInterpolation();
/*     */ 
/*     */     
/* 280 */     AnimationModel.Interpolation interpolation = (interpolationString == null) ? AnimationModel.Interpolation.LINEAR : AnimationModel.Interpolation.valueOf(interpolationString);
/*     */     
/* 282 */     AnimationModel.Sampler sampler = new AnimationModel.Sampler(inputAccessorModel, interpolation, outputAccessorModel);
/*     */ 
/*     */ 
/*     */     
/* 286 */     AnimationChannelTarget animationChannelTarget = animationChannel.getTarget();
/*     */     
/* 288 */     Integer nodeIndex = animationChannelTarget.getNode();
/* 289 */     NodeModel nodeModel = null;
/* 290 */     if (nodeIndex != null) {
/* 291 */       nodeModel = this.nodeModels.get(nodeIndex.intValue());
/*     */     }
/* 293 */     String path = animationChannelTarget.getPath();
/*     */     
/* 295 */     return new AnimationModel.Channel(sampler, nodeModel, path);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initDenseAccessorModel(int accessorIndex, Accessor accessor, AccessorModel accessorModel) {
/* 300 */     Integer bufferViewIndex = accessor.getBufferView();
/* 301 */     if (bufferViewIndex != null) {
/*     */ 
/*     */ 
/*     */       
/* 305 */       BufferViewModel bufferViewModel1 = this.bufferViewModels.get(bufferViewIndex.intValue());
/* 306 */       accessorModel.setBufferViewModel(bufferViewModel1);
/*     */     }
/*     */     else {
/*     */       
/* 310 */       int count = accessorModel.getCount();
/* 311 */       int elementSizeInBytes = accessorModel.getElementSizeInBytes();
/* 312 */       int byteLength = elementSizeInBytes * count;
/* 313 */       ByteBuffer bufferData = Buffers.create(byteLength);
/* 314 */       String uriString = "buffer_for_accessor" + accessorIndex + ".bin";
/*     */       
/* 316 */       BufferViewModel bufferViewModel1 = createBufferViewModel(uriString, bufferData);
/* 317 */       accessorModel.setBufferViewModel(bufferViewModel1);
/*     */     } 
/*     */     
/* 320 */     BufferViewModel bufferViewModel = accessorModel.getBufferViewModel();
/* 321 */     Integer byteStride = bufferViewModel.getByteStride();
/* 322 */     if (byteStride == null) {
/* 323 */       accessorModel.setByteStride(accessorModel
/* 324 */           .getElementSizeInBytes());
/*     */     } else {
/* 326 */       accessorModel.setByteStride(byteStride.intValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initSparseAccessorModel(int accessorIndex, Accessor accessor, AccessorModel accessorModel) {
/* 335 */     int count = accessorModel.getCount();
/* 336 */     int elementSizeInBytes = accessorModel.getElementSizeInBytes();
/* 337 */     int byteLength = elementSizeInBytes * count;
/* 338 */     ByteBuffer bufferData = Buffers.create(byteLength);
/* 339 */     String uriString = "buffer_for_accessor" + accessorIndex + ".bin";
/*     */     
/* 341 */     BufferViewModel denseBufferViewModel = createBufferViewModel(uriString, bufferData);
/* 342 */     accessorModel.setBufferViewModel(denseBufferViewModel);
/* 343 */     accessorModel.setByteOffset(0);
/*     */     
/* 345 */     Integer bufferViewIndex = accessor.getBufferView();
/* 346 */     if (bufferViewIndex != null) {
/*     */ 
/*     */ 
/*     */       
/* 350 */       Consumer<ByteBuffer> sparseSubstitutionCallback = denseByteBuffer -> {
/*     */           BufferViewModel baseBufferViewModel = this.bufferViewModels.get(bufferViewIndex.intValue());
/*     */ 
/*     */           
/*     */           ByteBuffer baseBufferViewData = baseBufferViewModel.getBufferViewData();
/*     */           
/*     */           AccessorData baseAccessorData = AccessorDatas.create(accessorModel, baseBufferViewData);
/*     */           
/*     */           AccessorData denseAccessorData = AccessorDatas.create(accessorModel, bufferData);
/*     */           
/*     */           substituteSparseAccessorData(accessor, accessorModel, denseAccessorData, baseAccessorData);
/*     */         };
/*     */       
/* 363 */       denseBufferViewModel.setSparseSubstitutionCallback(sparseSubstitutionCallback);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 368 */       Consumer<ByteBuffer> sparseSubstitutionCallback = denseByteBuffer -> {
/*     */           AccessorData denseAccessorData = AccessorDatas.create(accessorModel, bufferData);
/*     */ 
/*     */           
/*     */           substituteSparseAccessorData(accessor, accessorModel, denseAccessorData, null);
/*     */         };
/*     */       
/* 375 */       denseBufferViewModel.setSparseSubstitutionCallback(sparseSubstitutionCallback);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void substituteSparseAccessorData(Accessor accessor, AccessorModel accessorModel, AccessorData denseAccessorData, AccessorData baseAccessorData) {
/* 383 */     AccessorSparse accessorSparse = accessor.getSparse();
/* 384 */     int count = accessorSparse.getCount().intValue();
/*     */ 
/*     */     
/* 387 */     AccessorSparseIndices accessorSparseIndices = accessorSparse.getIndices();
/*     */     
/* 389 */     AccessorData sparseIndicesAccessorData = createSparseIndicesAccessorData(accessorSparseIndices, count);
/*     */     
/* 391 */     AccessorSparseValues accessorSparseValues = accessorSparse.getValues();
/* 392 */     ElementType elementType = accessorModel.getElementType();
/*     */     
/* 394 */     AccessorData sparseValuesAccessorData = createSparseValuesAccessorData(accessorSparseValues, accessorModel
/* 395 */         .getComponentType(), elementType
/* 396 */         .getNumComponents(), count);
/*     */     
/* 398 */     AccessorSparseUtils.substituteAccessorData(denseAccessorData, baseAccessorData, sparseIndicesAccessorData, sparseValuesAccessorData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AccessorData createSparseIndicesAccessorData(AccessorSparseIndices accessorSparseIndices, int count) {
/* 407 */     Integer componentType = accessorSparseIndices.getComponentType();
/* 408 */     Integer bufferViewIndex = accessorSparseIndices.getBufferView();
/* 409 */     BufferViewModel bufferViewModel = this.bufferViewModels.get(bufferViewIndex.intValue());
/* 410 */     ByteBuffer bufferViewData = bufferViewModel.getBufferViewData();
/* 411 */     int byteOffset = (accessorSparseIndices.getByteOffset() == null) ? 0 : accessorSparseIndices.getByteOffset().intValue();
/* 412 */     return AccessorDatas.create(componentType
/* 413 */         .intValue(), bufferViewData, byteOffset, count, 1, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private AccessorData createSparseValuesAccessorData(AccessorSparseValues accessorSparseValues, int componentType, int numComponentsPerElement, int count) {
/* 419 */     Integer bufferViewIndex = accessorSparseValues.getBufferView();
/* 420 */     BufferViewModel bufferViewModel = this.bufferViewModels.get(bufferViewIndex.intValue());
/* 421 */     ByteBuffer bufferViewData = bufferViewModel.getBufferViewData();
/* 422 */     int byteOffset = (accessorSparseValues.getByteOffset() == null) ? 0 : accessorSparseValues.getByteOffset().intValue();
/* 423 */     return AccessorDatas.create(componentType, bufferViewData, byteOffset, count, numComponentsPerElement, null);
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\AnimationStructure.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */