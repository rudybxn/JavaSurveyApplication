package SurveySystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TF extends MCQ {
    private static final long serialVersionUID = 423L;
    public TF(String str){
        super(str);
        this.qType="TF";
        this.questionchoices.addElement("True");
        this.questionchoices.addElement("False");
    }

    public String getType(){
        return this.qType;
    }

    public Responses getAnswers()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inp = null;
        Responses response=null;
        System.out.println("t for true, f for false");
        inp = br.readLine();
        if (!inp.equals("t") && !inp.equals("f")) {
            System.out.println("Enter 'true' or 'false' only.");
           this.getAnswers();
        }else { response = new Responses(inp, "TF");
        }
        return response;
    }

    public void display(){
        System.out.println(this.prompt);
        for(String x: this.questionchoices){
            System.out.println(x);
        }
    }


    public void edit()throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Editing prompt?Enter y or n");
        String x = br.readLine();
        if (x.equalsIgnoreCase("y")) {
            System.out.println("Current prompt:" + this.prompt);
            System.out.println("Enter new prompt: ");
            String prm = br.readLine();
            this.setPrompt(prm);
        } else if (x.equalsIgnoreCase("n")) {
            System.out.println("Will not be modified.");
        } else {
            System.out.println("Invalid choice, enter y or n only");
            this.edit();
        }
    }
}
