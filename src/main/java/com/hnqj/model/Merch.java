package com.hnqj.model;

import java.util.Date;

public class Merch {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merch.uid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merch.merchname
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String merchname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merch.userinfouid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String userinfouid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merch.bondvalue
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private Integer bondvalue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merch.builddatetime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private Date builddatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_merch.merchscroe
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private Integer merchscroe;


    private Short statu;

    public Short getStatu() {
        return statu;
    }

    public void setStatu(Short statu) {
        this.statu = statu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merch.uid
     *
     * @return the value of tb_merch.uid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merch.uid
     *
     * @param uid the value for tb_merch.uid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merch.merchname
     *
     * @return the value of tb_merch.merchname
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getMerchname() {
        return merchname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merch.merchname
     *
     * @param merchname the value for tb_merch.merchname
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setMerchname(String merchname) {
        this.merchname = merchname == null ? null : merchname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merch.userinfouid
     *
     * @return the value of tb_merch.userinfouid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getUserinfouid() {
        return userinfouid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merch.userinfouid
     *
     * @param userinfouid the value for tb_merch.userinfouid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setUserinfouid(String userinfouid) {
        this.userinfouid = userinfouid == null ? null : userinfouid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merch.bondvalue
     *
     * @return the value of tb_merch.bondvalue
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public Integer getBondvalue() {
        return bondvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merch.bondvalue
     *
     * @param bondvalue the value for tb_merch.bondvalue
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setBondvalue(Integer bondvalue) {
        this.bondvalue = bondvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merch.builddatetime
     *
     * @return the value of tb_merch.builddatetime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public Date getBuilddatetime() {
        return builddatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merch.builddatetime
     *
     * @param builddatetime the value for tb_merch.builddatetime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setBuilddatetime(Date builddatetime) {
        this.builddatetime = builddatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_merch.merchscroe
     *
     * @return the value of tb_merch.merchscroe
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public Integer getMerchscroe() {
        return merchscroe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_merch.merchscroe
     *
     * @param merchscroe the value for tb_merch.merchscroe
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setMerchscroe(Integer merchscroe) {
        this.merchscroe = merchscroe;
    }
}