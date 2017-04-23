package com.example.shubhraj.notesp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class CatalogActivity extends AppCompatActivity implements LoginFragment.OnLoginButton,
        NoteListFragment.OnNoteListListener, EditorFragment.OnEditorExit
{
    public static final String MYPREFERENCES = "MyPrefs";
    public static final String Name = "username";
    public static final String Password = "password";

    SharedPreferences sharedPreferences;

    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        sharedPreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Name, "SP");
        editor.putString(Password, "1234");
        editor.commit();
        contextOfApplication = getApplicationContext();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        LoginFragment lFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString("USERNAME", sharedPreferences.getString(Name,"Username"));
        bundle.putString("PASSWORD", sharedPreferences.getString(Password, "0000"));
        lFragment.setArguments(bundle);
        transaction.add(R.id.list_container,lFragment);
        transaction.commit();
    }


    @Override
    public void onLoginButton(Uri uri)
    {
        if(uri.toString().equals("SUCCESSFULL"))
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            NoteListFragment listFragment = new NoteListFragment();
            transaction.replace(R.id.list_container,listFragment);
            transaction.commit();
        }
    }

    @Override
    public void onNoteListListener(Uri uri)
    {
        String listFragment = uri.toString();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(listFragment.equals("NEW"))
        {
            EditorFragment newNote = new EditorFragment();
            setTitle(getString(R.string.editor_activity_title_new_pet));
            invalidateOptionsMenu();
            Bundle bundle = new Bundle();
            bundle.putString("ID",listFragment);
            newNote.setArguments(bundle);
            transaction.replace(R.id.list_container, newNote);
            transaction.commit();
        }
        else
        {
            EditorFragment editNote = new EditorFragment();
            setTitle(getString(R.string.editor_activity_title_edit_pet));
            Bundle bundle = new Bundle();
            bundle.putString("ID",listFragment);
            editNote.setArguments(bundle);
            transaction.replace(R.id.list_container, editNote);
            transaction.commit();
        }
    }

    @Override
    public void onEditorExit(Uri uri)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        NoteListFragment listFragment = new NoteListFragment();
        transaction.replace(R.id.list_container,listFragment);
        transaction.commit();
        setTitle(R.string.app_name);
    }
}
