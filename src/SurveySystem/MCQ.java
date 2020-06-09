package SurveySystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MCQ extends Question{
    private static final long serialVersionUID = 4L;
    public MCQ(String prompt){

        super(prompt);
        this.qType="MCQ";
    }


    public void setOptions()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;int i=0;
        System.out.println(" enter the number of questionchoices");
        choice = Integer.parseInt(br.readLine());

        if(choice<=1){
            System.out.println("Invalid input, please enter more than 1 choice");
            this.setOptions();
        }else {
            while(i<choice){
                System.out.println("Choice " + (i + 1));
                this.questionchoices.addElement(br.readLine());
                i++;
            }

        }
    }

    public String getType(){
        return qType;
    }

    public void display(){
        System.out.println(this.prompt);
        for(int i = 0; i<this.questionchoices.size(); i++){
            System.out.println(this.questionchoices.get(i));
        }
    }

    public Responses getAnswers() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Responses response =null;
        System.out.println("Enter correct choice number.");
        int ch = Integer.parseInt(br.readLine());
        if(ch<1 || ch >this.questionchoices.size()){
            System.out.println("Not in range");
            this.getAnswers();
        }else {
            String str = this.questionchoices.get(ch - 1);
            response = new Responses(str, "MCQ"); //Create apporpriate response type
        }
        return response;
    }

    public void edit()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Editing prompt?Enter y or n");
        String x = br.readLine();
        if(x.equalsIgnoreCase("y")){
            System.out.println("Current prompt:"+this.prompt);
            System.out.println("Enter new prompt: ");
            String prm = br.readLine();
            this.setPrompt(prm);
            System.out.println("Editing options?Enter y or n");
            String y = br.readLine();
            if(y.equalsIgnoreCase("y")){
                System.out.println("Which choice to modify?");
                int ch = Integer.parseInt(br.readLine());
                if(!(ch<1) || !(ch>this.questionchoices.size())) {
                    System.out.println("Enter new choice: ");
                    String inp = br.readLine();
                    this.questionchoices.set(ch - 1, inp);
                }else{
                    System.out.println("Bad choice, out of range.");
                }
            }else if(y.equalsIgnoreCase("n")){
                System.out.println("No questionchoices modified.");
            }else{
                System.out.println("Please enter y or n.");
            }
        }else if(x.equalsIgnoreCase("n")){
            System.out.println("Nothing will be modified.");
        }else{
            System.out.println("Bad choice, enter y or n only.");
            this.edit();
        }
    }


}
