package com.hnqj.model;

import java.util.Date;

public class Merchdown {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merchdown.uid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merchdown.merchuid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private String merchuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merchdown.merchscore
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private Integer merchscore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merchdown.downdatetime
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private Date downdatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merchdown.uid
     *
     * @return the value of tb_merchdown.uid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merchdown.uid
     *
     * @param uid the value for tb_merchdown.uid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merchdown.merchuid
     *
     * @return the value of tb_merchdown.merchuid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public String getMerchuid() {
        return merchuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merchdown.merchuid
     *
     * @param merchuid the value for tb_merchdown.merchuid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setMerchuid(String merchuid) {
        this.merchuid = merchuid == null ? null : merchuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merchdown.merchscore
     *
     * @return the value of tb_merchdown.merchscore
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public Integer getMerchscore() {
        return merchscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merchdown.merchscore
     *
     * @param merchscore the value for tb_merchdown.merchscore
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setMerchscore(Integer merchscore) {
        this.merchscore = merchscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merchdown.downdatetime
     *
     * @return the value of tb_merchdown.downdatetime
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public Date getDowndatetime() {
        return downdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merchdown.downdatetime
     *
     * @param downdatetime the value for tb_merchdown.downdatetime
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setDowndatetime(Date downdatetime) {
        this.downdatetime = downdatetime;
    }
}