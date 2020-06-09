package SurveySystem;
import java.io.*;

public class Input extends AbstractIO{
    BufferedReader br;

    public Input(){ this.br = new BufferedReader(new InputStreamReader(System.in));
    }
    public String getFromUser()throws IOException{
        String x =this.br.readLine();
        return x;
    }
    public void display(String msg){
        System.out.println(msg);
    }
}
