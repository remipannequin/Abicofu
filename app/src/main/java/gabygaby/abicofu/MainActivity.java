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

    long number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Abicofu.ttf");
        int i = 0;
        for (int b : new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonA, R.id.buttonB,
                R.id.buttonC, R.id.buttonD, R.id.buttonE, R.id.buttonF}) {
            Button button = (Button) findViewById(b);
            button.setTypeface(font);
            button.setTag(i++);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = (Integer)v.getTag();
                    addFigure(number);
                }
            });
        }

        TextView tv = (TextView) findViewById(R.id.textViewResultAbicofu);
        tv.setTypeface(font);
        update();
    }

    /**
     * Add a figure to the nuber displayed
     * @param c the char to append
     */
    private void addFigure(int c) {
        number = number * 16 + c;
        update();
    }


    public void clear(View button) {
        number = 0;
        update();
    }

    private void update() {
        StringBuilder hex_repr = new StringBuilder(String.format("%08X", number));
        for (int i = hex_repr.length(); i >= 0; i = i-2){
            hex_repr.insert(i, " ");
        }
        TextView tv1 = (TextView) findViewById(R.id.textViewResultAbicofu);
        tv1.setText(hex_repr);
        TextView tv3 = (TextView) findViewById(R.id.textViewResultHexa);
        tv3.setText(hex_repr);

        String text_repr = new AbicofuFormatter(number).write();
        TextView tv2 = (TextView) findViewById(R.id.textViewResultText);
        tv2.setText(text_repr);


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
