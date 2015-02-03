package uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import uz.greenwhite.slidingmenu.support.v10.SupportApplication;
import uz.greenwhite.slidingmenu.support.v10.common.mold.SupportActivity;
import uz.greenwhite.slidingmenu.support.v10.error.AppError;
import uz.greenwhite.slidingmenu.support.v10.service.life_cycle.FragmentLife;
import uz.greenwhite.slidingmenu.support.v10.util.MoldUtil;

import java.io.Serializable;

class ContentFragment extends android.support.v4.app.Fragment {

    public static final String SUPPORT_ARG = "uz.greenwhite.lib.support.v10.argument";

    //******************************************************************************************************************

    public static Intent open(Activity activity, Class<? extends SupportFragment> cls, Bundle arg) {
        return SupportActivity.open(activity, cls, arg);
    }

    public static Intent open(Activity activity, Class<? extends SupportFragment> cls, Parcelable parcel) {
        if (parcel == null) {
            throw AppError.NullPointer();
        }
        Bundle arg = new Bundle();
        arg.putParcelable(SUPPORT_ARG, parcel);
        return open(activity, cls, arg);
    }

    public static Intent open(Activity activity, Class<? extends SupportFragment> cls, Serializable serial) {
        if (serial == null) {
            throw AppError.NullPointer();
        }
        Bundle arg = new Bundle();
        arg.putSerializable(SUPPORT_ARG, serial);
        return open(activity, cls, arg);
    }

    public static Intent open(Activity activity, Class<? extends SupportFragment> cls) {
        return open(activity, cls, (Bundle) null);
    }

    //******************************************************************************************************************

    public static Intent openFullContent(Activity activity, Class<? extends SupportFragment> cls, Bundle arg) {
        return SupportActivity.openFullDisplay(activity, cls, arg);
    }

    public static Intent openFullContent(Activity activity, Class<? extends SupportFragment> cls, Parcelable parcel) {
        if (parcel == null) {
            throw AppError.NullPointer();
        }
        Bundle arg = new Bundle();
        arg.putParcelable(SUPPORT_ARG, parcel);
        return openFullContent(activity, cls, arg);
    }

    public static Intent openFullContent(Activity activity, Class<? extends SupportFragment> cls, Serializable serial) {
        if (serial == null) {
            throw AppError.NullPointer();
        }
        Bundle arg = new Bundle();
        arg.putSerializable(SUPPORT_ARG, serial);
        return openFullContent(activity, cls, arg);
    }

    public static Intent openFullContent(Activity activity, Class<? extends SupportFragment> cls) {
        return openFullContent(activity, cls, (Bundle) null);
    }

    //******************************************************************************************************************

    public static <T extends SupportFragment> T newInstance(Class<? extends SupportFragment> cls, Bundle arg) {
        SupportFragment fragment;
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

    public static <T extends SupportFragment> T newInstance(Class<? extends SupportFragment> cls, Parcelable parcel) {
        Bundle arg = new Bundle();
        arg.putParcelable(SUPPORT_ARG, parcel);
        return newInstance(cls, arg);
    }

    public static <T extends SupportFragment> T newInstance(Class<? extends SupportFragment> cls, Serializable serial) {
        Bundle arg = new Bundle();
        arg.putSerializable(SUPPORT_ARG, serial);
        return newInstance(cls, arg);
    }

    public static <T extends SupportFragment> T newInstance(Class<? extends SupportFragment> cls) {
        return newInstance(cls, (Bundle) null);
    }

    //******************************************************************************************************************

    public void addContent(SupportFragment supportFragment) {
        MoldUtil.addContent((SupportActivity) getActivity(), supportFragment);
    }

    public void replaceContent(SupportFragment supportFragment) {
        MoldUtil.replaceContent((SupportActivity) getActivity(), supportFragment);
    }

    //******************************************************************************************************************

    protected final void dLog(String msg) {
        if (SupportApplication.DEBUG) {
            Log.d(SupportApplication.TAG, msg);
        }
    }

    protected final void eLog(String msg) {
        if (SupportApplication.DEBUG) {
            Log.e(SupportApplication.TAG, msg);
        }
    }

    protected final void eLog(String msg, Throwable error) {
        if (SupportApplication.DEBUG) {
            Log.e(SupportApplication.TAG, msg, error);
        }
    }

    protected final void wLog(String msg) {
        if (SupportApplication.DEBUG) {
            Log.w(SupportApplication.TAG, msg);
        }
    }

    //******************************************************************************************************************

    @Override
    public void onStart() {
        super.onStart();
        FragmentLife.instance.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        FragmentLife.instance.onStop();
    }
}
