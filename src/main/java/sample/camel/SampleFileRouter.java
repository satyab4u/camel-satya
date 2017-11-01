package sample.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Satya on 10/28/17.
 */
//@Component
public class SampleFileRouter extends RouteBuilder {

    public void configure() throws Exception {

        from("file:///Users/Satya/Documents/code/input?noop=true")
                .routeId("fileReaderRouter")
                .log("Body of the file:${body}")
                .log("Class Name of the file read:: ${body.getClass.getName}")
                .split().tokenize("\n",2)
                .log("Inside the split the body:${body}")
                .to("file:///Users/Satya/Documents/code/output?fileExist=append")
                .end()
                .log("Output of the content: ${body}");

                /*end();*/
    }
}
