package st.ghostca.ghostcast.custom;

/**
 * Created by irvin on 10/26/2014.
 */

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import st.ghostca.ghostcast.R;


/**
 * This is a common activity that all other activities of the app can extend to
 * inherit the common behaviors like setting a Theme to activity.
 */
public class CustomActivity extends FragmentActivity implements OnClickListener
{

    /** The Constant THEME. */
    private static final String THEME = "appTheme";

    /** THEME_BUBBLE **/
    public static final int THEME_BUBBLE = R.drawable.theme_red_flat;

    /** THEME_RADICAL_RED **/
    public static final int THEME_RADICAL_RED = R.drawable.theme_radical_red;

    /** THEME_HICCUP **/
    public static final int THEME_HICCUP = R.drawable.theme_hiccup;

    /** THEME_HICCUP **/
    public static final int THEME_HICCUP1 = R.drawable.theme_hiccup1;

    /** THEME_HICCUP **/
    public static final int THEME_HICCUP2 = R.drawable.theme_hiccup2;

    /** THEME_HICCUP **/
    public static final int THEME_HICCUP3 = R.drawable.theme_hiccup3;

    /** THEME_HICCUP **/
    public static final int THEME_HICCUP4 = R.drawable.theme_hiccup4;

    public static final int THEME_DARK = R.drawable.theme_dark;

    /** The Constant THEME_YELLOW. */
    public static final int THEME_YELLOW = R.drawable.theme_yellow_flat;

    /** The Constant THEME_GREEN. */
    public static final int THEME_GREEN = R.drawable.theme_green_flat;

    /** The Constant THEME_RED. */
    public static final int THEME_RED = R.drawable.theme_red_flat;

    /** The Constant THEME_BLUE. */
    public static final int THEME_BLUE = R.drawable.theme_blue_flat;


    /** The current theme. */
    protected int theme;

    /**
     * Apply this Constant as touch listener for views to provide alpha touch
     * effect. The view must have a Non-Transparent background.
     */
    //public static final TouchEffect TOUCH = new TouchEffect();

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        theme = getAppTheme();
        if (getActionBar() != null)
            setupActionBar();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Apply current theme to the background of a View.
     *
     * @param v
     *            the View on which theme will be applied.
     */
    public void applyBgTheme(View v)
    {
        v.setBackgroundResource(theme);
    }

    /**
     * This method will setup the top title bar (Action bar) content and display
     * values. It will also setup the custom background theme for ActionBar. You
     * can override this method to change the behavior of ActionBar for
     * particular Activity
     */
    protected void setupActionBar()
    {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setLogo(R.drawable.icon);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        actionBar.setBackgroundDrawable(getResources().getDrawable(theme));
    }

    /**
     * Save the theme of the app.
     *
     * @param theme
     *            the theme to save
     */
    protected void saveAppTheme(int theme)
    {
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt(THEME, theme).commit();
    }

    /**
     * Returns the current theme of the app. The return value can be one of
     * following: THEME_BLACK THEME_BLUE THEME_GREEN THEME_RED
     *
     * @return the app theme
     */
    protected int getAppTheme()
    {
        return PreferenceManager.getDefaultSharedPreferences(this).getInt(
                THEME, THEME_BUBBLE);
    }

    /**
     * Sets the touch and click listeners for a view..
     *
     * @param id
     *            the id of View
     * @return the view
     */
    public View setTouchNClick(int id)
    {

        View v = setClick(id);
        //v.setOnTouchListener(TOUCH);
        return v;
    }

    /**
     * Sets the click listener for a view.
     *
     * @param id
     *            the id of View
     * @return the view
     */
    public View setClick(int id)
    {

        View v = findViewById(id);
        v.setOnClickListener(this);
        return v;
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub

    }

}
