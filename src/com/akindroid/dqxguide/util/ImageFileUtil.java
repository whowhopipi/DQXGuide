package com.akindroid.dqxguide.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.akindroid.dqxguide.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageFileUtil {
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	
	private Context mContext;
	
	public ImageFileUtil(Context context) {
		mContext = context;
	}
	 
	public void initialize() throws IOException {
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		
		inputStream = mContext.getResources().openRawResource(R.raw.leaf);
		outputStream = mContext.openFileOutput("leaf.png", Context.MODE_WORLD_READABLE);
		
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int n = 0;
		
		while (-1 != (n = inputStream.read(buffer))) {
			outputStream.write(buffer, 0, n);
		}
		
		inputStream.close();
		outputStream.close();
	}
	
	public Bitmap getBitmapImage(String imageName) {
		Bitmap image = null;
		
		InputStream inputStream = null;
		try {
			inputStream = mContext.openFileInput(imageName);
			image = BitmapFactory.decodeStream(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return image;	
	}
}
