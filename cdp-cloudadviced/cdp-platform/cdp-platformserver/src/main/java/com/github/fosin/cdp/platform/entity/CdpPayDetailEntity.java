package com.github.fosin.cdp.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * 系统支付明细表(CdpPayDetail)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:34
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_pay_detail")
@ApiModel(value = "系统支付明细表实体类", description = "表(cdp_pay_detail)的对应的实体类")
public class CdpPayDetailEntity extends AbstractPersistable<Long> implements Serializable {
    private static final long serialVersionUID = -23058131683087837L;

    @Basic
    @ApiModelProperty(value = "支付ID", required = true)
    @Column(name = "pay_id", nullable = false)
    private Long payId;

    @Basic
    @ApiModelProperty(value = "付款方式", required = true)
    @Column(name = "payway", nullable = false)
    private Integer payway;

    @Basic
    @ApiModelProperty(value = "付款金额", required = true)
    @Column(name = "money", nullable = false, precision = 12, scale = 2)
    private Double money;

}