package SurveySystem;
import java.util.*;
import java.io.*;

public class Matching extends Question {

    private static final long serialVersionUID = 444L;
    Vector<String> matchingChoice;
    public Input input;
    public Output outp;

    public Matching(String str){
        super(str);
        this.matchingChoice=new Vector<String>();
        this.qType="Matching";
    }

    public void setOptions()throws Exception { //First set of questionchoices
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Specify match options:");
        int choice = Integer.parseInt(br.readLine());
        if (choice < 1) {
            System.out.println("Invalid choice. Quitting.");
            System.exit(0);
        }
        int i = 0;
        while (i < choice) {
            System.out.println("Choice " + (i + 1) + ": ");
            this.questionchoices.addElement(br.readLine());
            i++;
        }
        this.setMatches();
    }


    public void display()throws Exception{
        System.out.println(this.prompt);
        int i=0;int j=0;
        System.out.println("The options:");
        while(i<this.questionchoices.size()){
            System.out.println(this.questionchoices.get(i));
            i++;
        }
        System.out.println("The matches:");
        while(j<this.matchingChoice.size()){
            System.out.println(this.matchingChoice.get(j));
            j++;
        }

    }

    public String getType(){
        return qType;
    }

//Get a response
    public Responses getAnswers()throws Exception{
        Vector<String> paper = new Vector<String>();
        Responses response;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i<this.questionchoices.size(); i++){
            System.out.println("Enter matching choice( Enter integer of matching option from 1 to max) for number: "+i+1);
            int x = Integer.parseInt(br.readLine());
            if(x<1){
                System.out.println("Choice cannot be less than 1");
            }else if(x>this.matchingChoice.size()){
                System.out.println("Invalid, must be less than the number of matching questionchoices");

            }else{
            paper.addElement(Integer.toString(x));}
        }

        StringBuilder sb =new StringBuilder();
        for(String s: paper){
            sb.append(s);
            sb.append(" ");
        }
        String str = sb.toString();
        response = new Responses(str,"Matching"); //Create response type
        return response;
    }

    public void setMatches()throws Exception{ //Second set of questionchoices i.e. matches
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x;
        System.out.println("Number of options for matching:");
        x = Integer.parseInt(br.readLine());
        if (x < this.matchingChoice.size()) {
            System.out.println("Invalid. Number of match options cant be less than questionchoices.");
            this.setMatches();
        }
        for(int j = 0;j<x;j++){
            System.out.println("Match option:"+(j+1));
            this.matchingChoice.addElement(br.readLine());
        }
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
            System.out.println("Editing questionchoices? y or n.");
            String opt = br.readLine();
            if (opt.equalsIgnoreCase("y")) {
                System.out.println("Enter choice to modify: ");
                for(int i = 0; i<this.questionchoices.size(); i++){
                    System.out.println("Choice "+(i+1)+" is "+this.questionchoices.get(i));
                }
                int ch = Integer.parseInt(br.readLine());
                if (!(ch < 1) || !(ch > this.questionchoices.size())) {
                    System.out.println("current choice:"+this.questionchoices.get(ch-1));
                    System.out.println("Enter new choice: ");
                    String c = br.readLine();
                    this.questionchoices.set(ch - 1, c);
                } else {
                    System.out.println("Input out of range.");
                }
            } else if (opt.equalsIgnoreCase("n")) {
                System.out.println("First set of choice won't be modified.");
            } else {
                System.out.println("Invalid choice, enter y or n.");
            }
        } else if (x.equalsIgnoreCase("n")) {
            System.out.println("Prompt will not be modified.");
        } else {
            System.out.println("Invalid choice, please enter y or n.");
            this.edit();
        }

        System.out.println("Do you want to modify matching questionchoices? y or n");
        String str = br.readLine();
        if (str.equalsIgnoreCase("y")) {
            System.out.println("Choose choice to modify: ");
            for(int i=0;i<this.matchingChoice.size();i++){
                System.out.println("Choice "+(i+1)+" is "+this.matchingChoice.get(i));
            }
            int ch2 = Integer.parseInt(br.readLine());
            if (!(ch2 < 1) || !(ch2 > this.matchingChoice.size())) {
                System.out.println("current choice:"+this.matchingChoice.get(ch2-1));
                System.out.println("Enter new choice :");
                String prmt = br.readLine();
                this.matchingChoice.set(ch2 - 1, prmt);
            } else {
                System.out.println("Invalid range.");
            }
        } else if (str.equalsIgnoreCase("n")) {
            System.out.println("Choices won't be modified.");
        } else {
            System.out.println("Please enter y or n only..");
            this.edit();
        }
    }


}
