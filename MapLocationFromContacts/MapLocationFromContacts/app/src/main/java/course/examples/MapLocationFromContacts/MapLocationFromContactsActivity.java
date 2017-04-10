package course.examples.MapLocationFromContacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MapLocationFromContactsActivity extends Activity {

	private static final String DATA_MIMETYPE = ContactsContract.Data.MIMETYPE;
	private static final Uri DATA_CONTENT_URI = ContactsContract.Data.CONTENT_URI;
	private static final String DATA_CONTACT_ID = ContactsContract.Data.CONTACT_ID;

	private static final String CONTACTS_ID = ContactsContract.Contacts._ID;
	private static final Uri CONTACTS_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;

	private static final String STRUCTURED_POSTAL_CONTENT_ITEM_TYPE = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE;
	private static final String STRUCTURED_POSTAL_FORMATTED_ADDRESS = ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS;

	private static final int PICK_CONTACT_REQUEST = 0;
	static String TAG = "MapLocation";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button button = (Button) findViewById(R.id.mapButton);

		button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_PICK, CONTACTS_CONTENT_URI);
					startActivityForResult(intent, PICK_CONTACT_REQUEST);
				} catch (Exception e) {
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		/*
		Quando viene chiamata onActivityResult, potrebbero essere successe due cose:
		 l'utente ha scelto un contatto
		 l'utente ha cliccato su back
		 */
		if (resultCode == Activity.RESULT_OK //l'activitie deve restituire ok e non canceled (cioè l'utente ha cliccato indietro)
				&& requestCode == PICK_CONTACT_REQUEST) { // il codice deve essere lo stesso del chiamante

			ContentResolver cr = getContentResolver(); //viene preso un riferimento alla lista di tutti i contatti
			Cursor cursor = cr.query(data.getData(), null, null, null, null);

			//concettualmente accede a tutta la lista dei contatti e nel manifest è dichiarato che lo farà
			//così quando si installa questa app, verrà dichiarato che farà uso della permission chiamata reads contact
			if (null != cursor && cursor.moveToFirst()) {
				String id = cursor.getString(cursor.getColumnIndex(CONTACTS_ID));
				String where = DATA_CONTACT_ID + " = ? AND " + DATA_MIMETYPE + " = ?"; //il contact id è uguale a quello che ci è stato restituito
				String[] whereParameters = new String[] { id, STRUCTURED_POSTAL_CONTENT_ITEM_TYPE }; //vai a prenderti la colonna postal address
				Cursor addrCur = cr.query(DATA_CONTENT_URI, null, where, whereParameters, null);

				if (null != addrCur && addrCur.moveToFirst()) {
					String formattedAddress = addrCur.getString(addrCur.getColumnIndex(STRUCTURED_POSTAL_FORMATTED_ADDRESS));

					if (null != formattedAddress) {
						formattedAddress = formattedAddress.replace(' ', '+');
						Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=" + formattedAddress));
						startActivity(geoIntent); //per favore gestiscimi queste info di geolocalizzazione
						//è come se fosse un accesso a un db
					}
				}
				if (null != addrCur)
					addrCur.close();
			}
			if (null != cursor)
				cursor.close();
		}
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, "The activity is about to be restarted.");
		super.onRestart();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "The activity is about to become visible.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "The activity has become visible (it is now \"resumed\")");
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
