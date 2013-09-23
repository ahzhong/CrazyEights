package com.ahzhong.crazyeights;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

public class TitleView extends View {
	
	private Bitmap titleGraphic;
	private Bitmap playButtonUp;
	private Bitmap playButtonDown;
	private int screenW;
	private int screenH;
	
	private Context myContext;
	
	private boolean playButtonPressed;

	public TitleView(Context context) {
		super(context);
		myContext = context;
		setBackgroundColor(Color.BLACK);
		titleGraphic = BitmapFactory.decodeResource(getResources(), R.drawable.title_graphic);
		playButtonUp = BitmapFactory.decodeResource(getResources(), R.drawable.play_button_up);
		playButtonDown = BitmapFactory.decodeResource(getResources(), R.drawable.play_button_down);
	}
	
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		screenW = w;
		screenH = h;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(titleGraphic, (screenW - titleGraphic.getWidth()) / 2, 0, null);
		if (playButtonPressed) {
			canvas.drawBitmap(playButtonDown, (screenW - playButtonUp.getWidth()) / 2, (int)(screenH * 0.7), null); 
		}
		else {
			canvas.drawBitmap(playButtonUp, (screenW - playButtonUp.getWidth()) / 2, (int)(screenH * 0.7), null); 
		}
		
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		int eventaction = event.getAction();
		int x = (int)event.getX();
		int y = (int)event.getY();
		switch (eventaction) {
		case MotionEvent.ACTION_UP:
			if (playButtonPressed) {
				Intent gameIntent = new Intent(myContext, GameActivity.class);
				myContext.startActivity(gameIntent);
			}
			playButtonPressed = false;
			break;
		case MotionEvent.ACTION_DOWN:
			if (x > (screenW - playButtonUp.getWidth()) / 2 && x < (screenW - playButtonUp.getWidth()) / 2 + playButtonUp.getWidth()
					&& y > (int)(screenH * 0.7) && y < (int)(screenH * 0.7) + playButtonUp.getHeight()) {
				playButtonPressed = true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		invalidate();
		return true;
	}

}
