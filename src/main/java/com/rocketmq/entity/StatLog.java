package com.rocketmq.entity;

import java.sql.Timestamp;

public class StatLog {

    private long id;
    private int uid;
    private int appId;
    private int pageId;
    private String actionId;
    private String description;
    private int preAppId;
    private int prePageId;
    private String channel;
    private Timestamp createTime;

    public long getId() {
        return id;
    }

    public StatLog setId(long id) {
        this.id = id;
        return this;
    }

    public int getUid() {
        return uid;
    }

    public StatLog setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public int getAppId() {
        return appId;
    }

    public StatLog setAppId(int appId) {
        this.appId = appId;
        return this;
    }

    public int getPageId() {
        return pageId;
    }

    public StatLog setPageId(int pageId) {
        this.pageId = pageId;
        return this;
    }

    public String getActionId() {
        return actionId;
    }

    public StatLog setActionId(String actionId) {
        this.actionId = actionId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StatLog setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getPreAppId() {
        return preAppId;
    }

    public StatLog setPreAppId(int preAppId) {
        this.preAppId = preAppId;
        return this;
    }

    public int getPrePageId() {
        return prePageId;
    }

    public StatLog setPrePageId(int prePageId) {
        this.prePageId = prePageId;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public StatLog setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public StatLog setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }
}
