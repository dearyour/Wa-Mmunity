package com.web.wam.model.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    @Value("${cloud.aws.s3.hosting}")
    public String hosting;  // S3 정적 호스팅 이름

    @Value("${cloud.aws.s3.dir-freeboard}")
    public String dirFreeboard;  // S3  dir 이름

    @Value("${cloud.aws.s3.dir-resellboard}")
    public String dirResellboard;  // S3  dir 이름

    @Value("${cloud.aws.s3.dir-member}")
    public String dirMember;  // S3  dir 이름

    @Value("${cloud.aws.s3.dir-wine}")
    public String dirWine;  // S3  dir 이름

    public String uploadToFreeboard(MultipartFile file) {

        return uploadFileToS3(file, dirFreeboard);
    }

    public String uploadToResellboard(MultipartFile file) {

        return uploadFileToS3(file, dirResellboard);
    }

    public String uploadToMember(MultipartFile file) {

        return uploadFileToS3(file, dirMember);
    }

    public String uploadToWine(MultipartFile file) {

        return uploadFileToS3(file, dirWine);
    }

    private String uploadFileToS3(MultipartFile file, String dir) {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        ObjectMetadata objMeta = new ObjectMetadata();
        try {
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            objMeta.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayIs = new ByteArrayInputStream(bytes);

            amazonS3Client.putObject(new PutObjectRequest(bucket, dir + "/" + fileName, byteArrayIs, objMeta));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = amazonS3Client.getUrl(bucket, dir + "/" + fileName).getPath();
        path = hosting + path;
        return path;
    }

    public ResponseEntity<byte[]> downloadFromLinkdetailDir(String filename) throws IOException {
        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, filename));
        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String storedFileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", storedFileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}
