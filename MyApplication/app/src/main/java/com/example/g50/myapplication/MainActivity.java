package com.example.g50.myapplication;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewSwitcher;


public class MainActivity extends Activity implements ViewSwitcher.ViewFactory {
 RadioGroup b;
    ImageSwitcher imgs;
RadioButton b1,b2,b3;
    Integer[] imageList = {
            R.drawable.chat,
            R.drawable.chaton,
            R.drawable.jolichat

    };
    RadioButton[] tab={b1,b2,b3};
int index=0;
    int curIndex=0;
    int downX,upX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      b=(RadioGroup)findViewById(R.id.radiogroup);

        b1=(RadioButton) findViewById(R.id.radioButton1);
        b2=(RadioButton) findViewById(R.id.radioButton2);
        b3=(RadioButton) findViewById(R.id.radioButton3);
        imgs=(ImageSwitcher) findViewById(R.id.imageSwitcher);
        imgs.setFactory(this);
        imgs.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imgs.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        imgs.setImageResource(imageList[curIndex]);

        imgs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downX = (int) event.getX();
                    Log.i("event.getX()", " downX " + downX);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    upX = (int) event.getX();
                    Log.i("event.getX()", " upX " + downX);
                    if (upX - downX > 100) {

                        //curIndex  current image index in array viewed by user
                        curIndex--;
                        index--;
                        if (curIndex < 0) {
                            curIndex = imageList.length - 1;
                        }

                        imgs.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_left));
                        imgs.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_right));

                        imgs.setImageResource(imageList[curIndex]);
                        //GalleryActivity.this.setTitle(curIndex);
                    } else if (downX - upX > -100) {

                        curIndex++;
                        index++;
                        if (curIndex > 2) {
                            curIndex = 0;
                            index = 0;
                        }

                        imgs.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_right));
                        imgs.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_left));

                        imgs.setImageResource(imageList[curIndex]);
                        // en principe hna n7ot tab[index].setChecked(true); mais rahi l'app tetcracha,
                        // choufilha 7al bech yet3ada lil les radio bouttons lo5rin


                    }
                    return true;
                }
                return false;
         }
     });

        imgs.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imgs.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));






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

    @Override
    public View makeView() {
        ImageView i = new ImageView(this);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return i;
    }
}
