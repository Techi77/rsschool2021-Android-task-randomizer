package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.ActionPerformedListenerForFirstFragment, SecondFragment.ActionPerformedListenerForSecondFragment {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment =
                getSupportFragmentManager().findFragmentByTag(SecondFragment.class.getName());
        if (fragment == null) super.onBackPressed();
        else ((SecondFragment) fragment).backButtonClicked();
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,
                        SecondFragment.newInstance(min, max),
                        SecondFragment.class.getName()
                )
                .commit();
    }

    @Override
    public void onActionPerformed(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void backButtonClicked(int previousResult) {
        openFirstFragment(previousResult);
    }
}
