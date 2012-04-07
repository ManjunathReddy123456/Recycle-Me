package recycle.me;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.*;

public class RecycleMeActivity extends MapActivity implements OnClickListener {

	private Button btnMap;
	private MapView mapView;
	private boolean flag = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googlemap);

		btnMap = (Button) findViewById(R.id.btnMap);
		mapView = (MapView) findViewById(R.id.mapview);

		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		btnMap.setOnClickListener(this);
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.binmarker);
		
		BinItemizedOverlay bio = new BinItemizedOverlay(drawable, this);
		
		GeoPoint point = new GeoPoint(46484921, 30734484);
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Odessa!");
		
		bio.addOverlay(overlayitem);
		mapOverlays.add(bio);
		//bio.onTap(0);
		
		MapController mMapController = mapView.getController();
		
		// Координаты пишутся без точки после первых двух чисел
		// center to city park
		mMapController.animateTo((new GeoPoint(46484921, 30734484)));
		 
		//1 is world view, 21 is the tightest zoom possible
		mMapController.setZoom(15);
		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onClick(View v) {
		// to sat
		if (flag) {
			btnMap.setText("Map");
			mapView.setSatellite(true);
			Toast.makeText(this, "Switched to satellite view",
					Toast.LENGTH_SHORT).show();
			// to map
		} else if (!flag) {
			btnMap.setText("Satellite");
			mapView.setSatellite(false);
			Toast.makeText(this, "Switched to map view", Toast.LENGTH_SHORT)
					.show();
		}
		flag = !flag;
	}
}