package com.leon.hello.email;

import net.lingala.zip4j.ZipFile;
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
public class ZipTest {

    /**
     * 压缩单个文件
     */
    @Test
    public void compressSingleFile() throws Exception {

        //输出压缩包
        FileOutputStream fos = new FileOutputStream("src/main/resources/zipSources/singleCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        //被压缩文件
        File fileToZip = new File("src/main/resources/zipSources/a.txt");
        FileInputStream fis = new FileInputStream(fileToZip);

        //向压缩包中添加文件
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();


    }

    /**
     * 压缩多个文件
     */
    @Test
    public void compressMultipleFile() throws IOException {
        List<String> srcFiles = Arrays.asList("src/main/resources/zipSources/a.txt", "src/main/resources/zipSources/b.txt");
        FileOutputStream fos = new FileOutputStream("src/main/resources/zipSources/multiCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        //向压缩包中添加多个文件
        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }

        zipOut.close();
        fos.close();
    }

    /**
     * 压缩文件夹
     */
    @Test
    public void compressFolder() throws IOException {
        //被压缩的文件夹
        String sourceFile = "src/main/resources/zipSources/dir";
        //压缩结果输出，即压缩包
        FileOutputStream fos = new FileOutputStream("src/main/resources/zipSources/dirCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        //递归压缩文件夹
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        //关闭输出流
        zipOut.close();
        fos.close();
    }

    /**
     * 将fileToZip文件夹及其子目录文件递归压缩到zip文件中
     *
     * @param fileToZip 递归当前处理对象，可能是文件夹，也可能是文件
     * @param fileName  fileToZip文件或文件夹名称
     * @param zipOut    压缩文件输出流
     * @throws IOException
     */
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        //不压缩隐藏文件夹
        if (fileToZip.isHidden()) {
            return;
        }
        //判断压缩对象如果是一个文件夹
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                //如果文件夹是以“/”结尾，将文件夹作为压缩箱放入zipOut压缩输出流
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                //如果文件夹不是以“/”结尾，将文件夹结尾加上“/”之后作为压缩箱放入zipOut压缩输出流
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            //遍历文件夹子目录，进行递归的zipFile
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            //如果当前递归对象是文件夹，加入ZipEntry之后就返回
            return;
        }
        //如果当前的fileToZip不是一个文件夹，是一个文件，将其以字节码形式压缩到压缩包里面
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    @Test
    public void zip4JTest() throws IOException {

        String filePath = "src/main/resources/zipSources/a.txt";
        ZipFile zipFile = new ZipFile("src/main/resources/zipSources/zip4j.zip");
        zipFile.addFile(filePath);

    }

}
