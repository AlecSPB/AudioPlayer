package com.tc.audioplayer.utils;

import com.tc.base.utils.TLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import okio.ByteString;

/**
 * Created by itcayman on 2017/8/23.
 */

public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();
    public static final String PATH_BASE = android.os.Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/AudioPlayer/";
    public static final String PATH_LRC = PATH_BASE + "lyrics";

    public static String key(String url) {
        return ByteString.encodeUtf8(url).md5().hex();
    }

    /**
     * 把流写进文件生成lrc
     *
     * @param body    lrc流
     * @param lrclink url
     */
    public static boolean writeResponseBodyToDisk(ResponseBody body, String lrclink) {
        try {
            File futureStudioIconFile = getLrcFile(lrclink);
            if(!futureStudioIconFile.exists()){
                futureStudioIconFile.createNewFile();
            }
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    TLogger.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                TLogger.e(TAG, "writeResponseBodyToDisk exception: ", e);
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            TLogger.e(TAG, "writeResponseBodyToDisk exception: ", e);
            return false;
        }
    }

    /**
     * 获取lrc文件
     */
    public static File getLrcFile(String lrclink) {
        String fileName = FileUtil.key(lrclink);
        File file = new File(FileUtil.PATH_LRC, fileName);
        return file;
    }
}
