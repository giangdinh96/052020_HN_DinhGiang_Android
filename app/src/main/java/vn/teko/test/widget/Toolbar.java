package vn.teko.test.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ImageViewCompat;

import vn.teko.test.R;
import vn.teko.test.utils.FontUtils;

public class Toolbar extends ConstraintLayout {
    View mHeaderContentContainerV;
    ImageView mActionLeftIv;
    ImageView mActionRightIv;
    TextView mTitleTv;
    TextView mSubtitleTv;

    public Toolbar(Context context) {
        super(context);
        init(null, 0);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        //------------------------------------------------- Init view ------------------------------------------------------//
        LayoutInflater.from(getContext()).inflate(R.layout.toolbar, this, true);
        mHeaderContentContainerV = findViewById(R.id.toolbar_header_content_container_v);
        mActionLeftIv = findViewById(R.id.toolbar_header_action_left_iv);
        mActionRightIv = findViewById(R.id.toolbar_header_action_right_iv);
        mTitleTv = findViewById(R.id.toolbar_header_title_tv);
        mSubtitleTv = findViewById(R.id.toolbar_header_subtitle_tv);

        TypedArray input = getContext().obtainStyledAttributes(attrs, R.styleable.Toolbar, 0, 0);
        //------------------------------------------------- Header content container ------------------------------------------------------//
        // Drawable
        if (input.hasValue(R.styleable.Toolbar_tbl_header_content_container_drawable)) {
            Drawable drawable = input.getDrawable(R.styleable.Toolbar_tbl_header_content_container_drawable);
            setHeaderContentContainerDrawable(drawable);
        }
        // Height
        if (input.hasValue(R.styleable.Toolbar_tbl_header_content_container_height)) {
            float height = input.getDimension(R.styleable.Toolbar_tbl_header_content_container_height, dp2Px(48));
            setHeaderContentContainerHeight(height);
        }

        //------------------------------------------------- Display Style -------------------------------------------------//
        resetVisibility();
        ActionStyle actionLeftStyle = ActionStyle.getActionStyle(input.getInt(R.styleable.Toolbar_tbl_header_action_left_style, ActionStyle.NONE.input));
        ActionStyle actionRightStyle = ActionStyle.getActionStyle(input.getInt(R.styleable.Toolbar_tbl_header_action_right_style, ActionStyle.NONE.input));
        initActionStyle(actionLeftStyle, actionRightStyle);


        //------------------------------------------------- Title ---------------------------------------------------------//
        // Style
        if (input.hasValue(R.styleable.Toolbar_tbl_header_title_style)) {
            int titleStyle = input.getInt(R.styleable.Toolbar_tbl_header_title_style, TextStyle.BOLD.input);
            setTitleStyle(TextStyle.getTextStyle(titleStyle).value);
        }
        // Text
        if (input.hasValue(R.styleable.Toolbar_tbl_header_title)) {
            String title = input.getString(R.styleable.Toolbar_tbl_header_title);
            setTitle(title);
        }
        // Color
        if (input.hasValue(R.styleable.Toolbar_tbl_header_title_color)) {
            int titleColor = input.getColor(R.styleable.Toolbar_tbl_header_title_color, Color.BLACK);
            setTitleColor(titleColor);
        }
        // Size
        if (input.hasValue(R.styleable.Toolbar_tbl_header_title_size)) {
            float titleSize = input.getDimension(R.styleable.Toolbar_tbl_header_title_size, sp2Px(12));
            setTitleSize(titleSize);
        }
        // AllCaps
        if (input.hasValue(R.styleable.Toolbar_tbl_header_title_all_caps)) {
            boolean titleAllCaps = input.getBoolean(R.styleable.Toolbar_tbl_header_title_all_caps, false);
            setTitleAllCaps(titleAllCaps);
        } else {
            setTitleAllCaps(false);
        }

        //------------------------------------------------- Subtitle ---------------------------------------------------------//
        // Style
        if (input.hasValue(R.styleable.Toolbar_tbl_header_subtitle_style)) {
            int subtitleStyle = input.getInt(R.styleable.Toolbar_tbl_header_subtitle_style, TextStyle.BOLD.input);
            setSubtitleStyle(TextStyle.getTextStyle(subtitleStyle).value);
        }
        // Text
        if (input.hasValue(R.styleable.Toolbar_tbl_header_subtitle)) {
            String subtitle = input.getString(R.styleable.Toolbar_tbl_header_subtitle);
            setSubtitle(subtitle);
        }
        // Color
        if (input.hasValue(R.styleable.Toolbar_tbl_header_subtitle_color)) {
            int subtitleColor = input.getColor(R.styleable.Toolbar_tbl_header_subtitle_color, Color.BLACK);
            setSubtitleColor(subtitleColor);
        }
        // Size
        if (input.hasValue(R.styleable.Toolbar_tbl_header_subtitle_size)) {
            float subtitleSize = input.getDimension(R.styleable.Toolbar_tbl_header_subtitle_size, sp2Px(12));
            setSubtitleSize(subtitleSize);
        }
        // AllCaps
        if (input.hasValue(R.styleable.Toolbar_tbl_header_subtitle_all_caps)) {
            boolean subtitleAllCaps = input.getBoolean(R.styleable.Toolbar_tbl_header_subtitle_all_caps, false);
            setSubtitleAllCaps(subtitleAllCaps);
        } else {
            setSubtitleAllCaps(false);
        }
        // Show
        boolean subtitleShow = input.getBoolean(R.styleable.Toolbar_tbl_header_subtitle_show, false);
        setSubtitleShow(subtitleShow);


        //------------------------------------------------- Action left ----------------------------------------------------//
        // Left image
        if (input.hasValue(R.styleable.Toolbar_tbl_header_left_image)) {
            Drawable imageLeft = input.getDrawable(R.styleable.Toolbar_tbl_header_left_image);
            setImageActionLeft(imageLeft);
        }
        // Left image tint
        if (input.hasValue(R.styleable.Toolbar_tbl_header_left_image_tint)) {
            int imageLeftTint = input.getColor(R.styleable.Toolbar_tbl_header_left_image_tint, Color.WHITE);
            setImageActionLeftTint(imageLeftTint);
        }
        // Left image size
        if (input.hasValue(R.styleable.Toolbar_tbl_header_left_image_size)) {
            float imageLeftSize = input.getDimension(R.styleable.Toolbar_tbl_header_left_image_size, dp2Px(24));
            setImageActionLeftSize(imageLeftSize);
        }
        // Left image background
        if (input.hasValue(R.styleable.Toolbar_tbl_header_left_image_background)) {
            Drawable imageLeftBackground = input.getDrawable(R.styleable.Toolbar_tbl_header_left_image_background);
            setImageActionLeftBackground(imageLeftBackground);
        }

        // Right image
        if (input.hasValue(R.styleable.Toolbar_tbl_header_right_image)) {
            Drawable imageRight = input.getDrawable(R.styleable.Toolbar_tbl_header_right_image);
            setImageActionRight(imageRight);
        }
        // Right image tint
        if (input.hasValue(R.styleable.Toolbar_tbl_header_right_image_tint)) {
            int imageLeftTint = input.getColor(R.styleable.Toolbar_tbl_header_right_image_tint, Color.WHITE);
            setImageActionRightTint(imageLeftTint);
        }
        // Right image size
        if (input.hasValue(R.styleable.Toolbar_tbl_header_right_image_size)) {
            float imageRightSize = input.getDimension(R.styleable.Toolbar_tbl_header_right_image_size, dp2Px(24));
            setImageActionRightSize(imageRightSize);
        }
        // Right image background
        if (input.hasValue(R.styleable.Toolbar_tbl_header_right_image_background)) {
            Drawable imageRightBackground = input.getDrawable(R.styleable.Toolbar_tbl_header_right_image_background);
            setImageActionRightBackground(imageRightBackground);
        }

        //------------------------------------------------- Notification --------------------------------------------------//
//    setupLeftNotification();
//    setupRightNotification();

        //------------------------------------------------- recycle -------------------------------------------------------//
        input.recycle();
    }

