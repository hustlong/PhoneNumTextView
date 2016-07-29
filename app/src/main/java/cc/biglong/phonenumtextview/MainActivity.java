package cc.biglong.phonenumtextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import cc.biglong.widget.PhoneNumTextView;

public class MainActivity extends AppCompatActivity{

    private EditText et;
    private PhoneNumTextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        et = (EditText) findViewById(R.id.editText);
        tv1 = (PhoneNumTextView) findViewById(R.id.view1);
        tv2 = (PhoneNumTextView) findViewById(R.id.view2);
        tv3 = (PhoneNumTextView) findViewById(R.id.view3);

        tv1.setEditText(et);
        tv2.setEditText(et);
        tv3.setEditText(et);

    }

}
