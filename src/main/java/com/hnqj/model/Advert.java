package com.hnqj.model;

import java.math.BigDecimal;

public class Advert {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_advert.uid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_advert.adposition
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private String adposition;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_advert.adprice
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private BigDecimal adprice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_advert.adheight
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private Integer adheight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_advert.adwidth
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    private Integer adwidth;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_advert.uid
     *
     * @return the value of tb_advert.uid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_advert.uid
     *
     * @param uid the value for tb_advert.uid
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_advert.adposition
     *
     * @return the value of tb_advert.adposition
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public String getAdposition() {
        return adposition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_advert.adposition
     *
     * @param adposition the value for tb_advert.adposition
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setAdposition(String adposition) {
        this.adposition = adposition == null ? null : adposition.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_advert.adprice
     *
     * @return the value of tb_advert.adprice
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public BigDecimal getAdprice() {
        return adprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_advert.adprice
     *
     * @param adprice the value for tb_advert.adprice
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setAdprice(BigDecimal adprice) {
        this.adprice = adprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_advert.adheight
     *
     * @return the value of tb_advert.adheight
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public Integer getAdheight() {
        return adheight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_advert.adheight
     *
     * @param adheight the value for tb_advert.adheight
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setAdheight(Integer adheight) {
        this.adheight = adheight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_advert.adwidth
     *
     * @return the value of tb_advert.adwidth
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public Integer getAdwidth() {
        return adwidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_advert.adwidth
     *
     * @param adwidth the value for tb_advert.adwidth
     *
     * @mbg.generated Tue Dec 12 11:10:02 CST 2017
     */
    public void setAdwidth(Integer adwidth) {
        this.adwidth = adwidth;
    }
}