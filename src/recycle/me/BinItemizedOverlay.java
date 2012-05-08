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
	public static int lat;
	public static int lng;
	static boolean hasPoint = false;
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
		dialog.setPositiveButton("Void bin", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(mContext, "Bin cleared", Toast.LENGTH_SHORT).show();
			}
		});
		
		dialog.setNegativeButton("Delete bin", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				AlertDialog.Builder confirm = new AlertDialog.Builder(mContext);
				confirm.create();
				confirm.setTitle("Are you sure?");
				confirm.setMessage("Delete %binname%?");
				confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(mContext, "Bin deleted", Toast.LENGTH_SHORT);
					}
				});
				
				confirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(mContext, "Well..", Toast.LENGTH_SHORT);
					}
				});
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
			hasPoint = true;
			Log.i("GeoPoint", "Latitude: " + lat + " Longitude: " + lng);
//			Toast.makeText(mContext,
//					"Latitude: " + lat + "\nLongitude: " + lng,
//					Toast.LENGTH_SHORT).show();
		}
		return false;
	}
}
