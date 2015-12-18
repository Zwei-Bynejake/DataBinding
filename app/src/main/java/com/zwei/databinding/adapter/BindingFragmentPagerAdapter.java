package com.zwei.databinding.adapter;

import android.databinding.ObservableList;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Zwei on 12/18/15.
 */
public class BindingFragmentPagerAdapter extends FragmentPagerAdapter {
    private final WeakReferenceOnListChangedCallback mCallback = new WeakReferenceOnListChangedCallback(this);
    private List<Fragment> mItems;
    private List<CharSequence> mTitles;

    public BindingFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setItems(@Nullable List<Fragment> items) {
        if (mItems instanceof ObservableList) {
            ((ObservableList<Fragment>) mItems).removeOnListChangedCallback(mCallback);
        }
        mItems = items;
        if (items instanceof ObservableList) {
            ((ObservableList<Fragment>) items).addOnListChangedCallback(mCallback);
        }
        notifyDataSetChanged();
    }

    public void setPageTitles(@Nullable List<CharSequence> titles) {
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? null : mTitles.get(position);
    }

    private static class WeakReferenceOnListChangedCallback extends ObservableList.OnListChangedCallback<ObservableList<Fragment>> {
        private final WeakReference<BindingFragmentPagerAdapter> mAdapterRef;

        WeakReferenceOnListChangedCallback(BindingFragmentPagerAdapter adapter) {
            mAdapterRef = new WeakReference<>(adapter);
        }


        @Override
        public void onChanged(ObservableList<Fragment> sender) {
            final BindingFragmentPagerAdapter adapter = mAdapterRef.get();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList<Fragment> sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeInserted(ObservableList<Fragment> sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeMoved(ObservableList<Fragment> sender, int fromPosition, int toPosition, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeRemoved(ObservableList<Fragment> sender, int positionStart, int itemCount) {
            onChanged(sender);
        }
    }
}
