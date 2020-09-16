package com.dimeno.commons.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.dimeno.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * FileUtils
 * Created by wangzhen on 2020/9/16.
 */
public class FileUtils {
    /**
     * 通过Uri获取文件绝对路径
     *
     * @param context context
     * @param uri     uri
     * @return path
     */
    public static String getFileFromUri(Context context, Uri uri) {
        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
            return uri.getPath();
        }
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return queryMedia(context, uri);
            } else {
                if (DocumentsContract.isDocumentUri(context, uri)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        return queryDocumentQ(context, uri);
                    }
                    return queryDocument(context, uri);
                } else {
                    return queryMedia(context, uri);
                }
            }
        }
        return null;
    }

    private static String queryMedia(Context context, Uri uri) {
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (columnIndex > -1) {
                    path = cursor.getString(columnIndex);
                }
            }
            cursor.close();
        }
        return path;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private static String queryDocumentQ(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                InputStream stream = null;
                FileOutputStream fos = null;
                try {
                    String name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    File file = new File(context.getExternalFilesDir(null) + File.separator + name);
                    fos = new FileOutputStream(file);
                    stream = contentResolver.openInputStream(uri);
                    if (stream != null) {
                        byte[] buffer = new byte[2048];
                        int length;
                        while ((length = stream.read(buffer)) != -1) {
                            fos.write(buffer, 0, length);
                        }
                        return file.getAbsolutePath();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(stream, fos);
                }
            }
            cursor.close();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String queryDocument(Context context, Uri uri) {
        String path = null;
        String docId = DocumentsContract.getDocumentId(uri);
        if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
            // ExternalStorageProvider
            String[] split = docId.split(":");
            String type = split[0];
            if ("primary".equalsIgnoreCase(type)) {
                path = Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
            // DownloadsProvider
            String fileName = getNameColumn(context, uri);
            if (!TextUtils.isEmpty(fileName)) {
                String s = Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName;
                if (new File(s).exists()) {
                    return s;
                }
            }
            if (docId.startsWith("raw:")) {
                return docId.replaceFirst("raw:", "");
            }
            Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(docId));
            path = getDataColumn(context, contentUri, null, null);
        } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
            // MediaProvider
            String[] split = docId.split(":");
            String type = split[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            String selection = "_id=?";
            String[] selectionArgs = new String[]{split[1]};
            path = getDataColumn(context, contentUri, selection, selectionArgs);
        }
        return path;
    }

    private static String getNameColumn(Context context, Uri uri) {
        Cursor cursor = null;
        String column = MediaStore.MediaColumns.DISPLAY_NAME;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndexOrThrow(column));
            }
        } catch (Exception ignore) {

        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = MediaStore.MediaColumns.DATA;
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndexOrThrow(column));
            }
        } catch (Exception ignore) {

        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}
