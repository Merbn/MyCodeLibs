package com.example.studymvp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.studymvp.presenter.GetStringPresenter;
import com.example.studymvp.view.IGetStringView;

public class MainActivity extends AppCompatActivity implements IGetStringView{
private     EditText editText;
private     Button button;
    private GetStringPresenter presenter;
    private static Context mContext ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        presenter = new GetStringPresenter(this);
        mContext = getApplicationContext();
        presenter.showName();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.saveName();
            }
        });
    }
    public static void  getAppContext(){

    }

    @Override
    public void showName(String name) {
editText.setText(name);
    }

    @Override
    public String getName() {
        return editText.getText().toString();
    }
}
