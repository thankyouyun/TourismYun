package com.goodyun.tourismyun;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchActivity extends AppCompatActivity {

    TextView tvEmpty;
    EditText editText;
    ListView lv;
    ArrayList<SearchReadItem> items = new ArrayList<>();
    SearchReadAdapter adapter;
    Button btn;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn = findViewById(R.id.btn);
        editText = findViewById(R.id.edit);
        date = DateFormat.getDateInstance().format(new Date());
        items.add(new SearchReadItem("기록1", date));
        items.add(new SearchReadItem("기록2", date));
        items.add(new SearchReadItem("기록3", date));
        items.add(new SearchReadItem("기록4", date));

        lv = findViewById(R.id.list_view);
        adapter = new SearchReadAdapter(items, getLayoutInflater());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(SearchActivity.this, position + "번째 리스트를 클릭했습니다", Toast.LENGTH_SHORT).show();

            }
        });


        editText.setOnKeyListener(new EditKeyListener());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchGo();

            }
        });
        tvEmpty = findViewById(R.id.tv_empty_list);
        lv.setEmptyView(tvEmpty);


    }//create


    //엔터 입력시
    class EditKeyListener implements View.OnKeyListener{


        @Override
        public boolean onKey(View view, int keycode, KeyEvent keyEvent) {

            if(keycode==KeyEvent.KEYCODE_ENTER){
                searchGo();
                return true;
            }
            return false;
        }
    }


    //서치버튼 눌렀을때 메소드
    public void searchGo(){

        String s = editText.getText().toString().replace(" ","");

        if (!s.equals("")) {
            s = editText.getText().toString();
            SearchReadItem item = new SearchReadItem(s + "", date);

            items.add(item);
            adapter.notifyDataSetChanged();
            lv.setSelection(items.size() - 1);

            editText.setText("");

        }else{
            Toast.makeText(SearchActivity.this, "검색를 입력해주세요", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
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