    //------------------------------------------------- Header content container ----------------------------------------//
    public void setHeaderContentContainerDrawable(Drawable drawable) {
        mHeaderContentContainerV.setBackground(drawable);
    }

    public void setHeaderContentContainerHeight(float height) {
        setViewHeight(mHeaderContentContainerV, height);
    }

    //------------------------------------------------- Display Style ---------------------------------------------------//
    private void resetVisibility() {
        setActionLeftImageVisibility(View.INVISIBLE);
        setActionRightImageVisibility(View.INVISIBLE);
    }

    private void initActionLeftStyle(ActionStyle actionLeftStyle) {
        if (actionLeftStyle == ActionStyle.IMAGE) {
            setActionLeftImageVisibility(VISIBLE);
        }
    }

    private void initActionRightStyle(ActionStyle actionRightStyle) {
        if (actionRightStyle == ActionStyle.IMAGE) {
            setActionRightImageVisibility(VISIBLE);
        }
    }

    private void initActionStyle(ActionStyle actionLeftStyle, ActionStyle actionRightStyle) {
        resetVisibility();
        initActionLeftStyle(actionLeftStyle);
        initActionRightStyle(actionRightStyle);
    }

    //------------------------------------------------- Action left -----------------------------------------------------//
    // Image action left
    public void setImageActionLeft(Drawable drawable) {
        mActionLeftIv.setImageDrawable(drawable);
    }

