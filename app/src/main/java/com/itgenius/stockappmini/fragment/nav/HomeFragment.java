package com.itgenius.stockappmini.fragment.nav;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.itgenius.stockappmini.R;
import com.itgenius.stockappmini.adapter.TabMenuAdapter;

import java.util.Objects;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        TabLayout tabLayout = rootView.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("In Stock"));
        tabLayout.addTab(tabLayout.newTab().setText("Orders"));
        tabLayout.addTab(tabLayout.newTab().setText("Invoices"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        // โหลด fragment แสดงผลใน ViewPager
        final ViewPager viewPager = rootView.findViewById(R.id.viewPager);
        final TabMenuAdapter adapter = new TabMenuAdapter(Objects.requireNonNull(getFragmentManager()), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

}
