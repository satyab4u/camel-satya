package sample.camel.aws;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Satya on 10/31/17.
 */
@Component
public class AwsConsumer extends RouteBuilder {

    public void configure() throws Exception {

        from("aws-s3://satyathecamelguy?amazonS3Client=#amazonS3Client&deleteAfterRead=false")
                .log("S3 file content::::${body}")
                .log("S3 file class name::${body.getClass.getName}");
    }
}
