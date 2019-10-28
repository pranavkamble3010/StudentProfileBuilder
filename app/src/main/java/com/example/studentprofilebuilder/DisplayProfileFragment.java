package com.example.studentprofilebuilder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DisplayProfileFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Profile profile;

    private TextView fullName;
    private TextView studentID;
    private TextView department;
    private Button btnEdit;
    private ImageView displayPic;

    public DisplayProfileFragment() {
        // Required empty public constructor
    }

    public DisplayProfileFragment(Profile profile){
        this.profile = profile;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_profile, container, false);
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

        getActivity().setTitle("Display My Profile");

        fullName = getActivity().findViewById(R.id.lbl_fullName);
        studentID = getActivity().findViewById(R.id.lbl_stuid);
        department = getActivity().findViewById(R.id.lbl_dept);
        btnEdit = getActivity().findViewById(R.id.btn_edit);
        displayPic = getActivity().findViewById(R.id.iv_displayPic);

        fullName.setText(this.profile.getFname()+" "+this.profile.getLname());
        studentID.setText(this.profile.getStuId().toString());
        department.setText(this.profile.getDepartment());
        //displayPic.setImageDrawable(this.profile.getImageDrawable());
        displayPic.setImageResource(this.profile.getDrawablePath());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.editProfile(profile);
            }
        });


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
        void editProfile(Profile profile);
    }
}
