package com.example.randomapplication

object ActivityManager {
    private val activities = arrayListOf(
       FiirstActivity::class.java,
        secondActivity::class.java,
        ThirdActivity::class.java,
        FourActivity::class.java,
    )
    private var lastActivity: Class<*>? = null

    fun getNextActivity(currentActivity: Class<*>): Class<*> {
        val availableActivities = activities.filter { it != lastActivity && it != currentActivity }
        lastActivity = currentActivity
        return if (availableActivities.isNotEmpty()) {
            availableActivities.random()
        } else {
            activities.random()
        }
    }
}