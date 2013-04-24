package mobi.espier.utils.colorpicker;

import mobi.espier.utils.colorpicker.ColorPicker.OnColorChangedListener;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnCheckedChangeListener,
		OnColorChangedListener, OnItemClickListener {
	private final static String TAG = MainActivity.class.getName();
	TextView mTest;

	RadioGroup mGroup;

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

		mGroup = (RadioGroup) findViewById(R.id.radio_group);
		mGroup.setOnCheckedChangeListener(this);

		mPalettePage = (LinearLayout) findViewById(R.id.palette_page);
		mPalette = (Palette) findViewById(R.id.palette);
		PaletteAdapter adapter = new PaletteAdapter(this, colors);
		mPalette.setAdapter(adapter);
		mPalette.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
		mPalette.setOnItemClickListener(this);

		mCPPage = (ColorPickerPage) findViewById(R.id.color_picker_page);
		picker = (ColorPicker) findViewById(R.id.picker);
		picker.setOnColorChangedListener(this);
		SVBar svBar = (SVBar) findViewById(R.id.svbar);

		/*
		 * OpacityBar opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
		 * SaturationBar saturationBar = (SaturationBar)
		 * findViewById(R.id.saturationbar); ValueBar valueBar = (ValueBar)
		 * findViewById(R.id.valuebar);
		 */

		picker.addSVBar(svBar);
		/*
		 * picker.addOpacityBar(opacityBar);
		 * picker.addSaturationBar(saturationBar); picker.addValueBar(valueBar);
		 */

		mGroup.check(R.id.palette_button);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onColorChanged(int color) {
		picker.setOldCenterColor(color);
		if (mCPPage.getVisibility() == View.VISIBLE) {
			mTest.setText(Integer.toHexString(picker.getColor()));
			mTest.setTextColor(picker.getColor());
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group == mGroup) {
			switch (checkedId) {
			case R.id.palette_button:
				mPalettePage.setVisibility(View.VISIBLE);
				mCPPage.setVisibility(View.GONE);
				break;
			case R.id.color_picker_button:
				mPalettePage.setVisibility(View.GONE);
				mCPPage.setVisibility(View.VISIBLE);
				break;
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		if (view instanceof TextView) {
			if (mPalette.getVisibility() == View.VISIBLE) {
				int color = (Integer) view.getTag();
				mTest.setTextColor(color);
				mTest.setText(Integer.toHexString(color));
				mPalette.setItemChecked(position, true);
			}
		}
		Log.i(TAG, "onItemClicked");
	}

}
