package edu.kvcc.cis298.cis298assignment3;

// This class holds the information for each individual WineItem object.

public class WineItem {

        // Declare the variables that define the WineItem object:
    private int mId;
    private String mDescription;
    private String mPackSize;
    private double mCasePrice;
    private boolean mIsActive;

        // Default constructor - not actually used in this application
    public WineItem() {
        // Not needed:
    }

     // 5 Parameter constructor to process WineItems:
    public WineItem(int id, String description, String packSize, double price, boolean isActive) {
        mId = id;
        mDescription = description;
        mPackSize = packSize;
        mCasePrice = price;
        mIsActive = isActive;
    }

        // Getters and Setters for the variables:

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmPackSize() {
        return mPackSize;
    }

    public void setmPackSize(String mPackSize) {
        this.mPackSize = mPackSize;
    }

    public double getmCasePrice() {
        return mCasePrice;
    }

    public void setmCasePrice(double mCasePrice) {
        this.mCasePrice = mCasePrice;
    }

    public boolean ismIsActive() {
        return mIsActive;
    }

    public void setmIsActive(boolean mIsActive) {
        this.mIsActive = mIsActive;
    }
}
