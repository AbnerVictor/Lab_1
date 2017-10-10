package com.example.abnervictor.lab_1;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //=====================================================================================//
        //AlertDialog对象的创建
        ImageView profilePic = (ImageView) findViewById(R.id.Profile_picture);

        final AlertDialog.Builder ProPic_upload = new AlertDialog.Builder(this);
        final String[] Items_ProPic_upload = {"拍摄","从相册选择"};
        ProPic_upload.setTitle("上传头像").setItems(Items_ProPic_upload, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"您选择了["+Items_ProPic_upload[i]+"]",Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"您选择了[取消]",Toast.LENGTH_SHORT).show();
            }
        }).create();

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProPic_upload.show();
            }
        });
        //监听图片点击事件与触发对话框
        //=====================================================================================//

        //=====================================================================================//
        //选项与Snackbar提示
        final RadioButton Radio_Stu = (RadioButton) findViewById(R.id.radio_stu);
        Radio_Stu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(Radio_Stu,"您选择了学生",Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        final RadioButton Radio_Teach = (RadioButton) findViewById(R.id.radio_teach);
        Radio_Stu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(Radio_Teach,"您选择了教职工",Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        //=====================================================================================//




    }
}
