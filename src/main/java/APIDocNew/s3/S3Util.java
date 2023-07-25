package APIDocNew.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

public class S3Util {
    private final String accessKey = "00bb23af63581 0edc943";
    private final String secretKey = "FbWzvIg1PjfCV6q5XoEzXnqMTwk1Pm6/3u0vYTVC";
    private final String urlEndpoint = "s3-almaty.abay.kazteleport.kz:443";

    public AmazonS3 getClient(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(urlEndpoint, "auto")
                )
                .build();
    }

    public void createBucket (String bucketName){
        AmazonS3 s3Client =  getClient();

        if (s3Client.doesBucketExistV2(bucketName)) {
            System.out.format("Bucket %s already exists.\n", bucketName);
        } else {
            try {
                s3Client.createBucket(bucketName);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
    }

    public void PutObjectInBucket (String bucketName, String objectKey, File file){
        AmazonS3 s3Client =  getClient();
        //System.out.println("создано подключение для записи");
        PutObjectRequest request = new PutObjectRequest(bucketName, objectKey, file);
        s3Client.putObject(request);
    }

    /**
     * Удалить обьект из бакета
     * @param bucketName имя бакета
     * @param objectKey имя обьекта
     */
    public void DeleteObjectFromBucket (String bucketName, String objectKey){
        AmazonS3 s3Client =  getClient();
        s3Client.deleteObject(bucketName, objectKey);
    }

    /**
     * удалить можно только пустой бакет, поэтому сначала нужно удалить все обьекты из него
     * @param bucketName имя бакета
     */
    public void DeleteBucket (String bucketName){
        AmazonS3 s3Client =  getClient();
        s3Client.deleteBucket(bucketName);
    }

    /**
     * Проверка существования обьекта
     * @param bucketName имя бакета
     * @param objectName имя обьекта
     * @return
     */
    public boolean isExsist (String bucketName, String objectName){
        AmazonS3 s3Client =  getClient();
        //System.out.println("создано подключение");
        if (s3Client.doesBucketExistV2(bucketName)) {
            //System.out.println("существует бакет");
        }
        return s3Client.doesObjectExist(bucketName, objectName);
    }

    /**
     * получить данные из обьекта
     * @param bucketName имя бакета
     * @param objectName имя обьекта
     * @return
     */
    public String getStringFromObject(String bucketName, String objectName){
        AmazonS3 s3Client = getClient();
        return s3Client.getObjectAsString(bucketName, objectName);
    }

}