    public void setImageActionLeft(@DrawableRes int idRes) {
        mActionLeftIv.setImageResource(idRes);
    }

    public void setImageActionLeftTint(int color) {
        ImageViewCompat.setImageTintList(mActionLeftIv, ColorStateList.valueOf(color));
    }

    public void setImageActionLeftSize(float size) {
        ViewGroup.LayoutParams layoutParams = mActionLeftIv.getLayoutParams();
        layoutParams.width = (int) size;
        layoutParams.height = (int) size;
        mActionLeftIv.setLayoutParams(layoutParams);
    }

    public void setImageActionLeftBackground(Drawable background) {
        mActionLeftIv.setBackground(background);
    }

    //------------------------------------------------- Action Right ------------------------------------------------------//
    // Image action right
    public void setImageActionRight(Drawable drawable) {
        mActionRightIv.setImageDrawable(drawable);
    }

    public void setImageActionRight(@DrawableRes int idRes) {
        mActionRightIv.setImageResource(idRes);
    }

    public void setImageActionRightTint(int color) {
        ImageViewCompat.setImageTintList(mActionRightIv, ColorStateList.valueOf(color));
    }

    public void setImageActionRightSize(float size) {
        ViewGroup.LayoutParams layoutParams = mActionRightIv.getLayoutParams();
        layoutParams.width = (int) size;
        layoutParams.height = (int) size;
        mActionRightIv.setLayoutParams(layoutParams);
    }

    public void setImageActionRightBackground(Drawable background) {
        mActionRightIv.setBackground(background);
    }

    //------------------------------------------------- Title --------------------------------------------------------------//
    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    public void setTitle(@StringRes int idRes) {
        mTitleTv.setText(idRes);
    }

    public void setTitleColor(int color) {
        mTitleTv.setTextColor(color);
    }

