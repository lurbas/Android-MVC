package com.lucasurbas.mvc.view.interfaces;


import com.lucasurbas.mvc.model.screenstate.ScreenState;

/**
 * Created by Lucas on 2/27/15.
 */
public interface ILayout {

    public void setScreenState(ScreenState screenState);

    public void update();
}
