package com.kisaa.www.moviecataloguejetpack

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.kisaa.www.moviecataloguejetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun checkMovie() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.movie_title)).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_movie_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_backdrop))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.grade_movie))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkTvShow() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tv_show_title)).perform(click())
        onView(withId(R.id.rv_tvShow))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_tvshow_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_backdrop))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_tvshow))
            .check(matches(isDisplayed()))
        onView(withId(R.id.grade_tv_show))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkFavoriteMovie() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
        onView(withText(R.string.movie_title)).perform(click())
        onView(withText(R.string.no_favorite_movie)).check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favorite_icon)).perform(click())
        onView(isRoot()).perform(pressBack())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.rv_movie_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favorite_icon)).perform(click())
    }

    @Test
    fun checkFavoriteTvShow() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
        onView(withText(R.string.tv_show_title)).perform(click())
        onView(withText(R.string.no_favorite_tvShow)).check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
        onView(withText(R.string.tv_show_title)).perform(click())
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favorite_icon)).perform(click())
        onView(isRoot()).perform(pressBack())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.favorite)).perform(click())
        onView(withText(R.string.tv_show_title)).perform(click())
        onView(withId(R.id.rv_tvShow_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favorite_icon)).perform(click())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }
}