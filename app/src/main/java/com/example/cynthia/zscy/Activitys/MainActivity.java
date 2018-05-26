package com.example.cynthia.zscy.Activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.cynthia.zscy.Fragments.AboutMeFragment;
import com.example.cynthia.zscy.Fragments.AskQuestionFragment;
import com.example.cynthia.zscy.Fragments.ClassFragment;
import com.example.cynthia.zscy.Fragments.FindFragment;
import com.example.cynthia.zscy.R;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private String aboutMe = "AboutMeFragment";
    private String ask = "AskQuestionFragment";
    private String cls = "ClassFragment";
    private String find = "FindFragment";

    private Fragment classFragment = new ClassFragment();
    private AskQuestionFragment askQuestionFragment = new AskQuestionFragment();
    private FindFragment findFragment = new FindFragment();
    private AboutMeFragment aboutMeFragment = new AboutMeFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        RadioGroup radioGroup = findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(this);
        addFragment(classFragment,cls);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        switch (group.getCheckedRadioButtonId()){
            case  R.id.main_class:
                showFragment(cls);
                hideFragment(ask);
                hideFragment(find);
                hideFragment(aboutMe);
                break;
            case  R.id.main_ask:
                if (manager.findFragmentByTag(ask)==null){
                    addFragment(askQuestionFragment,ask);
                    hideFragment(cls);
                    hideFragment(aboutMe);
                    hideFragment(find);
                }else {
                    showFragment(ask);
                    hideFragment(cls);
                    hideFragment(aboutMe);
                    hideFragment(find);
                }
                break;
            case R.id.main_find:
                if (manager.findFragmentByTag(find)==null){
                    addFragment(findFragment,find);
                    hideFragment(cls);
                    hideFragment(ask);
                    hideFragment(aboutMe);
                }else {
                    showFragment(find);
                    hideFragment(cls);
                    hideFragment(ask);
                    hideFragment(aboutMe);
                }
                break;
            case R.id.main_me:
                if (manager.findFragmentByTag(aboutMe)==null){
                    addFragment(aboutMeFragment,aboutMe);
                    hideFragment(cls);
                    hideFragment(ask);
                    hideFragment(find);
                }else {
                    showFragment(aboutMe);
                    hideFragment(cls);
                    hideFragment(ask);
                    hideFragment(find);
                }
                break;
        }
    }

    private void hideFragment(String tag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (manager.findFragmentByTag(tag) != null){
            Fragment fragment = manager.findFragmentByTag(tag);
            transaction.hide(fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void showFragment(String tag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (manager.findFragmentByTag(tag) != null){
            Fragment fragment = manager.findFragmentByTag(tag);
            transaction.show(fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void addFragment(Fragment fragment,String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragment, tag);
        transaction.commit();
    }
}
