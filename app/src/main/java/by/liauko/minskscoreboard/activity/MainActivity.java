package by.liauko.minskscoreboard.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import by.liauko.minskscoreboard.R;
import by.liauko.minskscoreboard.fragment.WebViewFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private WebViewFragment mWebViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.DefaultTheme);
        setContentView(R.layout.activity_main);

        initFragments();
        initMainFrame();
        initToolbar();
        initNavigationView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        View view = mWebViewFragment.getView();
        if (view != null) {
            WebView webView = (WebView) view.findViewById(R.id.web_view);
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("url", webView.getOriginalUrl());
            editor.apply();
        }
    }

    private void initFragments() {
        mWebViewFragment = new WebViewFragment();
        mWebViewFragment.setPreferences(getPreferences(MODE_PRIVATE));
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .commit();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
    }

    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.view_navigation_open, R.string.view_navigation_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initMainFrame() {
        getFragmentManager().beginTransaction()
                .add(R.id.main_frame, mWebViewFragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean result = false;
        switch (item.getItemId()) {
            case R.id.home_menu_nav_item:
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_frame, mWebViewFragment)
                        .commit();
                result = true;
                break;
        }
        mDrawerLayout.closeDrawers();

        return result;
    }
}
