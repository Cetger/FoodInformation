package com.example.count.foodinformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import Model.CategoryClass;
import Model.CategoryLanguage;
import Model.DynamicPreference;
import Model.ErrorClass;
import Model.LanguagesClass;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private TextView txName;
//    private TextView txSurName;
    private Service service;
    private BottomNavigationView nav;
    private FrameLayout frameLayout;
    private FragmentAdd fragmentAdd;
    private FragmentLogin fragmentLogin;
    private FragmentSearch fragmentSearch;
    private FragmentProfile fragmentProfile;
    private Spinner spinner;
    private ArrayList<CharSequence> LanguageCodes = new ArrayList<>();
    private Map<String,String> Errors = new TreeMap<>();
    public static Map<Integer,String> Categories = new TreeMap<>();
    public static ArrayList<CharSequence> arrayList = new ArrayList<>();





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.Settings)
        {
            startActivity(new Intent(this,SettingsActivity.class));
           // Toast.makeText(getApplicationContext(),"text",Toast.LENGTH_SHORT).show();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Connection connection = new Connection();
//        txName = findViewById(R.id.txName);
//        txSurName = findViewById(R.id.txtSurname);
//        findViewById(R.id.button).setOnClickListener(this);
//        findViewById(R.id.btnGet).setOnClickListener(this);
//        findViewById(R.id.btnSend).setOnClickListener(this);
        nav = findViewById(R.id.nav);
        fragmentAdd = new FragmentAdd();
        fragmentLogin = new FragmentLogin();
        fragmentSearch = new FragmentSearch();
        fragmentProfile = new FragmentProfile();

        findViewById(R.id.btnStart).setOnClickListener(this);

        nav.setOnNavigationItemSelectedListener(menuItem -> {
            findViewById(R.id.lyWelcome).setVisibility(View.INVISIBLE);
            switch (menuItem.getItemId()) {
                case R.id.nav_login:
                    if(!Common.Logon)
                        setFragment(fragmentLogin);
                    else
                        setFragment(fragmentProfile);
                    return true;
                case R.id.nav_search:

                    setFragment(fragmentSearch);
                    return true;
                case R.id.nav_add:
                    setFragment(fragmentAdd);
                    return true;
                default:
                    return false;
            }
        });
        frameLayout = findViewById(R.id.frame_layout);
        service = Common.GetService();
        GetErrorList();
        if(!PreferenceManager.getDefaultSharedPreferences(this).getString("Language", "").equals(""))
            SkipWelcome();
        else
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
                ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<CharSequence>(MainActivity.this,support_simple_spinner_dropdown_item);
                String Language =  Locale.getDefault().getDisplayLanguage();
                List<String> llist = new ArrayList<>();
                List<String> dlist = new ArrayList<>();
                int Index = -1;
                for(int i = 0 ;i<response.body().getResult().length;i++)
                {
                    if(Index == -1 && response.body().getResult()[i].getLanguageName().equalsIgnoreCase(Language))
                        Index = i;
                    llist.add(response.body().getResult()[i].getLanguageName());
                    dlist.add(response.body().getResult()[i].getLanguageCode());
                    arrayAdapter.add(response.body().getResult()[i].getLanguageName());
                    LanguageCodes.add(response.body().getResult()[i].getLanguageCode());
                }
                spinner.setAdapter(arrayAdapter);
                DynamicPreference.Languages = llist.toArray(new String[llist.size()]);
                DynamicPreference.Values = dlist.toArray(new String[dlist.size()]);
                if(Index!=-1)
                    spinner.setSelection(Index);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Language getting error else",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this,"Error getting error else",Toast.LENGTH_SHORT).show();
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
        service.Categories(new CategoryLanguage(PreferenceManager.getDefaultSharedPreferences(this).getString("Language", ""))).enqueue(new Callback<CategoryClass>() {
            @Override
            public void onResponse(Call<CategoryClass> call, Response<CategoryClass> response) {
                if(response.isSuccessful())
                {
                    for(int i = 0 ; i<response.body().getResult().length;i++)
                    {
                        Categories.put(response.body().getResult()[i].getID(),response.body().getResult()[i].getCategoryName());
                        arrayList.add(response.body().getResult()[i].getCategoryName());
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Error getting Category else",Toast.LENGTH_SHORT).show();
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
        setFragment(fragmentLogin);
        GetCategoryList();
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}


