package org.example;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import java.lang.*;
import java.util.Calendar;
import java.util.Date;

public class SQS {
    public static void main(String[] args) {


        String AWS_ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
        String AWS_SECRET_KEY = System.getenv("AWS_SECRET_KEY");
        String AWS_SESSION_TOKEN = System.getenv("AWS_SESSION_TOKEN");
        String queueURL = System.getenv("queueURL");

        String json = "{\n\t";
        String[] combo = {"pasta+pop", "pizza+pop", "garlic bread + pop", "poutine+pop"};
        String[] users = {"sagar", "parth", "ridham", "juhil"};

        BasicSessionCredentials credentials = new BasicSessionCredentials(
                AWS_ACCESS_KEY,
                AWS_SECRET_KEY,
                AWS_SESSION_TOKEN
        );

        double d1 = Math.random() * 5;
        double d2 = Math.random() * 4;
        double d3 = Math.random() * 100;

        int r1 = (int) d1;
        int r2 = (int) d2;
        int order_id = (int) d3;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, (int) d3 + 1);

        json += "\"order_id\": " + order_id + ",";
        json += "\n\t\"combo\": \"" + combo[r1] + "\",";
        json += "\n\t\"users\": \"" + users[r2] + "\",";
        json += "\n\t\"order_time\": \"" + calendar.getTime() + "\"";
        json += "\n}";

        System.out.println(json);

        AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.DEFAULT_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueURL)
                .withMessageBody(json)
                .withDelaySeconds(10);

        SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);
        System.out.println(sendMessageResult);
        System.out.println(sendMessageResult.getMessageId());
        System.out.println("Message Sent");
    }
}
