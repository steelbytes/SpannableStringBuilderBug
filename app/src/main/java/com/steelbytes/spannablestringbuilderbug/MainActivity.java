package com.steelbytes.spannablestringbuilderbug;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i=0;i<5;++i) {
            int p = spannableStringBuilder.length();
            spannableStringBuilder.append("aAAAAa\n");
            spannableStringBuilder.setSpan(new StyleSpan(Typeface.ITALIC), p+1, p+5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        boolean pass = true;
        StyleSpan[] spans = spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), StyleSpan.class);
        int p = 0;
        for (StyleSpan span : spans) {
            int start = spannableStringBuilder.getSpanStart(span);
            if (start<p) {
                pass = false;
                break;
            }
            p = start;
        }

        ((TextView)findViewById(R.id.text)).setText(pass?"passed":"failed");
        ((TextView)findViewById(R.id.text2)).setText(spannableStringBuilder);
    }
}
