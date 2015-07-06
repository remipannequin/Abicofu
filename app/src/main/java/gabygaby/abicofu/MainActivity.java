package gabygaby.abicofu;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Abicofu.ttf");
        for (int b : new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonA, R.id.buttonB,
                R.id.buttonC, R.id.buttonD, R.id.buttonE, R.id.buttonF}) {
            Button button = (Button) findViewById(b);
            button.setTypeface(font);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button) v;
                    CharSequence text = b.getText();
                    addFigure(text.charAt(0));
                }
            });

        }

        TextView tv = (TextView) findViewById(R.id.textViewResultAbicufo);
        tv.setTypeface(font);
    }

    /**
     * Add a figure to the nuber displayed
     * @param c the char to append
     */
    private void addFigure(char c) {
        TextView tv = (TextView) findViewById(R.id.textViewResultAbicufo);
        CharSequence old_t = tv.getText();
        String new_t = String.format("%s%s", old_t, c);
        tv.setText(new_t);
    }


    private void update() {



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
