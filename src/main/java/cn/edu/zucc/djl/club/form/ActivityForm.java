package cn.edu.zucc.djl.club.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

@ApiModel
public class ActivityForm {
    @ApiModelProperty(value = "社团id")
    int cid;
    @ApiModelProperty(value = "活动场地id")
    int place;
    @ApiModelProperty(value = "场地使用的开始时间")
    Timestamp start;
    @ApiModelProperty(value = "场地使用的结束时间")
    Timestamp end;
    @ApiModelProperty(value = "活动人数")
    int number;
    @ApiModelProperty(value = "活动预算")
    double budget;
    @ApiModelProperty(value = "活动内容")
    String detail;
    @ApiModelProperty(value = "活动限制")
    int limit;
    @ApiModelProperty(value = "活动标题")
    String name;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
