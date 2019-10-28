package com.example.studentprofilebuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MyProfileFragment extends Fragment {

    EditText fname, lname,stuId;
    RadioGroup rg;
    Button btn_save;
    ImageView iv_image;

    Profile profile;
    String selectedDept = "";

    private OnFragmentInteractionListener mListener;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    public MyProfileFragment(Profile profile) {
        // Required empty public constructor
        this.profile = profile;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("My Profile");

        fname = getActivity().findViewById(R.id.txt_fname);
        lname = getActivity().findViewById(R.id.txt_lname);
        stuId = getActivity().findViewById(R.id.txt_stuId);
        rg = getActivity().findViewById(R.id.rg);
        btn_save = getActivity().findViewById(R.id.btn_save);
        iv_image = getActivity().findViewById(R.id.iv_profilePic);


        //profile = (Profile) loadFromSharedPreferences("profile", Profile.class);

        if(profile!=null){
            Log.d("MyProfile", "onActivityCreated: "+profile.toString());

            fname.setText(this.profile.getFname());
            lname.setText(this.profile.getLname());
            if(profile.getStuId()!=null)
                stuId.setText(this.profile.getStuId().toString());

            if(this.profile.getDepartment()!=null){
                selectedDept = this.profile.getDepartment();
                switch (this.profile.getDepartment().toLowerCase()){

                    case "cs" :
                        rg.check(R.id.rb_cs);
                        break;

                    case "sis":
                        rg.check(R.id.rb_sis);
                        break;

                    case "bio":
                        rg.check(R.id.rb_bio);
                        break;

                    case "other":
                        rg.check(R.id.rb_other);
                        break;

                }
            }

//            if(profile.getImageDrawable()!=null)
//                iv_image.setImageDrawable(profile.getImageDrawable());
            if(profile.getDrawablePath()!=0)
                iv_image.setImageResource(profile.getDrawablePath());
        }

        //Set radiobutton listener
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = getActivity().findViewById(i);
                selectedDept = rb.getText().toString();
                Log.d("prf_frag", "onCheckedChanged: "+getActivity().findViewById(i));

            }
        });

        //Set profile_pic click listener
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile profile = new Profile();
                profile.setFname(fname.getText().toString());
                profile.setLname(lname.getText().toString());
                if(!stuId.getText().toString().equals(""))
                    profile.setStuId(Long.parseLong(stuId.getText().toString()));
                profile.setDepartment(selectedDept);
                mListener.OnSelectImageClicked(profile);
            }
        });


        //Set btn_save click listener
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    Profile saveProfile = new Profile();
                    saveProfile.setFname(fname.getText().toString());
                    saveProfile.setLname(lname.getText().toString());
                    saveProfile.setStuId(Long.parseLong(stuId.getText().toString()));
                    saveProfile.setDepartment(selectedDept);
                    //profile.setImageDrawable(iv_image.getDrawable());


                    //Log.d("MyProfileFrag", "onClick: "+getActivity().getResources().getResourceEntryName(profile.getDrawablePath()));
                    saveProfile.setDrawablePath(profile.getDrawablePath());
                    mListener.onProfileSaved(saveProfile);
                    //saveToSharedPreferences(profile);
                }


            }
        });

    }

    private boolean validate() {

        boolean flag = true;

        if(fname.getText().toString().equals("") || fname.getText().equals(null))
        {
            fname.setError("First name cannot be empty!");
            flag = false;
        }

        if(lname.getText().toString().equals("") || fname.getText().equals(null))
        {
            lname.setError("Last name cannot be empty!");
            flag = false;
        }

        if(stuId.getText().toString().equals("") || fname.getText().equals(null))
        {
            stuId.setError("Student ID cannot be empty!");
            flag = false;
        }

        try{
            Long.parseLong(stuId.getText().toString());
        }catch (Exception ex){
            stuId.setError("Only numeric values allowed!");
            flag = false;
        }

        if(selectedDept.equals("") || selectedDept.equals(null))
        {
            Toast.makeText(getContext(),"Please select department!",Toast.LENGTH_SHORT).show();
            flag = false;
        }

        if(profile.getDrawablePath() == 0){
                Toast.makeText(getContext(),"Please select avatar by clicking on the arrow!",Toast.LENGTH_SHORT).show();
                flag = false;
        }

        return flag;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onProfileSaved(Profile profile);
        void OnSelectImageClicked(Profile profile);
    }
}
