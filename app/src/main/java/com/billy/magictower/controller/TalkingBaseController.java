package com.billy.magictower.controller;

public class TalkingBaseController {
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

}
