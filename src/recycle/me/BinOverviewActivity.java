// This class is probably not needed


package recycle.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BinOverviewActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bin_overview);

		Button next = (Button) findViewById(R.id.btnConfirm);
		next.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}

		});
	}
}
