/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.solid.mindgame.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SebseGhatarActivityTest {

    @Rule
    public ActivityTestRule<SebseGhatarActivity> mActivityTestRule = new ActivityTestRule<>(SebseGhatarActivity.class);

    @Test public void ReciveMesasge(){


    }
    @Test
    public void testDefaultUi() {
        onView(withId(R.id.onlineGame)).check(matches(isDisplayed()));
        onView(withId(R.id.firstLayer)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.opponentFinderHolder)).check(matches(not(isDisplayed())));
    }

    @Test
    public void gameMenuToggleMenu() {
        onView(withId(R.id.onlineGame)).check(matches(isDisplayed()));
        onView(withId(R.id.gameMenu)).perform(click());
        onView(withId(R.id.gameOptionsSection)).check(matches(not(isDisplayed())));
        onView(withId(R.id.gameMenu)).perform(click());
        onView(withId(R.id.gameOptionsSection)).check(matches(isDisplayed()));
    }

    @Test
    public void onlinePlayerhideMenu() {
        onView(withId(R.id.onlineGame)).perform(click());
        onView(withId(R.id.gameOptionsSection)).check(matches(not(isDisplayed())));
    }

    @Test
    public void twoPlayerClickHideMenu() {
        onView(withId(R.id.twoPlayer)).perform(click());
        onView(withId(R.id.gameOptionsSection)).check(matches(not(isDisplayed())));
    }

    @Test
    public void restgameClickHioeMenu() {
        onView(withId(R.id.gameReset)).perform(click());
        onView(withId(R.id.gameOptionsSection)).check(matches(not(isDisplayed())));
    }

    @Test
    public void sebseGhatarActivityTest() {
        onView(withId(R.id.twoPlayer)).perform(click());
        ViewInteraction sabzehCircle = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.firstLayer),
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.container),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(android.R.id.content),
                                                                        0),
                                                                0)),
                                                0),
                                        0)),
                        4),
                        isDisplayed()));
        sabzehCircle.perform(click());

        ViewInteraction sabzehCircle2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.secondLayer),
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.firstLayer),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(R.id.container),
                                                                        0),
                                                                0)),
                                                0),
                                        4)),
                        3),
                        isDisplayed()));
        sabzehCircle2.perform(click());

        ViewInteraction sabzehCircle3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.secondLayer),
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.firstLayer),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(R.id.container),
                                                                        0),
                                                                0)),
                                                0),
                                        4)),
                        4),
                        isDisplayed()));
        sabzehCircle3.perform(click());

        ViewInteraction sabzehCircle4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.firstLayer),
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.container),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(android.R.id.content),
                                                                        0),
                                                                0)),
                                                0),
                                        0)),
                        3),
                        isDisplayed()));
        sabzehCircle4.perform(click());

        ViewInteraction sabzehCircle5 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.thirdLayer),
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.secondLayer),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(R.id.firstLayer),
                                                                        0),
                                                                4)),
                                                0),
                                        0)),
                        4),
                        isDisplayed()));
        sabzehCircle5.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.redScore), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.scoreHolder),
                                        childAtPosition(
                                                withId(R.id.firstLayer),
                                                6)),
                                0),
                        isDisplayed()));
        onView(withId(R.id.redScore)).check(matches(withText("1")));

    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
