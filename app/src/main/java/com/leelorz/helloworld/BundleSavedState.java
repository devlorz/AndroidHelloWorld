package com.leelorz.helloworld;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by Lee Lorz on 12/22/2016.
 */

public class BundleSavedState extends View.BaseSavedState {

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new BundleSavedState(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new BundleSavedState[i];
        }
    };
    private Bundle bundle;

    public BundleSavedState(Parcel source) {
        super(source);
        bundle = source.readBundle();
    }

    public BundleSavedState(Parcelable superState) {
        super(superState);
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeBundle(bundle);
    }
}
