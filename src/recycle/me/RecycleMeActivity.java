package recycle.me;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.*;

public class RecycleMeActivity extends MapActivity implements OnClickListener {

	private Button btnSat;
	private Button btnMap;
	private MapView mapView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googlemap);
		
		btnSat = (Button) findViewById(R.id.btnSat);
		btnMap = (Button) findViewById(R.id.btnMap);
		mapView = (MapView) findViewById(R.id.mapview);
		
		mapView.setBuiltInZoomControls(true);
		btnSat.setOnClickListener(this);
		btnMap.setOnClickListener(this);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onClick(View v) {
		if (v.getId() == R.id.btnSat) {
			mapView.setSatellite(true);
			Toast.makeText(this, "Switched to satellite view", Toast.LENGTH_SHORT).show();
		} else if (v.getId() == R.id.btnMap) {
			mapView.setSatellite(false);
			Toast.makeText(this, "Switched to map view", Toast.LENGTH_SHORT).show();
		}
	}
}