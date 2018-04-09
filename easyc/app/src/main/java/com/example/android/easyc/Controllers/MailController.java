package com.example.android.easyc.Controllers;

import com.example.android.easyc.Connections.MailSender;
import com.example.android.easyc.Interfaces.OnTaskListeners;

import java.sql.ResultSet;

/**
 * Created by KhALeD SaBrY on 22-Mar-18.
 */

public class MailController extends Controller {
    private MailSender sender;

    public MailController() {
        sender = new MailSender();
    }


    //get the company email
    public String getCompanyEmail() {
        return sender.getCompanyEmail();
    }


    //message if you forgot your password
    public void forgetUserNameOrPassword(final String email, final OnTaskListeners.Word listener) {

        databaseAdapter().selectUserUsernamePassword(email, new OnTaskListeners.Result() {
            @Override
            public void onSuccess(ResultSet data) {
                if (!checkIfFound(data))
                    return;
                String username = (String) resultToValue(data, 1);
                String password = (String) resultToValue(data, 2);

                sender.setRecipientEmail(email);
                sender.setSubject("Retore your username/password");
                sender.setBody("thanks for using our app we are hoping that you have a great time while using our app \n be careful next time you forget your password or username \n \n username :" + username + "\n password : " + password);
                sender.sendEmail(listener);

            }
        });

    }

    // when you sign up this message sent for you
    public void sendWelcomeMessage(String email) {
        sender.setRecipientEmail(email);
        sender.setSubject("Welcome to our app");
        sender.setBody("this app cost is 200.000$");
        sender.sendEmail(new OnTaskListeners.Word() {
            @Override
            public void onSuccess(String result) {

            }
        });
    }

}