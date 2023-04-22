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
@TableName("logtext")
@ApiModel(value="Logtext对象", description="")
public class Logtext implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "logtext_id", type = IdType.AUTO)
    private Integer logtextId;

    @TableField("management_id")
    private Integer managementId;

    @TableField("management_name")
    private String managementName;

    @TableField("management_text")
    private String managementText;

    @TableField("management_time")
    private LocalDateTime managementTime;


}
