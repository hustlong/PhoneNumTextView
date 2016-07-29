package cc.biglong.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import cc.biglong.phonenumtextview.R;

/**
 * Created by code on 16/7/29.
 */
public class PhoneNumTextView extends TextView {

    /**
     * 号码显示格式:XXX-XXXX-XXXX
     */
    private int start = 3;
    private int middle = 4;
    private int end = 4;

    /** 分隔符 */
    private String division = "-";

    /** 和自己绑定的edittext */
    private EditText editText;

    /** 获取自己 */
    private PhoneNumTextView phoneNumTextView;


    public PhoneNumTextView(Context context) {
        super(context);
        phoneNumTextView = this;
    }

    public PhoneNumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        phoneNumTextView = this;
        /** 获取自定义参数 */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PhoneNumTextView);
        if (typedArray != null) {
            int pattern = typedArray.getInteger(R.styleable.PhoneNumTextView_pattern, 344);
            start = pattern/100;
            middle = pattern%100/10;
            end = pattern%10;
            if ((start + middle + end) != 11) {
                start = 3;
                middle = 4;
                end = 4;
            }
            division = typedArray.getString(R.styleable.PhoneNumTextView_division);
            if (division == null || division.length() == 0)
                division = "-";
        }
    }

    public PhoneNumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        phoneNumTextView = this;
    }

    private String format(String phone) {
        int length = phone.length();
        if (length < start)
            return phone;
        else if (length < start+middle)
            return phone.substring(0,start) + division + phone.substring(start,length);
        else if (length <= 11)
            return phone.substring(0,start)+ division + phone.substring(start,start+middle) + division + phone.substring(start+middle,length);
        else {
            editText.setText(phone.substring(0,11));
            editText.setSelection(11);
            return phone.substring(0,start)+ division + phone.substring(start,start+middle) + division + phone.substring(start+middle,11);
        }
    }

    public void setPhoneNum(String phone) {
        this.setText(format(phone));
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                phoneNumTextView.setPhoneNum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public int getStart() {
        return start;
    }

    public int getMiddle() {
        return middle;
    }

    public int getEnd() {
        return end;
    }
}
