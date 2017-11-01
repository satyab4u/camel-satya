package sample.camel;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import sample.camel.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Satya on 10/28/17.
 */
//@Component
public class CompletionTestRouter extends RouteBuilder {
    public void configure() throws Exception {

       // onCompletion().to("log:global").log("Global on completion completed....");

        from("timer://TestTimer?repeatCount=1")
                .log("Test Timer started.........")
                .onCompletion()
                 .log("Completion ended with ${body.size} ")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.exit(3);

                    }
                })
                .end()

              /*  .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        List<Student> studentList=new ArrayList<Student>();
                        studentList.add(new Student(1,"sone",10));
                        studentList.add(new Student(2,"stwo",11));
                        studentList.add(new Student(3,"sthree",12));
                        studentList.add(new Student(4,"sfour",13));
                        studentList.add(new Student(5,"sfive",14));
                        studentList.add(new Student(6,"ssix",15));
                        studentList.add(new Student(7,"sseven",16));
                        studentList.add(new Student(8,"seight",17));
//                        studentList.add(new Student(9,"snine",18));

                        exchange.getIn().setBody(studentList);
                    }
                })*/
                .to("bean:studentProcessor?method=process")
                .log("After process method::${body.size}")
                .split(simple("${body}"))
                .parallelProcessing(true)
                .aggregate().constant(true).aggregationStrategy(new ArrayListAggregator()).completionSize(2).completionTimeout(1000L)
                //  completionInterval(1000L)
                .log("Aggregated size::${header.CamelAggregatedSize}")
                .log("Aggregated completed::${header.CamelAggregatedCompletedBy}")
                .to("direct:eachStudentRoute")
                .end()
                .end()

                .log("Test Timer ended...........");

        from("direct:eachStudentRoute").routeId("studentProcessor")
                .log("Student Router started with size:${body.size}... ${body}....................")
                .delay(100)
                .log("Student Router ended with size:${body.size}.... ${body}....................");

    }
}
