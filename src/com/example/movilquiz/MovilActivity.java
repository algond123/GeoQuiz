package com.example.movilquiz;


import java.util.Arrays;
import java.util.Collections;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MovilActivity extends Activity {
	
	private Button mTrueButton;
	private Button mFalseButton;
	private TextView mQuestionTextView;
	private TextView mPuntajeTextView;
	private TextView mLPuntajeTextView;
	private TextView inicio;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
			new TrueFalse(R.string.q1,true),
			new TrueFalse(R.string.q2,true),
			new TrueFalse(R.string.q3,true),
			new TrueFalse(R.string.q4,true),
			new TrueFalse(R.string.q5,true),
			new TrueFalse(R.string.q6,true),
			new TrueFalse(R.string.q7,true),
			new TrueFalse(R.string.q8,true),
			new TrueFalse(R.string.q9,true),
			new TrueFalse(R.string.q10,true),
			new TrueFalse(R.string.q11,true),
			new TrueFalse(R.string.q12,true),
			new TrueFalse(R.string.q13,true),
			new TrueFalse(R.string.q14,true),
			new TrueFalse(R.string.q15,true)
	};
	
	private int mCurrentIndex=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movil);
		
		
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		mPuntajeTextView = (TextView) findViewById(R.id.cont_true);
		mLPuntajeTextView = (TextView) findViewById(R.id.puntaje);
		inicio= (TextView) findViewById(R.id.inicio);
		mPuntajeTextView.setText("0");
		mLPuntajeTextView.setText("Puntaje: ");
		inicio.setText("Inicio");
		 
		// Shuffle the elements in the array
		Collections.shuffle(Arrays.asList(mQuestionBank));
		int question = mQuestionBank[mCurrentIndex].getmQuestion();
		mQuestionTextView.setText(question);
		
		mTrueButton=(Button)findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();				
				checkAnswer(true);
			}
		});
		
		mFalseButton=(Button)findViewById(R.id.false_button);	
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
				checkAnswer(false);
			}
		});
	}
	
	private void checkAnswer ( boolean userPresTrue){
		
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
		int messageResId=0;
		mPuntajeTextView.setVisibility(1);
		mLPuntajeTextView.setVisibility(1);
		inicio.setVisibility(-1);
		
		
		if (userPresTrue == answerIsTrue){
			messageResId=R.string.correct_toast;
			
			mPuntajeTextView.setText( Integer.parseInt( mPuntajeTextView.getText().toString() )+1 + "");
			
		}else {
			
			messageResId=R.string.incorrect_toast;
		}
		Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show();
		mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
		if(mCurrentIndex>9){
			mCurrentIndex= 0;
			Toast.makeText(this,"Fin del quiz puntuacion : "+mPuntajeTextView.getText().toString(), Toast.LENGTH_SHORT).show();
			mPuntajeTextView.setText("0");
			Collections.shuffle(Arrays.asList(mQuestionBank));
			int question = mQuestionBank[mCurrentIndex].getmQuestion();
			mQuestionTextView.setText(question);
			mPuntajeTextView.setVisibility(-1);
			mLPuntajeTextView.setVisibility(-1);
			inicio.setVisibility(1);
			
		}else{
			int question = mQuestionBank[mCurrentIndex].getmQuestion();
			mQuestionTextView.setText(question);
		}
	}

}
