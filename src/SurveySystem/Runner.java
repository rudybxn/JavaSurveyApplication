package SurveySystem;

import java.io.*;
import java.util.Vector;


public class Runner implements Serializable {
    public static Survey currentForm; //Survey to store current survey and facilitate saving and creation of new survey in same instance.
    public static Test currentForm2; // Same thing as above but with Test classes.
    public static Input input;
    public static Output io;


    public static void main(String[] args) throws Exception {
        menu(); //Calls the menu driver to create UI
    }

    public static void menu() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose your option:");
        System.out.println("1. Survey");
        System.out.println("2. Test");
        System.out.println("3. Quit");
        String inp;
        try {
            inp = br.readLine();

            int n = Integer.parseInt(inp); //Decipher between survey and test.
            if (n == 1) {
                System.out.println("Survey selected");
                beginSurvey();
            } else if (n == 2) {
                System.out.println("Test selected");
                beginTest();

            } else if (n == 3) {
                System.out.print("Quitting survey system.");
                System.exit(0);
            } else if ((n < 1) || (n > 3)) {
                System.out.println("Please choose between 1 and 3.");
                menu();
            }
        } catch (NumberFormatException e) { //Catches errors if user inputs characters. All other errors are thrown.
            System.out.println("You cannot choose characters.....");
            e.printStackTrace();
            menu();
        }
    }

    public static void beginSurvey() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int menuchoice=0;
       do {
           System.out.println("\n");
           System.out.println("Enter your choice:");
           System.out.println("\n");
           System.out.println("1. Create Survey");
           System.out.println("2. Display Survey");
           System.out.println("3. Load Survey");
           System.out.println("4. Save Survey");
           System.out.println("5. Edit Survey");
           System.out.println("6. Take Survey");
           System.out.println("7. Tabulate Survey");
           System.out.println("8. Quit");
           System.out.println("Select 1-8");
           String x = br.readLine();
           menuchoice = Integer.parseInt(x);
           //Check for faulty inputs
           if (menuchoice > 8) {
               System.out.println("Enter a valid choice please.");
               continue;
           } else if(menuchoice<1){
               System.out.println("Choice cannot be less than 1....");
               continue;
           } else if(menuchoice ==1){
               if (currentForm != null) {
                   System.out.println("You need to save the survey you're working on first.");
               } else {
                   currentForm = new Survey();
                   currentForm.createSurvey();
                   currentForm.displaySurvey();

               }
           }
           else if(menuchoice ==2){
               if(currentForm!=null) {
                   currentForm.displaySurvey();
                   for (int i = 0; i < currentForm.prompts.size(); i++) {
                       System.out.println("Question"+(i+1));
                       currentForm.prompts.get(i).display();
                   }
               }else{
                   System.out.println("You need to load something to display");
                   continue;
               }
           }
           else if(menuchoice==3){
               loadSurvey(currentForm);
           }
           else if(menuchoice==4){
               if(currentForm!=null) {
                   currentForm.saveSurvey();
                   currentForm=null; // facilitates next survey and saving.
               }else{
                   System.out.println("You must create a test first.");
               }
           }
           else if(menuchoice==5){
               System.out.println("You choose to edit.");
               loadSurvey(currentForm);
               currentForm.displaySurvey();
               for (int i = 0; i < currentForm.prompts.size(); i++) {
                   System.out.println("Question"+(i+1));
                   currentForm.prompts.get(i).display();
               }
               System.out.println("Choose question to modify.");
               int ques = Integer.parseInt(br.readLine());
               currentForm.edit(ques);
               System.out.println("Save changes? Enter y or n.");
               String str = br.readLine();
               if(str.equalsIgnoreCase("y")){
                   currentForm.saveSurvey();
               }else if(str.equalsIgnoreCase("n")){
                   System.out.println("Not saved.");
               }else{
                   System.out.println("Invalid option, enter y or n only.");
               }
           }else if(menuchoice ==6){
               System.out.println("Taking Survey. Please load the survey first. ");
               loadSurvey(currentForm);
               Question q;
               Responses r = null;
               Vector<Responses> entry = new Vector<Responses>();
               int i = 0;
               while(i<currentForm.prompts.size()){
                   System.out.println("For question "+(i+1));
                   q=currentForm.prompts.get(i); // Store current question
                   q.display(); //Display the questions
                   r = q.getAnswers(); //Store user responses
                   if(r!=null) { //check if response is empty, disregard if so
                       entry.addElement(r); // add to user response vector
                   }
                   i++;
               }
               currentForm.responses.addElement(entry); //Store user response vector in a vector of all responses for the given survey.
           }
           else if(menuchoice==7){
               System.out.println("Tabulating Survey");
               if(currentForm!=null){
                   currentForm.tabulate();
               }else{
                   System.out.println("Please load a survey first to tabulate.");
               }
           }
           else {
               System.out.println("Qutting survey system.");
               System.exit(0);
           }
       }while(menuchoice!=8); //Run this menu until user does not explicitly want to exit the screen.
    }

    public static void beginTest() throws Exception {
        //Same as beginSurvey() in most aspects. Does the same checks to validate user choice.
        // Does the same checks to validate current test in instance , to decide whether to let user know.
        int choice=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Enter your choice:");
            System.out.println("\n");
            System.out.println("1. Create Test");
            System.out.println("2. Display Test");
            System.out.println("3. Load Test");
            System.out.println("4. Save Test");
            System.out.println("5. Edit Test");
            System.out.println("6. Take Test");
            System.out.println("7. Tabulate Test");
            System.out.println("8. Grade Test");
            System.out.println("9. Exit");
            System.out.println("Select 1-9");
            String x = br.readLine();
            choice = Integer.parseInt(x);
            if (choice > 9) {
                System.out.println("Enter a valid choice please.");
                continue;
            }
            else if(choice < 1){
                System.out.println("Choice cannot be less than 1....");
                continue;
            }
            else if(choice ==1){
                if (currentForm2 != null) {
                    System.out.println("You need to save the survey you're working on first.");
                } else {
                    currentForm2 = new Test();
                    currentForm2.createTest();
                    currentForm2.display();
                }
            }
            else if(choice ==2){
                if(currentForm2!=null) {
                    currentForm2.display();
                    /*for (int i = 0; i < currentForm2.prompts.size(); i++) {
                        System.out.println(currentForm2.prompts.get(i).getPrompt()); //Prints the prompts of the test.
                        Vector<Responses> al = currentForm2.correctAnswerList.get(i);
                        System.out.println("Correct responses are:");
                        for (int j = 0; j < al.size(); j++) {
                            System.out.println("For " + (j + 1) + ": " + al.get(j).response); //Print the accepted answers for the chosen prompt.
                        }
                    }*/
                }else{
                    System.out.println("You need to load something to display");
                    continue;
                }
            }
            else if(choice==3){
                loadTest(currentForm2);
            }
            else if(choice==4){
                if(currentForm2!=null) {
                    currentForm2.saveTest();
                    currentForm2=null; // facilitates next survey and saving.
                }else{
                    System.out.println("You must create a test first.");
                }
            }
            else if(choice==5){
                System.out.println("You choose to edit.");
                loadTest(currentForm2);
                currentForm2.display();
                System.out.println("Choose question to modify.");
                int ques = Integer.parseInt(br.readLine());
                currentForm2.edit(ques);
                System.out.println("Save changes? Enter y or n.");
                String str = br.readLine();
                if(str.equalsIgnoreCase("y")){
                    currentForm2.saveTest();
                }else if(str.equalsIgnoreCase("n")){
                    System.out.println("Not saved.");
                }else{
                    System.out.println("Invalid option, enter y or n only.");
                }
            }else if(choice ==6){
                System.out.println("Taking Test.");
                loadTest(currentForm2);
                Question q=null;
                Responses r = null;
                Vector<Responses> userEntry = new Vector<Responses>();
                int i = 0;
                while(i<currentForm2.prompts.size()){
                    System.out.println("For question "+(i+1));
                    q=currentForm2.prompts.get(i);
                    q.display();
                    r = q.getAnswers();
                    if(r!=null) {
                        userEntry.addElement(r);
                    }
                    i++;
                }
                currentForm2.responses.addElement(userEntry);
                String str="";
                str = currentForm2.grade();
                System.out.println("Points: "+str);
            }
            else if(choice==7){
                System.out.println("Tabulating Test");
                if(currentForm2!=null){
                    currentForm2.tabulate();
                }else{
                    System.out.println("Please load a test first to tabulate.");
                }
            }
            else if(choice==8){
                if(currentForm2!=null){
                    String str="";
                    str = currentForm2.grade();
                    System.out.println("Points: "+str);
                }else{
                    System.out.println("Please load a test first.");
                }
            }
            else if(choice==9){
                System.out.println("Qutting survey system.");
                System.exit(0);
            }
        }while(choice!=9);//Run this menu until user does not explicitly want to exit the screen.
    }

    public static void loadSurvey(Survey s) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String filepath = "Surveys/"; //will be stored in this folder.
        File f = new File(filepath);
        File[] files = f.listFiles(); //create an array of files to show all files in the folder.
        if (files.length == 0) {
            System.out.println("There are no surveys present.");
        } else {
            System.out.println("Please choose survey to load. Enter number.");
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + " " + files[i].getName()); //Show the files.
            }
            int surveynum = Integer.parseInt(br.readLine()); //User choice for file to load.
            try {
                if (surveynum > 0 || surveynum < files.length) {
                    filepath = files[surveynum - 1].getPath(); //Consolidate to index.
                    FileInputStream fis = new FileInputStream(filepath);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    currentForm = (Survey) ois.readObject(); //Read the file into the the current form for the survey, for further displaying, modifying etc. purposes.
                    System.out.println("File loaded.");
                    //close all streams.
                    ois.close();
                    fis.close();
                } else {
                    System.out.println("Out of range. ");
                }
            } catch (Exception e) {
                System.out.println("Error loading.");
            }
        }
    }

    public static void loadTest(Test t) throws Exception {
        String filepath = "Tests/"; //Stored in this folder.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File f = new File(filepath);
        File[] files = f.listFiles();
        if (files.length == 0) {
            System.out.println("There are no tests present.");
        } else {
            System.out.println("Please choose test to load. Enter number.");
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + " " + files[i].getName());
            }
            int surveynum = Integer.parseInt(br.readLine());
            try {
                if (surveynum > 0 || surveynum < files.length) {
                    filepath = files[surveynum - 1].getPath();
                    FileInputStream fis = new FileInputStream(filepath);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    currentForm2 = (Test) ois.readObject(); // Write into test object to make it current test.
                    System.out.println("File loaded.");
                    //close streams.
                    ois.close();
                    fis.close();
                } else {
                    System.out.println("Out of range. ");
                }
            } catch (Exception e) {
                System.out.println("Error loading.");
            }
        }
    }
}




