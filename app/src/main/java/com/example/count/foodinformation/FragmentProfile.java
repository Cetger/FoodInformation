package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment implements View.OnClickListener {

    public FragmentProfile() {
        // Required empty public constructor
    }
public static String name,surname,username,email;
EditText txName,txSurname,txUsername,txEmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() :  inflater.inflate(R.layout.fragment_profile, container, false);
        view.findViewById(R.id.btnLogOut).setOnClickListener(this);
        txName =  view.findViewById(R.id.txPName);
        txSurname =  view.findViewById(R.id.txPSurname);
        txUsername =  view.findViewById(R.id.txPUsername);
        txEmail =  view.findViewById(R.id.txPEMail);
        if(getArguments()!=null) {
          //  new Thread(() -> {
            //    getActivity().runOnUiThread(() -> {
                    txName.setText(getArguments().getString("NAME", ""));
                    name=getArguments().getString("NAME", "");
                    txSurname.setText(getArguments().getString("SURNAME", ""));
                    surname = getArguments().getString("SURNAME", "");
                    txUsername.setText(getArguments().getString("EMAIL", ""));
                    username = getArguments().getString("EMAIL", "");
                    txEmail.setText(getArguments().getString("USERNAME", ""));
                    email = getArguments().getString("USERNAME", "");
              //  });
            //});

        }
        else
        {
        if(name != null)txName.setText(name);
        if(surname != null)txSurname.setText(surname);
        if(username != null)txUsername.setText(username);
        if(email != null)txEmail.setText(email);

        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogOut)
        {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FragmentLogin()).commit();
            FragmentLogin.UserID = 0;
            MainActivity.users.setVisible(false);
            MainActivity.product.setVisible(false);
            MainActivity.Categorize.setVisible(false);
        }


    }
}
