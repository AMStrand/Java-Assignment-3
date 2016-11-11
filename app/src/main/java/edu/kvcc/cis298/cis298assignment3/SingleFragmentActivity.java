package edu.kvcc.cis298.cis298assignment3;

// This class is the basic activity template and sets up the fragments to be used when called.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {

        // Method to be implemented in child classes:
    protected abstract Fragment createFragment();

        // Override the onCreate method:
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            // Set the content view to the activity_fragment:
        setContentView(R.layout.activity_fragment);
            // Create a fragment manager:
        FragmentManager fragmentManager = getSupportFragmentManager();
            // Create a fragment and have the manager assign to it the fragment_container:
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
            // If the fragment does not exist yet,
        if (fragment == null) {
                // Create the fragment:
            fragment = createFragment();
                // Have the manager finalize the creation of the fragment:
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
