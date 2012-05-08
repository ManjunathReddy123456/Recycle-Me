package recycle.me;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity {
	
	Button closeButton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		closeButton = (Button) findViewById(R.id.closeAboutButton);
		
		closeButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
		
	}
}
