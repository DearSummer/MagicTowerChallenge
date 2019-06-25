package com.billy.magictower.controller;

import com.billy.magictower.activity.MTBaseActivity;
import com.billy.magictower.model.NpcStory;
import com.billy.magictower.util.JsonUtil;

public class StoryController extends TalkingBaseController {

    private NpcStory[] npcStories;
    private int npcId,floor;

    private HeroController heroController;

    public StoryController(MTBaseActivity context,HeroController heroController)
    {
        this.heroController = heroController;
        npcStories = JsonUtil.parseJson(JsonUtil.loadJsonFromAsset(context,"npc.json"),NpcStory[].class);
    }

    public int getNpcId()
    {
        return npcId;
    }


    public void setStory(int npcId,int floor)
    {
        this.npcId = npcId;
        this.floor = floor;
    }


    public NpcStory getStory()
    {
        for (NpcStory npcStory : npcStories) {
            if (npcStory.getId()  == npcId && npcStory.getFloor() == floor + 1)
                return npcStory;
        }

        return null;
    }



    @Override
    public void end()
    {
        super.end();
        NpcStory localStory = getStory();
        heroController.getHeroAttribute().setHasEquipment(true);
        heroController.addEquipment(localStory.getGif());

    }



}
