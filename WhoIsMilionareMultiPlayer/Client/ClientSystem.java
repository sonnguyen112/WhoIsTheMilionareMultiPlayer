package Client;

import Question.Question;
import Server.System;

public class ClientSystem {
    private ClientSystem(){}
    private static final ClientSystem sys = new ClientSystem();

    public static ClientSystem getInstance(){
        return sys;
    }

    public void sendToServer(int answer){

    }

    public void showQuestion(Question ques){
        // UPDATE QUESTION IN QUESTION INTERFACE
    }

    public int getAnswerFromPlayer(){
        // GET ANSWER FROM GAME INTERFACE
        return 0;
    }

    public Question receiveFromServer(){
        //GET CURRENT QUESTION FROM SERVER, IF QUESTION IS NULL, JUST RETURN NULL, THE UPDATE
        //FUNCTION FROM SERVER WILL UPDATE RESULT TO PLAYERS

        return null;
    }

    public boolean getResultUpdateFromServer(){
        //IF PLAYER IS WINNER, SHOW WIN INTERFACE

        //ELSE SHOW LOSE INTERFACE

        return false; //RETURN FALSE IF GAME IS NOT OVER, TRUE IF GAME IS OVER
    }  

    public boolean joinGame(){
        //REMEMBER TO SEND THE INFOR OF PLAYER TO SERVER HERE

        return false; //    RETURN TRUE IF SUCESS, FALSE IF FAIL
    }

    public void playGame(){
        if (this.joinGame()){
            //JOIN GAME SUCCESSFULLY

            //LAUNCH WAITING INTERFACE
        }
        else{
            //CANNOT JOIN THE GAME
        }


        while (true){
            Question ques = this.receiveFromServer();
            this.showQuestion(ques);
            int ans = this.getAnswerFromPlayer();
            this.sendToServer(ans);

            if (this.getResultUpdateFromServer()){
                break;
            }
        }
    }
}
