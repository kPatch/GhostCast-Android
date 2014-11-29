/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package st.ghostca.ghostcast;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;
import android.widget.ViewAnimator;

import st.ghostca.ghostcast.common.activities.SampleActivityBase;
import st.ghostca.ghostcast.common.logger.Log;
import st.ghostca.ghostcast.common.logger.LogFragment;
import st.ghostca.ghostcast.common.logger.LogWrapper;
import st.ghostca.ghostcast.common.logger.MessageOnlyLogFilter;
import st.ghostca.ghostcast.fragments.FeedFragment;
import st.ghostca.ghostcast.fragments.FriendsFragment;
import st.ghostca.ghostcast.fragments.LurkFragment;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class MainActivity extends SampleActivityBase
        implements  FeedFragment.OnFragmentInteractionListener,
                    FriendsFragment.OnFragmentInteractionListener,
                    LurkFragment.OnFragmentInteractionListener{

    public static final String TAG = "MainActivity";

    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsColorsFragment fragment = new SlidingTabsColorsFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
        logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);
        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_toggle_log:
                mLogShown = !mLogShown;
                ViewAnimator output = (ViewAnimator) findViewById(R.id.sample_output);
                if (mLogShown) {
                    output.setDisplayedChild(1);

                    View backgroundMap = findViewById(R.id.background_map);
                    backgroundMap.setVisibility(View.VISIBLE);
                    backgroundMap.setAlpha(1);

                    View core = findViewById(R.id.core_fragment);
                    slideDown(core);
                } else {
                    output.setDisplayedChild(0);

                    View backgroundMap = findViewById(R.id.background_map);
                    backgroundMap.setVisibility(View.GONE);
                    backgroundMap.setAlpha(0);

                    View core = findViewById(R.id.core_fragment);
                    slideUp(core);
                }
                supportInvalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Create a chain of targets that will receive log data */
    @Override
    public void initializeLogging() {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());

        Log.i(TAG, "Ready");
    }

    public void onFeedFragmentInteraction(String id){
        Toast.makeText(getApplicationContext(), "ID: " + id, Toast.LENGTH_SHORT).show();
    }

    public void onFriendFragmentInteraction(String id){
        Toast.makeText(getApplicationContext(), "ID: " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLurkFragmentInteraction(Uri uri) {
    }

    //////////////////
    private Animation.AnimationListener slideDownAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //View view = findViewById(R.id.sample_main_layout);
            View view = findViewById(R.id.core_fragment);
            final int left = view.getLeft();
            final int top = view.getTop();
            final int right = view.getRight();
            final int bottom = view.getBottom();
            //view.layout(left, (int)(top - 0.25 * top), right, (int)(bottom - 0.25 * bottom));
            view.layout(left, (int)(top + 0.25 * top), right, (int)(bottom + 0.25 * bottom));
        }
    };

    private Animation.AnimationListener slideUpAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //View view = findViewById(R.id.sample_main_layout);
            View view = findViewById(R.id.core_fragment);
            final int left = view.getLeft();
            final int top = view.getTop();
            final int right = view.getRight();
            final int bottom = view.getBottom();
            //view.layout(left, (int)(top - 0.25 * top), right, (int)(bottom - 0.25 * bottom));
            view.layout(left, (int)(top - 0.25 * top), right, (int)(bottom - 0.25 * bottom));
        }
    };

    private Animation slideDownAnimation = new TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f,
            //Animation.RELATIVE_TO_SELF, -0.25f,
            //Animation.RELATIVE_TO_SELF, 0.0f
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.25f
    );

    private Animation slideUpAnimation = new TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f,
            //Animation.RELATIVE_TO_SELF, -0.25f,
            //Animation.RELATIVE_TO_SELF, 0.0f
            Animation.RELATIVE_TO_SELF, 0.25f,
            Animation.RELATIVE_TO_SELF, 0.0f
    );

    private void slideDown(final View view) {

        slideDownAnimation.setDuration(1000);
        slideDownAnimation.setFillAfter(true);
        slideDownAnimation.setFillEnabled(true);
        slideDownAnimation.setAnimationListener(slideDownAnimationListener);
        view.startAnimation(slideDownAnimation);
    }

    private void slideUp(final View view) {

        slideUpAnimation.setDuration(1000);
        slideUpAnimation.setFillAfter(true);
        slideUpAnimation.setFillEnabled(true);
        slideUpAnimation.setAnimationListener(slideDownAnimationListener);
        view.startAnimation(slideUpAnimation);
    }
}
