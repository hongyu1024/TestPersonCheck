package com.example.testpersoncheck;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;

import java.io.ByteArrayOutputStream;

import static com.example.testpersoncheck.AESDecode.sendPost;

public class MainActivity extends AppCompatActivity {

         private Button getPicture;

        private Button startVerify;

        private ImageView showImage;

        public static Bitmap imageBitmap;

        public static String photo1;

    public static String photo2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPicture = (Button) findViewById(R.id.txt);
        startVerify = (Button) findViewById(R.id.verify);
        showImage = (ImageView) findViewById(R.id.image2);
        getPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        startVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVerify();
            }
        });



    }

    private boolean getVerify(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    photo1 = ImageUtils.bitmapToString(imageBitmap);
                    photo2 = ImageUtils.bitmapToString(imageBitmap);
                    String body = "{\"type\":1,\"content_1\":\""+photo1+"\",\"content_2\":\""+photo2+"\"}";
                    final String s = sendPost("https://dtplus-cn-shanghai.data.aliyuncs.com/face/verify",body,"LTAIUID5sUs2WsbQ","yg0NuekLuMGVbY6EOqvD2L4kbtNsHL");
                    Log.d("rain",s);
                    final Bean jsonObject = JSON.parseObject(s,Bean.class);
                    Log.d("rain","confidence:"+jsonObject.getConfidence());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(jsonObject.getConfidence()>80){
                                Toast.makeText(MainActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this,"验证失败",Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }catch (final Exception e){
                    e.printStackTrace();
                }


            }
        }).start();
        return true;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            showImage.setImageBitmap(imageBitmap);
        }
    }

  /*  public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt://相机
//                startCamera();
                openCamera(this);
                break;

            case R.id.verify:

                getVerify();
                break;


        }
    }*/

}
