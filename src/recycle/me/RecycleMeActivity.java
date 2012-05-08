package recycle.me;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.maps.*;

public class RecycleMeActivity extends MapActivity implements OnClickListener {

	private Button btnMap;
	private MapView mapView;
	private EditText edtTitle;
	private EditText edtSnippet;
	private Drawable drawable;
	private boolean flag = true;
	private BinItemizedOverlay bio;
	private String title;
	private String snippet;
	private GeoPoint point;
	private List<Overlay> mapOverlays;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googlemap);

		btnMap = (Button) findViewById(R.id.btnMap);
		mapView = (MapView) findViewById(R.id.mapview);
		edtTitle = (EditText) findViewById(R.id.edtTitle);
		edtSnippet = (EditText) findViewById(R.id.edtSnippet);

		mapView.setBuiltInZoomControls(true);
		mapView.setHapticFeedbackEnabled(true);
		mapView.setEnabled(true);
		mapView.setSatellite(false);
		btnMap.setOnClickListener(this);

		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.binmarker);

		bio = new BinItemizedOverlay(drawable, this);

		for (int i = 0; i < 10; i++) {
			GeoPoint gpOdessa = new GeoPoint(46484921 + 100, 30734484);
			OverlayItem overlayitem = new OverlayItem(gpOdessa, "Hola, Mundo!",
					"I'm in Odessa!");
	
			bio.addOverlay(overlayitem);
			mapOverlays.add(bio);
		}
		
		MapController mMapController = mapView.getController();

		// center to city park
		//mMapController.animateTo(gpOdessa);

		// 1 is world view, 21 is the tightest zoom possible
		mMapController.setZoom(15);
		mapView.invalidate();
	}

	public void createNewMarker(Context context) {
		try {
			// near to odessa point
			GeoPoint p = new GeoPoint(bio.lng, bio.lat);
			OverlayItem newBin = new OverlayItem(p, "Title", "Snippet");
			BinItemizedOverlay b = new BinItemizedOverlay(drawable, this);
			b.addOverlay(newBin);
			mapOverlays.add(b);
			
			// a teper sdelaem vsyo dinamichesky
			b.addOverlay(newBin);
		} catch (NullPointerException e) {
			e.printStackTrace();
			Log.w("Exc", "Exceptin NULL caught!");
		}
	}

	public void callDialog() {
		// custom dialog
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.bin_overview);
		dialog.setTitle("Enter information");
		dialog.setCancelable(true);

		// on Confirm dialog set Title and Snippet to a variablies
		Button btnConfirm = (Button) dialog.findViewById(R.id.btnConfirm);
		btnConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(RecycleMeActivity.this, "confirmed",
						Toast.LENGTH_SHORT).show();
				title = "Title"; // edtTitle.getText().toString();
				snippet = "Snippet"; // edtSnippet.getText().toString();
				createNewMarker(RecycleMeActivity.this);
				dialog.dismiss();
			}
		});

		// on Cancel button click - close dialog.
		Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	public void showAbout() {
		Intent myIntent = new Intent(getApplicationContext(),
				AboutActivity.class);
		startActivityForResult(myIntent, 0);
	}

	// Inflate menu, declared in main_menu.xml
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	// Handles menu item selections
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("Menu press", "Pressed menu " + item.getTitle());
		switch (item.getItemId()) {
		case R.id.menu_add_bin:
			if (BinItemizedOverlay.hasPoint) {
				callDialog();
			}
			return true;
		case R.id.menu_about:
			showAbout();
			return true;
		case R.id.menu_exit:
			finish();
			return true;
		default:
			Log.i("Menu press", "default selected");
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onClick(View v) {
		Drawable img;
		// to sat
		if (flag) {
			btnMap.setText("Map");
			img = getResources().getDrawable(R.drawable.mapicon);
			btnMap.setCompoundDrawablesWithIntrinsicBounds(img, null, null,
					null);
			mapView.setSatellite(true);
			Toast.makeText(this, "Switched to satellite view",
					Toast.LENGTH_SHORT).show();
			// to map
		} else if (!flag) {
			btnMap.setText("Satellite");
			img = getResources().getDrawable(R.drawable.saticon);
			btnMap.setCompoundDrawablesWithIntrinsicBounds(img, null, null,
					null);
			mapView.setSatellite(false);
			Toast.makeText(this, "Switched to map view", Toast.LENGTH_SHORT)
					.show();
		}
		flag = !flag;
	}
}