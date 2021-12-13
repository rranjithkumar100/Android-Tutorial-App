package ranjih.kotlinandroid.controller.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

	public static Paint drawPaint, canvasPaint;
	Context mContext;
	private Path drawPath;
	private int paintColor = 0xFF000000;
	private Canvas drawCanvas;
	private Bitmap canvasBitmap;
	private String mSomeProperty;
	private float brushSize;
	private boolean erase = false;


	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
		this.mContext = context;
	}

	private void setupDrawing() {
		brushSize = 15;
		drawPath = new Path();
		drawPaint = new Paint();

		drawPaint.setColor(Color.TRANSPARENT);
		drawPaint.setAntiAlias(true);

		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
		canvasPaint = new Paint(Paint.DITHER_FLAG);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(drawPath, drawPaint);

		setDrawingCacheEnabled(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchX = event.getX();
		float touchY = event.getY();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				drawPath.moveTo(touchX, touchY);
				break;
			case MotionEvent.ACTION_MOVE:
				drawPath.lineTo(touchX, touchY);
				break;
			case MotionEvent.ACTION_UP:
				drawPath.lineTo(touchX, touchY);
				drawCanvas.drawPath(drawPath, drawPaint);
				drawPath.reset();
				break;
			default:
				return false;
		}
		invalidate();
		return true;

	}

	public void setSomeProperty(String value) {
		mSomeProperty = value;
		setDrawingCacheEnabled(false); // clear the cache here
		invalidate();
	}

	public void setColor(String newColor) {
		invalidate();
		if (newColor.startsWith("#")) {
			paintColor = Color.parseColor(newColor);
			drawPaint.setColor(paintColor);
			drawPaint.setShader(null);
		}
	}

	public void setBrushSize(float newSize) {
		float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				newSize, getResources().getDisplayMetrics());
		brushSize = pixelAmount;
		drawPaint.setStrokeWidth(brushSize);
	}

	public void setErase(boolean isErase) {
		erase = isErase;
		if (erase) {
		    /*drawPaint.setAlpha(0xFF);*/
			drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
			// drawPaint.setAlpha(0x80);
			drawPaint.setAlpha(0xFF);
		} else drawPaint.setXfermode(null);
	}


	//start new drawing
	public void startNew() {
		drawCanvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
		invalidate();
	}


	public Bitmap saveimages() {
		Bitmap newBitmap = Bitmap.createBitmap(canvasBitmap.getWidth(), canvasBitmap.getHeight(), canvasBitmap.getConfig());
		Canvas canvas = new Canvas(newBitmap);
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(canvasBitmap, 0, 0, null);

		Bitmap cs = null;
		Bitmap resized = Bitmap.createScaledBitmap(newBitmap, 480, 320, true);
		cs = Bitmap.createBitmap(480, 320, Bitmap.Config.ARGB_8888);
		Canvas comboImage = new Canvas(cs);

		comboImage.drawBitmap(resized, 0f, 0f, null);
		return cs;

	}

}