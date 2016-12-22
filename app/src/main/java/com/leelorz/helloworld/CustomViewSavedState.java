package com.leelorz.helloworld;

import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;

/**
 * Created by Lee Lorz on 12/22/2016.
 */

public class CustomViewSavedState extends Preference.BaseSavedState {

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new CustomViewSavedState(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new CustomViewSavedState[i];
        }
    };
    private boolean blue;

    public CustomViewSavedState(Parcel source) {
        super(source);
        // read back
        blue = source.readInt() == 1;
    }

    public CustomViewSavedState(Parcelable superState) {
        super(superState);
    }

    public boolean isBlue() {
        return blue;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        // write var here
        dest.writeInt(blue ? 1 : 0);
    }
}
