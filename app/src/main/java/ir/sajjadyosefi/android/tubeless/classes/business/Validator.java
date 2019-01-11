package ir.sajjadyosefi.android.tubeless.classes.business;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Pattern;

import ir.sajjadyosefi.android.tubeless.R;

/**
 * Created by sajjad on 9/28/2018.
 */

public class Validator {

    Context context;

    public Validator(Context context) {
        this.context = context;

    }

    public boolean isValidMobile(String phone) {
        boolean isValid = false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() != 11 || (!phone.substring(0,2).equals("09"))) {
                isValid = false;
            } else {
                isValid = true;
            }
        } else {
            isValid=false;
        }
        return isValid;
    }
}
