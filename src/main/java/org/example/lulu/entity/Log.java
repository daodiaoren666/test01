package org.example.lulu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 露露
 * @since 2023-03-22
 */
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private long logId;

    private String logName;

    private Integer logState;

    private String logText;

    private String logIp;

    private LocalDateTime logTime;

    private Integer managId;


    public long getLogId(){
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public Integer getLogState() {
        return logState;
    }

    public void setLogState(Integer logState) {
        this.logState = logState;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public Integer getManagId() {
        return managId;
    }

    public void setManagId(Integer managId) {
        this.managId = managId;
    }

    @Override
    public Serializable pkVal() {
        return this.logId;
    }

    @Override
    public String toString() {
        return "Log{" +
        "logId=" + logId +
        ", logName=" + logName +
        ", logState=" + logState +
        ", logText=" + logText +
        ", logIp=" + logIp +
        ", logTime=" + logTime +
        ", managId=" + managId +
        "}";
    }
}
