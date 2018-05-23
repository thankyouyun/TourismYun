package com.goodyun.tourismyun;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SearchActivity extends AppCompatActivity {


    EditText editText;

    Fragment[] frags = new Fragment[2];
    FragmentTransaction transaction;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        btn = findViewById(R.id.btn);
        editText = findViewById(R.id.edit);


        editText.setOnKeyListener(new EditKeyListener());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchGo();

            }
        });


        //////////////////////
        frags[0] = new SearchReadFrag();





    }//create

    @Override
    protected void onResume() {
        super.onResume();

    }

    //엔터 입력시
    class EditKeyListener implements View.OnKeyListener {


        @Override
        public boolean onKey(View view, int keycode, KeyEvent keyEvent) {

            if (keycode == KeyEvent.KEYCODE_ENTER) {
                searchGo();
                return true;
            }
            return false;
        }
    }


    //서치버튼 눌렀을때 메소드
    public void searchGo() {
        String s = editText.getText().toString().replace(" ", "");

        if (s.equals("")) {
            Toast.makeText(SearchActivity.this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);


        frags[1] = new SearchViewFrag();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.search_change, frags[1]);
        transaction.commit();
        Bundle bundle = new Bundle(1);
        bundle.putString("ask", s);
        frags[1].setArguments(bundle);




    }

    public void clickback(View v) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        finish();

    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.disappear_search, R.anim.disappear_search);
    }


}
