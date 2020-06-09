package SurveySystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Short extends Essay{
    private int limit = 90;
    private static final long serialVersionUID = 412L;

    public Short(String s){
        super(s);
        this.qType="Short";
    }

    public Responses getAnswers() throws Exception
    {
           // boolean accept = true;
        Responses response = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s ;
            s = br.readLine();
            if (s.length() <= 0) {
                System.out.println("Short response must be atleast 1 character");
                this.getAnswers();
            }
            if (s.length() > limit) {
                System.out.println("short must be 75 chars or less. ");
                this.getAnswers();
            }
            else { response = new Responses(s, "Short");
            }
            return response;
    }

    public String getType(){
        return qType;
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
            System.out.println("Invalid choice, enter y or n only.");
            this.edit();
        }
    }

}
