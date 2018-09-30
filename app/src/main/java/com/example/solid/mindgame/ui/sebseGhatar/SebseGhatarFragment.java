
/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solid.mindgame.R;
import com.example.solid.mindgame.Utils.Util;
import com.example.solid.mindgame.base.BaseFragment;
import com.example.solid.mindgame.internaldi.component.UserComponent;
import com.example.solid.mindgame.ui.sebseGhatar.Model.ChatModel;
import com.example.solid.mindgame.ui.sebseGhatar.chat.ChatAdapter;
import com.example.solid.mindgame.ui.widget.SabzehCircle;
import com.example.solid.mindgame.ui.widget.SquareView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SebseGhatarFragment extends BaseFragment<SebseGhatarPresenter> implements SebseGhatarContract.View, SabzehCircle.SebzehListener {

    public static final int DURATION_DURATION = 300;


    @BindView(R.id.firstLayer)
    SquareView firstLayer;
    @BindView(R.id.secondLayer)
    SquareView secondLayer;
    @BindView(R.id.thirdLayer)
    SquareView thirdLayer;
    @BindView(R.id.blueScore)
    TextView blueScore;
    @BindView(R.id.redScore)
    TextView redScore;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.msgExt)
    EditText msgExt;
    @BindView(R.id.bottomItems)
    LinearLayout bottomItems;
    @BindView(R.id.gameOptionsSection)
    ConstraintLayout gameOptionsSection;
    @BindView(R.id.gameMenu)
    FrameLayout gameMenu;
    @BindView(R.id.sendMessage)
    FrameLayout sendMessage;
    @BindDimen(R.dimen.gameOptionsHeight)
    int gameOptionsHeight;
    @BindDimen(R.dimen.menuSize)
    int menuSize;
    @BindView(R.id.opponentTxt)
    TextView opponentTxt;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.opponentFinderHolder)
    LinearLayout opponentFinderHolder;
    private OnFragmentInteractionListener mListener;
    private Unbinder unbinder;
    List<SabzehCircle> items = new ArrayList<>();
    private ChatAdapter adapter;

    public SebseGhatarFragment() {

    }

    public static SebseGhatarFragment newInstance() {
        SebseGhatarFragment fragment = new SebseGhatarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sebse_ghatar, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        for (int i = 0; i < firstLayer.getChildCount(); i++) {
            if (firstLayer.getChildAt(i) instanceof SabzehCircle) {
                SabzehCircle sabzehCircle = (SabzehCircle) firstLayer.getChildAt(i);
                sabzehCircle.setListener(this);
                sabzehCircle.setLayer(3);
                items.add(sabzehCircle);
            }
        }
        for (int i = 0; i < secondLayer.getChildCount(); i++) {
            if (firstLayer.getChildAt(i) instanceof SabzehCircle) {
                SabzehCircle sabzehCircle = (SabzehCircle) secondLayer.getChildAt(i);
                sabzehCircle.setListener(this);
                sabzehCircle.setLayer(2);
                items.add(sabzehCircle);
            }
        }
        for (int i = 0; i < thirdLayer.getChildCount(); i++) {
            if (firstLayer.getChildAt(i) instanceof SabzehCircle) {
                SabzehCircle sabzehCircle = (SabzehCircle) thirdLayer.getChildAt(i);
                sabzehCircle.setListener(this);
                sabzehCircle.setLayer(1);
                items.add(sabzehCircle);
            }
        }
        adapter = new ChatAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void resetTheBoard() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).unFill();
        }
    }


    @Override
    public void toggleGameOptions() {
        getActivity().runOnUiThread(() -> {
            if (gameOptionsSection.getVisibility() == View.VISIBLE) {
                gameOptionsSection.setVisibility(View.GONE);
            } else {
                gameOptionsSection.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public void showOpponentFinder() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            opponentFinderHolder.setVisibility(View.VISIBLE);
            opponentTxt.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        });

    }

    @Override
    public void hideOpponentFinde() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            opponentFinderHolder.setVisibility(View.GONE);
            opponentTxt.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public void gameIsEnded(int blueScore, int redScore) {
        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "بازی تمام شد", Toast.LENGTH_LONG).show());
    }

    @Override
    public void displayChatBox() {
        getActivity().runOnUiThread(() -> {
            msgExt.setVisibility(View.VISIBLE);
            sendMessage.setVisibility(View.VISIBLE);
        });

    }

    @Override
    public void hideChatBox() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            msgExt.setVisibility(View.GONE);
            sendMessage.setVisibility(View.GONE);
        });

    }

    @Override
    public void InjectFragment() {
        getComponent(UserComponent.class).inject(this);
    }

    @OnClick(R.id.gameMenu)
    void toggleMenu() {
        toggleGameOptions();
    }

    @OnClick(R.id.sendMessage)
    void sendMessageClicked() {
        String messageTxt = msgExt.getText().toString();
        msgExt.setText("");
        Util.hideKeyboard(Objects.requireNonNull(getActivity()));
        mPresenter.sendMessage(messageTxt);
    }

    @Override
    public void clearChat() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> adapter.setData(new ArrayList<>()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void changeItemAt(int row, int col, int layer, boolean player) {
        SabzehCircle sabzehCircle = findItemAt(row, col, layer);
        if (sabzehCircle != null && getActivity() != null) {
            getActivity().runOnUiThread(() -> sabzehCircle.changeItem(player));
        }
    }

    @Override
    public void showRedScore(int score) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> redScore.setText(String.valueOf(score)));
    }

    @Override
    public void showBlueScore(int score) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> blueScore.setText(String.valueOf(score)));
    }

    @Override
    public void addNewMessage(String messageText, boolean userMessage) {
        getActivity().runOnUiThread(() -> {
            adapter.addNewMessage(new ChatModel(messageText, userMessage));
            recyclerView.postDelayed(() -> recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1), 300);

        });
    }

    private SabzehCircle findItemAt(int row, int col, int layer) {
        SabzehCircle item = null;
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            if (item.isValid(row, col, layer)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void showError(String msg) {
        showToastMessage(msg);
    }

    @OnClick(R.id.gameReset)
    void gameRestClicked() {
        mPresenter.resetTheGame();
    }

    @OnClick(R.id.onlineGame)
    void onlineGameClicked() {
        mPresenter.onlineGame();
    }

    @OnClick(R.id.twoPlayer)
    void twoPlayerClicked() {
        mPresenter.twoPlayerGame();
    }

    @Override
    public boolean isYourTurn() {
        return mPresenter.isYourTurn();
    }

    @Override
    public void cellClickedAt(int currentLayer, int currentRow, int currentCol) {
        mPresenter.cellClickedAt(currentRow, currentCol, currentLayer);
    }

    public interface OnFragmentInteractionListener {

    }
}
