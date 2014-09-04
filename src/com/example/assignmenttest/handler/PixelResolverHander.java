package com.example.assignmenttest.handler;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.Log;

import com.nextin.pixelsresolver.PixelResolver;


public class PixelResolverHander {

	private static PixelResolver pixelResolverObj;
	
	public static void initPixelResolverInstance(Activity context)
	{
		pixelResolverObj=new PixelResolver();

		Configuration orientationConfig = context.getResources().getConfiguration();
		pixelResolverObj.resolvepixels(context, AppConfig.MAX_CANVAS_HEIGHT,
				AppConfig.MAX_CANVAS_WIDTH, true);
	Log.d("current_screen_width", pixelResolverObj.current_screen_width+"");
	Log.d("current_screen_height", pixelResolverObj.current_screen_height+"");
	
	if(pixelResolverObj.current_screen_height<1280&&pixelResolverObj.current_screen_height>800)
	{
		AppConfig.UI_ELEMENT_SIZE_INCREASE_RATIO=1.8;
	}
	else if(pixelResolverObj.current_screen_height<=800)
	{
		AppConfig.UI_ELEMENT_SIZE_INCREASE_RATIO=1.3;
	}
	else
	{
		if(Utils.isAmazonTV()||Utils.isTablet(context))
		{
		AppConfig.UI_ELEMENT_SIZE_INCREASE_RATIO=1;
		}
		else
		{
			AppConfig.UI_ELEMENT_SIZE_INCREASE_RATIO=0.7;
		}
	}
	}
	
	public static PixelResolver getPixelResolverInstance()
	{
		return pixelResolverObj;
	}
}
