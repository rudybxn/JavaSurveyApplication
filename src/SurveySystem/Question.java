package SurveySystem;

import java.io.Serializable;
import java.util.*;

public abstract class Question implements Serializable {
    private static final long serialVersionUID = 323L;
    protected String prompt; //Stores the question title.
    protected Vector<String> questionchoices; //Stores a vector of all choices associated with question, if need be.
    public String qType; //Used to ascertain type of question for grading and tabulation purposes.

    public Question(String str){
        this.prompt = str;
        this.questionchoices = new Vector<String>();
        this.qType="";
    }

    public void setPrompt(String x)throws Exception{
        this.prompt =x;
    }

    public String getPrompt()throws Exception{
        return this.prompt;
    }

    public void setType(String type){
        this.qType=type;
    }

    public String getType(){
        return qType;
    }

    public Vector<String> getQuestionchoices(){
        return questionchoices;
    }

    public void display()throws Exception{
        System.out.println(this.prompt);
    }
    public abstract void setOptions()throws Exception;
    public abstract Responses getAnswers()throws Exception;
    public void edit()throws Exception { }
}
