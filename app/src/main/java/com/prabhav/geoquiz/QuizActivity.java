package com.prabhav.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.prabhav.geoquiz.Model.Question;

public class QuizActivity extends AppCompatActivity {

private static final String TAG = "QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private Button mCheatButton;
    private static final int REQUEST_CODE_CHEAT =0;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_india,true),
            new Question(R.string.question_America,true),
            new Question(R.string.question_pakistan,false),
            new Question(R.string.question_japan,true),
    };
    private int mCurrentIndex =0;
    private TextView mTextView;
    private boolean mIsCheater;

    private void updateQuestion()
    {
        int question = mQuestionBank[mCurrentIndex].getTextId();
        mTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue) {
        boolean answeristrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageId = 0;
        if (mIsCheater) {
            messageId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answeristrue)

            {
                messageId = R.string.correct_toast;
            } else {
                messageId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onStart() called");
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //start cheat activity
               boolean answer_is_true = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.IntentNew(QuizActivity.this,answer_is_true);
                startActivityForResult(i,REQUEST_CODE_CHEAT);

            }

        });


        mNextButton =(Button) findViewById(R.id.nxt_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                //int question = mQuestionBank[mCurrentIndex].getTextId();
                //mTextView.setText(question);
                updateQuestion();
            }
        });
   mPreviousButton = (Button)findViewById(R.id.prv_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
        public void onClick(View v) {
      if(mCurrentIndex==0)
                mCurrentIndex =  mQuestionBank.length-1;
                else
      {
          mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
      }
                updateQuestion();
            }
        });

        mTextView =(TextView)findViewById(R.id.txt_view);
      //  int question = mQuestionBank[mCurrentIndex].getTextId();
       // mTextView.setText(question);
          updateQuestion();
        mTrueButton= (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
        public void onClick(View v)
            {
                checkAnswer(true);
                //Do something here
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
        public void onClick(View v)
            {
                checkAnswer(false);
                //Do something for false button
            }
        });


    }

@Override
protected void onActivityResult(int requestCode,int resultCode,Intent data)
{
    if(resultCode != Activity.RESULT_OK){
        return;
    }
    if(requestCode == REQUEST_CODE_CHEAT)
    {
        if(data==null)
        {
            return;
        }
        mIsCheater = CheatActivity.wasAnswerShown(data);
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
