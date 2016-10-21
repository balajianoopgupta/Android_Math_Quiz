package balajianoopgupta.projects.com.android_math_quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class QuizActivity extends AppCompatActivity implements QuestionsFragment.Callbacks{

    private Bundle b;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnEnter;
    private Button btnClr;

    private String input="";

    private int result = 0, correct = 0;
    //private int qCount=1,
    FragmentManager fm;
    QuestionsFragment qFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.add);
        actionBar.setDisplayUseLogoEnabled(true);


        Intent i = getIntent();
        String opr = i.getStringExtra(MainActivity.OPERATOR);

        b = new Bundle();
        b.putString("myOperator",opr);

        Button btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "0";
                checkResult(0);
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "1";
                checkResult(0);
            }

        });

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "2";
                checkResult(0);
            }
        });

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "3";
                checkResult(0);
            }
        });

        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "4";
                checkResult(0);
            }
        });

        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "5";
                checkResult(0);
            }
        });

        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "6";
                checkResult(0);
            }
        });

        btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "7";
                checkResult(0);
            }
        });

        btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "8";
                checkResult(0);
            }
        });

        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input += "9";
                checkResult(0);
            }
        });

        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult(-1);
            }
        });

        btnClr = (Button) findViewById(R.id.btnClr);
        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input="";
                qFragment.displayResultText(input);
            }
        });

        //call loadQuestion for the first time only
        loadQuestion();
    }

    @Override
    public void getResult(int value) {
        result = value;
    }

    @Override
    public int sendScore() {
        return correct;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Warning !!");

        // set dialog message
        alertDialogBuilder
                .setMessage("Quiz will be quit. Do you want to quit?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close the current activity
                        result = 0;
                        input = "";
                        QuizActivity.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

//        Reset the question count on exiting the quiz
//        qCount=0;

        return true;

    }

    public void loadQuestion(){

        fm = getSupportFragmentManager();

        Fragment questionsFragment = fm.findFragmentById(R.id.quiz);
        FragmentTransaction transaction1 = fm.beginTransaction();
        if(questionsFragment == null){
            questionsFragment = new QuestionsFragment();
            questionsFragment.setArguments(b);
            transaction1.add(R.id.quiz, questionsFragment);
            transaction1.commit();
        }

        qFragment = (QuestionsFragment) getSupportFragmentManager().findFragmentById(R.id.quiz);
    }

    public void newQuestion(){
        input="";
        result = 0;
        qFragment.resetTimer();
    }

    public void checkResult(int val){

        if(qFragment == null){
            qFragment = (QuestionsFragment) getSupportFragmentManager().findFragmentById(R.id.quiz);
        }

        if(val == -1)
        {
            if(input == ""){
                //Toast.makeText(this,"Question Skipped",Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(android.R.id.content), "Question Skipped", Snackbar.LENGTH_LONG)
                        .show();

//                input ="";
//                result = 0;
            }
            //Check on pressing the Enter Button
            else if(Integer.parseInt(input) == result){
                //Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(android.R.id.content), "Correct", Snackbar.LENGTH_LONG)
                        .show();
                ++correct;
            }
            else{
                //Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();

                Snackbar.make(findViewById(android.R.id.content), "Incorrect", Snackbar.LENGTH_LONG)
                        .show();
            }

            //On Reset, display a new question
            input ="";
            result = 0;
            newQuestion();

        }
        else
        {
            qFragment.displayResultText(input);
            if(Integer.parseInt(input) == result){
                //Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(android.R.id.content), "Correct", Snackbar.LENGTH_LONG)
                        .show();
                ++correct;
                input ="";
                result = 0;

//                qFragment.showResult();

                newQuestion();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("correct",correct);
        outState.putInt("result",result);
        outState.putString("input",input);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        input = savedInstanceState.getString("input");
        result = savedInstanceState.getInt("result");
        correct = savedInstanceState.getInt("correct");

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void resetInput() {
        input ="";
    }
}
