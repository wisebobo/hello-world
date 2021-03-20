package com.hsbc.itid.es.digiba.qtz.controller;

import com.hsbc.itid.es.digiba.quartz.entity.SysJobLog;
import com.hsbc.itid.es.digiba.quartz.service.ISysJobLogService;
import com.hsbc.itid.es.digiba.quartz.service.ISysJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/monitor/jobLog")
@Api("Quartz Api Log")
public class SysJobLogController
{
    private String prefix = "monitor/job";

    @Autowired
    private ISysJobService jobService;

    @Autowired
    private ISysJobLogService jobLogService;


    @PostMapping("/list")
    @ResponseBody
    @ApiOperation("list log")
    public List<SysJobLog> list(SysJobLog jobLog)
    {
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        return list;
    }


    @PostMapping("/remove")
    @ResponseBody
    @ApiOperation("remove log")
    public int remove(String ids)
    {
        return jobLogService.deleteJobLogByIds(ids);
    }


    @PostMapping("/clean")
    @ResponseBody
    @ApiOperation("clean log")
    public void clean()
    {
        jobLogService.cleanJobLog();
    }
}
