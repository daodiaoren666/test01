package org.example.lulu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("authority")
@ApiModel(value="Authority对象", description="")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("authority_name")
    private String authorityName;

    @TableId(value = "authority_id", type = IdType.AUTO)
    private Integer authorityId;

    @TableField("authority_state")
    private Integer authorityState;

    @TableField("authority_createTime")
    private LocalDateTime authorityCreatetime;

    @TableField("authority_updateTime")
    private LocalDateTime authorityUpdatetime;


}
