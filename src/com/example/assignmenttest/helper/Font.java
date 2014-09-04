package com.example.assignmenttest.helper;

import android.graphics.Typeface;

import com.example.assignmenttest.handler.Utils;

public class Font {

	public static Typeface getBankGthHDTypeFace() {
		return Typeface.createFromAsset(Utils.context.getAssets(),
				"Fonts/bank_gthd.ttf");
	}

}
