package com.prabhav.geoquiz.Model;

/**
 * Created by ps292 on 2/14/2016.
 */
public class Question {
    private  int mTextId;
    private boolean mAnswerTrue;
    public Question(int mtextresourceid,boolean answerTrue)
    {
        mTextId  = mtextresourceid;
        mAnswerTrue = answerTrue;
    }

    public int getTextId() {
        return mTextId;
    }

    public void setTextId(int textId) {
        mTextId = textId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