    public void setTitleSize(float size) {
        mTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setTitleStyle(String font) {
        loadFont(mTitleTv, font);
    }

    public void setTitleAllCaps(boolean allCaps) {
        mTitleTv.setAllCaps(allCaps);
    }

    //------------------------------------------------- Subtitle --------------------------------------------------------------//
    public void setSubtitle(String title) {
        mSubtitleTv.setText(title);
    }

    public void setSubtitle(@StringRes int idRes) {
        mSubtitleTv.setText(idRes);
    }

    public void setSubtitleColor(int color) {
        mSubtitleTv.setTextColor(color);
    }

    public void setSubtitleSize(float size) {
        mSubtitleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setSubtitleStyle(String font) {
        loadFont(mSubtitleTv, font);
    }

    public void setSubtitleAllCaps(boolean allCaps) {
        mSubtitleTv.setAllCaps(allCaps);
    }

    public void setSubtitleShow(boolean show) {
        mSubtitleTv.setVisibility(show ? VISIBLE : GONE);
    }

    //------------------------------------------------- Visibility -----------------------------------------------------------//

    public void setActionLeftImageVisibility(int visibility) {
        mActionLeftIv.setVisibility(visibility);
    }

    public void setActionRightImageVisibility(int visibility) {
        mActionRightIv.setVisibility(visibility);
    }


    //------------------------------------------------- Notification ---------------------------------------------------------//
//  public void addLeftNotiDot() {
//    mLeftNotification
//        .setBadgeText("")
//        .setBadgePadding(4, true)
//        .setGravityOffset(14, 12, true);
//  }
//
//  public void addRightNotiDot() {
//    mRightNotification
//        .setBadgeText("")
//        .setBadgePadding(4, true)
//        .setGravityOffset(14, 12, true);
//  }
//
//  public void addLeftNotiCount(String text) {
//    mLeftNotification
//        .setBadgeText(text)
//        .setBadgeTextSize(10, true)
//        .setBadgePadding(4, true)
//        .setGravityOffset(10, 4, true);
//  }
//
//  public void addRightNotiCount(String text) {
//    mRightNotification
//        .setBadgeText(text)
//        .setBadgeTextSize(10, true)
//        .setBadgePadding(4, true)
//        .setGravityOffset(10, 4, true);
//  }
//
//  public void clearLeftNotification() {
//    mLeftNotification.setBadgeText("")
//        .setBadgePadding(0, true);
//  }
//
//  public void clearRightNotification() {
//    mRightNotification.setBadgeText("")
//        .setBadgePadding(0, true);
//  }


    //------------------------------------------------- Listener -------------------------------------------------------------//
    public void setOnActionLeftClickListener(OnClickListener onClickListener) {
        mActionLeftIv.setOnClickListener(onClickListener);
    }

    public void setOnActionRightClickListener(OnClickListener onClickListener) {
        mActionRightIv.setOnClickListener(onClickListener);
    }


    //------------------------------------------------- More ------------------------------------------------------------------//
    private void setViewHeight(View view, float height) {
        ViewGroup.LayoutParams currentLayoutParams = view.getLayoutParams();
        currentLayoutParams.height = (int) height;
        view.setLayoutParams(currentLayoutParams);
    }

    private void setViewWidth(View view, float width) {
        ViewGroup.LayoutParams currentLayoutParams = view.getLayoutParams();
        currentLayoutParams.width = (int) width;
        view.setLayoutParams(currentLayoutParams);
    }

    private void setViewSize(View view, float width, float height) {
        ViewGroup.LayoutParams currentLayoutParams = view.getLayoutParams();
        currentLayoutParams.width = (int) width;
        currentLayoutParams.height = (int) height;
        view.setLayoutParams(currentLayoutParams);
    }

    private void loadFont(TextView textView, String font) {
        String root = "fonts/";
        Typeface typeface = FontUtils.get(getContext(), root.concat(font));
        textView.setTypeface(typeface);
    }

    private float dp2Px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public float sp2Px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public enum ActionStyle {
        NONE(0), IMAGE(1);
        int input;

        ActionStyle(int input) {
            this.input = input;
        }

        static ActionStyle getActionStyle(int input) {
            for (ActionStyle actionStyle : values()) {
                if (actionStyle.input == input) {
                    return actionStyle;
                }
            }
            return NONE;
        }
    }

    public enum TextStyle {
        NORMAL(0, "SFProText-Regular.ttf"),
        MEDIUM(1, "SFProText-Medium.ttf"),
        BOLD(2, "SFProText-SemiBold.ttf");

        int input;
        String value;

        TextStyle(int input, String value) {
            this.input = input;
            this.value = value;
        }

        static TextStyle getTextStyle(int input) {
            for (TextStyle textStyle : values()) {
                if (textStyle.input == input) {
                    return textStyle;
                }
            }
            return NORMAL;
        }
    }
}