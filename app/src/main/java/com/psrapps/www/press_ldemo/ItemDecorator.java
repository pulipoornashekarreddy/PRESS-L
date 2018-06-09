package com.psrapps.www.press_ldemo;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by poornashekarreddy.p on 30-04-2017.
 */

public class ItemDecorator extends RecyclerView.ItemDecoration{
    private final int left;
    private final int right;
    private final int top;
    private final int bottom;

    public ItemDecorator(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }




    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        super.getItemOffsets(outRect,view,parent,state);
        outRect.set(left,top,right,bottom);

    }
}