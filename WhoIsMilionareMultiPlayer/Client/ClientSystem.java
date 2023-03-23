package Client;

import Question.Question;
import Server.System;

public class ClientSystem {
    private ClientSystem(){}
    private static final ClientSystem sys = new ClientSystem();

    public static ClientSystem getInstance(){
        return sys;
    }

    public void sendToServer(){

    }

    public Question receiveFromServer(){
        return null;
    }
}
