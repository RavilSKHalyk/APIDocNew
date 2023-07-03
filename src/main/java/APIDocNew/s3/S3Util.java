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
    private final String accessKey = "00bb23af635810edc943";
    private final String secretKey = "FbWzvIg1PjfCV6q5XoEzXnqMTwk1Pm6/3u0vYTVC";
    private final String urlEndpoint = "s3-almaty.abay.kazteleport.kz:443";

    public AmazonS3 getClient(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(urlEndpoint, "auto")
                )
                .build();
        return s3Client;
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
        PutObjectRequest request = new PutObjectRequest(bucketName, objectKey, file);
        s3Client.putObject(request);
    }

    public void DeleteObjectFromBucket (String bucketName, String objectKey){
        AmazonS3 s3Client =  getClient();
        s3Client.deleteObject(bucketName, objectKey);
    }

    /**
     * удалить можно только пустой бакет, поэтому сначала нужно удалить все обьекты из него
     * @param bucketName
     */
    public void DeleteBucket (String bucketName){
        AmazonS3 s3Client =  getClient();
        s3Client.deleteBucket(bucketName);
    }
}
