package SurveySystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Essay extends Question{
    private static final long serialVersionUID = 423L;
    private int limit = 9999; //Essay limit.
    public Input input;
    public Output outp;

    public Essay(String prompt) {
        super(prompt);
        this.qType="Essay";
    }

    public Responses getAnswers() throws Exception {
       // boolean accept = true;
        Responses response=null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s ;
        s = br.readLine();

        if (s.length() <= 0) {
            System.out.println("Essay response must be atleast 1 character");
            this.getAnswers();
        }else
        if (s.length() > limit) {
            System.out.println("essay must be 9999 chars or less. ");
            this.getAnswers();
        }else {
            response = new Responses(s, "Essay");
        }
        return response;
    }

    public void setOptions() throws Exception {
    }

    public String getType(){
        return qType;
    }

    public void edit()throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Editing prompt? Enter y or n");
        String x = br.readLine();
        if (x.equalsIgnoreCase("y")) {
            System.out.println("Current prompt:" + this.prompt);
            System.out.println("Enter new prompt: ");
            String prm = br.readLine();
            this.setPrompt(prm);
        }else if(x.equalsIgnoreCase("n")){
            System.out.println("Nothing will be modified.");
        }else{
            System.out.println("Bad option, enter y or n only");
            this.edit();
        }
    }

    public void display() {
        System.out.println(this.prompt);
    }
}
