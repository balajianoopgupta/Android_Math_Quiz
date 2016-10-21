package balajianoopgupta.projects.com.android_math_quiz;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionsFragment extends Fragment {

    private Handler handler = new Handler();
    private static String operator, opr1, opr2;
    private TextView myOpr,opd1,opd2,timerText,questionText;
    public TextView res;
    private int questionNumber=0,result=0;
    QuizActivity quizActi;
    private Callbacks activity;
    Question question;
    private long ticks,maxTicks = 5000;
    boolean flag=true;

    CountDownTimer myClock;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    public interface Callbacks {

        public void getResult(int result);

        public int sendScore();

        public void resetInput();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null){
            Log.d("Question","Flag value is now "+flag);
            ticks = savedInstanceState.getLong("timer");
            maxTicks = ticks;
            //myClock.start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_questions, container, false);

        myOpr = (TextView) v.findViewById(R.id.operator);
        opd1 = (TextView) v.findViewById(R.id.opnd1);
        opd2 = (TextView) v.findViewById(R.id.opnd2);
        res = (TextView) v.findViewById(R.id.result);
        timerText = (TextView) v.findViewById(R.id.timerText);
        questionText = (TextView) v.findViewById(R.id.questionText);

        questionText.setText("Question "+Integer.toString(questionNumber)+" of 10");

        //generate the question
        if(savedInstanceState == null) {
            Log.d("Question", "New Question");
            //Get the operation selected from the Main Activity that was sent in the Bundle from QuizActivity to the QuizFragment
            operator = getArguments().getString("myOperator");

            //Set the Operator
            checkOperator();
            //resetQuestion(true);
            showResult();
        }
        else{
            Log.d("Question: ","Retrieving the values");
            operator = savedInstanceState.getString("operator");
            questionNumber = savedInstanceState.getInt("questionNumber");
            opr1 = savedInstanceState.getString("operand1");
            opr2 = savedInstanceState.getString("operand2");
            result = savedInstanceState.getInt("result");
            resetQuestion(false);
        }

        return v;
    }


    public void checkOperator(){
        if(operator.toString().equals("add")){
            operator = "+";
            myOpr.setText("+");
        }
        else if(operator.toString().equals("subtract")){
            operator = "-";
            myOpr.setText("-");
        }
        else{
            operator = "*";
            myOpr.setText("*");
        }
    }
    public void resetQuestion(boolean flag){

        if(flag){
            Log.d("Question: ","Generating New Question Now");
            question = new Question(operator);
            ++questionNumber;
            //question = new Question(operator);
            opr1 = String.valueOf(question.getOperand1());
            opr2 = String.valueOf(question.getOperand2());
            result = question.getResult();
            maxTicks = 5000;
        }

        if(questionNumber <=10){
            Log.d("In Question:",Integer.toString(questionNumber));

            opd1.setText(opr1);
            opd2.setText(opr2);
            myOpr.setText(operator);
            questionText.setText("Question "+questionNumber+" of 10");

            activity = (Callbacks) quizActi;
            activity.getResult(result);

            res.setText("");

           // myClock.cancel();
            startTimer(maxTicks);
        }
        else{
            res.setText("");
            activity = (Callbacks) quizActi;
            int score = activity.sendScore();
            if(myClock != null) {
                myClock.cancel();
            }

//            Toast.makeText(this,"Quiz Over",Toast.LENGTH_LONG).show();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

            // set title
            alertDialogBuilder.setTitle("RESULTS");

            // set dialog message
            alertDialogBuilder
                    .setMessage(score+" out of 10 were correct!")
                    .setCancelable(false)
                    .setPositiveButton("Back to Home",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close the current activity
                            quizActi.finish();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

    }

    public void resetTimer()
    {
        myClock.cancel();
        //resetQuestion(true);
        showResult();
    }
    public void displayResultText(String val){
        res.setText(val);
    }

    @Override
    public void onAttach(Context context) {
        Log.d("Question: ","In onAttach");

        super.onAttach(context);
        if(context instanceof QuizActivity)
            quizActi = (QuizActivity) context;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        Log.d("Question: ","In onSaveInstanceState");
        outState.putString("operator",operator);
        outState.putInt("questionNumber",questionNumber);
        outState.putString("operand1",opr1);
        outState.putString("operand2",opr2);
        outState.putInt("result",result);
        outState.putLong("timer",ticks);
        myClock.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("Question: ","Session Destroyed");
        myClock.cancel();

    }

    public void startTimer(long myTimer){
         myClock = new CountDownTimer(myTimer, 1000) {

            public void onTick(long millisUntilFinished) {
                ticks = millisUntilFinished;
                timerText.setText("Time Remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //timerText.setText("done!");
                activity.resetInput();
                res.setText("");
                Toast.makeText(getActivity(),"Timed Out!",Toast.LENGTH_SHORT).show();
                //resetQuestion(true);
                showResult();
            }
        }.start();
    }


    public void showResult(){
        handler.postDelayed(new Runnable() {
            public void run() {

                resetQuestion(true);
                //callanswer.setValues();
                //countdownTimer.cancel();
                //startTimer(5000);
                //callanswer.setProgress();

            }
        }, 1000);
    }
}
