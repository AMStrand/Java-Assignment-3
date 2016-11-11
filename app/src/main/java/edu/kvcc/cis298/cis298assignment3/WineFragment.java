package edu.kvcc.cis298.cis298assignment3;

// Class to contain the fragment for the detail wine item look, handling widget connection
// and text changes to ensure wine item information is changed properly:

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DecimalFormat;

public class WineFragment extends Fragment{

        // Static final String to serve as the key for the wine item id:
    private static final String ARG_WINE_ID = "wine_id";
        // Widgets to be assigned:
    private WineItem mWineItem;
    private EditText mDescriptionField;
    private EditText mIdField;
    private EditText mPackField;
    private EditText mPriceField;
    private CheckBox mActiveCheckBox;

        // Public method to start a new instance of the wine fragment:
    public static WineFragment newInstance(int Id) {
            // Create a bundle and add the wine id as a serializable extra:
        Bundle args = new Bundle();
        args.putSerializable(ARG_WINE_ID, Id);
            // Make a new fragment and set the args, return it:
        WineFragment fragment = new WineFragment();
        fragment.setArguments(args);
        return fragment;
    }

        // Override the onCreate method:
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            // Save the serializable extra as the wine item id:
        int wineId = (int) getArguments().getSerializable(ARG_WINE_ID);
            // Find the wine item with that id:
        mWineItem = WineList.get(getActivity()).getWineItem(wineId);
    }

        // Override the onCreateView method:
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // Create a view and inflate it with the fragment_wine layout:
        View view = inflater.inflate(R.layout.fragment_wine, container, false);

            // Connect the widget to the description variable and set the text:
        mDescriptionField = (EditText) view.findViewById(R.id.wine_item_description);
        mDescriptionField.setText(mWineItem.getmDescription());
            // When the text changes...
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                // Save it as it changes, setting the description of the wine item:
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mWineItem.setmDescription(charSequence.toString());
            }

            @Override public void afterTextChanged(Editable editable) {}
        });

            // Connect the widget to the id variable and set the text:
        mIdField = (EditText) view.findViewById(R.id.wine_item_id);
        mIdField.setText(mWineItem.getmId());
            // When the text changes...
        mIdField.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                // Save it as it changes, setting the id of the wine item:
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mWineItem.setmId(Integer.parseInt(charSequence.toString()));
            }

            @Override public void afterTextChanged(Editable editable) {}
        });

            // Connect the widget to the pack size variable and set the text:
        mPackField = (EditText) view.findViewById(R.id.wine_item_pack);
        mPackField.setText(mWineItem.getmPackSize());
            // When the text changes...
        mPackField.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                // Save it as it changes, setting the pack size of the wine item:
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mWineItem.setmPackSize(charSequence.toString());
            }

            @Override public void afterTextChanged(Editable editable) {}
        });

            // Connect the widget to the case price variable and set the text:
        mPriceField = (EditText) view.findViewById(R.id.wine_item_price);
        mPriceField.setText(DecimalFormat.getCurrencyInstance().format(mWineItem.getmCasePrice()));
            // When the text changes...
        mPriceField.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                // Save it as it changes, setting the case price of the wine item:
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mWineItem.setmCasePrice(Double.parseDouble(charSequence.toString()));
            }

            @Override public void afterTextChanged(Editable editable) {}
        });

            // Connect the checkbox widget to the is active variable and set the text:
        mActiveCheckBox = (CheckBox) view.findViewById(R.id.wine_item_active);
        mActiveCheckBox.setChecked(mWineItem.ismIsActive());
            // When the checkbox is checked or unchecked:
        mActiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Set whether the item is active equal to whether it is checked:
                mWineItem.setmIsActive(isChecked);
            }
        });

            // Return the view:
        return view;
    }
}
