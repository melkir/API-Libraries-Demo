package com.melkir.libraries.modules.modules;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.melkir.libraries.R;
import com.melkir.libraries.modules.ModulesActivity;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class LauncherTest {
    @Rule
    public ActivityTestRule<ModulesActivity> mActivityTestRule = new ActivityTestRule<>(ModulesActivity.class);

    @Test
    public void startBarcodeScanner() {
        startModule(0);
    }

    @Test
    public void startFaceTracker() {
        startModule(1);
    }

    @Test
    public void startGooglyEyes() {
        startModule(2);
    }

    @Test
    public void startGiantEmoji() {
        startModule(3);
    }

    @Test
    @Ignore
    public void startAccelerometerPlay() {
        startModule(4);
    }

    @Test
    public void startOurStreets() {
        startModule(5);
    }

    @Test
    public void startMaterialDesignCodelabs() {
        startModule(6);
    }

    @Test
    public void startTextToSpeech() {
        startModule(7);
    }

    private void startModule(int i) {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
        // Click on the fab button to launch the activity
        onView(withId(R.id.fab_action)).perform(click());
    }

}
