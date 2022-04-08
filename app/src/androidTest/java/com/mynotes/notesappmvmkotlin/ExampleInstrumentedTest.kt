package com.mynotes.notesappmvmkotlin

import androidx.navigation.ActivityNavigatorExtras
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("dev.vengateshm.cleanarchitecturemvvnotesapp", appContext.packageName)
    }

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule()
    val splash = ActivityScenarioRule(SplashScreen::class.java)


    fun save_notes(){
        onView(withId(R.id.btnAddNotes)).perform(click())
    }

    fun delete_notes(){
        onView(withId(R.id.menu_delete)).perform(click())
    }

    fun share_notes(){
        onView(withId(R.id.action_share)).perform(click())
    }
}