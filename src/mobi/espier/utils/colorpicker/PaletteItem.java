package mobi.espier.utils.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Checkable;
import android.widget.TextView;

public class PaletteItem extends TextView implements Checkable {
	private boolean checked;
	private Paint mFramePaint = new Paint();

	public PaletteItem(Context context) {
		this(context, null);
	}

	public PaletteItem(Context context, AttributeSet attr) {
		this(context, attr, android.R.attr.textViewStyle);

	}

	public PaletteItem(Context context, AttributeSet attr, int defStyle) {
		super(context, attr, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (checked) {
			mFramePaint.setColor(Color.WHITE);
		} else {
			mFramePaint.setColor(Color.BLACK);
		}
		mFramePaint.setStyle(Style.STROKE);
		mFramePaint.setStrokeWidth(TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 5, getContext().getResources()
						.getDisplayMetrics()));
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
