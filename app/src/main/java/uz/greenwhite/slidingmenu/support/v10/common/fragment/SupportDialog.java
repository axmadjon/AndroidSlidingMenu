package uz.greenwhite.slidingmenu.support.v10.common.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import uz.greenwhite.slidingmenu.support.v10.error.AppError;

import java.io.Serializable;

public class SupportDialog extends DialogFragment {

    public static final String SUPPORT_ARG = "uz.greenwhite.slidingmenu.support.v10.dialog";

    public static <T extends SupportDialog> T newInstance(Class<? extends SupportDialog> cls, Bundle arg) {
        SupportDialog fragment = null;
        try {
            fragment = cls.newInstance();
            if (arg != null) {
                fragment.setArguments(arg);
            }
        } catch (Exception e) {
            throw AppError.Unsupported();
        }
        return (T) fragment;
    }

    public static <T extends SupportDialog> T newInstance(Class<? extends SupportDialog> cls) {
        return newInstance(cls, (Bundle) null);
    }

    public static <T extends SupportDialog> T newInstance(Class<? extends SupportDialog> cls, Parcelable parcel) {
        Bundle arg = new Bundle();
        arg.putParcelable(SUPPORT_ARG, parcel);
        return newInstance(cls, arg);
    }

    public static <T extends SupportDialog> T newInstance(Class<? extends SupportDialog> cls, Serializable serial) {
        Bundle arg = new Bundle();
        arg.putSerializable(SUPPORT_ARG, serial);
        return newInstance(cls, arg);
    }


}
