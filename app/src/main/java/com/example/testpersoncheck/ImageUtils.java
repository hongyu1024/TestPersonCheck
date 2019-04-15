package com.example.testpersoncheck;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class ImageUtils {
    public static Bitmap getSmallBitmap(String filePath){
        final BitmapFactory.Options options= new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath,options);

        options.inSampleSize = calculateInSampleSize(options,480,800);

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath,options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width>reqWidth){
            final int heightRatio = Math.round((float) height/(float)reqHeight);
            final int widthRation = Math.round((float) width/(float) reqWidth);
            inSampleSize = heightRatio < widthRation ? heightRatio : widthRation;
        }
        return inSampleSize;
    }

    public static String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,40,baos);
        byte[] b = baos.toByteArray();
        Log.d("rain","压缩后大小:"+b.length);
        return Base64.encodeToString(b,Base64.DEFAULT);
    }

}
