package com.example.studentprofilebuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SelectAvatarFragment extends Fragment {

    ImageView iv_1;
    ImageView iv_2;
    ImageView iv_3;
    ImageView iv_4;
    ImageView iv_5;
    ImageView iv_6;

    Profile profile;



    private OnFragmentInteractionListener mListener;

    public SelectAvatarFragment() {
        // Required empty public constructor
    }


    public SelectAvatarFragment(Profile profile) {
        // Required empty public constructor
        this.profile = profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_avatar, container, false);
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
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Select Avatar");

        iv_1 = getActivity().findViewById(R.id.iv_1);
        iv_2 = getActivity().findViewById(R.id.iv_2);
        iv_3 = getActivity().findViewById(R.id.iv_3);
        iv_4 = getActivity().findViewById(R.id.iv_4);
        iv_5 = getActivity().findViewById(R.id.iv_5);
        iv_6 = getActivity().findViewById(R.id.iv_6);

        iv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setDrawablePath(R.drawable.avatar_f_1);
                //saveToSharedPreferences(profile);
                mListener.onImageSelected(profile);

            }
        });

        iv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setDrawablePath(R.drawable.avatar_f_2);
                mListener.onImageSelected(profile);
            }
        });

        iv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setDrawablePath(R.drawable.avatar_f_3);
                mListener.onImageSelected(profile);
            }
        });

        iv_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setDrawablePath(R.drawable.avatar_m_1);
                mListener.onImageSelected(profile);

            }
        });

        iv_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setDrawablePath(R.drawable.avatar_m_2);
                mListener.onImageSelected(profile);
            }
        });

        iv_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setDrawablePath(R.drawable.avatar_m_3);
                mListener.onImageSelected(profile);
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
        void onImageSelected(Profile profile);
    }
}
