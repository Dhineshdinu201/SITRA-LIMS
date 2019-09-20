package com.example.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ActivityAddPaymentMode extends Activity {
    String[] current={""};
    String current_date;
    ImageView pay_date;
    final String[] too = {""};
    String to=" ";
    Bundle tempBundle = new Bundle();

    Button GetImageFromGalleryButton, UploadImageOnServerButton,pay_cancel;

    ImageView ShowSelectedImage;

    EditText GetImageName,pay_ref,pay_val,pay_remarks;
    TextView txt_paydate;

    Bitmap FixBitmap;
    String cusid;

    String ImageTag = "image_tag" ;

    String ImageName = "image_data" ;
    Constant constant=new Constant();
    String ServerUploadPath =constant.ip+"upload_payment_documents" ;

    ProgressDialog progressDialog ;

    ByteArrayOutputStream byteArrayOutputStream ;

    byte[] byteArray ;

    String ConvertImage ;

    String GetImageNameFromEditText;

    HttpURLConnection httpURLConnection ;

    URL url;

    OutputStream outputStream;

    BufferedWriter bufferedWriter ;

    int RC ;

    BufferedReader bufferedReader ;

    StringBuilder stringBuilder;

    boolean check = true;
    Spinner spinner_department;
    String paymode="";
    String payref="";
    String payval="";
    String payremarks="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_mode);
        pay_cancel=(Button)findViewById(R.id.pay_cancel);

        GetImageFromGalleryButton = (Button)findViewById(R.id.button);

        UploadImageOnServerButton = (Button)findViewById(R.id.button2);
        txt_paydate=(TextView)findViewById(R.id.txt_date);


        ShowSelectedImage = (ImageView)findViewById(R.id.imageView);

        GetImageName = (EditText)findViewById(R.id.editText);
        pay_date=(ImageView)findViewById(R.id.pay_date);
        pay_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showwdialog_to();
            }
        });
        pay_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        spinner_department=(Spinner)findViewById(R.id.pay_mode);
        String[]dept={"Cash","Cheque","DD","Online Payment","SITRA Payment gateway"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,dept);
        spinner_department.setAdapter(adapter);
        spinner_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        paymode="1";
                        break;
                    case 1:
                        paymode="2";
                        break;
                    case 2:
                        paymode="3";
                        break;
                    case 3:
                        paymode="4";
                        break;
                    case 4:
                        paymode="5";

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }




            });
        try {
            cusid = getIntent().getStringExtra("cusid");
        }
        catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(this, "try login again and refresh", Toast.LENGTH_SHORT).show();
        }
        pay_ref=(EditText)findViewById(R.id.pay_ref);
        pay_val=(EditText)findViewById(R.id.pay_val);
        pay_remarks=(EditText)findViewById(R.id.pay_remarks);





        byteArrayOutputStream = new ByteArrayOutputStream();

        GetImageFromGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });


        UploadImageOnServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetImageNameFromEditText = GetImageName.getText().toString();

                UploadImageToServer();
