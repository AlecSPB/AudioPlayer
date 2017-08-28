package com.tc.audioplayer.player;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tc.model.entity.SongEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.tc.audioplayer.player.Player.LIST;
import static com.tc.audioplayer.player.Player.LOOP;
import static com.tc.audioplayer.player.Player.SHUFFLE;
import static com.tc.audioplayer.player.Player.SINGLE;

/**
 * Created by tianchao on 2017/8/10.
 */

public class PlayList {
    public static final int NO_POSITION = -1;
    private
    @Player.PlayMode
    int playMode = LOOP;
    private List<SongEntity> songList = new ArrayList<>();
    private int playingIndex = -1;

    public PlayList() {
        // EMPTY
    }

    public PlayList(SongEntity song) {
        songList.add(song);
    }

    @NonNull
    public List<SongEntity> getSongList() {
        if (songList == null)
            return new ArrayList<>();
        return songList;
    }

    public SongEntity getCurrentSong() {
        if (songList == null || playingIndex < 0 || playingIndex >= songList.size())
            return null;
        return songList.get(playingIndex);
    }


    public int getSongCount() {
        return songList == null ? 0 : songList.size();
    }

    public void addSong(@Nullable SongEntity song) {
        if (song == null) return;

        songList.add(song);
    }

    public void addSong(@Nullable SongEntity song, int index) {
        if (song == null) return;

        songList.add(index, song);
    }

    public void addSongList(@Nullable List<SongEntity> songs, int index) {
        if (songs == null || songs.isEmpty()) return;

        songList.addAll(index, songs);
    }

    public void addSongList(@Nullable List<SongEntity> songs) {
        if (songs == null || songs.isEmpty()) return;

        songList.addAll(songs);
    }

    public boolean removeSong(SongEntity song) {
        if (song == null) return false;

        int index;
        if ((index = songList.indexOf(song)) != -1) {
            if (songList.remove(index) != null) {
                return true;
            }
        } else {
            for (Iterator<SongEntity> iterator = songList.iterator(); iterator.hasNext(); ) {
                SongEntity item = iterator.next();
                if (song.path.equals(item.path)) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public int getPlayingIndex() {
        return playingIndex;
    }

    public void setPlayingIndex(int playingIndex) {
        this.playingIndex = playingIndex;
    }

    public
    @Player.PlayMode
    int getPlayMode() {
        return playMode;
    }

    public void setPlayMode(@Player.PlayMode int playMode) {
        this.playMode = playMode;
    }

    public boolean prepare() {
        if (songList.isEmpty()) return false;
        if (playingIndex == NO_POSITION) {
            playingIndex = 0;
        }
        return true;
    }

    /**
     * Move the playingIndex forward depends on the play mode
     *
     * @return The next song to play
     */
    public SongEntity prev() {
        switch (playMode) {
            case LOOP:
            case LIST:
            case SINGLE:
                int newIndex = playingIndex - 1;
                if (newIndex < 0) {
                    newIndex = 0;
                }
                playingIndex = newIndex;
                break;
            case SHUFFLE:
                playingIndex = randomPlayIndex();
                break;
        }
        return songList.get(playingIndex);
    }

    /**
     * Move the playingIndex forward depends on the play mode
     *
     * @return The next song to play
     */
    public SongEntity next() {
        switch (playMode) {
            case LOOP:
            case LIST:
            case SINGLE:
                int newIndex = playingIndex + 1;
                if (newIndex >= songList.size()) {
                    newIndex = 0;
                }
                playingIndex = newIndex;
                break;
            case SHUFFLE:
                playingIndex = randomPlayIndex();
                break;
        }
        return songList.get(playingIndex);
    }

    private int randomPlayIndex() {
        int randomIndex = new Random().nextInt(songList.size());
        // Make sure not play the same song twice if there are at least 2 songs
        if (songList.size() > 1 && randomIndex == playingIndex) {
            randomPlayIndex();
        }
        return randomIndex;
    }
}
