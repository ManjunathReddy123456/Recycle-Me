package recycle.me;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

@SuppressWarnings("rawtypes")
public class BinItemizedOverlay extends ItemizedOverlay {
	private static int lat;
	private static int lng;
	private GeoPoint p;

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;

	public BinItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet() + "\n" + "lat: " + lat + "\n"
				+ "long: " + lng);
		dialog.setNeutralButton("Gotcha",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		dialog.show();
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		// ---when user lifts his finger---
		if (event.getAction() == 1) {
			p = mapView.getProjection().fromPixels((int) event.getX(),
					(int) event.getY());

			lat = (int) (p.getLatitudeE6());
			lng = (int) (p.getLongitudeE6());
			Log.i("Jelly", "Latitude: " + lat + "Longitude: " + lng);
			Toast.makeText(mContext,
					"Latitude: " + lat + "\nLongitude: " + lng,
					Toast.LENGTH_SHORT).show();
		}
		return false;
	}
}
