package com.dimeno.commons.encryption;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.Nullable;

import com.dimeno.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * base64 util
 * Created by wangzhen on 2020/8/19.
 */
public class Base64Util {
    /**
     * convert local file to base64 string
     *
     * @param path    local file
     * @param quality quality unit: kb
     * @return base64 string
     */
    @Nullable
    public static String imageToBase64(String path, int quality) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return imageToBase64(bitmap, quality);
    }

    /**
     * convert bitmap to base64 string
     *
     * @param bitmap  bitmap
     * @param quality quality unit: kb
     * @return base64 string
     */
    @Nullable
    public static String imageToBase64(Bitmap bitmap, int quality) {
        if (bitmap != null) {
            ByteArrayOutputStream out = null;
            try {
                out = new ByteArrayOutputStream();
                int options = 100;
                do {
                    out.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, out);
                    options -= 10;
                } while (out.toByteArray().length / 1024 > quality && options > 0);
                return Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(out);
            }
        }
        return null;
    }

    /**
     * convert file to base64 string
     *
     * @param path file path
     * @return base64 string
     */
    @Nullable
    public static String fileToBase64(String path) {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(path);
            baos = new ByteArrayOutputStream(fis.available());
            int len;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fis, baos);
        }
        return null;
    }
}
