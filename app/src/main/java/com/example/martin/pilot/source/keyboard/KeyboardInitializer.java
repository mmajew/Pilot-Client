package com.example.martin.pilot.source.keyboard;

import android.view.View;

import com.example.martin.pilot.R;
import com.example.martin.pilot.source.handlers.KeyboardHandler;


public class KeyboardInitializer {
    private OnKeyTouchListener listener;
    private KeyboardActivity activity;

    public KeyboardInitializer(KeyboardHandler handler,  KeyboardActivity activity) {
        this.listener = new OnKeyTouchListener(handler);
        this.activity = activity;
    }

    public void initializeKeys() {
        initializeFirstRow(listener);
        initializeSecondRow(listener);
        initializeThirdRow(listener);
        initializeFourthRow(listener);
        initializeFifthRow(listener);
    }

    private void initializeFirstRow(OnKeyTouchListener listener) {
        View button;

        button = activity.findViewById(R.id.tildeButton);
        button.setTag(KeyTable.TIDLE);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num1Button);
        button.setTag(KeyTable.ONE);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num2Button);
        button.setTag(KeyTable.TWO);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num3Button);
        button.setTag(KeyTable.THREE);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num4Button);
        button.setTag(KeyTable.FOUR);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num5Button);
        button.setTag(KeyTable.FIVE);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num6Button);
        button.setTag(KeyTable.SIX);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num7Button);
        button.setTag(KeyTable.SEVEN);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num8Button);
        button.setTag(KeyTable.EIGHT);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num9Button);
        button.setTag(KeyTable.NINE);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.num0Button);
        button.setTag(KeyTable.ZERO);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.minusButton);
        button.setTag(KeyTable.MINUS);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.plusButton);
        button.setTag(KeyTable.EQUAL);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.backspaceButton);
        button.setTag(KeyTable.BACKSPACE);
        button.setOnTouchListener(listener);
    }

    private void initializeSecondRow(OnKeyTouchListener listener) {
        View button;

        button = activity.findViewById(R.id.tabButton);
        button.setTag(KeyTable.TAB);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.qButton);
        button.setTag(KeyTable.Q);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.wButton);
        button.setTag(KeyTable.W);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.eButton);
        button.setTag(KeyTable.E);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.rButton);
        button.setTag(KeyTable.R);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.tButton);
        button.setTag(KeyTable.T);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.yButton);
        button.setTag(KeyTable.Y);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.uButton);
        button.setTag(KeyTable.U);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.iButton);
        button.setTag(KeyTable.I);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.oButton);
        button.setTag(KeyTable.O);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.pButton);
        button.setTag(KeyTable.P);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.lBracketButton);
        button.setTag(KeyTable.LEFT_BRACKET);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.rBracketButton);
        button.setTag(KeyTable.RIGHT_BRACKET);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.backslashButton);
        button.setTag(KeyTable.BACKSLASH);
        button.setOnTouchListener(listener);
    }

    private void initializeThirdRow(OnKeyTouchListener listener) {
        View button;

        button = activity.findViewById(R.id.capsButton);
        button.setTag(KeyTable.CAPS_LOCK);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.aButton);
        button.setTag(KeyTable.A);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.sButton);
        button.setTag(KeyTable.S);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.dButton);
        button.setTag(KeyTable.D);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.fButton);
        button.setTag(KeyTable.F);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.gButton);
        button.setTag(KeyTable.G);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.hButton);
        button.setTag(KeyTable.H);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.jButton);
        button.setTag(KeyTable.J);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.kButton);
        button.setTag(KeyTable.K);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.lButton);
        button.setTag(KeyTable.L);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.doubleDotButton);
        button.setTag(KeyTable.SEMI_COLON);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.apostropheButton);
        button.setTag(KeyTable.APOSTROPHE);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.enterButton);
        button.setTag(KeyTable.RETURN);
        button.setOnTouchListener(listener);
    }

    private void initializeFourthRow(OnKeyTouchListener listener) {
        View button;

        button = activity.findViewById(R.id.lShiftButton);
        button.setTag(KeyTable.SHIFT);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.zButton);
        button.setTag(KeyTable.Z);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.xButton);
        button.setTag(KeyTable.X);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.cButton);
        button.setTag(KeyTable.C);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.vButton);
        button.setTag(KeyTable.V);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.bButton);
        button.setTag(KeyTable.B);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.nButton);
        button.setTag(KeyTable.N);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.mButton);
        button.setTag(KeyTable.M);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.colonButton);
        button.setTag(KeyTable.COMMA);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.dotButton);
        button.setTag(KeyTable.DOT);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.slashButton);
        button.setTag(KeyTable.SLASH);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.upArrowButton);
        button.setTag(KeyTable.UP_ARROW);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.rShiftButton);
        button.setTag(KeyTable.SHIFT);
        button.setOnTouchListener(listener);
    }

    private void initializeFifthRow(OnKeyTouchListener listener) {
        View button;

        button = activity.findViewById(R.id.lCtrlButton);
        button.setTag(KeyTable.CTRL);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.lWinButton);
        button.setTag(KeyTable.WIN);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.lAltButton);
        button.setTag(KeyTable.ALT);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.spaceButton);
        button.setTag(KeyTable.SPACE);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.rAltButton);
        button.setTag(KeyTable.ALT);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.leftArrowButton);
        button.setTag(KeyTable.LEFT_ARROW);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.downArrowButton);
        button.setTag(KeyTable.DOWN_ARROW);
        button.setOnTouchListener(listener);

        button = activity.findViewById(R.id.rightArrowButton);
        button.setTag(KeyTable.RIGHT_ARROW);
        button.setOnTouchListener(listener);
    }
}
