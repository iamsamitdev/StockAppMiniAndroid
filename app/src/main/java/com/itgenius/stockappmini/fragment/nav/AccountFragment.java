package com.itgenius.stockappmini.fragment.nav;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itgenius.stockappmini.R;
import com.itgenius.stockappmini.activity.LoginActivity;

public class AccountFragment extends Fragment {

    SharedPreferences pref;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        Button btnLogout = rootView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref = getContext().getSharedPreferences("pref_login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("pref_userid");
                editor.commit();

                // ทำการ Intent ไปหน้า login
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

}
