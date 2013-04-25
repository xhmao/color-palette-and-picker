package mobi.espier.utils.colorpicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mobi.espier.utils.colorpicker.ColorPicker.OnColorChangedListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.GridView;
import android.widget.TextView;
import cn.fmsoft.ioslikeui.widget.SegmentedPageCallback;
import cn.fmsoft.ioslikeui.widget.SegmentedPageCallback.SegmentedPageCallbackRegister;

public class Palette extends GridView implements ColorView,
		SegmentedPageCallbackRegister {
	private int mColor;
	private int mInitChecked;
	private OnColorChangedListener mColorChangedListener;
	private SegmentedPageCallback mSegmentedPageCallback;

	public Palette(Context context) {
		super(context);
		init(context, null, 0);
	}

	public Palette(Context context, AttributeSet attr) {
		this(context, attr, 0);

	}

	public Palette(Context context, AttributeSet attr, int defStyle) {
		super(context, attr, defStyle);
		init(context, attr, defStyle);
	}

	private void init(Context context, AttributeSet attrs, int defStyle) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.Palette, defStyle, 0);
		mInitChecked = a.getInt(R.styleable.Palette_initChecked, -1);

		setOnItemClickListener();
		setChoiceMode(CHOICE_MODE_SINGLE);
	}

	public void setAdapter(Integer[] palette) {
		setAdapter(new PaletteAdapter(palette));
		if (mInitChecked > -1 && mInitChecked < getAdapter().getCount()) {
			setItemChecked(mInitChecked, true);
			mColor = palette[mInitChecked];

		}
	}
	
	@Override
	protected void layoutChildren(){
		super.layoutChildren();
		
		updateOnScreenCheckedViews();
	}

	public void setOnItemClickListener() {
		setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (view instanceof Checkable) {
					setItemChecked(position, true);
				}
				mColor = (Integer) getAdapter().getItem(position);
				if (mColorChangedListener != null) {
					mColorChangedListener.onColorChanged(mColor);
				}

				if (mSegmentedPageCallback != null) {
					mSegmentedPageCallback.event(Palette.this, mColor);
				}
			}
		});
	}

	@Override
	public int getColor() {
		return mColor;
	}

	public void setOnColorChangedListener(OnColorChangedListener listener) {
		mColorChangedListener = listener;
	}

	public OnColorChangedListener getOnColorChangedListener() {
		return mColorChangedListener;
	}

	public void setSegmentedPageCallback(SegmentedPageCallback callback) {
		mSegmentedPageCallback = callback;
	}

	public SegmentedPageCallback getSegmentedPageCallback() {
		return mSegmentedPageCallback;
	}

	class PaletteAdapter extends BaseAdapter {
		private final static int PALETTE_LIMIT = 20;

		private List<Integer> mData = new ArrayList<Integer>();
		private int mInnerCount;

		class Holder {
			TextView item;
		}

		public PaletteAdapter(Integer[] palette) {

			if (palette != null) {
				Collections.addAll(mData, palette);
				mInnerCount = palette.length;
			} else {
				mInnerCount = 0;
			}
		}

		public boolean add(Integer color) {
			if (mData.size() >= PALETTE_LIMIT) {
				mData.add(PALETTE_LIMIT - 1, color);
				return true;
			}

			return mData.add(color);
		}

		public Integer remove(int location) {
			int size = mData.size();
			if (size == mInnerCount) {
				return 0;
			}

			int i = location + mInnerCount;
			if (i >= size) {
				return 0;
			}

			return mData.remove(i);
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				PaletteItem tv = new PaletteItem(getContext());
				tv.setMinHeight((int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_SP, 40, getContext()
								.getResources().getDisplayMetrics()));
				convertView = tv;
			}

			int color = mData.get(position);
			convertView.setBackgroundColor(color);
			convertView.setTag(color);

			return convertView;
		}

	}

	public static final int CHOICE_MODE_NONE = 0;
	public static final int CHOICE_MODE_SINGLE = 1;
	int mChoiceMode = CHOICE_MODE_NONE;
	private SparseBooleanArray mCheckStates;

	public void setChoiceMode(int choiceMode) {
		mChoiceMode = choiceMode;
		if (mChoiceMode != CHOICE_MODE_NONE) {
			if (mCheckStates == null) {
				mCheckStates = new SparseBooleanArray();
			}
		}
	}

	public void setItemChecked(int position, boolean value) {
		if (mChoiceMode == CHOICE_MODE_NONE) {
			return;
		}

		if (mChoiceMode == CHOICE_MODE_SINGLE) {
			if (value || isItemChecked(position)) {
				mCheckStates.clear();
			}

			if (value) {
				mCheckStates.put(position, true);
			} else if (mCheckStates.size() == 0 || !mCheckStates.valueAt(0)) {
			}
		}

		requestLayout();
	}

	private void updateOnScreenCheckedViews() {
		final int firstPos = getFirstVisiblePosition();
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			final int position = firstPos + i;
			if (child instanceof Checkable) {
				((Checkable) child).setChecked(mCheckStates.get(position));
			}
		}
	}

	public boolean isItemChecked(int position) {
		if (mChoiceMode != CHOICE_MODE_NONE && mCheckStates != null) {
			return mCheckStates.get(position);
		}

		return false;
	}
}
