package mobi.espier.utils.colorpicker.test;

import mobi.espier.utils.colorpicker.ColorPicker;
import mobi.espier.utils.colorpicker.ColorView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.android.xh.segmentedctrl.SegmentedPage;
import cn.android.xh.segmentedctrl.SegmentedPage.OnSegmentedPageDataChangedListener;
import cn.android.xh.segmentedctrl.SegmentedPageGroup;
import cn.android.xh.segmentedctrl.SegmentedPageGroup.OnPageShownListener;
import cn.android.xh.segmentedctrl.SegmentedRadioGroup;

public class MainActivity extends Activity implements OnPageShownListener,
		OnSegmentedPageDataChangedListener {
	TextView mTest;

	SegmentedRadioGroup mGroup;
	SegmentedPageGroup mPageGroup;

	SegmentedPageColorPicker mCPPage;
	ColorPicker picker;
	LinearLayout mPalettePage;
	SegmentedPagePalette mPalette;

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
		mPageGroup
				.setRadioGroup(mGroup, new int[] { R.id.palette_button,
						R.id.palette_button1, R.id.color_picker_button },
						new int[] { R.id.palette_page, R.id.palette_page1,
								R.id.color_picker_page });
		mPageGroup.setOnPageShownListener(this);
		mPageGroup.setOnSegmentedPageDataChangedListener(this);

		mPalette = (SegmentedPagePalette) findViewById(R.id.palette_page);
		mPalette.setAdapter(colors);

		mPalette = (SegmentedPagePalette) findViewById(R.id.palette_page1);
		mPalette.setAdapter(colors);
		mPalette.mPalette.getAdapter().setColorByIndex(3);

		View page = mPageGroup.getSelectedPage();
		if (page instanceof ColorView) {
			int color = ((ColorView) page).getColor();
			mTest.setText(Integer.toHexString(color));
			mTest.setTextColor(color);
		}
		
		mPalette.mPalette.getAdapter().setColorByIndex(3);
	}

	@Override
	public void pageShown(View page) {
		if (page instanceof ColorView) {
			int color = ((ColorView) page).getColor();
			mTest.setText(Integer.toHexString(color));
			mTest.setTextColor(color);
		}
	}

	@Override
	public void pageChangedNotify(SegmentedPage v, Object o, int type) {
		if (v instanceof ColorView) {
			int color = ((ColorView) v).getColor();
			mTest.setText(Integer.toHexString(color));
			mTest.setTextColor(color);
		}
	}
}
