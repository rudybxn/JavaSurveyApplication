package SurveySystem;
import java.io.*;
import java.util.*;

public class Test extends Survey {
    protected Vector<Vector<Responses>> correctAnswerList; //To store all correct responses.
    private static final long serialVersionUID = 4L;
    public Input input;
    public Output outp;

    public Test() {
        this.correctAnswerList = new Vector<Vector<Responses>>();
        surveyName="";
    }

    public void display() throws Exception {
        System.out.println("Name: " + surveyName);
        for (int i = 0; i < this.prompts.size(); i++) {
            System.out.println(this.prompts.get(i).getPrompt());
            System.out.println("Correct responses are:");
            for (int j = 0; j < this.correctAnswerList.get(i).size(); j++) {
                if(!(this.correctAnswerList.get(i).get(j).getType()=="Essay")) { //Since essays do not have correct answers.
                    System.out.println("For " + (j + 1) + ": " + this.correctAnswerList.get(i).get(j).response);
                }
            }
        }
    }

    public Test createTest() throws Exception {
        int num = 0;
        int i = 0;
        // An arraylist to hold all the correct response for this test must be created.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a name for your test!");
        String n = br.readLine();
        if (n.length() != 0) {
            this.setName(n);
        } else {
            System.out.println("Test name cannot be blank.");
            createTest();
        }

        System.out.println("How many questions will your test have?");
        String x = br.readLine();
        num = Integer.parseInt(x);

        if(num<=0){
            System.out.println("Test cannot have 0 questions.Try again.");
            this.createTest();
        }else{
        while (i < num) {
            Question q = createQuestion();
            this.addPrompts(q);
            System.out.println("Enter the number of correct responses for the question.");
            int input = Integer.parseInt(br.readLine());
            Vector<Responses> correct = new Vector<Responses>();
            for (int j = 0; j < input; j++) { //create answer list
                System.out.println("Correct response " + (j + 1) + " :");

                correct.addElement(q.getAnswers()); //This holds all the correct answers for the given test, which will be added into the vector of all correct answers.

            }
            this.correctAnswerList.addElement(correct); //Add correct answers into the vector correct answer.
            i++;
        }
        }

        return this;
    }

    public void addPrompts(Question q) {
        this.prompts.addElement(q);
    }

    public int options() throws Exception {
        System.out.println("Select your question type.");
        System.out.println("1) Add new TF");
        System.out.println("2) Add new Multiple Choice");
        System.out.println("3) Add new Emoji");
        System.out.println("4) Add new Essay");
        System.out.println("5) Add new ShortAnswer");
        System.out.println("6) Add new Matching");
        System.out.println("7) Add new Ranking");
        System.out.println("8) Return to Test Menu");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        choice = br.readLine();
        int num = Integer.parseInt(choice);
        if (num >= 1 || num <= 8) {
            return num;

        } else {
            System.out.println("Invalid choice.Please select from 1-7.");
            options();
        }
        return 0;
    }

    public Question createQuestion() throws Exception {
        Question ques = null;
        int num = options();
        if (num == 0) {
            System.out.println("Enter valid option.");
            options();
        }
        System.out.println("Enter prompt.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String prompt = br.readLine();

        if (num == 1) {
            System.out.println("You chose True or False.");
            ques = new TF(prompt);
            ques.setPrompt(prompt);
            ques.setOptions();
        } else if (num == 2) {
            System.out.println("You chose MCQ.");
            ques = new MCQ(prompt);
            ques.setPrompt(prompt);
            ques.setOptions();
        } else if (num == 3) {
            System.out.println("You chose Emoji.");
            ques = new Emoji(prompt);
            ques.setPrompt(prompt);
            ques.setOptions();
        } else if (num == 4) {
            System.out.println("You chose Essay.");
            ques = new Essay(prompt);
            ques.setPrompt(prompt);
            ques.setOptions();
        } else if (num == 5) {
            System.out.println("You chose Short.");
            ques = new Short(prompt);
            ques.setPrompt(prompt);
            ques.setOptions();
        } else if (num == 6) {
            System.out.println("You chose Matching.");
            ques = new Matching(prompt);
            ques.setPrompt(prompt);
            ques.setOptions();
        } else if (num == 7) {
            System.out.println("You choose Ranking.");
            ques = new Ranking(prompt);
            ques.setPrompt(prompt);
            ques.setOptions();
        } else if (num == 8) {
            this.createTest();
        }
        return ques;
    }


    public void saveTest() throws Exception {
        File f = new File("./Tests/");
        if (!f.exists()) { //Check for directory.
            f.mkdir();
        }
        String path = "Tests/" + this.surveyName + ".ser";
        ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(path));
        ois.writeObject(this); //Write into object.
        System.out.println("Saved Successfully.");
        //close stream.
        ois.close();
    }

