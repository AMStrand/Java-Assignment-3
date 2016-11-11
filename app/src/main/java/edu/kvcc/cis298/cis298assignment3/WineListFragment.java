package edu.kvcc.cis298.cis298assignment3;

// This class defines the fragment that is the WineListFragment, which is the recycler view,
// its adapter and its view holders and all their associated information.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class WineListFragment extends Fragment {

        // Create a recycler view object:
    private RecyclerView mWineRecyclerView;

        // Create an adapter object:
    private WineItemAdapter mAdapter;

        // Override the onCreateView method:
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // Create a new view based on the fragment_wine_list layout:
        View view = inflater.inflate(R.layout.fragment_wine_list, container, false);
            // Assign the recycler view to the wine_recycler_view layout:
        mWineRecyclerView = (RecyclerView) view.findViewById(R.id.wine_recycler_view);
            // Set the layout manager of the recycler view:
        mWineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            // Update the UI:
        UpdateUI();
            // Return the view:
        return view;
    }

        // Public class for the wine holders that make up the recycler view:
    public class WineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // Variables for the text views in each wine holder:
        private TextView mIdTextView;
        private TextView mDescriptionTextView;
        private TextView mPriceTextView;

            // Override the onClick method:
        @Override
        public void onClick(View view) {
                // When clicked, start the wine pager activity for that wine item:
            Intent intent = WinePagerActivity.newIntent(getActivity(), mWineItem.getmId());
            startActivity(intent);
        }

            // Variable to hold a wine item:
        private WineItem mWineItem;

            // Public method to create a wine holder:
        public WineHolder(View itemView) {
            super(itemView);
                // Set an on click listener:
            itemView.setOnClickListener(this);

                // Connect the widgets to the variables:
            mIdTextView = (TextView) itemView.findViewById(R.id.list_item_wine_description_text_view);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item_wine_description_text_view);
            mPriceTextView = (TextView) itemView.findViewById(R.id.list_item_wine_price_text_view);
        }

            // Public method to bind the wine item to the holder:
        public void bindWineItem(WineItem wineItem) {
                // Wine item variable:
            mWineItem = wineItem;
                // Set the text of the current wine holder to the info from the current wine item:
            mIdTextView.setText(mWineItem.getmId());
            mDescriptionTextView.setText(mWineItem.getmDescription());
            mPriceTextView.setText(DecimalFormat.getCurrencyInstance().format(mWineItem.getmCasePrice()));
        }
    }

        // Private class for the wine item adapter:
    private class WineItemAdapter extends  RecyclerView.Adapter<WineHolder> {
            // Local variable for the wine items list:
        private List<WineItem> mWineItems;

            // Constructor, takes in a list of wine items:
        public WineItemAdapter(List<WineItem> wineItems) {
                // Set the local list to the wine items list:
            mWineItems = wineItems;
        }

            // Override the on create view holder method:
        @Override
        public WineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a layout inflater from the activity:
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                // Create a view and inflate with the list_item_wine layout:
            View view = layoutInflater.inflate(R.layout.list_item_wine, parent, false);
                // Return the wine holder with the created view:
            return new WineHolder(view);
        }

            // Override the on bind view holder method:
        @Override
        public void onBindViewHolder(WineHolder holder, int position) {
                // Get the wine item based on the position:
            WineItem wineItem = mWineItems.get(position);
                // Bind that wine item to the holder:
            holder.bindWineItem(wineItem);
        }

            // Override the get item count method:
        @Override
        public int getItemCount() {
                // Set the item count equal to the size of the wine items list:
            return mWineItems.size();
        }
    }

        // Override the on resume method:
    @Override
    public void onResume() {
        super.onResume();
            // On resume, update the UI:
        UpdateUI();
    }

        // Private method to update the user interface:
    private void UpdateUI() {
            // Set the wine list:
        WineList wineList = WineList.get(getActivity());
            // Save the wine list as a list of wine items:
        List<WineItem> wineItems = wineList.getWineItemList();

            // If the adapter is empty...
        if (mAdapter == null) {
                // Make a new adapter with the wine items:
            mAdapter = new WineItemAdapter(wineItems);
                // Set the adapter of the recycler view:
            mWineRecyclerView.setAdapter(mAdapter);
        } else {
                // Otherwise, notify the adapter that something may have changed so it refreshes:
            mAdapter.notifyDataSetChanged();
        }
    }
}
