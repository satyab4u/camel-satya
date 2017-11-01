package sample.camel.aws;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.s3.S3Constants;
import org.springframework.stereotype.Component;

/**
 * Created by Satya on 10/31/17.
 */
//@Component
public class AwsProducer extends RouteBuilder {
    public void configure() throws Exception {

        from("file:///Users/Satya/Documents/code/input?noop=true")
                .routeId("fileReaderRouter")
                .log("Body of the file:${body}")
                .log("Class Name of the file read:: ${body.getClass.getName}")
                .setHeader(S3Constants.KEY,simple("${in.header.CamelFileNameOnly}"))
                .to("aws-s3://satyathecamelguy?amazonS3Client=#amazonS3Client&deleteAfterWrite=false")
                .log("Output of the content: ${body}");

    }
}
