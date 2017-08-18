package com.tc.librecyclerview.config;

/**
 * Date:15/9/24
 * Time:15:07
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public class RecycerViewConfig implements Config {

    private static final RecycerViewConfig instance = new RecycerViewConfig();
    private static ImageLoader imageLoader;

    public static RecycerViewConfig getInstance() {
        return instance;
    }

    public static void configure(Config config) {
        imageLoader = config.getImageLoader();
    }

    @Override
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
