/*     */ package com.tacz.guns.api.client.animation.gltf.accessor;
/*     */ 
/*     */ import com.tacz.guns.api.client.animation.gltf.AccessorModel;
/*     */ import com.tacz.guns.api.client.animation.gltf.BufferViewModel;
/*     */ import com.tacz.guns.api.client.animation.gltf.GltfConstants;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AccessorDatas
/*     */ {
/*     */   public static AccessorData create(AccessorModel accessorModel) {
/*  58 */     BufferViewModel bufferViewModel = accessorModel.getBufferViewModel();
/*  59 */     ByteBuffer bufferViewData = bufferViewModel.getBufferViewData();
/*  60 */     return create(accessorModel, bufferViewData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AccessorData create(AccessorModel accessorModel, ByteBuffer byteBuffer) {
/*  73 */     if (accessorModel.getComponentDataType() == byte.class) {
/*  74 */       return createByte(accessorModel, byteBuffer);
/*     */     }
/*  76 */     if (accessorModel.getComponentDataType() == short.class) {
/*  77 */       return createShort(accessorModel, byteBuffer);
/*     */     }
/*  79 */     if (accessorModel.getComponentDataType() == int.class) {
/*  80 */       return createInt(accessorModel, byteBuffer);
/*     */     }
/*  82 */     if (accessorModel.getComponentDataType() == float.class) {
/*  83 */       return createFloat(accessorModel, byteBuffer);
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AccessorData create(int componentType, ByteBuffer bufferViewData, int byteOffset, int count, int numComponentsPerElement, Integer byteStride) {
/* 109 */     if (isByteType(componentType)) {
/* 110 */       return new AccessorByteData(componentType, bufferViewData, byteOffset, count, numComponentsPerElement, byteStride);
/*     */     }
/*     */ 
/*     */     
/* 114 */     if (isShortType(componentType)) {
/* 115 */       return new AccessorShortData(componentType, bufferViewData, byteOffset, count, numComponentsPerElement, byteStride);
/*     */     }
/*     */ 
/*     */     
/* 119 */     if (isIntType(componentType)) {
/* 120 */       return new AccessorIntData(componentType, bufferViewData, byteOffset, count, numComponentsPerElement, byteStride);
/*     */     }
/*     */ 
/*     */     
/* 124 */     if (isFloatType(componentType)) {
/* 125 */       return new AccessorFloatData(componentType, bufferViewData, byteOffset, count, numComponentsPerElement, byteStride);
/*     */     }
/*     */ 
/*     */     
/* 129 */     throw new IllegalArgumentException("Not a valid component type: " + componentType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isByteType(int type) {
/* 141 */     return (type == 5120 || type == 5121);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isShortType(int type) {
/* 154 */     return (type == 5122 || type == 5123);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isIntType(int type) {
/* 167 */     return (type == 5124 || type == 5125);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isFloatType(int type) {
/* 179 */     return (type == 5126);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean isUnsignedType(int type) {
/* 190 */     return (type == 5121 || type == 5123 || type == 5125);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validateByteType(int type) {
/* 206 */     if (!isByteType(type)) {
/* 207 */       throw new IllegalArgumentException("The type is not GL_BYTE or GL_UNSIGNED_BYTE, but " + 
/*     */           
/* 209 */           GltfConstants.stringFor(type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validateShortType(int type) {
/* 223 */     if (!isShortType(type)) {
/* 224 */       throw new IllegalArgumentException("The type is not GL_SHORT or GL_UNSIGNED_SHORT, but " + 
/*     */           
/* 226 */           GltfConstants.stringFor(type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validateIntType(int type) {
/* 240 */     if (!isIntType(type)) {
/* 241 */       throw new IllegalArgumentException("The type is not GL_INT or GL_UNSIGNED_INT, but " + 
/*     */           
/* 243 */           GltfConstants.stringFor(type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validateFloatType(int type) {
/* 256 */     if (!isFloatType(type)) {
/* 257 */       throw new IllegalArgumentException("The type is not GL_FLOAT, but " + 
/*     */           
/* 259 */           GltfConstants.stringFor(type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static AccessorByteData createByte(AccessorModel accessorModel) {
/* 273 */     BufferViewModel bufferViewModel = accessorModel.getBufferViewModel();
/* 274 */     return createByte(accessorModel, bufferViewModel.getBufferViewData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static AccessorByteData createByte(AccessorModel accessorModel, ByteBuffer bufferViewByteBuffer) {
/* 292 */     return new AccessorByteData(accessorModel.getComponentType(), bufferViewByteBuffer, accessorModel
/*     */         
/* 294 */         .getByteOffset(), accessorModel
/* 295 */         .getCount(), accessorModel
/* 296 */         .getElementType().getNumComponents(), 
/* 297 */         Integer.valueOf(accessorModel.getByteStride()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static AccessorShortData createShort(AccessorModel accessorModel) {
/* 311 */     BufferViewModel bufferViewModel = accessorModel.getBufferViewModel();
/* 312 */     return createShort(accessorModel, bufferViewModel.getBufferViewData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static AccessorShortData createShort(AccessorModel accessorModel, ByteBuffer bufferViewByteBuffer) {
/* 330 */     return new AccessorShortData(accessorModel.getComponentType(), bufferViewByteBuffer, accessorModel
/*     */         
/* 332 */         .getByteOffset(), accessorModel
/* 333 */         .getCount(), accessorModel
/* 334 */         .getElementType().getNumComponents(), 
/* 335 */         Integer.valueOf(accessorModel.getByteStride()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static AccessorIntData createInt(AccessorModel accessorModel) {
/* 348 */     BufferViewModel bufferViewModel = accessorModel.getBufferViewModel();
/* 349 */     return createInt(accessorModel, bufferViewModel.getBufferViewData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static AccessorIntData createInt(AccessorModel accessorModel, ByteBuffer bufferViewByteBuffer) {
/* 366 */     return new AccessorIntData(accessorModel.getComponentType(), bufferViewByteBuffer, accessorModel
/*     */         
/* 368 */         .getByteOffset(), accessorModel
/* 369 */         .getCount(), accessorModel
/* 370 */         .getElementType().getNumComponents(), 
/* 371 */         Integer.valueOf(accessorModel.getByteStride()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AccessorFloatData createFloat(AccessorModel accessorModel) {
/* 384 */     BufferViewModel bufferViewModel = accessorModel.getBufferViewModel();
/* 385 */     return createFloat(accessorModel, bufferViewModel.getBufferViewData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static AccessorFloatData createFloat(AccessorModel accessorModel, ByteBuffer bufferViewByteBuffer) {
/* 400 */     return new AccessorFloatData(accessorModel.getComponentType(), bufferViewByteBuffer, accessorModel
/*     */         
/* 402 */         .getByteOffset(), accessorModel
/* 403 */         .getCount(), accessorModel
/* 404 */         .getElementType().getNumComponents(), 
/* 405 */         Integer.valueOf(accessorModel.getByteStride()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validateCapacity(int byteOffset, int numElements, int byteStridePerElement, int bufferCapacity) {
/* 421 */     int expectedCapacity = numElements * byteStridePerElement;
/* 422 */     if (expectedCapacity > bufferCapacity) {
/* 423 */       throw new IllegalArgumentException("The accessorModel has an offset of " + byteOffset + " and " + numElements + " elements with a byte stride of " + byteStridePerElement + ", requiring " + expectedCapacity + " bytes, but the buffer view has only " + bufferCapacity + " bytes");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Number[] computeMin(AccessorData accessorData) {
/* 441 */     if (accessorData instanceof AccessorByteData) {
/* 442 */       AccessorByteData accessorByteData = (AccessorByteData)accessorData;
/*     */       
/* 444 */       return NumberArrays.asNumbers(accessorByteData
/* 445 */           .computeMinInt());
/*     */     } 
/* 447 */     if (accessorData instanceof AccessorShortData) {
/* 448 */       AccessorShortData accessorShortData = (AccessorShortData)accessorData;
/*     */       
/* 450 */       return NumberArrays.asNumbers(accessorShortData
/* 451 */           .computeMinInt());
/*     */     } 
/* 453 */     if (accessorData instanceof AccessorIntData) {
/* 454 */       AccessorIntData accessorIntData = (AccessorIntData)accessorData;
/*     */       
/* 456 */       return NumberArrays.asNumbers(accessorIntData
/* 457 */           .computeMinLong());
/*     */     } 
/* 459 */     if (accessorData instanceof AccessorFloatData) {
/* 460 */       AccessorFloatData accessorFloatData = (AccessorFloatData)accessorData;
/*     */       
/* 462 */       return NumberArrays.asNumbers(accessorFloatData
/* 463 */           .computeMin());
/*     */     } 
/* 465 */     throw new IllegalArgumentException("Invalid data type: " + accessorData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Number[] computeMax(AccessorData accessorData) {
/* 478 */     if (accessorData instanceof AccessorByteData) {
/* 479 */       AccessorByteData accessorByteData = (AccessorByteData)accessorData;
/*     */       
/* 481 */       return NumberArrays.asNumbers(accessorByteData
/* 482 */           .computeMaxInt());
/*     */     } 
/* 484 */     if (accessorData instanceof AccessorShortData) {
/* 485 */       AccessorShortData accessorShortData = (AccessorShortData)accessorData;
/*     */       
/* 487 */       return NumberArrays.asNumbers(accessorShortData
/* 488 */           .computeMaxInt());
/*     */     } 
/* 490 */     if (accessorData instanceof AccessorIntData) {
/* 491 */       AccessorIntData accessorIntData = (AccessorIntData)accessorData;
/*     */       
/* 493 */       return NumberArrays.asNumbers(accessorIntData
/* 494 */           .computeMaxLong());
/*     */     } 
/* 496 */     if (accessorData instanceof AccessorFloatData) {
/* 497 */       AccessorFloatData accessorFloatData = (AccessorFloatData)accessorData;
/*     */       
/* 499 */       return NumberArrays.asNumbers(accessorFloatData
/* 500 */           .computeMax());
/*     */     } 
/* 502 */     throw new IllegalArgumentException("Invalid data type: " + accessorData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String createString(AccessorData accessorData, int elementsPerRow) {
/* 522 */     if (accessorData instanceof AccessorByteData) {
/* 523 */       AccessorByteData accessorByteData = (AccessorByteData)accessorData;
/*     */ 
/*     */       
/* 526 */       String accessorDataString = accessorByteData.createString(Locale.ENGLISH, "%4d", elementsPerRow);
/*     */       
/* 528 */       return accessorDataString;
/*     */     } 
/* 530 */     if (accessorData instanceof AccessorShortData) {
/* 531 */       AccessorShortData accessorShortData = (AccessorShortData)accessorData;
/*     */ 
/*     */       
/* 534 */       String accessorDataString = accessorShortData.createString(Locale.ENGLISH, "%6d", elementsPerRow);
/*     */       
/* 536 */       return accessorDataString;
/*     */     } 
/* 538 */     if (accessorData instanceof AccessorIntData) {
/* 539 */       AccessorIntData accessorIntData = (AccessorIntData)accessorData;
/*     */ 
/*     */       
/* 542 */       String accessorDataString = accessorIntData.createString(Locale.ENGLISH, "%11d", elementsPerRow);
/*     */       
/* 544 */       return accessorDataString;
/*     */     } 
/* 546 */     if (accessorData instanceof AccessorFloatData) {
/* 547 */       AccessorFloatData accessorFloatData = (AccessorFloatData)accessorData;
/*     */ 
/*     */       
/* 550 */       String accessorDataString = accessorFloatData.createString(Locale.ENGLISH, "%10.5f", elementsPerRow);
/*     */       
/* 552 */       return accessorDataString;
/*     */     } 
/* 554 */     return "Unknown accessor data type: " + accessorData;
/*     */   }
/*     */ }


/* Location:              C:\Users\gabri\AppData\Roaming\.minecraft\mods\tacz-1.20.1-1.0.3-all.jar!\com\tacz\guns\api\client\animation\gltf\accessor\AccessorDatas.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */