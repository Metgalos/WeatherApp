package com.example.weatherapp.infrastructure.viewmodel

import androidx.lifecycle.MutableLiveData

class Emitter : MutableLiveData<NavigationEvent>() {
    private val waitingsEvents: ArrayList<NavigationEvent> = ArrayList()
    private var isActive = false

    override fun onInactive() {
        isActive = false
    }

    override fun onActive() {
        isActive = true
        val postingEvents = ArrayList<NavigationEvent>()

        waitingsEvents
            .forEach {
                if (hasObservers()) {
                    this.value = it
                    postingEvents.add(it)
                }
            }.also { waitingsEvents.removeAll(postingEvents) }
    }

    private fun newEvent(event: NavigationEvent, type: Type) {
        event.type = type
        this.value = when (type) {
            Type.EXECUTE_WITHOUT_LIMITS,
            Type.EXECUTE_ONCE -> if (hasObservers()) event else null

            Type.WAIT_OBSERVER_IF_NEEDED_AND_EXECUTE_ONCE,
            Type.WAIT_OBSERVER_IF_NEEDED -> {
                if (hasObservers() && isActive) event
                else {
                    waitingsEvents.add(event)
                    null
                }
            }
        }
    }

    fun clearWaitingEvents() = waitingsEvents.clear()

    fun emitAndExecute(event: NavigationEvent) = newEvent(event,
        Type.EXECUTE_WITHOUT_LIMITS
    )
    fun emitAndExecuteOnce(event: NavigationEvent) = newEvent(event,
        Type.EXECUTE_ONCE
    )
    fun waitAndExecute(event: NavigationEvent) = newEvent(event,
        Type.WAIT_OBSERVER_IF_NEEDED
    )
    fun waitAndExecuteOnce(event: NavigationEvent) = newEvent(event,
        Type.WAIT_OBSERVER_IF_NEEDED_AND_EXECUTE_ONCE
    )
}