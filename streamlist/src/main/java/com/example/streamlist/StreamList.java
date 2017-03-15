package com.example.streamlist;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class StreamList extends ViewGroup implements View.OnClickListener {

    private int horizontalSpace = 10;

    private int verticalSpace = 10;

    private int type = Contant.ONLY_TEXT;

    private int textSize = 15;

    private int textColor = Color.parseColor("#000000");

    private int viewid = -1;

    public StreamList(Context context) {
        super(context);
    }

    public StreamList(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.item);
        textSize = typedArray.getInteger(R.styleable.item_textSize, 15);
        horizontalSpace = typedArray.getInteger(R.styleable.item_horizontalSpace, 10);
        verticalSpace = typedArray.getInteger(R.styleable.item_verticalSpace, 10);
        String string = typedArray.getString(R.styleable.item_textColor);
        if (string != null) {
            textColor = Color.parseColor(string);
        }
        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int maxWidth = 0;
        int maxHeight = 0;

        int viewWidth = getMeasuredWidth();

        for (int i = 0; i < count; i++) {

            View childAt = getChildAt(i);

            if (maxWidth + childAt.getMeasuredWidth() + getPaddingLeft() + getPaddingRight() <= viewWidth) {
                childAt.layout(getPaddingLeft() + maxWidth, getPaddingTop() + maxHeight, getPaddingLeft() + maxWidth + childAt.getMeasuredWidth(), getPaddingTop() + maxHeight + childAt.getMeasuredHeight());
                maxWidth += childAt.getMeasuredWidth() + horizontalSpace;
            } else {
                maxHeight += childAt.getMeasuredHeight() + verticalSpace;
                maxWidth = 0;
                childAt.layout(getPaddingLeft() + maxWidth, getPaddingTop() + maxHeight, getPaddingLeft() + maxWidth + childAt.getMeasuredWidth(), getPaddingTop() + maxHeight + childAt.getMeasuredHeight());
                maxWidth += childAt.getMeasuredWidth() + horizontalSpace;
            }
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setViewResouce(int view) {
        this.viewid = view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        int maxWidth = 0;
        int maxHeight = 0;

        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);

        for (int i = 0; i < count; i++) {

            View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);

            if (maxWidth + childAt.getMeasuredWidth() + getPaddingLeft() + getPaddingRight() <= viewWidth) {
                maxWidth += childAt.getMeasuredWidth() + horizontalSpace;
                if (maxHeight == 0) {
                    maxHeight = childAt.getMeasuredHeight();
                }
            } else {
                maxHeight += childAt.getMeasuredHeight() + verticalSpace;
                maxWidth = 0;
                maxWidth += childAt.getMeasuredWidth() + horizontalSpace;
            }
        }
        setMeasuredDimension(viewWidth, maxHeight + getPaddingTop() + getPaddingBottom());
    }

    public void setData(List<String> list) {

        if (type == Contant.ONLY_TEXT) {
            for (int i = 0; i < list.size(); i++) {
                TextView textView = getTextView();
                textView.setText(list.get(i));
                addView(textView);
            }
        } else if (type == Contant.TEXT_DELETE) {

            for (int i = 0; i < list.size(); i++) {
                View view = getView(list.get(i));
                addView(view);
            }
        } else if (type == Contant.VIEW_TEXT_DELETE) {
            if (viewid == -1) {
                throw new RuntimeException("must call setViewResouce()");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    addView(getCusView(list.get(i)));
                }
            }
        } else if (type == Contant.VIEW_TEXT) {
            if (viewid == -1) {
                throw new RuntimeException("must call setViewResouce()");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    TextView textView = (TextView) View.inflate(getContext(), viewid, null);
                    textView.setText(list.get(i));
                    addView(textView);
                }
            }
        }
        invalidate();
    }


    private View getCusView(String string) {
        View view = View.inflate(getContext(), viewid, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_delete);
        TextView textView = (TextView) view.findViewById(R.id.tv_item);
        if (imageView == null || textView == null) {
            throw new RuntimeException("the child id must is tv_item and iv_delete");
        }
        textView.setText(string);
        imageView.setOnClickListener(this);
        return view;
    }

    public void removeAllView() {
        removeAllView();
    }

    private View getView(String string) {
        View view = View.inflate(getContext(), R.layout.item_text_delete, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_delete);
        TextView textView = (TextView) view.findViewById(R.id.tv_item);
        textView.setText(string);
        textView.setTextColor(textColor);
        textView.setTextSize(textSize);
        imageView.setOnClickListener(this);
        return view;
    }

    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setPadding(10, 5, 10, 5);
        textView.setTextColor(textColor);
        textView.setTextSize(textSize);
        textView.setBackgroundResource(R.drawable.shape);
        return textView;
    }

    public void add(String string) {
        switch (type) {
            case Contant.ONLY_TEXT:
                TextView textView = getTextView();
                textView.setText(string);
                addView(textView);
                break;
            case Contant.TEXT_DELETE:
                addView(getView(string));
                break;
            case Contant.VIEW_TEXT_DELETE:
                addView(getCusView(string));
                break;
            case Contant.VIEW_TEXT:
                TextView textViewCus = (TextView) View.inflate(getContext(), viewid, null);
                textViewCus.setText(string);
                addView(textViewCus);
                break;
        }
        invalidate();
    }

    @Override
    public void onClick(View v) {
        LinearLayout parent = (LinearLayout) v.getParent();
        removeView(parent);
    }
}
