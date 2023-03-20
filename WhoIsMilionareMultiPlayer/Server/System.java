package System;

import Question.Question;

public class System {
    private System(){}
    private static final System sys = new System();

    public static System getInstance(){
        return sys;
    }

    private Question currentQuestion = new Question();
    private int currentPlayer;

    public void GetQuestion(){
        //GET NEW QUESTION FROM DATABASE FUNCTION, UPDATE CURRENT QUESTION

    }

    public boolean CheckAnswer(int ans){
        //CHECK ANSWER OF CURRENT QUESTION
        return true;
    }

    public void UpdateInfo(){
        //UPDATE INFORMATION TO 4 PLAYER: send PlayerList to 4 players

    }

    public void sendQuestion(){
        //SEND QUESTION TO PLAYER WITH INDEX = N

    }

    public int getAnswer(){
        //GET ANSWER FROM PLAYER: RETURN TRUE(IF ANSWER IS CORRECT) OR FALSE(IF ANSWER IS INCORRECT)
        
        //WAIT FOR PLAYER'S ANSWER
        return 0;
    }
}
