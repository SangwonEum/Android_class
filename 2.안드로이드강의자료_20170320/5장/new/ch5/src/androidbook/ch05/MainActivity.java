package androidbook.ch06;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickStorage(View v) {
        startActivity(new Intent(this, StorageActivity.class));
    }

    public void onClickDatabase(View v) {
        startActivity(new Intent(this, DatabaseActivity.class));
    }

    public void onClickDatabaseSQL(View v) {
        startActivity(new Intent(this, DatabaseSQLActivity.class));
    }

    public void onClickPreferences(View v) {
        startActivity(new Intent(this, PreferencesActivity.class));
    }
}
