package mobi.espier.utils.colorpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class Palette extends GridView {
	public Palette(Context context) {
		super(context);
	}

	public Palette(Context context, AttributeSet attr) {
		this(context, attr, android.R.attr.gridViewStyle);

	}

	public Palette(Context context, AttributeSet attr, int defStyle) {
		super(context, attr, defStyle);
	}
}
