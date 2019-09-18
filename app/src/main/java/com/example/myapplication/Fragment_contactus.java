package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.TELEPHONY_SERVICE;

public class Fragment_contactus  extends Fragment {

    //Declarations


    EditText et_name,et_location,et_email,et_phone,et_message;
    Button btn_submit,btn_cancel,btn_sitra_details;
    String name,location,email,phone,message;
    Spinner spinner_department;
    String deptid,dept_id;




    @Override
    @Nullable
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancetate) {
        View view = inflater.inflate(R.layout.fragment_contactus, container, false);

        //initialize

        et_name=(EditText)view.findViewById(R.id.et_name);
        et_location=(EditText)view.findViewById(R.id.et_location);
        et_email=(EditText)view.findViewById(R.id.et_email);
        et_phone=(EditText)view.findViewById(R.id.et_contact);

        et_message=(EditText)view.findViewById(R.id.et_message);
        spinner_department=(Spinner)view.findViewById(R.id.spinner_department);
        String[]dept={"Physics","Chemistry","COE","Engineering","Weaving"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,dept);
        spinner_department.setAdapter(adapter);
        spinner_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        deptid="Physics";
                        dept_id="0";
                        break;
                    case 1:
                        deptid="Chemistry";
                        dept_id="1";

                        break;
                    case 2:
                        deptid="COE";
                        dept_id="2";
                        break;
                    case 3:
                        deptid="Engineering";
                        dept_id="3";
                        break;
                    case 4:
                        deptid="Weaving";
                        dept_id="4";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit=(Button)view.findViewById(R.id.btn_submit);
        btn_cancel=(Button)view.findViewById(R.id.btn_cancel);
        btn_sitra_details=(Button)view.findViewById(R.id.btn_sitra_details);



        //****************************submit button************************************

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //edittext to string

                name = et_name.getText().toString();
                location = et_location.getText().toString();
                email = et_email.getText().toString();
                phone = et_phone.getText().toString();
                message = et_message.getText().toString();
                if (dept_id.isEmpty()||location.isEmpty()||email.isEmpty()||phone.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill the above details", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Query From Testing Laboratories");
                        intent.putExtra(Intent.EXTRA_TEXT, "Name :" + name + "\n" + "Location :" + location + "\n" + "Email :" + email + "\n" + "Phone" + " :" + phone + "\n"  + "message :" + message + "\n" + "Department :" + deptid);
                        if (dept_id.equals("0")) {
                            intent.setData(Uri.parse("mailto:physics@sitra.org.in"));
                        } else if (dept_id.equals("1")) {
                            intent.setData(Uri.parse("mailto:chem@sitra.org.in"));
                        } else if (dept_id.equals("2")) {
                            intent.setData(Uri.parse("mailto:coemed@sitra.org.in"));
                        } else if (dept_id.equals("3")) {
                            intent.setData(Uri.parse("mailto:engg@sitra.org.in"));
                        } else if (dept_id.equals("4")) {
                            intent.setData(Uri.parse("mailto:wvg@sitra.org.in"));
                        }
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


        //**************************Cancel button*********************************

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HomeScreen.class);
                startActivity(intent
                );
            }
        });


        //**************************sitra detail button*********************************
        btn_sitra_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //**********call intent***********************
                TextView call_fibre,call_yarn,call_fabric,call_sample_room,call_chemistry,call_knitting,call_engineering,call_sitra;
                TextView call_analytical,call_polymers,call_physics,call_micro,call_bio_tech,call_training;
                ImageView maplogo;
                TextView mail_phy,mail_che,mail_eng,mail_knitting,mail_analytical,mail_polymer,mail_coe_physics,mail_microbiology,mail_bio_technology,mail_training;



                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                AlertDialog alertDialog = dialogBuilder.create();
                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View view = factory.inflate(R.layout.sitra_details, null);
                call_fibre=(TextView)view.findViewById(R.id.call_fibre);
                call_yarn=(TextView)view.findViewById(R.id.call_yarn);
                call_fabric=(TextView)view.findViewById(R.id.call_fabric);
                call_sample_room=(TextView)view.findViewById(R.id.call_sample_room);
                call_chemistry=(TextView)view.findViewById(R.id.call_chem);
                call_knitting=(TextView)view.findViewById(R.id.call_knitting);
                call_engineering=(TextView)view.findViewById(R.id.call_eng);
                call_polymers=(TextView)view.findViewById(R.id.call_polymer);
                call_analytical=(TextView)view.findViewById(R.id.call_analytical);
                call_physics=(TextView)view.findViewById(R.id.call_physics);
                call_micro=(TextView)view.findViewById(R.id.call_microbiology);
                call_bio_tech=(TextView)view.findViewById(R.id.call_bio_technology);
                call_training=(TextView)view.findViewById(R.id.call_training);
                call_sitra=(TextView)view.findViewById(R.id.call_sitra);
                maplogo=(ImageView)view.findViewById(R.id.map_logo);
                mail_phy=(TextView)view.findViewById(R.id.mail_phy);
                mail_che =(TextView)view.findViewById(R.id.mail_che);
                mail_eng =(TextView)view.findViewById(R.id.mail_eng);
                mail_knitting=(TextView)view.findViewById(R.id.mail_knitting);
                mail_analytical=(TextView)view.findViewById(R.id.mail_analytical);
                mail_polymer=(TextView)view.findViewById(R.id.mail_polymer);
                mail_coe_physics=(TextView)view.findViewById(R.id.mail_coe_physics);
                mail_microbiology=(TextView)view.findViewById(R.id.mail_microbiology);
                mail_bio_technology=(TextView)view.findViewById(R.id.mail_bio_technology);
                mail_training=(TextView)view.findViewById(R.id.mail_training);




                //*********************map intent*******************************


                maplogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://www.google.co.in/maps/place/The+South+India+Textile+Research+Association+SITRA/@11.0391719,77.0342872,17z/data=!4m12!1m6!3m5!1s0x3ba8f81b41d4a5c7:0x2b897a4e8c4cae56!2sThe+South+India+Textile+Research+Association+SITRA!8m2!3d11.0391666!4d77.0364759!3m4!1s0x3ba8f81b41d4a5c7:0x2b897a4e8c4cae56!8m2!3d11.0391666!4d77.0364759?hl=en";
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                        getActivity().startActivity(intent);
                    }
                });





                    //*******************call intent*************************
                    call_fibre.setPaintFlags(call_fibre.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_fibre.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel: 04224215327"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });

                    call_yarn.setPaintFlags(call_yarn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_yarn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel: 04224215332"));
                                getActivity().startActivity(intent);
                            } catch (Exception e) {
                                Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();

                            }
                        }
                    });

                    call_fabric.setPaintFlags(call_fabric.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_fabric.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try{
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel: 04224215314"));
                                getActivity().startActivity(intent);
                            } catch (Exception e) {
                                Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();

                            }
                        }
                    });

                    call_sample_room.setPaintFlags(call_sample_room.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_sample_room.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {

                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel: 04224215303"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });

                    call_chemistry.setPaintFlags(call_chemistry.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_chemistry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel: 04224215328"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });

                    call_knitting.setPaintFlags(call_knitting.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_knitting.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try{
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel: 04224215341"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });

                    call_engineering.setPaintFlags(call_engineering.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_engineering.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel: 04224215344"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                            Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        }
                    });


                    call_sitra.setPaintFlags(call_sitra.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_sitra.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:+914222574367"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                            Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        }
                    });

                    call_analytical.setPaintFlags(call_analytical.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_analytical.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel: 04224215333"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });


                    call_polymers.setPaintFlags(call_polymers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_polymers.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel: 04224215333"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });


                    call_physics.setPaintFlags(call_physics.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_physics.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel: 04224215349"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });


                    call_micro.setPaintFlags(call_micro.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_micro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try{
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel: 04224215323"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });


                    call_bio_tech.setPaintFlags(call_bio_tech.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_bio_tech.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel: 04224215333"));
                                getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });


                    call_training.setPaintFlags(call_training.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    call_training.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {

                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel: 04224215333"));
                            getActivity().startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Toast.makeText(getContext(), "No Dialer Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });




                    mail_phy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                                intent.putExtra(Intent.EXTRA_TEXT, " ");
                                intent.setData(Uri.parse("mailto:physics@sitra.org.in")); // or just "mailto:" for blank
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                                startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                e.printStackTrace();
                                Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                mail_che.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:chem@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mail_eng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:engg@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mail_knitting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:wvg@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mail_analytical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:coeanalytical@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mail_polymer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:sitrameditech@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mail_coe_physics.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:coephy@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mail_microbiology.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:coebio@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mail_bio_technology.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:coebio@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mail_training.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "  ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setData(Uri.parse("mailto:techtextrg@sitra.org.in")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);
                        }catch (ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Mailer Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
        });




        //**************************bank detail button*********************************


        return view;
    }

    private boolean isTelephonyEnabled(){
        TelephonyManager tm = (TelephonyManager)getActivity().getSystemService(TELEPHONY_SERVICE);
        return tm != null && tm.getSimState()== TelephonyManager.SIM_STATE_READY;
    }
}