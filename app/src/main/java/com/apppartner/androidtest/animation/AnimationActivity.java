package com.apppartner.androidtest.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.apppartner.androidtest.R;

/**
 * The movement and animation of the AppPartner logo is dictated by click & motion events and from
 * the user.
 */
public class AnimationActivity extends AppCompatActivity {
    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private ImageView mLogo;
    private Button mFadeButton;
    private float mXDelta;
    private float mYDelta;
    private Animation mCompileAnimIn;
    private Animation mCompileAnimOut;

    private static final String TAG = "AnimationActivity";


    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context) {
        Intent starter = new Intent(context, AnimationActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mLogo = (ImageView) findViewById(R.id.logo);
        mFadeButton = (Button) findViewById(R.id.fade_button);
        setTitle(getString(R.string.animation_title));
        initButton();

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mLogo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mXDelta = v.getX() - event.getRawX();
                        mYDelta = v.getY() - event.getRawY();
                        Log.d(TAG, "Raw X: " + event.getRawX() + " view X: " + v.getX() + " , Raw Y: "
                                + event.getRawY() + " view Y " + v.getY());
                        Log.d(TAG, "X: " + mXDelta +" , Y: " + mYDelta);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.setY(event.getRawY() + mYDelta);
                        v.setX(event.getRawX() + mXDelta);
                        Log.d(TAG, " new X: " + v.getX() +" , new Y: " + v.getY());

                        break;
                }
                return true;
            }
        });
    }

    private void initButton() {
        mFadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAnim(v);
            }
        });
    }

    public void initAnim(View v) {
        mCompileAnimOut = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.fade_out);
        mCompileAnimIn = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.fade_in);

        mLogo.startAnimation(mCompileAnimOut);
        mLogo.startAnimation(mCompileAnimIn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }
}