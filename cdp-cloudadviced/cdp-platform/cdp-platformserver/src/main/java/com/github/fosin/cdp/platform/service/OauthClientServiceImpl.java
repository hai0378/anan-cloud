package com.github.fosin.cdp.platform.service;


import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platform.entity.OauthClientDetailsEntity;
import com.github.fosin.cdp.platform.repository.OauthClientRepository;
import com.github.fosin.cdp.platform.service.inter.IOauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import java.util.Objects;
import java.util.Optional;

/**
 * 2017/12/29.
 * Time:12:31
 *
 * @author fosin
 */
@Service
@Lazy
public class OauthClientServiceImpl implements IOauthClientService {

    @Autowired
    private OauthClientRepository oauthClientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OauthClientDetailsEntity create(OauthClientDetailsEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        String id = entity.getClientId();
        Optional<OauthClientDetailsEntity> entityOptional = oauthClientRepository.findById(id);
        Assert.isTrue(entityOptional.isPresent(), "该数据已存在，请重新设置客户端标识以区分");
        entity.setClientSecret(passwordEncoder.encode(entity.getClientSecret()));
        return oauthClientRepository.save(entity);
    }

    @Override
    public OauthClientDetailsEntity update(OauthClientDetailsEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        String id = entity.getClientId();
        Assert.isTrue(StringUtils.hasText(id), "更新数据时ClientId不能为空!");
        OauthClientDetailsEntity existsEntity = oauthClientRepository.findById(id).orElse(null);
        //如果密码与数据库中的不一致则需要加密
        if (!Objects.equals(entity.getClientSecret(), existsEntity.getClientSecret())) {
            entity.setClientSecret(passwordEncoder.encode(entity.getClientSecret()));
        }
        return oauthClientRepository.save(entity);
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<OauthClientDetailsEntity> condition = (Specification<OauthClientDetailsEntity>) (root, query, cb) -> {
            Path<String> clientId = root.get("clientId");
            Path<String> clientSecret = root.get("clientSecret");

            if (StringUtils.isEmpty(searchCondition)) {
                return query.getRestriction();
            }
            return cb.or(cb.like(clientId, "%" + searchCondition + "%"), cb.like(clientSecret, "%" + searchCondition + "%"));

        };
        //分页查找
        Page<OauthClientDetailsEntity> page = oauthClientRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public IJpaRepository<OauthClientDetailsEntity, String> getRepository() {
        return oauthClientRepository;
    }
}
