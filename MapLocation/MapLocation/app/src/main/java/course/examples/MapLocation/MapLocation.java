package course.examples.MapLocation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MapLocation extends Activity {

	private final String TAG = "MapLocation";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Restore any saved state 
		super.onCreate(savedInstanceState);
		
		// Set content view
		setContentView(R.layout.main);

		// Initialize UI elements
		final EditText addrText = (EditText) findViewById(R.id.location);
		final Button button = (Button) findViewById(R.id.mapButton);

		// Link UI elements to actions in code		
		button.setOnClickListener(new Button.OnClickListener() { //fai questo quando il bottone viene cliccato. (gli passo una classe)
			@Override
			public void onClick(View v) {
				try {
					String address = addrText.getText().toString(); //preso dalla casella di testo l'indirizzo e messo in una stringa
					address = address.replace(' ', '+');//rinpiazza gli spazi con +
					Intent geoIntent = new Intent( //viene creato un intent i cui parametri sono un pò diversi
							android.content.Intent.ACTION_VIEW, Uri //actionview è il primo parametro che sarà fatta su un URI
									.parse("geo:0,0?q=" + address)); //geo... è l'URI ed è uno standard di google
					startActivity(geoIntent);//lanciami un'activity che sia in grado di fare la view di una risorsa geo. Ovviamente trova google map o Waze
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
			}
		});
	}

	//gestisce altri eventi e cosa fa? Loggare altre info tramite la classe log
	/*
	La classe LOG ci permette in Android di lanciare delle strignhe che vengono collezionate dal
	log centralizzato per mandare messggi di tipo informativo
	 */

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "The activity is visible and about to be started."); //lo vedi nella logcat del android monitor una volta che l'app gira
	}

	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG, "The activity is visible and about to be restarted.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "The activity is and has focus (it is now \"resumed\")");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG,
				"Another activity is taking focus (this activity is about to be \"paused\")");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "The activity is no longer visible (it is now \"stopped\")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "The activity is about to be destroyed.");
	}
}
