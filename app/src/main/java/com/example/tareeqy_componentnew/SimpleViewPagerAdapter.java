package com.example.tareeqy_componentnew;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class SimpleViewPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragmentList;
    public SimpleViewPagerAdapter(FragmentActivity fragmentActivity,
                                  List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    { return fragmentList.get(position); }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
