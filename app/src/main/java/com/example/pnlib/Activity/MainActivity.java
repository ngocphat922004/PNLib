package com.example.pnlib.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Fragment.Add_book;
import com.example.pnlib.Fragment.FragmentDoiMK;
import com.example.pnlib.Fragment.Fragment_list_Book;
import com.example.pnlib.Fragment.Fragment_theloai;
import com.example.pnlib.Fragment.Fragment_thongke;
import com.example.pnlib.Fragment.Fragment_thuthu;
import com.example.pnlib.Fragment.Fragmentlistphieumuon;
import com.example.pnlib.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changelayout(new Fragment_list_Book());

        NavigationView navigationView = findViewById(R.id.Navigation_view);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.Toolbar);
        TextView tvback = findViewById(R.id.back);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
        );
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String chucvu = bundle.getString("chucvu");
            if (chucvu.equals("Admin")) {
                Menu menu = navigationView.getMenu();
                menu.getItem(5).setVisible(true);
            }

        }


        //bắt sự kiện click vào navigationview
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.themsach){
                    changelayout(new Add_book());
                    drawerLayout.close();
                }else if (item.getItemId() == R.id.qlpm){
                    changelayout(new Fragmentlistphieumuon());
                    drawerLayout.close();
                }else if (item.getItemId() == R.id.qlls){
                    changelayout(new Fragment_theloai());
                    drawerLayout.close();
                }else if (item.getItemId() == R.id.qls){
                    changelayout(new Fragment_list_Book());
                    drawerLayout.close();
                }else if (item.getItemId() == R.id.qltv){

                }else if (item.getItemId() == R.id.top10){

                }else if (item.getItemId() == R.id.danhthu){
                    changelayout(new Fragment_thongke());
                    drawerLayout.close();
                }else if (item.getItemId() == R.id.doimk){
                    changelayout(new FragmentDoiMK());
                    drawerLayout.close();
                }else if (item.getItemId() == R.id.logout){
                    onBackPressed();
                    //Xóa check đã đăng nhập xác định đăng xuất
                    SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("login");
                    editor.apply();
                } else if (item.getItemId() == R.id.qltt) {
                    changelayout(new Fragment_thuthu());
                    drawerLayout.close();
                }
                return true;
            }
        });
        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changelayout(new Fragment_list_Book());
            }
        });
    }
    public void changelayout(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentmain,fragment);
        fragmentTransaction.commit();
    }
}