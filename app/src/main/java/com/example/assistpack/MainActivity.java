package com.example.assistpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager2 viewPager2;
    private VPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablayout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewPager2);

        tablayout.addTab(tablayout.newTab().setText("Inventory"));
        tablayout.addTab(tablayout.newTab().setText("Cooler"));
        tablayout.addTab(tablayout.newTab().setText("Temperature"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new VPAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tablayout.selectTab(tablayout.getTabAt(position));
            }
        });
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

    }
}