package com.example.myfragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.security.auth.login.LoginException;

public class TestFragment extends Fragment {

    public static final String TAG = TestFragment.class.getSimpleName();
    public static final String ARGUMENT_NAME = "argument_name";
    public static final String ARGUMENT_NUMBER = "argument_number";
    private String name;
    private int number;


    public static TestFragment newInstance(String nameString, int number) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_NAME, nameString);
        bundle.putInt(ARGUMENT_NUMBER, number);
        TestFragment fragment = new TestFragment();
        //该bundle中的数据与返回的fragment绑定，后续可以使用
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);

        //获得初始化的数据
        Bundle bundle = this.getArguments();
        if(bundle != null){
            name = bundle.getString(ARGUMENT_NAME);
            number = bundle.getInt(ARGUMENT_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");

        //创建一个view，不绑定到视图中
        View view = inflater.inflate(R.layout.item_phone_book_friend, container ,false);
        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(name);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
