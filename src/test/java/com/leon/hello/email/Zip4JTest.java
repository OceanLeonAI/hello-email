package com.leon.hello.email;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @PROJECT_NAME: hello-email
 * @CLASS_NAME: ZipTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/18 17:42
 * @Version 1.0
 * @DESCRIPTION: 压缩文件测试
 **/
public class Zip4JTest {

    /**
     * ZipTest
     *
     * @throws IOException
     */
    @Test
    public void zip4JTest() throws IOException {

        String filePath = "src/main/resources/zipSources/a.txt";
        ZipFile zipFile = new ZipFile("src/main/resources/zipSources/zip4j.zip");
        zipFile.addFile(filePath);

    }


    // List all files in a zip
    @Test
    public void listAllFilesInAZipTest() throws IOException {
//        List<FileHeader> fileHeaders = new ZipFile("src/main/resources/zipSources/zip4j.zip").getFileHeaders();
//        List<FileHeader> fileHeaders = new ZipFile("src/main/resources/zipSources/multiCompressed.zip").getFileHeaders();
        List<FileHeader> fileHeaders = new ZipFile("src/main/resources/zipSources/dirCompressed.zip").getFileHeaders();
        fileHeaders.stream().forEach(fileHeader -> System.out.println(fileHeader.getFileName()));
    }

    @Test
    public void createZipWithPasswordTest() throws ZipException {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        // Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

        List<File> filesToAdd = Arrays.asList(
                new File("src/main/resources/zipSources/a.txt"),
                new File("src/main/resources/zipSources/b.txt")
        );

        ZipFile zipFile = new ZipFile("src/main/resources/zipSources/zipWithPassword.zip", "OceanLeonAI".toCharArray());
        zipFile.addFiles(filesToAdd, zipParameters);
    }


}
