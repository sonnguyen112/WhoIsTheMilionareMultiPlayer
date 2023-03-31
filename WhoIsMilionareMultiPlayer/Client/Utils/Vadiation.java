package Client.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vadiation {
    public static Boolean checkNickName(String name){

        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[_])[a-zA-Z0-9_]{1,10}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(name);

        if (m.find()){
            return true;
        }
        else {
            return false;
        }

    }
}
