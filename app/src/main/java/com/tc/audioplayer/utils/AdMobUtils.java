package com.tc.audioplayer.utils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
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
        if (nativeContentAd == null || adView == null) {
            return;
        }
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

    /**
     * 安装广告
     */
    public static void populateInstallAdView(NativeAppInstallAd appInstallAd,
                                             NativeAppInstallAdView adView) {
        ImageView ivImage = (ImageView) adView.findViewById(R.id.content_image);
        TextView tvHeadLine = (TextView) adView.findViewById(R.id.tv_headline);
        RatingBar rbRating = (RatingBar) adView.findViewById(R.id.rb_rating);
        TextView tvStore = (TextView) adView.findViewById(R.id.tv_store);
        Button btnCallToAction = (Button) adView.findViewById(R.id.btn_install);
        ImageView ivIcon = (ImageView) adView.findViewById(R.id.iv_appicon);
        adView.setImageView(ivImage);
        adView.setHeadlineView(tvHeadLine);
        adView.setStarRatingView(rbRating);
        adView.setStoreView(tvStore);
        adView.setCallToActionView(btnCallToAction);
        adView.setIconView(ivIcon);

        List<NativeAd.Image> images = appInstallAd.getImages();
        ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
        if (images.size() > 0) {
            ivImage.setImageDrawable(images.get(0).getDrawable());
        }
        NativeAd.Image icon = appInstallAd.getIcon();
        ivIcon.setImageDrawable(icon.getDrawable());
        tvHeadLine.setText(appInstallAd.getHeadline());
        tvStore.setText(appInstallAd.getStore());
        rbRating.setNumStars(5);
        rbRating.setRating(appInstallAd.getStarRating().floatValue());
        adView.setVisibility(View.VISIBLE);
        adView.setNativeAd(appInstallAd);
    }

    /**
     * 播放器歌词页面的广告
     */
    public static void showNativeContentAd(Context context, final NativeContentAdView adView) {
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
                        if (context == null) {
                            return;
                        }
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

    public static AdRequest.Builder getRequestBuilder() {
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("EFDE3632F6D6F87801F68CAB10796A46")
                .addTestDevice("DA6DE3BE84FC5D12846B2AD377CED73E")
                .addTestDevice("126101F178936B6BA282A3EB81EF29F0")
                .addTestDevice("FE3E29B85E2D0BC813D0AF1A53390C44")
                .addTestDevice("B2952405032D73534E695FE8897CC4B1")
                .addTestDevice("C357783CA84A3BDEAE79C5801DD2A323")
                .addTestDevice("40a940a6200eff90bd875a6b1df06c70")
                .addTestDevice("42EA86278DBB5EFA08801AED100FD88F");
    }

    public static void showHomeBigAd(Context context) {
        InterstitialAd mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(Constant.AdmobInterID);
        AdRequest adRequest = getRequestBuilder().build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                TLogger.d(TAG, "onAdClosed");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                TLogger.d(TAG, "onAdFailedToLoad: " + i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                TLogger.d(TAG, "onAdLeftApplication");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                TLogger.d(TAG, "onAdOpened");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
                TLogger.d(TAG, "onAdLoaded");
            }
        });
    }

    /**
     * 显示native广告
     */
    public static void loadNativeAd(Context context, String adId,
                                    NativeAppInstallAd.OnAppInstallAdLoadedListener appInstallAdLoadedListener,
                                    NativeContentAd.OnContentAdLoadedListener contentAdLoadedListener) {
        AdLoader adLoader = new AdLoader.Builder(context, adId)
                .forAppInstallAd(appInstallAdLoadedListener)
                .forContentAd(contentAdLoadedListener)
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
        AdRequest adRequest = AdMobUtils.getRequestBuilder()
                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                .build();
        adLoader.loadAd(adRequest);
    }
}
