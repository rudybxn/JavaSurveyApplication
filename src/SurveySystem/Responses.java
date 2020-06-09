package SurveySystem;

import java.io.Serializable;
import java.util.Objects;

public class Responses implements Serializable {
    private static final long serialVersionUID = 5L;
    public String response;
    public String type;

    public Responses(String response,String type) {
        this.response = response;
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public String getResponse(){
        return this.response;
    }

    public void setType(String type){
        this.type=type;
    }

    public void setResponse(String response){
        this.response=response;
    }


    @Override
    public boolean equals(Object o){// Check response string and type string of responses. Return false if not same, else true.
        Responses resp = (Responses) o;
        if(o==this){
            return true;
        }
        if(o==null){ //check for edge case of no response.
            return false;
        }
        if(this.response ==null && (resp.response!=null)){
            return false;
        }else if(!this.type.equals(resp.type)){
            return false;
        }else if(!this.response.equals(resp.response)){
            return false;
        }
        return true; // return true, if type and response are not dissimilar.
    }

    //Must override hascode if equals() is overriden, according to Java Object Contract.
    public int hashcode(){
        int code =20;
        code = 51*response.hashCode();
        code=51*type.hashCode();
        return code;
    }

    public void display() {
        System.out.println(this.response);
    }
}

