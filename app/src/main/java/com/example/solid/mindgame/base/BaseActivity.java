package com.example.solid.mindgame.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.solid.mindgame.App;
import com.example.solid.mindgame.BuildConfig;
import com.example.solid.mindgame.internaldi.component.ApplicationComponent;
import com.example.solid.mindgame.internaldi.component.DaggerUserComponent;
import com.example.solid.mindgame.internaldi.component.UserComponent;
import com.example.solid.mindgame.internaldi.modules.ActivityModule;


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
     this.getApplicationComponent().inject(this);
      initializeInjector();
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(new
              StrictMode.ThreadPolicy.Builder()
              .detectAll()
              .penaltyLog()
              .build());
      StrictMode.setVmPolicy(new
              StrictMode.VmPolicy.Builder()
              .detectAll()
              .penaltyLog()
              .build());
    }

  }
  @Override
  protected void onStart() {
    super.onStart();
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added......
   */
  protected void addFragment(int containerViewId, Fragment fragment) {
    FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
    fragmentTransaction.add(containerViewId, fragment);
    fragmentTransaction.commit();
  }
  protected UserComponent getActivityComponent(){
    return  DaggerUserComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
               .build();
  }
  /**
   * Get the Main Application component for dependency injection.
   *
   */
  public abstract void initializeInjector();
  protected ApplicationComponent getApplicationComponent() {
    return ((App) getApplication()).getApplicationComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

}
