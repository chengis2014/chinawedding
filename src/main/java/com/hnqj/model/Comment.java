package com.hnqj.model;

import java.util.Date;

public class Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.uid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.worksid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String worksid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.commentuserid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String commentuserid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.commenttime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private Date commenttime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.commentinfo
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String commentinfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.parentid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String parentid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.uid
     *
     * @return the value of tb_comment.uid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.uid
     *
     * @param uid the value for tb_comment.uid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.worksid
     *
     * @return the value of tb_comment.worksid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getWorksid() {
        return worksid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.worksid
     *
     * @param worksid the value for tb_comment.worksid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setWorksid(String worksid) {
        this.worksid = worksid == null ? null : worksid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.commentuserid
     *
     * @return the value of tb_comment.commentuserid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getCommentuserid() {
        return commentuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.commentuserid
     *
     * @param commentuserid the value for tb_comment.commentuserid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setCommentuserid(String commentuserid) {
        this.commentuserid = commentuserid == null ? null : commentuserid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.commenttime
     *
     * @return the value of tb_comment.commenttime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public Date getCommenttime() {
        return commenttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.commenttime
     *
     * @param commenttime the value for tb_comment.commenttime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.commentinfo
     *
     * @return the value of tb_comment.commentinfo
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getCommentinfo() {
        return commentinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.commentinfo
     *
     * @param commentinfo the value for tb_comment.commentinfo
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setCommentinfo(String commentinfo) {
        this.commentinfo = commentinfo == null ? null : commentinfo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.parentid
     *
     * @return the value of tb_comment.parentid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.parentid
     *
     * @param parentid the value for tb_comment.parentid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }
}