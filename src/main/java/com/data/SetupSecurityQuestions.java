package com.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetupSecurityQuestions {
    private List<String> securityQuestions = new ArrayList<>();

    public SetupSecurityQuestions() {

    }

    private void setRandomSecurityQuestion() {
        List<String> givenList = Arrays.asList("What was your childhood nickname?",
                "In what city did you meet your spouse/significant other?",
                "What is the name of your favorite childhood friend?",
                "What street did you live on in third grade?",
                "What is your oldest sibling's birthday month and year? (e.g., January 1900)",
                "What is the middle name of your youngest child?",
                "What is your oldest sibling's middle name?",
                "What school did you attend for sixth grade?",
                "What was your childhood phone number including area code? (e.g., 000-000-0000)",
                "What is your oldest cousin's first and last name?",
                "What was the name of your first stuffed animal?",
                "In what city or town did your mother and father meet?",
                "Where were you when you had your first kiss?",
                "What is the first name of the boy or girl that you first kissed?",
                "What was the last name of your third grade teacher?",
                "In what city does your nearest sibling live?",
                "What is your youngest brother’s birthday month and year? (e.g., January 1900)",
                "What is your maternal grandmother's maiden name?",
                "In what city or town was your first job?",
                "What is the name of the place your wedding reception was held?");

    }

    public List<String> getSecurityQuestions() {
        return securityQuestions;
    }

    public void setSecurityQuestions(List<String> securityQuestions) {
        this.securityQuestions = securityQuestions;
    }
}
