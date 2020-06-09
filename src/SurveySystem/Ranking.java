package SurveySystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class Ranking extends Matching {

    public Ranking(String str){
        super(str);
        this.qType="Ranking";
    }

    public void setOptions()throws Exception{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter ranking choices: ");
        int x = Integer.parseInt(br.readLine());
        if(x>0) {
            for (int i = 0; i < x; i++) {
                System.out.println("Enter Choice " + (i + 1));
                this.questionchoices.add(br.readLine());
            }

            System.out.println("Choices have been set!");
        }
    }

    public void display()throws Exception{
        System.out.println("Choices are:");
        int length = this.questionchoices.size();int i=0;

        while(i<length){
            System.out.println(this.questionchoices.get(i));
            i++;
        }
    }

    public void edit()throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Editing prompt?Enter y or n");
        String s = br.readLine();
        if (s.equalsIgnoreCase("y")) {
            System.out.println("Current prompt:" + this.prompt);
            System.out.println("Enter new prompt: ");
            String prm = br.readLine();
            this.setPrompt(prm);
        } else if (s.equalsIgnoreCase("n")) {
            System.out.println("Not modifying question prompt.");
        } else {
            System.out.println("Invalid input.");
            this.edit();
        }

        System.out.println("Editing questionchoices? Enter y or n.");
        String ch = br.readLine();
        if (ch.equalsIgnoreCase("y")) {
            System.out.println("Enter which choice to modify: ");
            for (int i = 0; i < this.questionchoices.size(); i++) {
                System.out.println("Choice " + (i + 1) + "is" + this.questionchoices.get(i));
            }
            int c = Integer.parseInt(br.readLine());
            if (!(c < 1) || !(c > this.questionchoices.size())) {
                System.out.println("current choice:" + this.questionchoices.get(c - 1));
                System.out.println("Enter new choice: ");
                String str = br.readLine();
                this.questionchoices.set(c - 1, str);
            } else {
                System.out.println("Input out of range.");
            }
        } else if (ch.equalsIgnoreCase("n")) {
            System.out.println("Choices won't be modified.");
        }else{
            System.out.println("Invalid input.");
            this.edit();
        }
    }

    public String getType(){
        return qType;
    }

    public Responses getAnswers()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Vector<String> response=new Vector<String>();
        StringBuilder sb =new StringBuilder();
        Responses answers;
        System.out.println("Enter response:");
        for(int i = 0; i<this.questionchoices.size(); i++){
            System.out.println("Enter ranking for "+this.questionchoices.get(i));
            int x = Integer.parseInt(br.readLine());
            if(x<1){
                System.out.println("Rank cannot be less than 1.");
            }
            else if(x>this.questionchoices.size()){
                System.out.println("Rank cannot be greater than number of options!");
            }else{
                String str = Integer.toString(x);
                response.addElement(str);
            }
        }

        for(String s: response){
            sb.append(s);
            sb.append(" ");
        }
        String str = sb.toString();
        answers = new Responses(str,"Ranking"); //Create response with type ranking
        return answers;
    }
}
