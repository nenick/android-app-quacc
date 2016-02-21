package de.nenick.quacc.expandablerecyclerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class ExpandableItemIndicatorImplAnim extends ExpandableItemIndicator.Impl {
    private ImageView mImageView;
    private int mColor;

    @Override
    public void onInit(Context context, AttributeSet attrs, int defStyleAttr, ExpandableItemIndicator thiz) {
        View v = LayoutInflater.from(context).inflate(R.layout.widget_expandable_item_indicator, thiz, true);
        mImageView = (ImageView) v.findViewById(R.id.image_view);
        //mColor = ContextCompat.getColor(context, R.color.expandable_item_indicator_color);
    }

    @Override
    public void setExpandedState(boolean isExpanded, boolean animate) {
        if (animate) {
            int resId = isExpanded ? R.drawable.ic_expand_more_to_expand_less : R.drawable.ic_expand_less_to_expand_more;
            mImageView.setImageResource(resId);
            //DrawableCompat.setTint(mImageView.getDrawable(), mColor);
            ((Animatable) mImageView.getDrawable()).start();
        } else {
            int resId = isExpanded ? R.drawable.ic_expand_less_vector : R.drawable.ic_expand_more_vector;
            mImageView.setImageResource(resId);
            //DrawableCompat.setTint(mImageView.getDrawable(), mColor);
        }
    }
}