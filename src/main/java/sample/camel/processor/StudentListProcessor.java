package sample.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import sample.camel.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Satya on 10/28/17.
 */
@Component("studentProcessor")
public class StudentListProcessor implements Processor {
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
}
