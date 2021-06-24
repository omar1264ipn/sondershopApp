package com.sondershop.sondershopapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.sondershop.sondershopapp.EmailLoginRegister.EmailRegisterActivity;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.detalleVenta.carritoUserAdapter;
import com.sondershop.sondershopapp.detalleVenta.db.AppDatabase;
import com.sondershop.sondershopapp.detalleVenta.db.User;
import com.sondershop.sondershopapp.splashscreen;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class carritoFragment extends Fragment {

    private carritoUserAdapter carritoUserAdapter;
    Button addNewUserButton;
    public static TextView precioTotal;
    carritoUserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_gold, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        carritoUserAdapter = new carritoUserAdapter(this.getActivity());
        recyclerView.setAdapter(carritoUserAdapter);

        return root;
    }

    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getActivity());
        List<User> userList =db.userDaoInterf().getAllUsers();
        carritoUserAdapter.setUserList(userList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadUserList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
