package com.hsbc.itid.es.digiba.qtz.controller;

import com.hsbc.itid.es.digiba.quartz.entity.Result;
import com.hsbc.itid.es.digiba.quartz.entity.SysJob;
import com.hsbc.itid.es.digiba.quartz.exception.TaskException;
import com.hsbc.itid.es.digiba.quartz.service.ISysJobService;
import com.hsbc.itid.es.digiba.quartz.util.CronUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/monitor/job")
@Api("Quartz Api")
public class SysJobController
{
    private String prefix = "monitor/job";

    @Autowired
    private ISysJobService jobService;

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation("list scheduler job")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", name = "jobId", dataType = "Long")
//    })
    public Result<List<SysJob>> list(SysJob job)
    {
        Result<List<SysJob>> list = jobService.selectJobList(job);
        return list;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation("delete scheduler job")
    public void delete(SysJob sysJob) throws SchedulerException
    {

        jobService.deleteJob(sysJob);
    }

    @GetMapping("/detail/{jobId}")
    @ApiOperation("get job detail by jobId")
    public Result<SysJob> detail(@PathVariable("jobId") Long jobId, ModelMap mmap)
    {
        return jobService.selectJobById(jobId);
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    @ApiOperation("change job status")
    public Result changeStatus(SysJob job) throws SchedulerException
    {
        return jobService.changeStatus(job);
    }

    @PostMapping("/run")
    @ResponseBody
    @ApiOperation("run job from database")
    public void run(SysJob job) throws SchedulerException
    {
         jobService.run(job);
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation("add job into database, default running")
    public Result addSave(@Validated SysJob job) throws SchedulerException, TaskException
    {
//        if (!CronUtils.isValid(job.getCronExpression()))
//        {
//            return Result.failed("error cron expression");
//        }
        return jobService.insertJob(job);
    }

    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation("edit job")
    public Result editSave(@Validated SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return Result.failed("error cron expression");
        }
        return jobService.updateJob(job);
    }

//    @PostMapping("/checkCronExpressionIsValid")
//    @ResponseBody
//    public boolean checkCronExpressionIsValid(SysJob job)
//    {
//        return CronUtils.isValid(job.getCronExpression());
//    }
}
