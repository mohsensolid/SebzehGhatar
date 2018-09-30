
package com.example.solid.mindgame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.solid.mindgame.R;
import com.example.solid.mindgame.internaldi.HasComponent;

import javax.inject.Inject;
import static android.widget.Toast.makeText;


/**
 * Base {@link Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    @Inject
    protected T mPresenter;
    private Toast toast = null;

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InjectFragment();
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private View ButterKnife(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.dispose();
        }
        mPresenter = null;
    }

    public abstract void InjectFragment();

    /**
     * Shows a {@link Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        if (toast != null) toast.cancel();
        toast = makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }



}
