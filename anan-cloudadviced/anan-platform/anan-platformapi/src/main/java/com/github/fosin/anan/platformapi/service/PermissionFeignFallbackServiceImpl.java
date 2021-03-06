package com.github.fosin.anan.platformapi.service;


import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.platformapi.service.inter.PermissionFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 远程调用权限服务-熔断类
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
@Service
public class PermissionFeignFallbackServiceImpl implements PermissionFeignService {

    @Override
    public ResponseEntity<List<AnanPermissionEntity>> findByAppName(String appName) {
        log.error("feign 远程查询当前应用权限失败:{}", appName);
        return null;
    }
}
