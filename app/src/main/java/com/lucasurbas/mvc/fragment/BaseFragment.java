package com.lucasurbas.mvc.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * Created by Lucas on 2/27/15.
 */
public class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class
            .getSimpleName();

    private boolean isReady;
    private boolean logState;
    private String orientation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (logState) {
            Log.v(TAG, "onCreate");
        }
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    public void setLog(boolean log) {
        logState = log;
    }

    @Override
    public void onAttach(Activity activity) {
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                orientation = "portrait";
                break;

            case Configuration.ORIENTATION_LANDSCAPE:
                orientation = "landscape";
                break;
        }
        if (logState) {
            Log.v(TAG, "onAttach: " + orientation);
        }
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (logState) {
            Log.v(TAG, "onViewCreated: " + orientation);
        }
        super.onViewCreated(view, savedInstanceState);
    }

//    @Override
//    public void onViewStateRestored(Bundle savedInstanceState) {
//        if (logState) {
//            Log.v(TAG, "onViewStateRestored: " + orientation);
//        }
//        super.onViewStateRestored(savedInstanceState);
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (logState) {
            Log.v(TAG, "onActivityCreated: " + orientation);
        }
        isReady = true;
    }

    @Override
    public void onPause() {
        if (logState) {
            Log.v(TAG, "onPause: " + orientation);
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (logState) {
            Log.v(TAG, "onResume: " + orientation);
        }
        super.onResume();
    }

    @Override
    public void onStart() {
        if (logState) {
            Log.v(TAG, "onStart: " + orientation);
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        if (logState) {
            Log.v(TAG, "onStop: " + orientation);
        }
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (logState) {
            Log.v(TAG, "onSaveInstanceState: " + orientation);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        if (logState) {
            Log.v(TAG, "onDestroyView: " + orientation);
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        if (logState) {
            Log.v(TAG, "onDetach: " + orientation);
        }
        isReady = false;
        super.onDetach();
    }


    @Override
    public void onDestroy() {
        if (logState) {
            Log.v(TAG, "onDestroy: " + orientation);
        }
        isReady = false;
        super.onDestroy();
    }

    public boolean isReady() {
        if (getActivity() == null) {
            return false;
        } else {
            return isReady;
        }
    }
}
