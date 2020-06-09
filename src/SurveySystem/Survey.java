package SurveySystem;

import java.beans.Transient;
import java.io.*;
import java.util.*;


public class Survey implements Serializable {
    private static final long serialVersionUID = 4L;
    protected String surveyName;
    protected Vector<Question> prompts;
    protected Vector<Vector<Responses>> responses; //A vector of vector of responses to store all responses for the given survey.
    private transient ObjectOutputStream os;

    public Input input;
    public Output outp;


    public Survey() {
        this.prompts = new Vector<Question>();
        this.responses = new Vector<Vector<Responses>>();
        this.surveyName = "";
    }

    public void setName(String name) {
        this.surveyName = name;
    }

    public String getName() {
        return this.surveyName;
    }

    public Survey createSurvey() throws Exception {
        int num = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a name for your survey!");
        String n = br.readLine();
        if (n.length() == 0) { //Check name length.
            System.out.println("Survey name cannot be blank.");
            this.createSurvey();
        } else {
            this.setName(n);
        }

        System.out.println("How many questions will your survey have?");
        String x = br.readLine();
        try {
            num = Integer.parseInt(x);
        } catch (Exception e) {
            System.out.println("Error with input. Must only be a number.");
        }
        if (num <= 0) {
            System.out.println("CSurvey cannot have less than 0 question. Try again.");
        } else {
            int i = 0;
            while (i < num) { //Create questions until number lasts.
                Question q = createQuestion();
                this.addPrompts(q);
                i++;
            }
        }
            return this;

    }

    public void edit(int num)throws Exception{
        System.out.println("Question to modify is "+num);
        this.prompts.get(num-1).edit();  //Send it to concerned question type for editing.
    }

    public void addPrompts(Question q) {
        this.prompts.addElement(q);
    } //Adds questions to Vector.

    public int options() throws Exception {
        System.out.println("Select your question type.");
        System.out.println("1) Add new TF");
        System.out.println("2) Add new Multiple Choice");
        System.out.println("3) Add new Emoji");
        System.out.println("4) Add new Essay");
        System.out.println("5) Add new ShortAnswer");
        System.out.println("6) Add new Matching");
        System.out.println("7) Add new Ranking");
        System.out.println("8) Return to Survey Menu");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        choice = br.readLine();
        int num = Integer.parseInt(choice);
        if (num >= 1 || num <= 8) { //Validate choice range.
            return num;
            // createQuestion(num);
        } else {
            System.out.println("Invalid choice.Please select from 1-7.");
            this.options();
        }
        return 0;
    }

    public Question createQuestion() throws Exception {
        Question ques = null;
        int num = options();
        if (num == 0) {
            System.out.println("Enter valid option.");
           this.options();
        }
        System.out.println("Enter prompt.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String prompt = br.readLine();

        switch (num) {
            case 1:
                System.out.println("You chose True or False.");
                ques = new TF(prompt);
                ques.setPrompt(prompt);
                ques.setOptions();
                break;
            case 2:
                System.out.println("You chose Multiple Choice.");
                ques = new MCQ(prompt);
                ques.setPrompt(prompt);
                ques.setOptions();
                break;
            case 3:
                System.out.println("You chose Emoji.");
                ques = new Emoji(prompt);
                ques.setPrompt(prompt);
                ques.setOptions();
                break;
            case 4:
                System.out.println("You chose Essay.");
                ques = new Essay(prompt);
                ques.setPrompt(prompt);
                ques.setOptions();
                break;
            case 5:
                System.out.println("You chose Short Answer.");
                ques = new Short(prompt);
                ques.setPrompt(prompt);
                ques.setOptions();
                break;
            case 6:
                System.out.println("You chose Matching.");
                ques = new Matching(prompt);
                ques.setPrompt(prompt);
                ques.setOptions();
                break;
            case 7:
                System.out.println("You chose Ranking.");
                ques=new Ranking(prompt);
                ques.setPrompt(prompt);
                ques.setOptions();
                break;
            case 8:
                this.createSurvey();
                break;
        }
        return ques;
    }

    public void displaySurvey() throws Exception {
        System.out.println("Name: " + this.surveyName);
        System.out.println("Number of questions: " + this.prompts.size());
    }

    public void saveSurvey() throws Exception {
        File f = new File("./Surveys/"); //Will always be the folder name.
        if (!f.exists()) {
            f.mkdir();
        }
        String path = "./Surveys/" + this.surveyName + ".ser";
        os = new ObjectOutputStream(new FileOutputStream(path));
        os.writeObject(this); //Write the survey into the stream.
        System.out.println("Saved Successfully.");
        //close stream.
        os.close();
    }


    public void tabulate()throws Exception {
        ArrayList<Responses> resp;
        Set<Responses>all;

        for (int i = 0; i < this.prompts.size(); i++) {
            int t = 0, f = 0;
            System.out.println("Question " + (i + 1));
            this.prompts.get(i).display();
            System.out.println("Recorded responses- ");
            resp = new ArrayList<Responses>();
            for (int j = 0; j < this.responses.size(); j++) {
                resp.add(this.responses.get(j).get(i));
            }
            all = new HashSet<>(resp);
            if (this.prompts.get(i).getType() == "TF") {
                for (Responses r : all) {
                    if (r.type == "TF") {
                        if (r.response.equalsIgnoreCase("t")) {
                            t++;
                        } else if (r.response.equalsIgnoreCase("f")) {
                            f++;
                        }
                    }

                }
                System.out.println("T: " + t);
                System.out.println("F: " + f);
            }else if (this.prompts.get(i).getType()=="Essay") {
                System.out.println("");
                for (Responses r : all) {
                    if (r.type == "Essay") {
                        System.out.println(r.response);
                    }
                    System.out.println(r.response+ ":"+ Collections.frequency(resp,r));
                }
            }else if (this.prompts.get(i).getType()=="Short") {
                System.out.println();
                for (Responses r : all) {
                    if (r.type == "Short") {
                        System.out.println(r.response);
                    }
                    System.out.println(r.response+ ":"+ Collections.frequency(resp,r));
                }
            }else if(this.prompts.get(i).getType()=="Matching"){
                for(Responses r: all){
                    System.out.println(r.response+" | ");
                }
            }else if(this.prompts.get(i).getType()== "Emoji"){
                for(Responses r: all){
                    if(r.response=="1") {
                        System.out.println("Smile :" + Collections.frequency(resp, r));
                    }else if(r.response=="2"){
                        System.out.println("Frown :" + Collections.frequency(resp, r));
                    }else if(r.response=="3"){
                        System.out.println("Angry :" + Collections.frequency(resp, r));
                    }
                    else if(r.response=="4"){
                        System.out.println("Surprised :" + Collections.frequency(resp, r));
                    }else if(r.response=="5"){
                        System.out.println("Sad :" + Collections.frequency(resp, r));
                    }else
                    System.out.println(r.response+ ":"+ Collections.frequency(resp,r));
                }
            }else if(this.prompts.get(i).getType()=="MCQ"){
                System.out.println("");
                for(Responses r: all){
                    if(r.type=="MCQ"){
                        System.out.println(r.response);
                    }
                }
            }else if(this.prompts.get(i).getType()=="Ranking"){
                System.out.println("");
                for(Responses r:all){
                    if(r.type=="Ranking"){
                        System.out.println(r.response);
                    }
                }
            }
            else {
                for (Responses r : all) {
                    System.out.println(r.response + ":" + Collections.frequency(resp, r));
                }
            }
        }
    }
}
