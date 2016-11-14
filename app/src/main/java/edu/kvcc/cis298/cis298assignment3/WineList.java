package edu.kvcc.cis298.cis298assignment3;

// This class is a singleton that holds the list of type WineItem and handles processes like output.

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WineList {

        // Static variable to hold the instance of the WineList:
    private static WineList sWineList;
        // Static list of type WineItem:
    private static List<WineItem> sWineItemList;
        // This context will be the hosting activity, assigned in the constructor:
    private Context mContext;

        // Static get method to get the single instance of the class:
    public static WineList get(Context context) {
            // Create the wine list if it has not been created yet:
        if (sWineList == null) {
            sWineList = new WineList(context);
        }
            // Return the wine list:
        return sWineList;
    }

        // PRIVATE constructor, called through the get method:
    private WineList(Context context) {
            // Create the wine item list as an array list:
        sWineItemList = new ArrayList<WineItem>();
            // Set the context:
        mContext = context;
            // Call a method to load the wine list from the csv file:
        LoadWineList();
    }

        // Public method to add a new wine item to the list:
    public void addWineItem(WineItem wineItem) {
        sWineItemList.add(wineItem);
    }

        // Public static method to get the list of wine items:
    public static List<WineItem> getWineItemList() {
        return sWineItemList;
    }

        // Public method to find a specific wine item by id:
    public WineItem getWineItem(String id) {
            // Loop through the wine items:
        for (WineItem wineItem : sWineItemList) {
                // If the id matches, return that wine item:
            if (wineItem.getmId() == id) {
                return wineItem;
            }
        }
            // No match - return null:
        return null;
    }

        // Private method to load the wine list csv file upon instantiation of the WineItemList:
    private void LoadWineList() {
            // Create a scanner object and start it null:
        Scanner scanner = null;
            // Enter a try statement for csv file processing:
        try {
                // Instantiate the scanner using the beverage_list.csv file in res\raw:
            scanner = new Scanner(mContext.getResources().openRawResource(R.raw.beverage_list));
                // While the file has not ended:
            while (scanner.hasNextLine()) {
                    // Save the line in a variable:
                String line = scanner.nextLine();
                    // Split the line by commas into the variables, save in array:
                String[] parts = line.split(",");
                    // Save each part in an appropriate variable, parsing where necessary:
                String id = parts[0];
                String description = parts[1];
                String packSize = parts[2];
                double price = Double.parseDouble(parts[3]);
                String boolString = parts[4];
                    // Set a boolean variable to false:
                boolean bool = false;
                    // If the boolString is "True", change the bool variable to true:
                if(boolString == "True") {
                    bool = true;
                }
                    // Add a new WineItem to the list by passing in the saved variables:
                sWineItemList.add(new WineItem(id, description, packSize, price, bool));
            }
        }
            // Catch to log the error if any of the above fails:
        catch (Exception ex) {
            Log.e("Read CSV", ex.toString());
        }
            // Close the scanner object:
        finally {
            scanner.close();
        }
    }

}
