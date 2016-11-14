package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class WinePagerActivity extends AppCompatActivity{

        // Create a static variable to hold the wine id in the intent:
    private static String EXTRA_WINE_ID = "edu.kvcc.cis298.cis298assignment3.wine_id";
        // Create the viewPager object:
    private ViewPager mViewPager;
        // Create a local list to hold the wine items:
    private List<WineItem> mWineItems;

        // Public method to retrieve the intent to begin this activity:
    public static Intent newIntent(Context packageContext, String wineId) {
            // Create a new intent with this context:
        Intent intent = new Intent(packageContext, WinePagerActivity.class);
            // Add the wine item id as a serializable extra in the intent:
        intent.putExtra(EXTRA_WINE_ID, wineId);
            // Return the intent:
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            // Set the content view to the wine pager layout:
        setContentView(R.layout.activity_wine_pager);
            // Get the wine id from the intent:
        String wineId = (String) getIntent().getSerializableExtra(EXTRA_WINE_ID);
            // Set the view pager to the correct layout:
        mViewPager = (ViewPager) findViewById(R.id.activity_wine_pager_view_pager);
            // Get the wine item list and save to the local list:
        mWineItems = WineList.get(this).getWineItemList();
            // Create a fragment manager to use:
        FragmentManager fragmentManager = getSupportFragmentManager();
            // Set the adapter:
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                    // Get the current item based on the position in the list:
                WineItem wineItem = mWineItems.get(position);
                    // Set up the fragment:
                return WineFragment.newInstance(wineItem.getmId());
            }

            @Override
            public int getCount() {
                    // Get the size of the wine items list:
                return mWineItems.size();
            }
        });

            // Loop to find the current position:
        for (int i = 0; i < mWineItems.size(); i++) {
                // If the id matches...
            if (mWineItems.get(i).getmId() == wineId) {
                    // Set the current item:
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
