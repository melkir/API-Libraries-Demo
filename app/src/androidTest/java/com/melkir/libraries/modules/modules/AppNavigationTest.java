package com.melkir.libraries.modules.modules;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import com.melkir.libraries.R;
import com.melkir.libraries.about.AboutActivity;
import com.melkir.libraries.modules.ModulesActivity;
import com.melkir.libraries.modules.custom.action.NavigationViewActions;
import com.melkir.libraries.settings.SettingsActivity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.melkir.libraries.modules.TestUtils.atPosition;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class AppNavigationTest {
    @Rule
    public IntentsTestRule<ModulesActivity> mActivityTestRule = new IntentsTestRule<>(ModulesActivity.class);

    @Before
    public void setUp() {
        // Check that left drawer is closed at startup
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.START)));
    }

    @Test
    public void clickOnModuleDetailFabButton_LaunchModule() {
        // Click on the first card item on the RecyclerView
        openFirstItemModuleDetail();

        // Click on the fab button to launch the activity
        onView(withId(R.id.fab_action)).perform(click());

        // Check that Barcode Reader Activity was opened
        onView(withId(R.id.activity_barcode_reader)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnModulesItem_ShowsDetailModule() {
        // Click on the first card item on the RecyclerView
        openFirstItemModuleDetail();

        // Check that Module Detail Activity was opened
        onView(withId(R.id.detail_content)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnModuleLaunchButton_LaunchModule() {
        // Click on the launch card button
        onView(allOf(withId(R.id.card_launch), isDisplayed())).perform(click());

        // Check that Barcode Reader Activity was opened
        onView(withId(R.id.activity_barcode_reader)).check(matches(isDisplayed()));
    }

    @Ignore("not ready yet")
    @Test
    public void clickOnLoginButton_Login() {
        // Open Drawer to show the login button
        openDrawer(R.id.drawer_layout);

        // Check that the user is currently logged out
        onView(withId(R.id.sign_in_button)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(withId(R.id.sign_out_button)).check(matches(withEffectiveVisibility(Visibility.GONE)));

        onView(withId(R.id.sign_in_button)).perform(click());
    }

    @Test
    public void clickOnSettingsNavigationItem_ShowsSettings() {
        // Open Drawer to click on settings
        openDrawer(R.id.drawer_layout);

        // Starts about settings
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.settings));

        // Check that Settings Activity was opened
        intended(hasComponent(SettingsActivity.class.getName()));
    }

    @Test
    public void clickOnAboutNavigationItem_ShowsAbout() {
        // Open Drawer to click on about
        openDrawer(R.id.drawer_layout);

        // Starts about screen
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.about));

        // Check that About Activity was opened
        intended(hasComponent(AboutActivity.class.getName()));
    }

    @Test
    public void clickOnAndroidSearchIcon_OpenSearch() {
        // Open the search view
        onView(withId(R.id.action_search)).perform(click());

        // Check that the list is sorted
        onView(withId(R.id.recycler_view))
                .check(matches(atPosition(0, hasDescendant(withText("AI Experiments")))));
    }

    @Test
    public void clickOnModulesSearchItem_ShowsDetailModule() {
        // Open the search view
        onView(withId(R.id.action_search)).perform(click());

        // Click on the first card item on the RecyclerView
        openFirstItemModuleDetail();

        // Check that Module Detail Activity was opened
        onView(withId(R.id.detail_content)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnAndroidHomeIcon_OpenNavigation() {
        // Open Drawer
        openDrawer(R.id.drawer_layout);

        // Check if drawer is open
        onView(withId(R.id.drawer_layout))
                .check(matches(isOpen(Gravity.START))); // Left drawer is open
    }

    @Test
    public void searchAnItem_SearchItem() {
        // Open the search view
        onView(withId(R.id.action_search)).perform(click());

        // Type "face" in the search frame
        ViewInteraction searchAutoComplete = onView(allOf(withId(R.id.search_src_text),
                withParent(allOf(withId(R.id.search_plate),
                        withParent(withId(R.id.search_edit_frame)))),
                isDisplayed()));
        searchAutoComplete.perform(replaceText("face"), closeSoftKeyboard());

        // Check if Face Tracker is the first item returned
        onView(withId(R.id.recycler_view))
                .check(matches(atPosition(0, hasDescendant(withText("Face Tracker")))));
    }

    private void openFirstItemModuleDetail() {
        // Click on the first card item on the RecyclerView
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

}