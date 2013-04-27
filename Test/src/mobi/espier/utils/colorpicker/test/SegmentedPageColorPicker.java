package mobi.espier.utils.colorpicker.test;

import mobi.espier.utils.colorpicker.ColorPicker;
import mobi.espier.utils.colorpicker.ColorPicker.OnColorChangedListener;
import mobi.espier.utils.colorpicker.ColorView;
import mobi.espier.utils.colorpicker.SVBar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import cn.android.xh.segmentedctrl.SegmentedPage;

public class SegmentedPageColorPicker extends SegmentedPage implements
		ColorView {
	ColorPicker mPicker;
	SVBar mSVBar;
	int mColor;

	public SegmentedPageColorPicker(Context context) {
		super(context);
	}

	public SegmentedPageColorPicker(Context context, AttributeSet attr) {
		super(context, attr);
	}

	@Override
	protected void setupView(Context context, AttributeSet attr) {
		LayoutInflater li = LayoutInflater.from(context);
		li.inflate(R.layout.color_picker_page, this, true);
		mPicker = (ColorPicker) findViewById(R.id.picker);
		mPicker.setOnColorChangedListener(new OnColorChangedListener() {
			@Override
			public void onColorChanged(int color) {
				mColor = color;
				toPageChangedNotify(SegmentedPageColorPicker.this, color, 0);
			}
		});

		mSVBar = (SVBar) findViewById(R.id.svbar);
		mPicker.addSVBar(mSVBar);

		/*
		 * OpacityBar opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
		 * SaturationBar saturationBar = (SaturationBar)
		 * findViewById(R.id.saturationbar); ValueBar valueBar = (ValueBar)
		 * findViewById(R.id.valuebar);
		 */

		/*
		 * picker.addOpacityBar(opacityBar);
		 * picker.addSaturationBar(saturationBar); picker.addValueBar(valueBar);
		 */
	}

	@Override
	public int getColor() {
		return mColor;
	}

	@Override
	public void setColor(int color) {
		mColor = color;
	}
}