
/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar;

import android.os.Bundle;
import com.example.solid.mindgame.R;
import com.example.solid.mindgame.base.BaseActivity;
import com.example.solid.mindgame.internaldi.HasComponent;
import com.example.solid.mindgame.internaldi.component.UserComponent;

public class SebseGhatarActivity extends BaseActivity implements HasComponent<UserComponent>, SebseGhatarFragment.OnFragmentInteractionListener {
    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebse_ghatar);
        if (savedInstanceState == null)
            addFragment(R.id.container, new SebseGhatarFragment());
    }

    @Override
    public void initializeInjector() {
        userComponent = getActivityComponent();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
