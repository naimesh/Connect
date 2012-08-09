/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.firstapp;

import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FingerPaint extends GraphicsActivity
        implements ColorPickerDialog.OnColorChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);

        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 },
                                       0.4f, 6, 3.5f);

        mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
    }

    private Paint       mPaint;
    private MaskFilter  mEmboss;
    private MaskFilter  mBlur;

    public void colorChanged(int color) {
        mPaint.setColor(color);
    }

    public class MyView extends View {

        private static final float MINP = 0.25f;
        private static final float MAXP = 0.75f;

        private Bitmap  mBitmap, currentBitmap;
        private Canvas  mCanvas;
        private Path    mPath;
        private Paint   mBitmapPaint;
        private ShapeDrawable[] mDrawables;
        private ShapeDrawable[] mDrawablesRight;   
        private int[] mDrawablesHeight;
        private int[] mDrawablesWidth;
        private int[] mDrawablesRightHeight;
        private int[] mDrawablesRightWidth;
        private int hitCounter;
        

        public MyView(Context c) {
            super(c);
            hitCounter = 0;
            Random generator = new Random();
            int random1 = generator.nextInt(4);
            int index, index1, index2, index3;
            Set<Integer> set = new HashSet();

    		// Add elements to the set
    		set.add(0);
    		set.add(1);
    		set.add(2);
    		set.add(3);
    		
    		index = (Integer) set.toArray()[random1];
    		set.remove(index);
            random1 = generator.nextInt(3);
            
            index1 = (Integer) set.toArray()[random1];
            set.remove(index1);
            random1 = generator.nextInt(2);
            
            index2 = (Integer) set.toArray()[random1];            
            set.remove(index2);
            
            index3 = (Integer) set.toArray()[0];
            set.remove(index3);
            
            
            mPath = new Path();
            
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            mDrawables = new ShapeDrawable[4];
            mDrawablesHeight = new int[4];
            mDrawablesWidth = new int[4];
            mDrawablesRight = new ShapeDrawable[4];
            mDrawablesRightHeight = new int[4];
            mDrawablesRightWidth = new int[4];
            
            mDrawables[index] = new ShapeDrawable(new RectShape());
            mDrawables[index].getPaint().setColor(0xFFFF0000);
            mDrawablesHeight[index] = 165;
            mDrawablesWidth[index] = 125;
            
            
            
            mDrawables[index1] = new ShapeDrawable(new OvalShape());
            mDrawables[index1].getPaint().setColor(0xFF00FF00);
            mDrawablesHeight[index1] = 165;
            mDrawablesWidth[index1] = 125;
            
            
            
            mDrawables[index2] = new ShapeDrawable(new RectShape());
            mDrawables[index2].getPaint().setColor(0xFF0000ff);
            mDrawablesHeight[index2] = 165;
            mDrawablesWidth[index2] = 165;
            
            
            
            
            mDrawables[index3] = new ShapeDrawable(new  ArcShape (0, 360));
            mDrawables[index3].getPaint().setColor(0xFFff00ff);
            mDrawablesHeight[index3] = 165;
            mDrawablesWidth[index3] = 165;
            
            
                        
            set.add(0);
    		set.add(1);
    		set.add(2);
    		set.add(3);
    		
    		index = (Integer) set.toArray()[random1];
    		set.remove(index);
            random1 = generator.nextInt(3);
            
            index1 = (Integer) set.toArray()[random1];
            set.remove(index1);
            random1 = generator.nextInt(2);
            
            index2 = (Integer) set.toArray()[random1];            
            set.remove(index2);
            
            index3 = (Integer) set.toArray()[0];
            set.remove(index3);
            
            mDrawablesRight[index] = new ShapeDrawable(new RectShape());
            mDrawablesRight[index].getPaint().setColor(0xFFFF0000);
            mDrawablesRightHeight[index] = 165;
            mDrawablesRightWidth[index] = 125;
            
            
            mDrawablesRight[index1] = new ShapeDrawable(new OvalShape());
            mDrawablesRight[index1].getPaint().setColor(0xFF00FF00);
            mDrawablesRightHeight[index1] = 165;
            mDrawablesRightWidth[index1] = 125;
            
            mDrawablesRight[index2] = new ShapeDrawable(new RectShape());
            mDrawablesRight[index2].getPaint().setColor(0xFF0000ff);
            mDrawablesRightHeight[index2] = 165;
            mDrawablesRightWidth[index2] = 165;
            
            mDrawablesRight[index3] = new ShapeDrawable(new  ArcShape (0, 360));
            mDrawablesRight[index3].getPaint().setColor(0xFFff00ff);
            mDrawablesRightHeight[index3] = 165;
            mDrawablesRightWidth[index3] = 165;
                        
            
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            currentBitmap =Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            int x = 10;
            int y = 10;
            int width = mDrawablesWidth[0];
            int height = mDrawablesHeight[0];
            
            mDrawables[0].setBounds(x, y, x + width, y + height);
            mDrawables[0].draw(mCanvas);
            
            y += height + 5;
            width = mDrawablesWidth[1];
            height = mDrawablesHeight[1];;
            
            
            mDrawables[1].setBounds(x, y, x + width, y + height);
            mDrawables[1].draw(mCanvas);
            
            y += height + 5;
            
            width = mDrawablesWidth[2];
            height = mDrawablesHeight[2];
            
            
            mDrawables[2].setBounds(x, y, x + width, y + height);
            mDrawables[2].draw(mCanvas);
            
            y += height + 5;
            width = mDrawablesWidth[3];
            height = mDrawablesHeight[3];
            
            mDrawables[3].setBounds(x, y, x + width, y + height);
            mDrawables[3].draw(mCanvas);
            
            
            x = 300 +165;
            y = 10;
            
            width = mDrawablesRightWidth[2];
            height = mDrawablesRightHeight[2];
            
            mDrawablesRight[2].setBounds(x - width, y, x, y + height);
            mDrawablesRight[2].draw(mCanvas);
            
            y += height + 5;
            width = mDrawablesRightWidth[0];
            height = mDrawablesRightHeight[0];
                        
            mDrawablesRight[0].setBounds(x - width, y, x, y + height);
            mDrawablesRight[0].draw(mCanvas);
            
                       
            y += height + 5;
            width = mDrawablesRightWidth[3];
            height = mDrawablesRightHeight[3];
            
            
            mDrawablesRight[3].setBounds(x - width, y, x, y + height);
            mDrawablesRight[3].draw(mCanvas);
            
            y += height + 5;
            width = mDrawablesRightWidth[1];
            height = mDrawablesRightHeight[1];
            
            
            mDrawablesRight[1].setBounds(x - width, y, x, y + height);
            mDrawablesRight[1].draw(mCanvas);            
            
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(0xFFAAAAAA);            

            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

            canvas.drawPath(mPath, mPaint);
            
            
            
        }

        private float mX, mY, sX, sY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            sX = mX = x;
            sY = mY = y;
        }
        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;
            }
        }
        private void touch_up(float x, float y) {
        	int color1, color2;
        	try
        	{
        		color1 =  mBitmap.getPixel((int)x, (int)y) ;
        		color2 = mBitmap.getPixel((int)sX, (int)sY);
        	}
        	catch(Exception e)
        	{
        		color1 = 0xFFFFFF00;
        		color2 = 1;
        	}
        	if(color1 == color2 && color1 != 0x0 && color1 != 0xFFFFFF00) {
        		mPath.lineTo(mX, mY);
        		hitCounter++;
        	}
        	else {
        		mPath.reset();
        	}
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);            
            if(hitCounter >= 4)
            {
            	done();            	
            }
            // kill this so we don't double draw
            mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up(x, y);
                    invalidate();
                    break;
            }
            return true;
        }
    }

    public void done(){
    	//Intent intent = new Intent(this, FingerPaint.class);
    	//startActivity(intent);
    	Intent intent = getIntent();
    	finish();
    	startActivity(intent);
    }
    
    private static final int COLOR_MENU_ID = Menu.FIRST;
    private static final int EMBOSS_MENU_ID = Menu.FIRST + 1;
    private static final int BLUR_MENU_ID = Menu.FIRST + 2;
    private static final int ERASE_MENU_ID = Menu.FIRST + 3;
    private static final int SRCATOP_MENU_ID = Menu.FIRST + 4;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, COLOR_MENU_ID, 0, "Color").setShortcut('3', 'c');
        menu.add(0, EMBOSS_MENU_ID, 0, "Emboss").setShortcut('4', 's');
        menu.add(0, BLUR_MENU_ID, 0, "Blur").setShortcut('5', 'z');
        menu.add(0, ERASE_MENU_ID, 0, "Erase").setShortcut('5', 'z');
        menu.add(0, SRCATOP_MENU_ID, 0, "SrcATop").setShortcut('5', 'z');

        /****   Is this the mechanism to extend with filter effects?
        Intent intent = new Intent(null, getIntent().getData());
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        menu.addIntentOptions(
                              Menu.ALTERNATIVE, 0,
                              new ComponentName(this, NotesList.class),
                              null, intent, 0, null);
        *****/
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xFF);

        switch (item.getItemId()) {
            case COLOR_MENU_ID:
                new ColorPickerDialog(this, this, mPaint.getColor()).show();
                return true;
            case EMBOSS_MENU_ID:
                if (mPaint.getMaskFilter() != mEmboss) {
                    mPaint.setMaskFilter(mEmboss);
                } else {
                    mPaint.setMaskFilter(null);
                }
                return true;
            case BLUR_MENU_ID:
                if (mPaint.getMaskFilter() != mBlur) {
                    mPaint.setMaskFilter(mBlur);
                } else {
                    mPaint.setMaskFilter(null);
                }
                return true;
            case ERASE_MENU_ID:
                mPaint.setXfermode(new PorterDuffXfermode(
                                                        PorterDuff.Mode.CLEAR));
                return true;
            case SRCATOP_MENU_ID:
                mPaint.setXfermode(new PorterDuffXfermode(
                                                    PorterDuff.Mode.SRC_ATOP));
                mPaint.setAlpha(0x80);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
