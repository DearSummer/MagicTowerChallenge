package com.billy.magictower.controller;

public class TalkingBaseController implements  IController{
    private boolean isShow = false;

    public void start()
    {
        isShow = true;
    }

    public void end()
    {
        isShow = false;
    }



    public boolean isShowing()
    {
        return isShow;
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }
}
