package SurveySystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Emoji extends MCQ{
    private static final long serialVersionUID = 456L;
    public Input input;
    public Output outp;

    public Emoji(String str){
        super(str); this.qType="Emoji";
    }

    @Override
    public void setOptions() throws Exception {
        this.questionchoices.addElement("Smile");
        this.questionchoices.addElement("Frown");
        this.questionchoices.addElement("Angry");
        this.questionchoices.addElement("Surprised");
        this.questionchoices.addElement("Sad");
    }

    public void displayChoice()throws Exception{
        for(int i = 0; i<this.questionchoices.size(); i++){
            System.out.println(this.questionchoices.get(i));
        }
    }

    public String getType(){
        return qType;
    }

    public Responses getAnswers() throws Exception{
        Responses response=null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("The questionchoices are pre determined.");
        System.out.println("Choose 1 for smile, 2 for frown, 3 for angry, 4 for surprised, 5 for sad");
        System.out.println("Enter correct choice number.");
        int ch = Integer.parseInt(br.readLine());
        if(ch<1 || ch >5){
            System.out.println("Not in range");
           this.getAnswers();
        }else {
            response = new Responses(Integer.toString((ch)), "Emoji"); //create appropriate response of type emoji.
        }
        return response;
    }

    public void edit()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //cannot modify choices in emoji.
        System.out.println("Editing prompt? Enter y or n");
        String x = br.readLine();
        if (x.equalsIgnoreCase("y")) {
            System.out.println("Current prompt:" + this.prompt);
            System.out.println("Enter new prompt: ");
            String prm = br.readLine();
            this.setPrompt(prm);
        }else if(x.equalsIgnoreCase("n")){
            System.out.println("Nothing will be changed");
        }else{
            System.out.println("Invalid choice, enter y or n only.");
            this.edit();
        }
    }


}


