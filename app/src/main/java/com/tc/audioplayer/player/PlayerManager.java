package com.tc.audioplayer.player;

/**
 * Created by itcayman on 2017/8/18.
 */

public class PlayerManager {
    private static PlayerManager instance;
    private IPlayer player;

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    /**
     * 注册播放器
     */
    public void registerPlayer(IPlayer iPlayer) {
        this.player = iPlayer;
    }

    private PlayerManager() {

    }
}
