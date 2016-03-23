package com.example.a481.lostfound;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.*;
import android.net.*;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;

public class PublishActivity extends Activity implements View.OnClickListener {

    public static final int CHOOSE_PHOTO = 3;

    private Uri uri;

    private String imagePath;

    private String imageName;

    private String imageUri;

    private Button publish_ensure;

    private Button image_upload;

    private Button image_add;

    private ImageView picture;

    private EditText editText1;

    private EditText editText2;

    private EditText editText3;

    private EditText editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_publish);
        publish_ensure = (Button) findViewById(R.id.Enter_Publish);
        image_upload = (Button) findViewById(R.id.image_upload);
        image_add = (Button) findViewById(R.id.image_add);
        picture = (ImageView) findViewById(R.id.image);
        editText1 = (EditText) findViewById(R.id.Kind);
        editText2 = (EditText) findViewById(R.id.Time);
        editText3 = (EditText) findViewById(R.id.Place);
        editText4 = (EditText) findViewById(R.id.Description);
        publish_ensure.setOnClickListener(this);
        image_upload.setOnClickListener(this);
        image_add.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Enter_Publish :
                final Item Information = new Item((String)BmobUser.getObjectByKey(PublishActivity.this, "username"), editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), editText4.getText().toString(), imageName, imageUri);

                Information.save(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(PublishActivity.this, "添加数据成功，返回objectId为："+Information.getObjectId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        Toast.makeText(PublishActivity.this, "创建数据失败：" + msg, Toast.LENGTH_SHORT).show();
                    }
                });

                break;

            case R.id.image_add :
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent, CHOOSE_PHOTO);
                displayImage(imagePath);
                break;

            case R.id.image_upload :
                BTPFileResponse response = BmobProFile.getInstance(PublishActivity.this).upload(imagePath, new UploadListener() {

                    @Override
                    public void onSuccess(String fileName,String url,BmobFile file) {
                        Log.i("bmob","文件上传成功："+fileName+",可访问的文件地址："+file.getUrl());
                        // TODO Auto-generated method stub
                        // fileName ：文件名（带后缀），这个文件名是唯一的，开发者需要记录下该文件名，方便后续下载或者进行缩略图的处理
                        // url        ：文件地址
                        // file        :BmobFile文件类型，`V3.4.1版本`开始提供，用于兼容新旧文件服务。
                        imageName = fileName;
                        imageUri = file.getUrl();
                        Toast.makeText(PublishActivity.this, "文件上传成功："+fileName+",可访问的文件地址："+file.getUrl() , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(int progress) {
                        // TODO Auto-generated method stub
                        Log.i("bmob","onProgress :"+progress);
                    }

                    @Override
                    public void onError(int statuscode, String errormsg) {
                        // TODO Auto-generated method stub
                        Log.i("bmob","文件上传失败："+errormsg);
                        Toast.makeText(PublishActivity.this, "文件上传失败："+errormsg , Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO :
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageonKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private  void handleImageonKitKat (Intent data) {
        imagePath = null;
        uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            }
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        uri = data.getData();
        imagePath = getImagePath(uri, null);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }
    }
}
