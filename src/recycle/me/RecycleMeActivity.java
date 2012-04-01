package recycle.me;

import android.os.Bundle;
import com.google.android.maps.*;
public class RecycleMeActivity extends MapActivity {
    /** Called when the activity is first created. */
        
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
    }

}