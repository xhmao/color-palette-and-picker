package mobi.espier.utils.colorpicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PaletteAdapter extends BaseAdapter {
	private final static int PALETTE_LIMIT = 20;

	private Context mContext;

	private List<Integer> mData = new ArrayList<Integer>();
	private int mInnerCount;

	class Holder {
		TextView item;
	}

	public PaletteAdapter(Context context, Integer[] palette) {
		mContext = context;

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

	/*
	 * @Override public View getView(int position, View convertView, ViewGroup
	 * parent) { Holder h; if (convertView == null) { LayoutInflater li =
	 * LayoutInflater.from(mContext); convertView =
	 * li.inflate(R.layout.palette_item, null); h = new Holder(); h.item =
	 * (TextView) convertView.findViewById(R.id.palette_item);
	 * convertView.setTag(h); } else { h = (Holder) convertView.getTag(); }
	 * 
	 * TextView item = h.item;
	 * 
	 * int color = mData.get(position); item.setBackgroundColor(color);
	 * item.setTag(color);
	 * 
	 * return convertView; }
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater li = LayoutInflater.from(mContext);
			convertView = li.inflate(R.layout.palette_item, null);
		}

		int color = mData.get(position);
		convertView.setBackgroundColor(color);
		convertView.setTag(color);

		return convertView;
	}

}
