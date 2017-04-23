package com.example.shubhraj.notesp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnLoginButton} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    private TextView loginID, loginPassword;
    private Button loginButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnLoginButton mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, param1);
        args.putString(PASSWORD, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(USERNAME);
            mParam2 = getArguments().getString(PASSWORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginID = (EditText) view.findViewById(R.id.userID);
        loginPassword = (TextView) view.findViewById(R.id.password);
        loginButton = (Button) view.findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginID.getText().toString();
                String password = loginPassword.getText().toString();
                if(username.length()==0|| password.length()==0)
                {
                    Toast.makeText(getContext(),"USERNAME AND PASSWORD CANNOT BE EMPTY",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    if((username.equals(mParam1))&&(password.equals(mParam2)))
                    {
                        Uri uri = Uri.parse("SUCCESSFULL");
                        onButtonPressed(uri);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "WRONG USER/PASS",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onLoginButton(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginButton) {
            mListener = (OnLoginButton) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginButton");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnLoginButton {
        // TODO: Update argument type and name
        void onLoginButton(Uri uri);
    }
}
