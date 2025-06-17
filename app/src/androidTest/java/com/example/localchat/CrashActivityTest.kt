package com.example.localchat

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrashActivityTest {
    @Test
    fun showsError() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = Intent(context, CrashActivity::class.java).apply {
            putExtra(CrashActivity.EXTRA_ERROR, "boom")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        ActivityScenario.launch<CrashActivity>(intent).use {
            onView(withId(R.id.crashMessage)).check(matches(withText("boom")))
            onView(withId(R.id.copyButton)).perform(click())
        }
    }
}
