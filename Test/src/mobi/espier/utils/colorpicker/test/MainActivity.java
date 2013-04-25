package mobi.espier.utils.colorpicker.test;

import mobi.espier.utils.colorpicker.ColorPicker;
import mobi.espier.utils.colorpicker.ColorPickerPage;
import mobi.espier.utils.colorpicker.ColorView;
import mobi.espier.utils.colorpicker.Palette;
import mobi.espier.utils.colorpicker.SVBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.fmsoft.ioslikeui.widget.SegmentedPageGroup;
import cn.fmsoft.ioslikeui.widget.SegmentedPageGroup.OnPageCallback;
import cn.fmsoft.ioslikeui.widget.SegmentedPageGroup.OnPageSelectedListener;
import cn.fmsoft.ioslikeui.widget.SegmentedRadioGroup;

public class MainActivity extends Activity implements OnPageSelectedListener,
		OnPageCallback {
	TextView mTest;

	SegmentedRadioGroup mGroup;
	SegmentedPageGroup mPageGroup;

	ColorPickerPage mCPPage;
	ColorPicker picker;
	LinearLayout mPalettePage;
	Palette mPalette;

	Integer[] colors = { Color.BLACK, Color.GREEN, Color.RED, Color.BLUE,
			Color.CYAN, Color.DKGRAY, Color.LTGRAY, Color.MAGENTA, Color.YELLOW };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTest = (TextView) findViewById(R.id.test);

		mGroup = (SegmentedRadioGroup) findViewById(R.id.radio_group);
		mGroup.check(R.id.color_picker_button);

		mPageGroup = (SegmentedPageGroup) findViewById(R.id.page_group);
		mPageGroup.setRadioGroup(mGroup, new int[] { R.id.palette_button,
				R.id.palette_button1, R.id.color_picker_button }, new int[] {
				R.id.palette, R.id.palette1, R.id.color_picker_page });
		mPageGroup.setOnPageSelectedListener(this);
		mPageGroup.setOnPageCallback(this);
		
		mPalette = (Palette) findViewById(R.id.palette);
		mPalette.setAdapter(colors);

		mPalette = (Palette) findViewById(R.id.palette1);
		mPalette.setAdapter(colors);

		mCPPage = (ColorPickerPage) findViewById(R.id.color_picker_page);
		picker = (ColorPicker) findViewById(R.id.picker);
		SVBar svBar = (SVBar) findViewById(R.id.svbar);
		picker.addSVBar(svBar);

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
	public void pageSelect(SegmentedPageGroup pageGroup, View selectedPage) {
		if (selectedPage instanceof ColorView) {
			int color = ((ColorView) selectedPage).getColor();
			mTest.setText(Integer.toHexString(color));
			mTest.setTextColor(color);
		}
	}

	@Override
	public void pageCall(View v, Object o) {
		if (v instanceof ColorView) {
			int color = ((ColorView) v).getColor();
			mTest.setText(Integer.toHexString(color));
			mTest.setTextColor(color);
		}
	}
}
