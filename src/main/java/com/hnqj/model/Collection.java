package com.hnqj.model;

import java.util.Date;

public class Collection {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_collection.collectionuid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String collectionuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_collection.userid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_collection.worksid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String worksid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_collection.collectiontime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private Date collectiontime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_collection.collectionflag
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String collectionflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_collection.collectionuid
     *
     * @return the value of tb_collection.collectionuid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getCollectionuid() {
        return collectionuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_collection.collectionuid
     *
     * @param collectionuid the value for tb_collection.collectionuid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setCollectionuid(String collectionuid) {
        this.collectionuid = collectionuid == null ? null : collectionuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_collection.userid
     *
     * @return the value of tb_collection.userid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_collection.userid
     *
     * @param userid the value for tb_collection.userid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_collection.worksid
     *
     * @return the value of tb_collection.worksid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getWorksid() {
        return worksid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_collection.worksid
     *
     * @param worksid the value for tb_collection.worksid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setWorksid(String worksid) {
        this.worksid = worksid == null ? null : worksid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_collection.collectiontime
     *
     * @return the value of tb_collection.collectiontime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public Date getCollectiontime() {
        return collectiontime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_collection.collectiontime
     *
     * @param collectiontime the value for tb_collection.collectiontime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setCollectiontime(Date collectiontime) {
        this.collectiontime = collectiontime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_collection.collectionflag
     *
     * @return the value of tb_collection.collectionflag
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getCollectionflag() {
        return collectionflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_collection.collectionflag
     *
     * @param collectionflag the value for tb_collection.collectionflag
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setCollectionflag(String collectionflag) {
        this.collectionflag = collectionflag == null ? null : collectionflag.trim();
    }
}