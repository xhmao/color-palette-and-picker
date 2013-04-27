package mobi.espier.utils.colorpicker.test;

import mobi.espier.utils.colorpicker.ColorPicker.OnColorChangedListener;
import mobi.espier.utils.colorpicker.ColorView;
import mobi.espier.utils.colorpicker.Palette;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import cn.fmsoft.ioslikeui.widget.SegmentedPage;

public class SegmentedPagePalette extends SegmentedPage implements ColorView {
	Palette mPalette;
	int mColor;

	public SegmentedPagePalette(Context context) {
		super(context);
	}

	public SegmentedPagePalette(Context context, AttributeSet attr) {
		super(context, attr);
	}

	@Override
	protected void setupView(Context context, AttributeSet attr) {
		LayoutInflater li = LayoutInflater.from(context);
		li.inflate(R.layout.default_palette, this, true);

		mPalette = (Palette) findViewById(R.id.palette);
		mPalette.setOnColorChangedListener(new OnColorChangedListener() {
			@Override
			public void onColorChanged(int color) {
				mColor = color;
				toPageChangedNotify(SegmentedPagePalette.this, color, 0);
			}
		});
	}

	public void setAdapter(Integer[] palette) {
		mPalette.setAdapter(palette);
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
