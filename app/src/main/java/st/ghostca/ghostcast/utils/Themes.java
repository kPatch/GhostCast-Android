package st.ghostca.ghostcast.utils;

/**
 * Created by irvin on 10/26/2014.
 */
/*
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import st.ghostca.ghostcast.MainActivity;
import st.ghostca.ghostcast.R;
import st.ghostca.ghostcast.custom.CustomActivity;

/**
 *  Created by irvin on 10/26/2014.
 * The Class Theme is the activity class that is launched when user tapes on Themes button in Settings screen.
 * This activity allows the user to select the for app.
 */
public class Themes extends CustomActivity
{
    /* (non-Javadoc)
     * @see com.newsfeeder.custom.CustomActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themes);

        initViewComponents();
    }

    /**
     * This method initialize the view components, assign OnClick, OnTouch and
     * other required listeners on views.
     */
    private void initViewComponents()
    {
        setTouchNClick(R.id.themeBlue);
        setTouchNClick(R.id.themeGreen);
        setTouchNClick(R.id.themeRed);
        setTouchNClick(R.id.themeYellow);

        getActionBar().setTitle("Themes");
    }

    /* (non-Javadoc)
     * @see com.fotoly.custom.CustomActivity#onClick(android.view.View)
     */
    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId()){
            case R.id.themeRed:
                saveAppTheme(THEME_BUBBLE);
                break;
            case R.id.themeRadicalRed:
                saveAppTheme(THEME_RADICAL_RED);
                break;
            case R.id.themeHiccup:
                saveAppTheme(THEME_HICCUP);
                break;
            case R.id.themeHiccup1:
                saveAppTheme(THEME_HICCUP1);
                break;
            case R.id.themeHiccup2:
                saveAppTheme(THEME_HICCUP2);
                break;
            case R.id.themeHiccup3:
                saveAppTheme(THEME_HICCUP3);
                break;
            case R.id.themeHiccup4:
                saveAppTheme(THEME_HICCUP4);
                break;
            case R.id.themeDark:
                saveAppTheme(THEME_DARK);
                break;
            case R.id.themeBlue:
                saveAppTheme(THEME_BLUE);
                break;
            case R.id.themeGreen:
                saveAppTheme(THEME_GREEN);
                break;
            case  R.id.themeYellow:
                saveAppTheme(THEME_YELLOW);
                break;
        }
        startActivity(new Intent(this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
