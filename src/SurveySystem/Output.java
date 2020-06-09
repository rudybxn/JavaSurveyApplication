package SurveySystem;
import java.io.*;
import java.nio.Buffer;

public class Output extends AbstractIO {

    public Output(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getFromUser() throws Exception {
        return null;
    }

    public void display(String msg) {
        System.out.println(msg);
    }
}

