package com.example.trnclong.bai7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText edtHeSoA, edtHeSoB, edtHeSoC;
    TextView txtKetQua;
    Button btnGiai, btnXoa;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        addEvents();
    }

    private void anhXa() {
        edtHeSoA = findViewById(R.id.edt_hsA);
        edtHeSoB = findViewById(R.id.edt_hsB);
        edtHeSoC = findViewById(R.id.edt_hsC);
        txtKetQua = findViewById(R.id.txt_KetQua);
        btnGiai = findViewById(R.id.btn_Giai);
        btnXoa = findViewById(R.id.btn_Xoa);
    }

    private void addEvents() {
        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heSoA = edtHeSoA.getText()+"";
                String heSoB = edtHeSoB.getText()+"";
                String heSoC = edtHeSoC.getText()+"";
                if(heSoA.isEmpty() || heSoB.isEmpty() || heSoC.isEmpty()) {

                    txtKetQua.setText("Error: Vui Long Nhap So");
                }
                else {
                    double a = Double.parseDouble(heSoA);
                    double b = Double.parseDouble(heSoB);
                    double c = Double.parseDouble(heSoC);
                    giaiPhuongTrinhBac2(a,b,c);
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtHeSoA.setText("");
                edtHeSoB.setText("");
                edtHeSoC.setText("");
                txtKetQua.setText("");
            }
        });
    }

    private void giaiPhuongTrinhBac2(double a, double b, double c) {
        try{
            if(a==0 && b==0 && c ==0 ) {
                txtKetQua.setText("Hệ Có Vô Số Nghiệm");
            }
            else if(a==0 && b==0 && c !=0 ) {
                txtKetQua.setText("Hệ Vô Nghiệm");
            }
            else if(a==0 && b!=0 && c ==0 || a!=0 && b==0 && c ==0   ) {
                txtKetQua.setText("0");
            }
            else if(a==0 && b!=0 && c !=0 ) {
                double x = -c /b;

                String ketQua = String.valueOf(decimalFormat.format(x));
                txtKetQua.setText(ketQua);
            }
            else if(a!=0 && b!=0 && c==0) {
                double x = -b/a;
                String ketQua = String.valueOf(decimalFormat.format(x));
                txtKetQua.setText("0 và "+ketQua);
            }
            else if(a!=0 && b==0 && c !=0 ) {
                if(a*c > 0) {
                    double x = Math.abs(c/a);
                    String ketQua = String.valueOf(decimalFormat.format(Math.sqrt(x)));
                    txtKetQua.setText(ketQua+"i và -"+ketQua+"i");
                }
                else {
                    double x =Math.abs(c/a);
                    String ketQua = String.valueOf(decimalFormat.format(Math.sqrt(x)));
                    txtKetQua.setText(ketQua+" và -"+ketQua);
                }
            }
            else if(a!=0 && b!=0 && c !=0) {
                double delta = b*b - 4*a*c;
                if(delta < 0 ) {
                    txtKetQua.setText("Hệ Vô Nghiệm");
                }
                else if(delta ==0) {
                    double x = -b / (2 *a);
                    String ketQua = String.valueOf(decimalFormat.format(x));
                    txtKetQua.setText(ketQua);
                }
                else {
                    double x1 =  (-b - Math.sqrt(delta) ) / (2*a);
                    double x2 =  (-b + Math.sqrt(delta) ) / (2*a);
                    String ketQua1 = String.valueOf(decimalFormat.format(x1));
                    String ketQua2 = String.valueOf(decimalFormat.format(x2));
                    txtKetQua.setText(ketQua1+" và "+ketQua2);
                }
            }
        }
        catch (Exception ex) {
            Toast.makeText(this,"Vui Long Nhap So",Toast.LENGTH_SHORT).show();
            txtKetQua.setText("Error");

        }
    }
}
