package com.example.myrestapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.myrestapplication.data.model.Identity;
import com.example.myrestapplication.data.model.IdentityLab;
import com.example.myrestapplication.data.model.LoggedInUser;
import com.example.myrestapplication.data.model.LoggedInUserLab;

import java.util.Map;

public class FirstFragment extends Fragment {

    private TextView textView_username;
    private TextView textView_secondname;
    private TextView textView_email;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoggedInUser user = LoggedInUserLab.getLoggedInUser();
        System.out.println("FirstFragment -> user: " + user.getUserName());

        textView_username = (TextView)view.findViewById(R.id.textview_usermane);
        textView_secondname = (TextView)view.findViewById(R.id.textview_secondname);
        textView_email= (TextView)view.findViewById(R.id.textview_email);

        IdentityLab identityLab = IdentityLab.get(user.getUserName());
        Identity iden = identityLab.getIdentity();
        Map map = iden.getViewableIdentityAttributes();

        textView_username.setText((String)map.get("Nombre"));
        textView_secondname.setText((String)map.get("Apellido"));
        textView_email.setText((String)map.get("Correo electrónico"));

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
                Intent intent = new Intent(getActivity(), WorkItemListActivity.class);
                startActivity(intent);
            }
        });
    }

}
