import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;



import java.io.File;


public class UploadIndex {
//    https://www.codejava.net/aws/upload-file-to-s3-java-console
    public void uploadfile(){
        CreateBucket createBucket = new CreateBucket();

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.DEFAULT_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials(
                        "ASIAS44XD3BR76XC7G4Q",
                        "LsNDVqZeLo07YX4oNdzMFdPvniluKStSO5AcPjwF",
                        "FwoGZXIvYXdzED4aDEZJW3l1fZTHZWy2BSLAARz5s62574lTWroOjeeHpywYRmoGRcRp6PLPWLSIxG8MKX+df+yaY+SapQKNtzg8a8ZeIqyLL8jyGRC4c34ZaKlx7EopLbZ17s1om5hoPc+kYg1G+n03MS759AlQDpp3vDIzhZ5HmW6nsCTOOuAKzYhdUj7a4RZ4ea6uqJtYwhkI4MRQQabDoMqwTi4Nl4kKBpzXlUHEb/5IDBz7wcsLDQ/LUV5pT4iUjF5ZwVJmHZc01ouDBbAZnny6l10d+bT3uijr1bGaBjItUjWLg1l1eYP2Fwv7f7byrzTwW3fpoFKYJeS4BHHbQChOCK3yXIzuqQIEBaP5"
                ))).build();

        try{
            System.out.println("File is being uploaded");
            s3.putObject("freezingfire", "index.html", new File("D:\\Sagar\\SEM-2 (SEP)\\Serverless Data Processing\\Assignment 1\\CodePart2\\src\\main\\java\\index.html"));

        } catch(AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }

    }


}





