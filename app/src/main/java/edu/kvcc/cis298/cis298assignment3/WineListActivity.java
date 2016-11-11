package edu.kvcc.cis298.cis298assignment3;

// This class override the createFragment method in the parent singleFragmentActivity and
// sends in the WineListFragment so that it is created by the fragment manager:

import android.support.v4.app.Fragment;

public class WineListActivity extends SingleFragmentActivity {

        // Override the createFragment method in the parent activity:
    @Override
    protected Fragment createFragment() {
            // Return the WineListFragment as the fragment to be created:
        return new WineListFragment();
    }
}
