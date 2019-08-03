package com.example.myfragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


public class TestFragmentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        //add，添加Fragment
        //获得Fragment控制器，和用于执行的秘书！控制器是老总，有一个秘书FragmentTransaction负责执行
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //新建一个Fragment。
        // 注意fragment在OnCreateView中已有过处理，但是未与父控件绑定，故能够放入新的布局中
        TestFragment testFragment = new TestFragment();
        //将该Fragment放入到fragment_view这个线性布局中，并执行！此时，一个Fragment已经完成
        fragmentTransaction.add(R.id.fragment_view, testFragment);

        //删除
        fragmentTransaction.remove(testFragment).commit();

        //找到一个已有的Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_test_1);
        if(fragment instanceof TestFragment){
            //TODO: DO YOUR ACTION
        }else{
            throw new IllegalStateException("is not testFragment!");
        }
    }
}
