import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;


public class CreateBucket {

    //https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
    public static Bucket getBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.DEFAULT_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials(
                        "ASIAS44XD3BR76XC7G4Q",
                        "LsNDVqZeLo07YX4oNdzMFdPvniluKStSO5AcPjwF",
                        "FwoGZXIvYXdzED4aDEZJW3l1fZTHZWy2BSLAARz5s62574lTWroOjeeHpywYRmoGRcRp6PLPWLSIxG8MKX+df+yaY+SapQKNtzg8a8ZeIqyLL8jyGRC4c34ZaKlx7EopLbZ17s1om5hoPc+kYg1G+n03MS759AlQDpp3vDIzhZ5HmW6nsCTOOuAKzYhdUj7a4RZ4ea6uqJtYwhkI4MRQQabDoMqwTi4Nl4kKBpzXlUHEb/5IDBz7wcsLDQ/LUV5pT4iUjF5ZwVJmHZc01ouDBbAZnny6l10d+bT3uijr1bGaBjItUjWLg1l1eYP2Fwv7f7byrzTwW3fpoFKYJeS4BHHbQChOCK3yXIzuqQIEBaP5"
                ))).build();
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }

    //https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
    public static Bucket createBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.DEFAULT_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials(
                        "ASIAS44XD3BR76XC7G4Q",
                        "LsNDVqZeLo07YX4oNdzMFdPvniluKStSO5AcPjwF",
                        "FwoGZXIvYXdzED4aDEZJW3l1fZTHZWy2BSLAARz5s62574lTWroOjeeHpywYRmoGRcRp6PLPWLSIxG8MKX+df+yaY+SapQKNtzg8a8ZeIqyLL8jyGRC4c34ZaKlx7EopLbZ17s1om5hoPc+kYg1G+n03MS759AlQDpp3vDIzhZ5HmW6nsCTOOuAKzYhdUj7a4RZ4ea6uqJtYwhkI4MRQQabDoMqwTi4Nl4kKBpzXlUHEb/5IDBz7wcsLDQ/LUV5pT4iUjF5ZwVJmHZc01ouDBbAZnny6l10d+bT3uijr1bGaBjItUjWLg1l1eYP2Fwv7f7byrzTwW3fpoFKYJeS4BHHbQChOCK3yXIzuqQIEBaP5"
                ))).build();
        Bucket b = null;
        if (s3.doesBucketExistV2(bucket_name)) {
            System.out.format("Bucket %s already exists.\n", bucket_name);
            b = getBucket(bucket_name);
        } else {
            try {
                b = s3.createBucket(bucket_name);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
        return b;
    }

    public static void main(String[] args) {

        UploadIndex uploadIndex = new UploadIndex();
        uploadIndex.uploadfile();

        final String USAGE = "\n" +
                "CreateBucket - create an S3 bucket\n\n" +
                "Usage: CreateBucket <bucketname>\n\n" +
                "Where:\n" +
                "  bucketname - the name of the bucket to create.\n\n" +
                "The bucket name must be unique, or an error will result.\n";

        String bucket_name = "freezingfire";

        System.out.format("\nCreating S3 bucket: %s\n", bucket_name);
        Bucket b = createBucket(bucket_name);
        if (b == null) {
            System.out.println("Error creating bucket!\n");
        } else {
            System.out.println("Done!\n");
        }
    }
}
