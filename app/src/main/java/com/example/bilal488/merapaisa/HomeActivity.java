package com.example.bilal488.merapaisa;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imagevieweasyload,imageviewmoneytransfer,imageviewBillPayment;

    //gallery image
    private static int IMG_RESULT=1;
    ImageView imageViewLoad;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //for circular image gallary
        View header=navigationView.inflateHeaderView(R.layout.nav_header_home);
        imageViewLoad=(ImageView)header.findViewById(R.id.circleimageView);
        imageViewLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,IMG_RESULT);
            }
        });////////

        imagevieweasyload=(ImageView)findViewById(R.id.imageVieweasyload);
        imagevieweasyload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EasyLoadActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
            }
        });

        imageviewmoneytransfer=(ImageView)findViewById(R.id.imageViewMoneyTransfer);
        imageviewmoneytransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MoneyTransferActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
            }
        });

        imageviewBillPayment=(ImageView)findViewById(R.id.imageViewBillPayment);
        imageviewBillPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BillPaymentsActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
            }
        });


    }//on create end//


    //////////////////set Image from gallery////////////////////
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode==RESULT_OK && requestCode==1 && null != data){
            decodeUri(data.getData());
        }
    }

    public void decodeUri(Uri uri){
        ParcelFileDescriptor parcelFD=null;
        try{
            parcelFD=getContentResolver().openFileDescriptor(uri,"r");
            FileDescriptor imageSource=parcelFD.getFileDescriptor();

            //Decode image size
            BitmapFactory.Options o= new BitmapFactory.Options();
            o.inJustDecodeBounds=true;
            BitmapFactory.decodeFileDescriptor(imageSource,null,o);

            //the new size we want to scale to
            final int REQUIRED_SIZE=1024;

            //Find the correct scale value. It should be the power of two
            int width_tmp=o.outWidth,height_tmp=o.outHeight;
            int scale=1;
            while (true){
                if (width_tmp<REQUIRED_SIZE && height_tmp<REQUIRED_SIZE){
                    break;
                }
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }

            //decode with in  sample size
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource,null,o2);

            imageViewLoad.setImageBitmap(bitmap);

        }catch (FileNotFoundException e){
            //handle error
        }catch (IOException e){
            //handle errors

        }finally {
            if (parcelFD!=null)
                try {
                    parcelFD.close();
                }catch (IOException e){
                    //ignored
                }
        }
    }///////////image from gallery end


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            open();
            //super.onBackPressed();
        }
    }

    //Dialog box
    public void open(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Alert");
        alertDialogBuilder.setMessage("Are you really want to exit");
             alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     //to close app
                     Intent intent = new Intent(getApplicationContext(),FirstActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     intent.putExtra("Exit me", true);
                     startActivity(intent);
                     finish();//to close app
                 }
             });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }//dialog box end


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_check) {

        } else if (id == R.id.nav_changepassword) {
            startActivity(new Intent(getApplicationContext(),ChangePasswordActivity.class));
            overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);

        } else if (id == R.id.nav_transaction) {
            startActivity(new Intent(getApplicationContext(),TransactionReportActivity.class));
            overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
            finish();

        } else if (id == R.id.nav_agent) {

        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
