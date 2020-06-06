package cn.edu.zucc.djl.club.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

@ApiModel
public class RoomForm {
    @ApiModelProperty(value = "活动id")
    int aid;
    @ApiModelProperty(value = "场地id")
    int rid;
    @ApiModelProperty(value = "使用开始时间")
    Timestamp start;
    @ApiModelProperty(value = "使用结束时间")
    Timestamp end;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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
}
