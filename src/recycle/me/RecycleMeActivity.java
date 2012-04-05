package recycle.me;

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