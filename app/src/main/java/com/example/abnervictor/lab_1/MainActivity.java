package com.example.abnervictor.lab_1;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitLoginView();
        //=====================================================================================//
    }

    private void InitLoginView(){

        final int REQUEST_CAMERA = 0;
        final int REQUEST_ALBUM = 1;

        //=====================================================================================//
        //AlertDialog对象的创建
        ImageView profilePic = (ImageView) findViewById(R.id.Profile_picture);

        final AlertDialog.Builder ProPic_upload = new AlertDialog.Builder(this);
        final String[] Items_ProPic_upload = {"拍摄","从相册选择"};
        ProPic_upload.setTitle("上传头像").setItems(Items_ProPic_upload, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"您选择了["+Items_ProPic_upload[i]+"]",Toast.LENGTH_SHORT).show();
                if(i == 0){
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,
                            REQUEST_CAMERA);
                }
                if(i == 1) {
                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, REQUEST_ALBUM);
                }
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
        Radio_Teach.setOnClickListener(new View.OnClickListener(){
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

        //=====================================================================================//
        //登陆检测
        final TextInputLayout mUsername = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout mPassword = (TextInputLayout) findViewById(R.id.passwordWrapper);
        final EditText Username = mUsername.getEditText();
        final EditText Password = mPassword.getEditText();

        final Button mLogin = (Button) findViewById(R.id.login_button);
        final Button mRegister = (Button) findViewById(R.id.register_button);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean login_success = true;
                if(Username.getText().toString().isEmpty()){
                    mUsername.setError("学号不能为空");
                    mUsername.setErrorEnabled(true);
                    login_success = false;
                }
                else if(!Username.getText().toString().equals("123456")){
                    mUsername.setError("该学号不存在");
                    mUsername.setErrorEnabled(true);
                    login_success = false;
                }
                else{
                    if(Password.getText().toString().isEmpty()){
                        mPassword.setError("密码不能为空");
                        mPassword.setErrorEnabled(true);
                        login_success = false;
                    }
                    else if(!Password.getText().toString().equals("6666")){
                        mPassword.setError("密码错误");
                        mPassword.setErrorEnabled(true);
                        login_success = false;
                    }
                    else{
                        mPassword.setErrorEnabled(false);
                    }
                    mUsername.setErrorEnabled(false);
                }



                if (login_success){
                    Snackbar.make(mLogin,"登陆成功",Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
                else{
                    Snackbar.make(mLogin,"学号或密码错误",Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }

            }
        });//点击登陆按钮时判断输入内容

        Username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUsername.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });//正在输入时消除提示
        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });//正在输入时消除提示

        //=====================================================================================//

        //=====================================================================================//
        //注册检测
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Radio_Stu.isChecked()){
                    Snackbar.make(mRegister,"学生注册功能未启用",Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
                else if(Radio_Teach.isChecked()){
                    Toast.makeText(MainActivity.this,"教职工注册功能尚未启用",Toast.LENGTH_SHORT).show();
                }
                mUsername.setErrorEnabled(false);
                mPassword.setErrorEnabled(false);
            }
        });
        //=====================================================================================//

    }
}
