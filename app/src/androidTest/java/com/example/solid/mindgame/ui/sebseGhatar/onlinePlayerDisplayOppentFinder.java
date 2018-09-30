/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.solid.mindgame.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class onlinePlayerDisplayOppentFinder {
    @Rule
    public ActivityTestRule<SebseGhatarActivity> mActivityTestRule = new ActivityTestRule<>(SebseGhatarActivity.class);
    @Test
    public void onlinePlayerDisplayOppentFinder() {
        onView(withId(R.id.onlineGame)).check(matches(isDisplayed()));
        onView(withId(R.id.onlineGame)).perform(click());
        onView(withId(R.id.opponentFinderHolder)).check(matches(isDisplayed()));
    }

}
