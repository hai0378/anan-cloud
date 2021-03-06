package com.github.fosin.anan.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.anan.util.DateTimeUtil;

/**
 * 系统版本表(AnanVersion)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本表查询DTO", description = "表(anan_version)的对应的查询DTO")
public class AnanVersionRetrieveDto implements Serializable {
    private static final long serialVersionUID = -91727267823167686L;
    
    @ApiModelProperty(value = "版本ID, 主键", example = "Long")
    private Long id;

    @ApiModelProperty(value = "版本名称", example = "String")
    private String name;

    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版", example = "Integer")
    private Integer type;

    @ApiModelProperty(value = "版本价格", example = "Double")
    private Double price;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动开始日期", example = "Date")
    private Date beginTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动结束日期", example = "Date")
    private Date endTime;

    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer")
    private Integer validity;

    @ApiModelProperty(value = "到期后保护期", example = "Integer")
    private Integer protectDays;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxOrganizs;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxUsers;

    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer")
    private Integer tryout;

    @ApiModelProperty(value = "试用天数", example = "Integer")
    private Integer tryoutDays;

    @ApiModelProperty(value = "启用状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "版本描述", example = "String")
    private String description;

    @ApiModelProperty(value = "创建人，创建人：该值由后台自动维护，取值于系统用户表的编号", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "更新人，创建人：该值由后台自动维护，取值于系统用户表的编号", example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台自动维护", example = "Date")
    private Date updateTime;

}