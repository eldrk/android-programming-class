package com.example.student.realmactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    EditText et_title,et_num;
    Button btn_add,btn_select,btn_update,btn_del,btn_delAll;
    TextView tv_select;

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_title = (EditText) findViewById(R.id.et_title);
        et_num = (EditText) findViewById(R.id.et_num);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_select = (Button) findViewById(R.id.btn_select);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_delAll = (Button) findViewById(R.id.btn_delAll);
        tv_select = (TextView) findViewById(R.id.tv_select);

        Realm.init(MainActivity.this);
        mRealm = Realm.getDefaultInstance();

        MyBtnListener btnListener = new MyBtnListener();

        btn_add.setOnClickListener(btnListener);
        btn_select.setOnClickListener(btnListener);
        btn_update.setOnClickListener(btnListener);
        btn_del.setOnClickListener(btnListener);
        btn_delAll.setOnClickListener(btnListener);


    }

    class MyBtnListener implements View.OnClickListener{

        String title = et_title.getText().toString();
        String number = et_num.getText().toString();

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_add:
                    if(!title.equals("") && !number.equals("")){
                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                MovieVo movieVo = realm.createObject(MovieVo.class);
                                movieVo.setTitle(title);
                                movieVo.setNumber(Integer.parseInt(number));
                            }
                        });
                    }
                    break;
                case R.id.btn_select:
                    RealmResults<MovieVo> results = mRealm.where(MovieVo.class).findAll();
                    String str = "";
                    if(results.size()>0){
                        for(int i=0;i<results.size();i++){
                            str += ("number : " +results.get(i).getNumber()
                                    +"title : " +results.get(i).getTitle()+"\n");
                        }
                    }else{
                        str += "no data";
                    }
                    tv_select.setText(str);
                    break;
                case R.id.btn_update:
                    if(!title.equals("") && !number.equals("")){
                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                MovieVo target = mRealm.where(MovieVo.class).equalTo("number",Integer.valueOf(number)).findFirst();
                                target.setTitle(title);
                            }
                        });
                    }
                    break;
                case R.id.btn_del:
                    mRealm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            MovieVo target=mRealm.where(MovieVo.class).equalTo("number",Integer.valueOf(number)).findFirst();
                            target.deleteFromRealm();
                        }
                    });
                    break;
                case R.id.btn_delAll:
                    mRealm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            mRealm.delete(MovieVo.class);
                        }
                    });
                    break;
            }
        }
    }
}