    public void tabulate() throws Exception { //Search through each question and ascertain what type they are. Different types are tabulated accordingly.
        ArrayList<Responses> resp;
        Set<Responses>all;

        for (int i = 0; i < this.prompts.size(); i++) {
            int t = 0, f = 0;
            System.out.println("Question :" + (i + 1));
            this.prompts.get(i).display();
            System.out.println("Recorded responses- ");
            resp = new ArrayList<Responses>();
            for (int j = 0; j < this.responses.size(); j++) {
                resp.add(this.responses.get(j).get(i));
            }
            all = new HashSet<>(resp); //Using set to store all responses. this will allow usage of frequency of responses.
            if (this.prompts.get(i).getType() == "TF") {
                f = 0;
                t = 0;
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
            }
            if (this.prompts.get(i).getType() == "Essay") {
                System.out.println("");
                for (Responses r : all) {
                    if (r.getType() == "Essay") { //Type of response is ascertained from vector to get correct response collection.
                        System.out.println(r.response);
                    }
                }
            }
            if (this.prompts.get(i).getType() == "Short") {
                System.out.println("");
                for (Responses r : all) {
                    if (r.type == "Short") {//Type of response is ascertained from vector to get correct response collection.
                        System.out.println(r.response);
                    }
                }
            } else if (this.prompts.get(i).getType() == "Matching") {
                for (Responses r : all) {
                    System.out.println(r.response + " |");
                }
            } else if (this.prompts.get(i).getType() == "Emoji") {
                for (Responses r : all) {
                    if (r.response == "1") {
                        System.out.println("Smile :" + Collections.frequency(resp, r));
                    } else if (r.response == "2") {
                        System.out.println("Frown :" + Collections.frequency(resp, r));
                    } else if (r.response == "3") {
                        System.out.println("Angry :" + Collections.frequency(resp, r));
                    } else if (r.response == "4") {
                        System.out.println("Surprised :" + Collections.frequency(resp, r));
                    } else if (r.response == "5") {
                        System.out.println("Sad :" + Collections.frequency(resp, r));
                    }
                    System.out.println(r.response + ":" + Collections.frequency(resp, r));
                }
            } else if (this.prompts.get(i).getType() == "MCQ") {
                System.out.println("");
                for (Responses r : all) {
                    if (r.type == "MCQ") {
                        System.out.println(r.response);
                    }
                    System.out.println(r.response + ":" + Collections.frequency(resp, r));
                }
            } else if (this.prompts.get(i).getType() == "Ranking") {
                System.out.println(" ");
                for (Responses r : all) {
                    if (r.type == "Ranking") {
                        System.out.println(r.response);
                    }
                }
            } else { //If types are not specified, or there is error with setting them, default to prinitng frequency of required response from given test.
                for (Responses r : all) {
                    System.out.println(r.response + ":" + Collections.frequency(resp, r));
                }
            }
        }

    }

    public String grade() throws Exception {
        int totalquestions = this.responses.size(), essay = 0, right = 0, full;
        System.out.println("Grading..");
        for (int i = 0; i < totalquestions; i++) {
            for (int j = 0; j < this.responses.size(); j++) {
                System.out.println("For response " + (j + 1));
                if(!(this.responses.get(i).get(j).equals(this.correctAnswerList.get(j).get(i)))) { //equate user response with correct response from list
                    System.out.println("Incorrect ");
                } else if (this.responses.get(i).get(j).getType() == "Essay") {  //Check type of response. if essay, disregard.
                    System.out.println("Essay question recorded.");
                } else {
                    right++;//increment iff correct.
                }
            }
        }
        for (int i = 0; i < this.prompts.size(); i++) {
            if (this.prompts.get(i).getType() == ("Essay")) { //check number of essay questions.
                essay++;
            }
        }
        full = (totalquestions - essay) * 10; //total genuine questions
        String a = Integer.toString(full);
        String b = Integer.toString(right*10); //total right answers
        return b+" out of "+a;
    }

}


