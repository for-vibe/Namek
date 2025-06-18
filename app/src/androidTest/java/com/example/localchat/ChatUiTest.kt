package com.example.localchat

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Instrumented tests for the basic chat UI. */
@RunWith(AndroidJUnit4::class)
class ChatUiTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun sendMessageShowsInList() {
        onView(withId(R.id.messageInput)).perform(typeText("hi"), closeSoftKeyboard())
        onView(withId(R.id.sendButton)).perform(click())
        onView(withText("hi")).check(matches(isDisplayed()))
    }

    @Test
    fun receiveMessageShowsInList() {
        rule.scenario.onActivity { it.onMessageReceived("hello") }
        onView(withText("hello")).check(matches(isDisplayed()))
    }
}

