package com.hb.utilities

import android.app.ActivityManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

object ServiceAndJobSchedulerUtils {

    /*Service and schedule jobs*/


    /**
     * This method is used to check whether the service is running or not
     * @param activity
     * @param serviceClass - is a service which we want to check it's running or not
     * @return
     */

    fun isMyServiceRunning(
        activity: AppCompatActivity,
        serviceClass: Class<*>
    ): Boolean {
        val manager = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    /**
     * This method is used to start required service
     * @param activity
     * @param serviceClass - is a service which we want start
     * @return
     */

    fun startRequiredService(
        activity: AppCompatActivity,
        serviceClass: Class<*>
    ) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!isMyServiceRunning(
                        activity,
                        serviceClass
                    )
                ) activity.startForegroundService(
                    Intent(
                        activity,
                        serviceClass
                    )
                ) else if (isMyServiceRunning(
                        activity,
                        serviceClass
                    )
                ) {
                    activity.stopService(Intent(activity, serviceClass))
                    activity.startForegroundService(Intent(activity, serviceClass))
                }
            } else {
                if (!isMyServiceRunning(
                        activity,
                        serviceClass
                    )
                ) activity.startService(
                    Intent(
                        activity,
                        serviceClass
                    )
                ) else if (isMyServiceRunning(
                        activity,
                        serviceClass
                    )
                ) {
                    activity.stopService(Intent(activity, serviceClass))
                    activity.startService(Intent(activity, serviceClass))
                }
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    /**
     * This method is used to start required service
     * @param context
     * @param serviceClass - is a service which we want schedule for a job
     * @return
     */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun scheduleJob(
        context: Context,
        serviceClass: Class<*>
    ) {
        val serviceComponent = ComponentName(context, serviceClass)
        val builder: JobInfo.Builder = JobInfo.Builder(0, serviceComponent)
        builder.setMinimumLatency(1 * 1000) // wait at least
        builder.setOverrideDeadline(3 * 1000) // maximum delay
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        val jobScheduler: JobScheduler? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getSystemService(JobScheduler::class.java)
        } else {
            context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        }
        if (jobScheduler != null) jobScheduler.schedule(builder.build())
    }
}