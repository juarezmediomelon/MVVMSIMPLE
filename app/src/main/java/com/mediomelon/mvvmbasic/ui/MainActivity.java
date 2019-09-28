package com.mediomelon.mvvmbasic.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mediomelon.mvvmbasic.adapter.UserAdapter;
import com.mediomelon.mvvmbasic.model.Hero;
import com.mediomelon.mvvmbasic.adapter.HeroeAdapter;
import com.mediomelon.mvvmbasic.model.User;
import com.mediomelon.mvvmbasic.utils.InternetConnection;
import com.mediomelon.mvvmbasic.viewmodel.HeroeViewModel;
import com.mediomelon.mvvmbasic.R;
import com.mediomelon.mvvmbasic.viewmodel.UserViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.txtDataResource)
    TextView txtdataResource;
    private HeroeAdapter adapter;
    private UserAdapter userAdapter;
    private List<Hero> heroList;
    private List<User> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //comprobar si existe conexion a internet
        new InternetConnection(internet -> {
            if(!internet)
                Toast.makeText(getApplicationContext(), "sin conexion a internet", Toast.LENGTH_SHORT).show();
            else{
                Toast.makeText(getApplicationContext(), "con conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });

        //instancia de viewmodel
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        //pasar contexto necesario para room
        userViewModel.setContext(this);
        //observador de userviemodel
        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                if(userList != null) {
                        usersList = userList;
                    userAdapter = new UserAdapter(MainActivity.this, userList);
                    recyclerView.setAdapter(userAdapter);
                    userAdapter.notifyDataSetChanged();
                   txtdataResource.setText(userViewModel.getDataResource());
                   userItemClick();

                }else
                    Toast.makeText(getApplicationContext(), "SIN DATOS", Toast.LENGTH_SHORT).show();
            }
        });


    }
    //click de algun item especifico de userCardview
    public void userItemClick(){

        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(), usersList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
