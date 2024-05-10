package com.backend.Backend_supermarket.services;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.model.*;
import com.oracle.bmc.objectstorage.requests.*;
import com.oracle.bmc.objectstorage.responses.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public interface CloudObjectStorageApiService {
    public InputStream getObject(String namespaceName, String bucketName, String objectName);
    public String createObject(String namespaceName, String bucketName, InputStream inputStream, String contentType);
    public void putObject(String namespaceName, String bucketName, String objectName, InputStream inputStream, String contentType);
    public void deleteObject(String namespaceName, String bucketName, String objectName);
}
