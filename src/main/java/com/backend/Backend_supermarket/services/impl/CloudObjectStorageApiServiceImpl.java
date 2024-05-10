//package com.backend.Backend_supermarket.services.impl;
//
//import com.backend.Backend_supermarket.services.CloudObjectStorageApiService;
//import com.oracle.bmc.ConfigFileReader;
//import com.oracle.bmc.auth.AuthenticationDetailsProvider;
//import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
//import com.oracle.bmc.objectstorage.ObjectStorageClient;
//import com.oracle.bmc.objectstorage.model.*;
//import com.oracle.bmc.objectstorage.requests.*;
//import com.oracle.bmc.objectstorage.responses.*;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Date;
//import java.util.UUID;
//import java.util.Arrays;
//
//public class CloudObjectStorageApiServiceImpl implements CloudObjectStorageApiService {
//    final ConfigFileReader.ConfigFile configFile;
//
//    {
//        try {
//            configFile = ConfigFileReader.parseDefault();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
//
//    /* Create a service client */
//    ObjectStorageClient client = ObjectStorageClient.builder().build(provider);
//
//    @Override
//    public InputStream getObject(String namespaceName, String bucketName, String objectName) {
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .namespaceName(namespaceName)
//                .bucketName(bucketName)
//                .objectName(objectName)
//                .build();
//
//        GetObjectResponse getObjectResponse = objectStorageClient.getObject(getObjectRequest);
//        return getObjectResponse.getInputStream();
//    }
//
//    @Override
//    public String createObject(String namespaceName, String bucketName, InputStream inputStream, String contentType) {
//        UpdateOb postObjectRequest = UpdateBucketRequest.builder()
//                .namespaceName(namespaceName)
//                .bucketName(bucketName)
//                .contentType(contentType)
//                .putObjectBody(inputStream)
//                .build();
//
//        UpdateObjectStorageTierDetails postObjectResponse = objectStorageClient.postObject(postObjectRequest);
//        return postObjectResponse.getOpcContentDisposition().split("=")[1];
//    }
//
//    @Override
//    public void putObject(String namespaceName, String bucketName, String objectName, InputStream inputStream, String contentType) {
//        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                .namespaceName(namespaceName)
//                .bucketName(bucketName)
//                .objectName(objectName)
//                .contentType(contentType)
//                .putObjectBody(inputStream)
//                .build();
//
//        objectStorageClient.putObject(putObjectRequest);
//    }
//
//    @Override
//    public void deleteObject(String namespaceName, String bucketName, String objectName) {
//        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
//                .namespaceName(namespaceName)
//                .bucketName(bucketName)
//                .objectName(objectName)
//                .build();
//
//        objectStorageClient.deleteObject(deleteObjectRequest);
//    }
//
//    private static InputStream generateStreamFromString(String data) {
//        return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
//    }
//}
