package com.example.hperchec.taquin;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Bitmap> mBitmap;

    public ImageAdapter(Context c, ArrayList<Bitmap> bit) {
        mContext = c;
        mBitmap = bit;
    }

    public int getCount() {
        return mBitmap.size();
    }

    public Object getItem(int position) {
        return mBitmap.get(position);
    }

    public long getItemId(int position) {
        return mBitmap.indexOf(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mBitmap.get(position));
        return imageView;
    }

}
