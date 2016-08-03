package com.flow.system.model;
/**
 * 运营商表
 * @author MK
 *
 */
public class Operator {
    private Integer id;				//主键

    private String operatorName;	//运营商名

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }
}