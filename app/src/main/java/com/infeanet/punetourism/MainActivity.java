package com.infeanet.punetourism;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.infeanet.punetourism.databinding.ActivityMainBinding;
import com.infeanet.punetourism.ui.DNAFragment;
import com.infeanet.punetourism.ui.home.HomeFragment;
import com.infeanet.punetourism.ui.menus_fragment.AboutPuneFragment;

//AppCompatActivity manages Activity life cycle.
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Home Page will be here..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();

                Snackbar mySnackbar = Snackbar.make(view,"Do you want to open DNA's github account?", Snackbar.LENGTH_SHORT);
                mySnackbar.setAction("Yes",(v) ->{
                    //show about_Pune fragment there
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.nav_host_fragment_content_main,new DNAFragment());
                    fragmentTransaction.commit();
                });
                mySnackbar.show();

            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_colleges, R.id.nav_events, R.id.nav_hotels,
                R.id.nav_malls, R.id.nav_places,R.id.nav_forts)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.menu_about_pune)
        {
            //show about_Pune fragment there
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.nav_host_fragment_content_main,new AboutPuneFragment());
            fragmentTransaction.commit();
        }
        if(id==R.id.menu_exit)
        {
            exitAlertDialog();
        }
        if(id==R.id.menu_settings)
        {
//            Toasty.success(MainActivity.this, "Success!", Toast.LENGTH_SHORT, true).show();
//            Toasty
        }
        if(id==R.id.menu_share_app)
        {
            Intent intent =new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,"hey i have created Pune Tourism App..download from <playstore link> ");
            startActivity(Intent.createChooser(intent, "share this news using..."));

        }
        return super.onOptionsItemSelected(item);
    }

    private void exitAlertDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Exit!");
        builder.setIcon(R.drawable.baseline_home_24);
        builder.setMessage("Do you want to exit App?");

        builder.setPositiveButton("Yes", (dialog, which)-> {
                    finish();
                }
        );
        builder.setNegativeButton("No", (dialog, which)-> {
                    dialog.dismiss();
                }
        );

        builder.setNeutralButton("Cancel", (dialog, which)-> {
                    dialog.dismiss();
                }
        );
        builder.show();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        exitAlertDialog();
    }

}