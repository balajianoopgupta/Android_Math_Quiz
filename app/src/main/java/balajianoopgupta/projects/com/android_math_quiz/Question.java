package balajianoopgupta.projects.com.android_math_quiz;

import java.util.Random;

/**
 * Created by balaji.byrandurga on 10/9/16.
 */

public class Question {

    private int operand1, operand2, result, operator;

    public Question(String operator){
        generateQuestion(operator);
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getOperand1() {
        return operand1;

    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void generateQuestion(String operator){

        operand1 = getRandNumbInRange(3,9) +7;
        operand2 = getRandNumbInRange(2,7) +3;
        if(operator == "+") {
            result = operand1 + operand2;
        }
        else if(operator == "-") {
            result = operand1 - operand2;
        }
        else{

                result = operand1 * operand2;
        }
    }

    private static int getRandNumbInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
