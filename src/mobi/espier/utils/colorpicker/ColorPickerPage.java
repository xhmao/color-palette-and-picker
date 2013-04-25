package mobi.espier.utils.colorpicker;

import mobi.espier.utils.colorpicker.ColorPicker.OnColorChangedListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import cn.fmsoft.ioslikeui.widget.SegmentedPageCallback;
import cn.fmsoft.ioslikeui.widget.SegmentedPageCallback.SegmentedPageCallbackRegister;

public class ColorPickerPage extends LinearLayout implements ColorView,
		SegmentedPageCallbackRegister {
	private int mColor;
	private ColorPicker mPicker;

	private SegmentedPageCallback mSegmentedPageCallback;

	public ColorPickerPage(Context context) {
		super(context);
	}

	public ColorPickerPage(Context context, AttributeSet attr) {
		super(context, attr);
	}

	public void setSegmentedPageCallback(SegmentedPageCallback callback) {
		mSegmentedPageCallback = callback;
		ColorPicker picker = getPicker();
		if (picker != null) {
			picker.setOnColorChangedListener(new OnColorChangedListener() {
				@Override
				public void onColorChanged(int color) {
					mColor = color;
					if (mSegmentedPageCallback != null) {
						mSegmentedPageCallback.event(ColorPickerPage.this,
								color);
					}
				}
			});
		}
	}

	public SegmentedPageCallback getSegmentedPageCallback() {
		return mSegmentedPageCallback;
	}

	private ColorPicker getPicker() {
		if (mPicker != null) {
			return mPicker;
		}

		ColorPicker picker = null;
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View v = getChildAt(i);
			if (v instanceof ColorPicker) {
				picker = (ColorPicker) v;
				break;
			}
		}

		mPicker = picker;

		return picker;
	}

	@Override
	public int getColor() {
		return mColor;
	}
}
