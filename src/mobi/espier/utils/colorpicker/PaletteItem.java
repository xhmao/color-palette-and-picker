package mobi.espier.utils.colorpicker;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Checkable;
import android.widget.TextView;

public class PaletteItem extends TextView implements Checkable {
	private boolean checked;
	private int mFrameNormalColor;
	private int mFrameSelectedColor;
	private float mFrameStroke;
	private Paint mFramePaint = new Paint();

	public PaletteItem(Context context) {
		this(context, null);
	}

	public PaletteItem(Context context, AttributeSet attr) {
		this(context, attr, android.R.attr.textViewStyle);
	}

	public PaletteItem(Context context, AttributeSet attr, int defStyle) {
		super(context, attr, defStyle);

		final Resources b = getContext().getResources();
		TypedArray a = context.obtainStyledAttributes(attr,
				R.styleable.PaletteItem, defStyle, 0);
		mFrameNormalColor = a.getColor(
				R.styleable.PaletteItem_frameNormalColor,
				b.getColor(R.color.palette_item_frame_normal));
		mFrameSelectedColor = a.getColor(
				R.styleable.PaletteItem_frameSelectedColor,
				b.getColor(R.color.palette_item_frame_selected));
		int stroke = a.getDimensionPixelSize(
				R.styleable.PaletteItem_frameStroke,
				b.getDimensionPixelSize(R.dimen.palette_item_frame_stroke));
		setRawFrameStroke(stroke);
		a.recycle();
	}

	public void setFrameNormalColor(int color) {
		mFrameNormalColor = color;
	}

	public void setFrameSelectedColor(int color) {
		mFrameSelectedColor = color;
	}

	public void setFrameStroke(int stroke) {
		mFrameStroke = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				stroke, getContext().getResources().getDisplayMetrics());
	}

	private void setRawFrameStroke(int stroke) {
		mFrameStroke = stroke;
	}

	public int getFrameNormalColor() {
		return mFrameNormalColor;
	}

	public int getFrameSelectedColor() {
		return mFrameSelectedColor;
	}

	public float getFrameStroke() {
		return mFrameStroke;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (checked) {
			mFramePaint.setColor(mFrameSelectedColor);
		} else {
			mFramePaint.setColor(mFrameNormalColor);
		}
		mFramePaint.setStyle(Style.STROKE);
		mFramePaint.setStrokeWidth(mFrameStroke);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mFramePaint);
	}

	@Override
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public boolean isChecked() {
		return checked;
	}

	@Override
	public void toggle() {
		checked = !checked;
	}
}
