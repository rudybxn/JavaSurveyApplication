package SurveySystem;

public abstract class AbstractIO {

    public AbstractIO(){}
    public abstract String getFromUser()throws Exception;
    public abstract void display(String s);

}
