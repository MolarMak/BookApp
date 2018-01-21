package com.molarmak.bookapp.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

/**
 * Created by Maxim on 10/13/17.
 */

public class SetImage {

    public static void setImage(int imageResource, ImageView imageView, Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                Drawable res = context.getResources().getDrawable(imageResource, context.getTheme());
                imageView.setImageDrawable(res);
            } else {
                Drawable res = context.getResources().getDrawable(imageResource);
                imageView.setImageDrawable(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setImage(ImageView imageView, byte[] data) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options);
            imageView.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
