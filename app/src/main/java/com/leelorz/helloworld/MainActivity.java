package com.leelorz.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvHello;
    EditText editTextHello;
    Button btnCopy;

    EditText editText1;
    EditText editText2;
    TextView tvResult;
    Button btnCalculate;
    RadioGroup rgOperator;

    CustomViewGroup viewGroup1;
    CustomViewGroup viewGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.activity_main);

        initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(MainActivity.this,
                "Width = " + width + ", Height = " + height,
                Toast.LENGTH_SHORT)
                .show();
    }

    private void initInstances() {
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        tvHello.setText(Html.fromHtml("<b>Hello</b> <i>World</i> <font color=\"#fafafa\">La La La</font> <a href=\"http://nuuneoi.com\">nuuneoi.com</a>"));

        editTextHello = (EditText) findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Copy text in
                    tvHello.setText(editTextHello.getText());
                    return true;
                }
                return false;
            }
        });

        btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);

        rgOperator = (RadioGroup) findViewById(R.id.rgOperator);

        viewGroup1 = (CustomViewGroup) findViewById(R.id.viewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.viewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");
    }

    @Override
    public void onClick(View v) {
        if (v == btnCopy) {
            tvHello.setText(editTextHello.getText());
        } else if (v == btnCalculate) {
            int val1 = 0;
            int val2 = 0;
            int result = 0;
            try {
                val1 = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException e) {
            }
            try {
                val2 = Integer.parseInt(editText2.getText().toString());
            } catch (NumberFormatException e) {
            }

            switch (rgOperator.getCheckedRadioButtonId()) {
                case R.id.rbPlus:
                    result = val1 + val2;
                    break;
                case R.id.rbMinus:
                    result = val1 - val2;
                    break;
                case R.id.rbMultiply:
                    result = val1 * val2;
                    break;
                case R.id.rbDivide:
                    result = val1 / val2;
                    break;
            }

            // show result
            tvResult.setText("= " + result);

            Log.d("Calculation", "Result = " + result);

            Toast.makeText(MainActivity.this,
                    "Result = " + result,
                    Toast.LENGTH_SHORT)
                    .show();

            Intent intent = new Intent(MainActivity.this,
                    SecondActivity.class
            );
            intent.putExtra("result", result);

            Coordinate c1 = new Coordinate();
            c1.x = 5;
            c1.y = 10;
            c1.z = 20;
            Bundle bundle = new Bundle();
            bundle.putInt("x", c1.x);
            bundle.putInt("y", c1.y);
            bundle.putInt("z", c1.z);
            intent.putExtra("cBundle", bundle);

            // Serializable
//            CoordinateSerializable c2 = new CoordinateSerializable();
//            c2.x = 5;
//            c2.y = 10;
//            c2.z = 20;
//            intent.putExtra("cSerializable", c2);

            CoordinateParcelable c3 = new CoordinateParcelable();
            c3.x = 5;
            c3.y = 10;
            c3.z = 20;
            intent.putExtra("cParcelable", c3);

            startActivityForResult(intent, 12345);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if it is a result from secondactivity
        if (requestCode == 12345) {
            if (resultCode == RESULT_OK) {
                // get data from data's extra
                String resultText = data.getStringExtra("result");
                Toast.makeText(MainActivity.this, resultText, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Press Button", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save things here

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore things here

    }
}
