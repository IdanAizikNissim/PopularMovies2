package gurpreetsk.me.popularmovies1;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import gurpreetsk.me.popularmovies1.utils.NetworkConnection;

public class MainActivity extends AppCompatActivity implements MovieGridViewFragment.Callback {

    private boolean hasFavouritesFragment = false;
    public static boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.details_fragment_container) != null) {
            mTwoPane = true;
//            if (NetworkConnection.isNetworkConnected(this)) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.details_fragment_container, new DetailFragment())
//                        .commit();
//            }
        } else {
            mTwoPane = false;
        }

        setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.swapFavouritesFragment:
                Intent intent = new Intent(this, FavouritesActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_sort_by:
                startActivity(new Intent(this, SettingsActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onItemSelected(Bundle data) {
        if (mTwoPane) {
            DetailFragment frag = new DetailFragment();
            Bundle bun = new Bundle();
            bun.putAll(data);
            frag.setArguments(bun);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment_container, frag)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        }
    }
}
