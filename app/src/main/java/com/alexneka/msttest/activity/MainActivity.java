package com.alexneka.msttest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexneka.msttest.R;
import com.alexneka.msttest.adapter.ITunesAdapter;
import com.alexneka.msttest.api.ITunesService;
import com.alexneka.msttest.model.ITunesContent;
import com.alexneka.msttest.model.RequestResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<RequestResult> {

    private final String BASE_URL = "https://itunes.apple.com/";
    private ITunesAdapter adapter;
    private ListView list;
    private EditText editSearch;
    private ITunesService service;
    private ProgressBar progressBar;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new ITunesAdapter(this);
        initView();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ITunesService.class);
    }

    private void initView(){
        list = (ListView) findViewById(R.id.main_list);
        list.setAdapter(adapter);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        progressBar.setVisibility(View.GONE);
        editSearch = (EditText) findViewById(R.id.main_search_edit);
        text = (TextView) findViewById(R.id.main_text);
        text.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                loadData();
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadData() {
        Call<RequestResult> call = service.listITunes(editSearch.getText().toString());
        call.enqueue(this);
        progressBar.setVisibility(View.VISIBLE);
        text.setVisibility(View.GONE);
        list.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {
        List<ITunesContent> listContent = response.body().getListResult();
        if (listContent.size() == 0) {
            text.setText(R.string.empty_result);
            text.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }
        else {
            adapter.setData(response.body().getListResult());
            list.setAdapter(adapter);
            list.setVisibility(View.VISIBLE);
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(Call<RequestResult> call, Throwable t) {
        text.setText(R.string.loading_error);
        progressBar.setVisibility(View.GONE);
        text.setVisibility(View.VISIBLE);
    }
}
