package com.tc.audioplayer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by itcayman on 2017/10/23.
 */
public class MD5Util {

    private static final int HEX_OXFO = 0xf0;
    private static final int HEX_OXOF = 0xf;
    private static final int MOVE_BIT_NUM = 4;

    protected static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',};
    protected static MessageDigest messageDigest = null;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
	
	
	/**
	 *32位 md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String makeMD5(String str) {
		String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
 
            int i;
 
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
 
            re_md5 = buf.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return re_md5;

	}


    /**
     * 计算文件的MD5.
     *
     * @param fileName 文件的绝对路径
     * @return 文件的md5
     * @throws IOException 讀寫錯誤.
     */
    public static String getFileMD5String(String fileName) throws IOException {
        File f = new File(fileName);
        return getFileMD5String(f);
    }

    /**
     * 计算文件的MD5，重载方法.
     *
     * @param file 文件对象
     * @return 文件的md5.
     * @throws IOException 讀寫異常.
     */
    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        messageDigest.update(byteBuffer);
        in.close();
        return bufferToHex(messageDigest.digest());
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & HEX_OXFO) >> MOVE_BIT_NUM];
        char c1 = hexDigits[bt & HEX_OXOF];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }


}