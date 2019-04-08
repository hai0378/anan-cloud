package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platform.service.inter.ParameterService;
import com.github.fosin.cdp.platformapi.constant.UrlPrefixConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description 参数控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.PARAMETER)
@Api(value = UrlPrefixConstant.PARAMETER, tags = "通用参数管理", description = "通用参数管理相关操作(参数获取、自动创建)")
public class ParameterController implements ISimpleController<CdpParameterEntity, Long, CdpParameterCreateDto, CdpParameterRetrieveDto, CdpParameterUpdateDto> {
    @Autowired
    private ParameterService parameterService;

    @ApiOperation(value = "获取指定机构或指定用户的参数整条数据", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数,如果缓存和数据库中都没有找到参数，返回null值")
    @RequestMapping(value = "/entity", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<CdpParameterEntity> getParameter(@RequestParam("type") Integer type,
                                                           @RequestParam("scope") String scope, @RequestParam("name") String name) {
        return ResponseEntity.ok(parameterService.getNearestParameter(type, scope, name));
    }

    @ApiOperation(value = "获取或创建指定机构或指定用户参数值", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数，如果缓存和数据库中都没有找到参数，则自动创建一个无域参数")
    @RequestMapping(value = "/value", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> getOrCreateParameter(@RequestBody CdpParameterRetrieveDto retrieveDto) {
        int type = retrieveDto.getType();
        String scope = retrieveDto.getScope();
        String name = retrieveDto.getName();
        String defaultValue = retrieveDto.getDefaultValue();
        String description = retrieveDto.getDescription();
        return ResponseEntity.ok(parameterService.getOrCreateParameter(type, scope, name, defaultValue, description).getValue());
    }

    @ApiOperation(value = "根据参数ID刷新参数缓存信息", notes = "该方法是幂等性的，可以重复调用")
    @ApiImplicitParam(name = "id", value = "参数ID,取值于CdpParameterEntity.id")
    @RequestMapping(value = "/apply/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Boolean> apply(@PathVariable("id") Long id) {
        return ResponseEntity.ok(parameterService.applyChange(id));
    }

    @ApiOperation(value = "刷新所有已更改参数缓存信息", notes = "该方法只能在有修改参数信息的情况下使用，这是一个批量刷新参数缓存的操作")
    @RequestMapping(value = "/applys", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Boolean> applys() {
        return ResponseEntity.ok(parameterService.applyChanges());
    }

    @Override
    public ISimpleService<CdpParameterEntity, Long, CdpParameterCreateDto, CdpParameterRetrieveDto, CdpParameterUpdateDto> getService() {
        return parameterService;
    }
}
