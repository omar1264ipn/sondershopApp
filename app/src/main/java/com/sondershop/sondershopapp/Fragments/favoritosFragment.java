package com.sondershop.sondershopapp.Fragments;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sondershop.sondershopapp.R;

public class favoritosFragment extends Fragment implements View.OnClickListener {


    DrawerLayout drawerLayout;
    ImageView navigationBar;
    LinearLayout ll_First, ll_Second, ll_Third, ll_Fourth, ll_Fifth, ll_Sixth, ll_Seventh;
    NavigationView navigationView;
    private View view;
    TextView your_orders, online_ordering_help, address_book, favorite_orders, send_feedback, report_safety_emergency, rate_playstore;
    private RelativeLayout loginSignIUp;

    public favoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_videos, container, false);
        onSetNavigationDrawerEvents();
        return view;
    }

    private void onSetNavigationDrawerEvents() {
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) view.findViewById(R.id.navigationView);
        navigationBar = (ImageView) view.findViewById(R.id.navigationBar);

        // aqui van los findviewid de cada elemento
        loginSignIUp = (RelativeLayout) view.findViewById(R.id.relativeLayout2);
        your_orders = (TextView) view.findViewById(R.id.your_orders);
        online_ordering_help = (TextView) view.findViewById(R.id.online_ordering_help);
        address_book = (TextView) view.findViewById(R.id.address_book);
        favorite_orders = (TextView) view.findViewById(R.id.favorite_orders);
        send_feedback = (TextView) view.findViewById(R.id.send_feedback);
        report_safety_emergency = (TextView) view.findViewById(R.id.report_safety_emergency);
        rate_playstore = (TextView) view.findViewById(R.id.rate_playstore);

        navigationBar.setOnClickListener(this);
        loginSignIUp.setOnClickListener(this);
        your_orders.setOnClickListener(this);
        online_ordering_help.setOnClickListener(this);
        address_book.setOnClickListener(this);
        favorite_orders.setOnClickListener(this);
        send_feedback.setOnClickListener(this);
        report_safety_emergency.setOnClickListener(this);
        rate_playstore.setOnClickListener(this);
        //  two.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigationBar:
                drawerLayout.openDrawer(navigationView, true);
                break;
            case R.id.relativeLayout2:
                Toast.makeText(getContext(), "loginSignIUp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.your_orders:
                Toast.makeText(getContext(), "your_orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.address_book:
                Toast.makeText(getContext(), "address_book", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite_orders:
                Toast.makeText(getContext(), "favorite_orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.send_feedback:
                Toast.makeText(getContext(), "send_feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.report_safety_emergency:
                Toast.makeText(getContext(), "report_safety_emergency", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}