package org.example.lulu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("daili")
@ApiModel(value="Baoming对象", description="")
public class Daili implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "daili_id", type = IdType.AUTO)
    private Integer dailiId;

    @TableField("daili_lv")
    private String dailiLv;

    @TableField("daili_sj")
    private String dailiSj;

    @TableField("daili_xj")
    private String dailiXj;

    @TableField("daili_number")
    private Integer dailiNumber;

    @TableField("daili_updateTime")
    private LocalDateTime dailiUpdatetime;

    @TableField("daili_createTime")
    private LocalDateTime dailiCreatetime;

    @TableField("daili_name")
    private String dailiName;


}
