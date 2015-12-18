package com.zwei.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.zwei.databinding.adapter.BindingFragmentPagerAdapter;

import java.util.List;

/**
 * Created by Zwei on 12/18/15.
 */
public final class ViewPagerBindingAdapter {
    @BindingAdapter(value = {"fragmentManager", "items", "titles"}, requireAll = false)
    public static void setAdapter(ViewPager viewPager, FragmentManager manager, List<Fragment> items, List<CharSequence> titles) {
        if (manager == null) {
            throw new IllegalArgumentException("manager must not be null");
        }
        BindingFragmentPagerAdapter adapter = (BindingFragmentPagerAdapter) viewPager.getAdapter();
        if (adapter == null) {
            adapter = new BindingFragmentPagerAdapter(manager);
        }
        adapter.setItems(items);
        adapter.setPageTitles(titles);
    }
}
