package com.example.demo;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class MinioAdapter {

    final MinioClient minioClient;

    @Value("${minio.buckek.name}")
    String defaultBucketName;

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

    public MinioAdapter(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * Lista todos os buckets disponíveis
     * @return Lista contendo os buckets disponíveis
     */
    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }

    }

    /**
     * Realiza o upload de um arquivo para o bucket
     * @param name Nome do arquivo
     * @param size Tamanho do arquivo em bytes
     * @param contentType Tipo do arquivo
     * @param content Conteúdo do arquivo
     */
    public void uploadFile(String name, long size, String contentType, byte[] content) {
        try  {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(defaultBucketName).object(name).stream(
                            new ByteArrayInputStream(content),  size, 10485760)
                            .contentType(contentType)
                            .build());
        } catch (Exception e) {
            throw new MinioException(e.getMessage());
        }

    }

    /**
     * Obtém um arquivo pelo chave
     * @param key Chave do arquivo
     * @return bytes do arquivo
     */
    public byte[] getFile(String key) {

        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(defaultBucketName)
                        .object(key)
                        .build())) {
            return IOUtils.toByteArray(stream);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidResponseException | ServerException | InsufficientDataException | XmlParserException | InternalException | InvalidBucketNameException | IOException | ErrorResponseException e) {
            throw new MinioException(e);
        }
    }

    @PostConstruct
    public void init() {
        // metodo construtor do bean
    }
}