package ch.hsr.mge.gadgeothek;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class LogoutTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public LogoutTest(){
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        Activity main = getActivity();
        String mail = "b@hsr.ch";
        String password = "12345";
        ((TextView)main.findViewById(R.id.login_email_input)).setText(mail);
        ((TextView)main.findViewById(R.id.login_password_input)).setText(password);
        onView(withId(R.id.login_login)).perform(click());
    }

    public void testLogout(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withId(R.id.action_logout)).perform(click());
        onView(withId(R.id.login_title)).check(matches(isDisplayed()));
    }

}
