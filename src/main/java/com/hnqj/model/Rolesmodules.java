package com.hnqj.model;

import java.util.Date;

public class Rolesmodules {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_rolesmodules.fid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String fid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_rolesmodules.role_id
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_rolesmodules.md_id
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String mdId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_rolesmodules.rm_creator
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private String rmCreator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_rolesmodules.rm_ctime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    private Date rmCtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_rolesmodules.fid
     *
     * @return the value of tb_rolesmodules.fid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getFid() {
        return fid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_rolesmodules.fid
     *
     * @param fid the value for tb_rolesmodules.fid
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_rolesmodules.role_id
     *
     * @return the value of tb_rolesmodules.role_id
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_rolesmodules.role_id
     *
     * @param roleId the value for tb_rolesmodules.role_id
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_rolesmodules.md_id
     *
     * @return the value of tb_rolesmodules.md_id
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getMdId() {
        return mdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_rolesmodules.md_id
     *
     * @param mdId the value for tb_rolesmodules.md_id
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setMdId(String mdId) {
        this.mdId = mdId == null ? null : mdId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_rolesmodules.rm_creator
     *
     * @return the value of tb_rolesmodules.rm_creator
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public String getRmCreator() {
        return rmCreator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_rolesmodules.rm_creator
     *
     * @param rmCreator the value for tb_rolesmodules.rm_creator
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setRmCreator(String rmCreator) {
        this.rmCreator = rmCreator == null ? null : rmCreator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_rolesmodules.rm_ctime
     *
     * @return the value of tb_rolesmodules.rm_ctime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public Date getRmCtime() {
        return rmCtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_rolesmodules.rm_ctime
     *
     * @param rmCtime the value for tb_rolesmodules.rm_ctime
     *
     * @mbg.generated Sun Nov 12 10:54:35 CST 2017
     */
    public void setRmCtime(Date rmCtime) {
        this.rmCtime = rmCtime;
    }
}