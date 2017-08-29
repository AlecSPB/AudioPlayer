package com.tc.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by itcayman on 2017/8/28.
 */

@Entity
public class CommonEntity {
    @Id(autoincrement = true)
    public long id;
    public String content;
    public String type;
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Generated(hash = 616838440)
    public CommonEntity(long id, String content, String type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }
    @Generated(hash = 1906383939)
    public CommonEntity() {
    }
}
