package uz.greenwhite.slidingmenu.support.v10.common.mold;

import android.os.Bundle;
import android.os.Parcelable;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.error.AppError;

import java.io.Serializable;

public class SupportActivity extends MoldActivity {

    public static final String SUPPORT_ARG = "uz.greenwhite.lib.support.v10.argument";

    public void addContent(Class<? extends SupportFragment> cls, Bundle bundle) {
        SupportFragment fragment;
        try {
            fragment = cls.newInstance();
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
        } catch (Exception e) {
            throw AppError.Unsupported();
        }
        addContent(fragment);
    }

    public void replaceContent(Class<? extends SupportFragment> cls, Bundle bundle) {
        SupportFragment fragment;
        try {
            fragment = cls.newInstance();
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
        } catch (Exception e) {
            throw AppError.Unsupported();
        }
        replaceContent(fragment);
    }

    //******************************************************************************************************************

    public void addContent(Class<? extends SupportFragment> cls, Parcelable parcelable) {
        if (parcelable == null) {
            throw AppError.NullPointer();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPPORT_ARG, parcelable);
        addContent(cls, bundle);
    }

    public void addContent(Class<? extends SupportFragment> cls, Serializable serializable) {
        if (serializable == null) {
            throw AppError.NullPointer();
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(SUPPORT_ARG, serializable);
        addContent(cls, bundle);
    }

    public void addContent(Class<? extends SupportFragment> cls) {
        addContent(cls, (Bundle) null);
    }

    //******************************************************************************************************************

    public void replaceContent(Class<? extends SupportFragment> cls, Parcelable parcelable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPPORT_ARG, parcelable);
        replaceContent(cls, bundle);
    }

    public void replaceContent(Class<? extends SupportFragment> cls, Serializable serializable) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SUPPORT_ARG, serializable);
        replaceContent(cls, bundle);
    }

    public void replaceContent(Class<? extends SupportFragment> cls) {
        replaceContent(cls, (Bundle) null);
    }

    //******************************************************************************************************************
}

