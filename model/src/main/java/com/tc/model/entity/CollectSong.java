package com.tc.model.entity;

import com.tc.model.db.greendao.CollectSongDao;
import com.tc.model.db.greendao.DaoSession;
import com.tc.model.db.greendao.SongEntityDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by itcayman on 2017/9/11.
 */

@Entity
public class CollectSong {
    @Id
    public Integer id;
    public String song_id;
    @ToOne(joinProperty = "song_id")
    public SongEntity song;
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1236679934)
    public void setSong(SongEntity song) {
        synchronized (this) {
            this.song = song;
            song_id = song == null ? null : song.getSong_id();
            song__resolvedKey = song_id;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 649023138)
    public SongEntity getSong() {
        String __key = this.song_id;
        if (song__resolvedKey == null || song__resolvedKey != __key) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SongEntityDao targetDao = daoSession.getSongEntityDao();
            SongEntity songNew = targetDao.load(__key);
            synchronized (this) {
                song = songNew;
                song__resolvedKey = __key;
            }
        }
        return song;
    }
    @Generated(hash = 1554908899)
    private transient String song__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 885215712)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCollectSongDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 1388933642)
    private transient CollectSongDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setSongid(String songid) {
        this.song_id = songid;
    }
    public String getSong_id() {
        return this.song_id;
    }
    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }
    @Generated(hash = 1018377737)
    public CollectSong(Integer id, String song_id) {
        this.id = id;
        this.song_id = song_id;
    }
    @Generated(hash = 911427201)
    public CollectSong() {
    }
}
