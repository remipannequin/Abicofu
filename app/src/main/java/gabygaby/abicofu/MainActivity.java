package gabygaby.abicofu;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private long number = 0;

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
     * Add a figure to the number displayed
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
        StringBuilder hex_representation = new StringBuilder(String.format("%08X", number));
        for (int i = hex_representation.length(); i >= 0; i = i - 2) {
            hex_representation.insert(i, " ");
        }
        TextView tv1 = (TextView) findViewById(R.id.textViewResultAbicofu);
        tv1.setText(hex_representation);
        TextView tv3 = (TextView) findViewById(R.id.textViewResultHex);
        tv3.setText(hex_representation);

        String text_representation = new AbicofuFormatter(number).write();
        TextView tv2 = (TextView) findViewById(R.id.textViewResultText);
        tv2.setText(text_representation);


    }
}
