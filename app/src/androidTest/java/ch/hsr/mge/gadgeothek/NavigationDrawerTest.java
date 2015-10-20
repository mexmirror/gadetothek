package ch.hsr.mge.gadgeothek;

import android.app.Activity;
import android.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class NavigationDrawerTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public NavigationDrawerTest(){
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        Activity main = getActivity();
        String mail = "oli@hsr.ch";
        String password = "qwertz";
        ((TextView)main.findViewById(R.id.login_email_input)).setText(mail);
        ((TextView)main.findViewById(R.id.login_password_input)).setText(password);
        onView(withId(R.id.login_login)).perform(click());
    }

    public void testOpenDrawer(){
        openDrawer(R.id.drawer_layout);
        onView(withText("Loan")).perform(click());
        onView(withText("No loans here")).check(matches(isDisplayed()));
    }

}
