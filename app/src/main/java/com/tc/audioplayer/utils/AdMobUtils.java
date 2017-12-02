package com.tc.audioplayer.utils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.Constant;
import com.tc.base.utils.TLogger;

import java.util.List;

/**
 * Created by itcayman on 2017/9/27.
 */

public class AdMobUtils {
    private static final String TAG = AdMobUtils.class.getSimpleName();

    /**
     * Populates a {@link NativeContentAdView} object with data from a given
     * {@link NativeContentAd}.
     *
     * @param nativeContentAd the object containing the ad's assets
     * @param adView          the view to be populated
     */
    public static void populateContentAdView(NativeContentAd nativeContentAd,
                                             NativeContentAdView adView, boolean showTopLayout) {
        // Assign native ad object to the native view.
        adView.setNativeAd(nativeContentAd);

        adView.setHeadlineView(adView.findViewById(R.id.contentad_headline));
        adView.setImageView(adView.findViewById(R.id.contentad_image));

        // Some assets are guaranteed to be in every NativeContentAd.
        if (adView.getHeadlineView() != null) {
            ((TextView) adView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        }

        List<NativeAd.Image> images = nativeContentAd.getImages();

        ((ImageView) adView.getImageView()).setScaleType(ImageView.ScaleType.FIT_XY);
        if (images.size() > 0) {
            ((ImageView) adView.getImageView()).setImageDrawable(images.get(0).getDrawable());
        }
        View topLayout = adView.findViewById(R.id.layout_top);
        if (topLayout != null) {
            topLayout.setVisibility(showTopLayout ? View.VISIBLE : View.GONE);
        }
        adView.setVisibility(View.VISIBLE);
    }

    public static void showPlayerDialogAd(Context context, final NativeContentAdView adView) {
        AdLoader adLoader = new AdLoader.Builder(context, Constant.AdmobNativeID_Billboard)
                .forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
                    @Override
                    public void onAppInstallAdLoaded(NativeAppInstallAd appInstallAd) {
                        TLogger.e(TAG, "onAppInstallAdLoaded: " + appInstallAd.getHeadline());
                    }
                })
                .forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                    @Override
                    public void onContentAdLoaded(NativeContentAd contentAd) {
                        TLogger.e(TAG, "onContentAdLoaded: " + contentAd.getBody());
                        populateContentAdView(contentAd, adView, false);
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        TLogger.e(TAG, "onAdFailedToLoad: " + errorCode);
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        Bundle extras = new FacebookAdapter.FacebookExtrasBundleBuilder()
                .setNativeAdChoicesIconExpandable(false)
                .build();
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("42EA86278DBB5EFA08801AED100FD88F")
                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                .build();
        adLoader.loadAd(adRequest);
    }
}
