package com.github.fosin.anan.auth.service.inter;

import com.github.fosin.anan.platformapi.entity.AnanUserEntity;

/**
 * 2017/12/27.
 * Time:15:12
 *
 * @author fosin
 */
public interface UserService {
    AnanUserEntity findByUsercode(String usercode);

}
