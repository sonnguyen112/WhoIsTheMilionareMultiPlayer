package Server;

import Question.Question;

public class System {
    private System(){}
    private static final System sys = new System();

    public static System getInstance(){
        return sys;
    }

    private Question currentQuestion = new Question();
    private int currentPlayer = 0;

    public void GetQuestion(){
        //GET NEW QUESTION FROM DATABASE FUNCTION, UPDATE CURRENT QUESTION

    }

    public boolean CheckAnswer(int ans){
        //CHECK ANSWER OF CURRENT QUESTION
        return true;
    }

    public int UpdateInfo(boolean correctAns){
        //UPDATE INFORMATION TO 4 PLAYER: send PlayerList to 4 players

        return -1;
    }

    public void sendQuestion(){
        //SEND QUESTION TO PLAYER WITH INDEX = N

    }

    public int getAnswer(){
        //GET ANSWER FROM PLAYER: RETURN TRUE(IF ANSWER IS CORRECT) OR FALSE(IF ANSWER IS INCORRECT)
        
        //WAIT FOR PLAYER'S ANSWER
        return 0;
    }

    public void waitForPlayer(){
        //  SYSTEM WAIT FOR PLAYERS, IF GAME BEGIN, THIS FUNCTION WILL RETURN
        //  REMEMBER TO UPDATE PLAYER LIST
    }

    public void play(){
        while(true){
            this.GetQuestion();
            this.sendQuestion();
            int answer = this.getAnswer();
            boolean correctAns = this.CheckAnswer(answer);
            int endgame = this.UpdateInfo(correctAns);

            if (endgame != -1){
                //  GAME IS OVER, SERVER GOES OFF, ALL PLAYERS AFTER UPDATE INFO WILL SHOW RESULT SCREEN
                break;
            }
        }
    }
}
