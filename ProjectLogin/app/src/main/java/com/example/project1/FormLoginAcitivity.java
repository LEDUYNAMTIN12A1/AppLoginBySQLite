package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class FormLoginAcitivity extends AppCompatActivity {
    EditText txtmk;
    Button btnlogin;
    String sDT,matKhau;
    UserDAO  userDAO;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 4 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login_acitivity);

        txtmk = (EditText) findViewById(R.id.mk);
        btnlogin = (Button) findViewById(R.id.btndangnhap);

        //Lấy dữ liệu từ bundle của form 1
        Bundle bundle = getIntent().getBundleExtra(MainActivity.BUNDLE);
        if(bundle != null){
            sDT = bundle.getString("sdt");
        }
        //khởi tạo kết nối csdl
        userDAO = new UserDAO(this);
        //Viết sự kiện cho nút đăng nhập
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validatePassWord()){
                    return;
                }
                //truyền dữ liệu vào obj UserDTO
                UserDTO userDTO = new UserDTO();
                userDTO.setMATKHAU(matKhau);
                userDTO.setSDT(sDT);
                //
                long ktra = userDAO.ThemUser(userDTO);
                if(ktra != 0){
                    Toast.makeText(FormLoginAcitivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    callLoginFromRegister();
                }else{
                    Toast.makeText(FormLoginAcitivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Hàm quay lại màn hình chính
    public void backFromLogin(View view)
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        //tạo animation cho thành phần
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.layoutLogin),"transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(FormLoginAcitivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    //Hàm chuyển màn hình khi hoàn thành
    public void callLoginFromRegister(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private boolean validatePassWord(){
        String val = txtmk.getText().toString().trim();
        if(val.isEmpty()){
            txtmk.setError("không được để trống");
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(val).matches()){
            txtmk.setError("Mật khẩu ít nhất 6 ký tự");
            return false;
        }
        else{
            txtmk.setError(null);
            txtmk.setEnabled(false);
            return true;
        }
    }
}