//                Intent intent=new Intent(ActivityAddPaymentMode.this,ActivityAddPaymentMode.class);
//                startActivity(intent);
                onCreate(tempBundle);

            }
        });
    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                FixBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                ShowSelectedImage.setImageBitmap(FixBitmap);
                ShowSelectedImage.setVisibility(View.VISIBLE);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void UploadImageToServer() {
        if (pay_ref.getText().toString().isEmpty() || pay_remarks.getText().toString().isEmpty() || pay_val.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill required filled before uploading", Toast.LENGTH_SHORT).show();
        } else {
            try {

                FixBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                byteArray = byteArrayOutputStream.toByteArray();

                ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                class AsyncTaskUploadClass extends AsyncTask<Void, Void, String> {

                    @Override
                    protected void onPreExecute() {

                        super.onPreExecute();

                        progressDialog = ProgressDialog.show(ActivityAddPaymentMode.this, "Image is Uploading", "Please Wait", false, false);
                    }

                    @Override
                    protected void onPostExecute(String string1) {

                        super.onPostExecute(string1);
                        Log.i("postexe", "******" + string1);

                        progressDialog.dismiss();

                        Toast.makeText(ActivityAddPaymentMode.this, string1, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Void... params) {

                        payref = pay_ref.getText().toString();
                        payval = pay_val.getText().toString();
                        payremarks = pay_remarks.getText().toString();

                        ImageProcessClass imageProcessClass = new ImageProcessClass();

                        HashMap<String, String> HashMapParams = new HashMap<String, String>();

                        HashMapParams.put("pay_date", to);
                        HashMapParams.put("pay_mode", paymode);
                        HashMapParams.put("pay_reference", payref);
                        HashMapParams.put("pay_value", payval);
                        HashMapParams.put("pay_remarks", payremarks);
                        HashMapParams.put(ImageTag, GetImageNameFromEditText);
                        HashMapParams.put("cusid", cusid);

                        HashMapParams.put(ImageName, "data:image/jpeg;base64," + ConvertImage);
                        Log.i("base64", "************" + ConvertImage);
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(ServerUploadPath)
                                .build();
                        try {
                            Response response = client.newCall(request).execute();
                            Log.i("res1", "+++++++" + response);
                            Log.i("res2", "********" + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

                        return FinalData;
                    }
                }
                AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
                AsyncTaskUploadClassOBJ.execute();
            } catch (NullPointerException e) {
                Toast.makeText(this, "Please select the image", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {
                url = new URL(requestURL);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(20000);

                httpURLConnection.setConnectTimeout(20000);

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);

                httpURLConnection.setDoOutput(true);

                outputStream = httpURLConnection.getOutputStream();

                bufferedWriter = new BufferedWriter(

                        new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.write(bufferedWriterDataFN(PData));

                bufferedWriter.flush();

                bufferedWriter.close();

                outputStream.close();

                RC = httpURLConnection.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReader.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            stringBuilder = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)
                    check = false;
                else
                    stringBuilder.append("&");

                stringBuilder.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilder.append("=");

                stringBuilder.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilder.toString();
        }

    }
    public void showwdialog_to(){
        final Activity activity = null;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        DatePicker datePicker;
        final TextView date;
        Button ok,cancel;
        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater factory = LayoutInflater.from(this);
        final View v = factory.inflate(R.layout.alert_date_picker, null);
        datePicker=(DatePicker)v.findViewById(R.id.date_picker);
        ok=(Button)v.findViewById(R.id.ok);
        cancel=(Button)v.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        Calendar calendar=Calendar.getInstance();
        current[0]=""+calendar.get(Calendar.DAY_OF_MONTH)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR) ;
        current_date=current[0];
        to=current_date;
        Log.i("currentdate",current_date);

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int mon=monthOfYear+1;
                too[0] =dayOfMonth+"-"+mon+"-"+year;
                to=too[0];

                Log.i("too", String.valueOf(too));
                Log.i("to", to);
                try {
                    Daybetween( to, current_date, "dd-mm-yyyy");
                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }
        });







        alertDialog.setView(v);
        alertDialog.show();
        alertDialog.setCancelable(false);




    }
    public void Daybetween(String date1,String date2,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date Date1 = null,Date2 = null;
        try{
            Date1 = sdf.parse(date1);
            Date2 = sdf.parse(date2);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        long val=(Date1.getTime() - Date2.getTime())/(24*60*60*1000);
        Log.i("date", String.valueOf(val));
        txt_paydate.setText(to);
        if(val>0){
            to="";
            txt_paydate.setText(to);
            Toast.makeText(this, "Enter Valid Date", Toast.LENGTH_SHORT).show();

        }
    }
//    @Override
//    public void onBackPressed(){
//        super.onBackPressed();
//        Intent intent=new Intent(ActivityAddPaymentMode.this,MemberLoginMenu.class);
//        intent.putExtra("cusid",cusid);
//        startActivity(intent);
//
//    }

}
