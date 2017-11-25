package com.hk4browser.app.hk4browser.pojo;

import java.util.List;

/**
 * Created by anton on 20.11.2017.
 */

public class PostPojo {
    String banned; //0
    String closed; // 1
    String comment; // "Сети и антисети-треды будут удаляться, поехавшие - баниться. Обсуждение распределённых вычислений не запрещается, если оно адекватно, без религии и вот этого всего.<br><br>P.S.: в связи с тем, что кому-то там не нравится свободное общение, банхаммер будет использоваться чаще. Скажите спасибо фурриёбу."
    String date; //"19/01/16 Втр 06:25:52"
    String email ; // ""
    String endless; //0
    List<FilePojo> files;
    String  lasthit; //1453174315
    String name ; //""
    String num; //"330078"
    String op; //0
    String parent; // "0"
    String sticky; //1
    String subject; // ""
    String tags; // ""
    String timestamp; //1453173952
    String trip ; //"!!%mod%!!"
    String trip_type;
    String unique_posters;

    public String getBanned() {
        return banned;
    }

    public void setBanned(String banned) {
        this.banned = banned;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndless() {
        return endless;
    }

    public void setEndless(String endless) {
        this.endless = endless;
    }

    public List<FilePojo> getFiles() {
        return files;
    }

    public void setFiles(List<FilePojo> files) {
        this.files = files;
    }

    public String getLasthit() {
        return lasthit;
    }

    public void setLasthit(String lasthit) {
        this.lasthit = lasthit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getSticky() {
        return sticky;
    }

    public void setSticky(String sticky) {
        this.sticky = sticky;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getTrip_type() {
        return trip_type;
    }

    public void setTrip_type(String trip_type) {
        this.trip_type = trip_type;
    }

    public String getUnique_posters() {
        return unique_posters;
    }

    public void setUnique_posters(String unique_posters) {
        this.unique_posters = unique_posters;
    }
}
