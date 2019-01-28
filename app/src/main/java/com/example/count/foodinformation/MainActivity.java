package com.example.count.foodinformation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import Model.CategoryClass;
import Model.CategoryLanguage;
import Model.CreateUserClass;
import Model.DynamicPreference;
import Model.DynamicPreference2;
import Model.ErrorClass;
import Model.LanguagesClass;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Service service;
    private BottomNavigationView nav;
    private FrameLayout frameLayout;
    public static MainActivity mainActivity;
    public FragmentLogin fragmentLogin;
    public FragmentProfile fragmentProfile;
    public FragmentShowInfo fragmentShowInfo;
    public FragmentSearch fragmentSearch;
    public FragmentAllUsers fragmentAllUsers;
    public FragmentCategoryMod fragmentCategoryMod;
    public FragmentAllProduct fragmentAllProduct;
    public  Scanner scanner ;
    public static Fragment active;
    private Spinner spinner;
    public static String Language;
    public static List<String> LanguageCodes = new ArrayList<>();
    public static Map<String,String> Errors = new TreeMap<>();
    public static Map<String,String> Categories = new TreeMap<>();
    public static Map<String,String> Languages = new TreeMap<>();
    public static ArrayList<CharSequence> arrayList = new ArrayList<>();
    public static List<String> llist ;
    public static MenuItem settings,product,Categorize,users;

    public static String GetErrorMessage(Response response) {
        Gson gson = new Gson();
        try {
            CreateUserClass r = gson.fromJson(response.errorBody().string(), CreateUserClass.class);
            if (r != null)
                return (MainActivity.Errors.get(r.getMessage()));
            else
                return "Unknown Error" + r.getMessage();
            //return  MainActivity.Errors.get(ErrorCode);
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown Error";
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.Settings)
        {
            startActivity(new Intent(this,SettingsActivity.class));
        }
        else if(item.getItemId()== R.id.Categorize)
        {
            setFragment(fragmentCategoryMod,false);
        }
        else if(item.getItemId()== R.id.product)
        {
            setFragment(fragmentAllProduct,false);
        }
        else if(item.getItemId()== R.id.Users)
        {
            setFragment(fragmentAllUsers,false);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        product = menu.findItem(R.id.product);
        Categorize = menu.findItem(R.id.Categorize);
        users = menu.findItem(R.id.Users);
        product.setVisible(false);
        users.setVisible(false);
        Categorize.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        nav = findViewById(R.id.nav);
        fragmentLogin = new FragmentLogin();
        fragmentProfile = new FragmentProfile();
        fragmentShowInfo = new FragmentShowInfo();
        fragmentSearch = new FragmentSearch();
        fragmentAllUsers = new FragmentAllUsers();
        fragmentAllProduct = new FragmentAllProduct();
        fragmentCategoryMod = new FragmentCategoryMod();
        fragmentSearch = new FragmentSearch();
        scanner = new Scanner();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        123);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);
        }
        findViewById(R.id.btnStart).setOnClickListener(this);

        nav.setOnNavigationItemSelectedListener(menuItem -> {
            findViewById(R.id.lyWelcome).setVisibility(View.INVISIBLE);
            if(scanner.getBarcode)scanner.getBarcode=false;
            if(fragmentLogin.addproduct)fragmentLogin.addproduct= false;
            if(fragmentLogin.addcontent)fragmentLogin.addcontent= false;
            switch (menuItem.getItemId()) {
                case R.id.nav_login:
                    if(FragmentLogin.UserID==0)
                        setFragment(fragmentLogin);
                    else
                        setFragment(fragmentProfile);
                    return true;
                case R.id.nav_scan:
                    setFragment(scanner);
                    return true;
                case R.id.nav_search:
                    setFragment(fragmentSearch);
                    return true;
                default:
                    return false;
            }
        });
        frameLayout = findViewById(R.id.frame_layout);
        service = Common.GetService();
        GetErrorList();
        Language = PreferenceManager.getDefaultSharedPreferences(this).getString("Language", "");
        if(!Language.equals(""))
            SkipWelcome();
        //else
        {
            spinner = findViewById(R.id.spinner);
            GetLanguageList();


        }
    }
    public void GetLanguageList()
    {
        service.Languages().enqueue(new Callback<LanguagesClass>() {
            @Override
            public void onResponse(Call<LanguagesClass> call, Response<LanguagesClass> response) {
                if(response.isSuccessful())
                {
                ArrayAdapter<CharSequence> arrayAdapter;
                arrayAdapter = new ArrayAdapter<CharSequence>(MainActivity.this,support_simple_spinner_dropdown_item);
                String Language =  Locale.getDefault().getDisplayLanguage();
                llist = new ArrayList<>();
                List<String> dlist = new ArrayList<>();
                int Index = -1;
                for(int i = 0 ;i<response.body().getResult().length-1;i++)
                {
                    if (response.body().getResult()[i].getLanguageName()==null)continue;
                    if(Index == -1 &&  response.body().getResult()[i].getLanguageName().equalsIgnoreCase(Language))
                        Index = i;
                    llist.add(response.body().getResult()[i].getLanguageName());
                    dlist.add(response.body().getResult()[i].getLanguageCode());
                    arrayAdapter.add(response.body().getResult()[i].getLanguageName());
                    LanguageCodes.add(response.body().getResult()[i].getLanguageCode());
                }
                spinner.setAdapter(arrayAdapter);
                DynamicPreference.Languages = llist.toArray(new String[llist.size()]);
                DynamicPreference.Values = dlist.toArray(new String[dlist.size()]);
                DynamicPreference2.Languages = new String[]{"Türkçe","English","Polski","Deutsche"};
                DynamicPreference2.Values = new String[]{"tr","en","pl","de"};
                if(Index!=-1)
                    spinner.setSelection(Index);
                }
                else
                {
                   Toast.makeText(MainActivity.this,getString(R.string.LanGeEror),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LanguagesClass> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Language getting error failure",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void GetErrorList()
    {
        service.Errors().enqueue(new Callback<ErrorClass>() {
            @Override
            public void onResponse(Call<ErrorClass> call, Response<ErrorClass> response) {
                if(response.isSuccessful())
                {
                    for(int i = 0 ; i<response.body().getResult().length;i++)
                        Errors.put(response.body().getResult()[i].getErrorCode(),response.body().getResult()[i].getErrorMessage());
                }
                else
                {
                    Toast.makeText(MainActivity.this,getString(R.string.ErrorGet),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ErrorClass> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error getting error failure",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void GetCategoryList()
    {
        Categories.clear();
        arrayList.clear();
        service.Categories(new CategoryLanguage(Language)).enqueue(new Callback<CategoryClass>() {
            @Override
            public void onResponse(Call<CategoryClass> call, Response<CategoryClass> response) {
                if(response.isSuccessful())
                {
                    for(int i = 0 ; i<response.body().getResult().length;i++)
                    {
                        Categories.put(response.body().getResult()[i].getCategoryName(),String.valueOf(response.body().getResult()[i].getID()));
                        arrayList.add(response.body().getResult()[i].getCategoryName());
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,getString(R.string.ErrorGetCat),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryClass> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error getting Category failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnStart)
        {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("Language", String.valueOf(LanguageCodes.get(spinner.getSelectedItemPosition()))).apply();
            SkipWelcome();
        }
    }
    private void SkipWelcome()
    {
        findViewById(R.id.lyWelcome).setVisibility(View.INVISIBLE);
        nav.setVisibility(View.VISIBLE);
        setFragment(fragmentLogin);//setFragment(fragmentLogin);//
        GetCategoryList();
    }
    private void setFragment(Fragment fragment){setFragment(fragment,true);}
    private void setFragment(Fragment fragment,boolean backtostack)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        if(backtostack)fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}


