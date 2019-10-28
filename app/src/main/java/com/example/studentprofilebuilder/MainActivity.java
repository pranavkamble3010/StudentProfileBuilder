package com.example.studentprofilebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/***
 * Team 143 members:
 * Pranav V. Kamble
 * Venky S. Hegde
 * */

public class MainActivity extends AppCompatActivity
        implements MyProfileFragment.OnFragmentInteractionListener,
        SelectAvatarFragment.OnFragmentInteractionListener,
        DisplayProfileFragment.OnFragmentInteractionListener{

    private Profile profile;

    void saveToSharedPreferences(Profile profile){
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson json = new Gson();
        String profileJSON = json.toJson(profile);

        editor.putString("profile",profileJSON);
        editor.commit();
    }


    Object loadFromSharedPreferences(String key, Type classType){
        Gson gson = new Gson();

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        String json = sp.getString(key, "");
        Object object = gson.fromJson(json, classType);

        return object;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = (Profile) loadFromSharedPreferences("profile",Profile.class);

        if(profile == null){
            this.profile = new Profile();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new MyProfileFragment(profile),"tag_MyProfile")
                    .commit();

        }

        else{
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new DisplayProfileFragment(profile),"tag_MyProfile")
                    .commit();
        }

    }

    @Override
    public void onProfileSaved(Profile profile) {

        Log.d("MainActivity", "onProfileSaved: "+profile.toString());

        saveToSharedPreferences(profile);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.container,new DisplayProfileFragment(profile),"tag_displayProfile")
                .commit();

    }

    @Override
    public void OnSelectImageClicked(Profile profile) {
        Log.d("MainActivity", "OnSelectImageClicked: "+profile.toString());
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container,new SelectAvatarFragment(profile),"tag_selectAvatar")
                .commit();

    }


    @Override
    public void onImageSelected(Profile profile) {
        Log.d("MainActivity", "onImageSelected: "+profile.toString());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new MyProfileFragment(profile),"tag_MyProfile")
                .commit();

    }

    @Override
    public void editProfile(Profile profile) {

        Log.d("MainActivity", "editProfile: "+profile.toString());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new MyProfileFragment(profile),"tag_MyProfile")
                .commit();

    }
}
