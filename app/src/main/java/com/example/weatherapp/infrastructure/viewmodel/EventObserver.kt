package com.example.weatherapp.infrastructure.viewmodel

import androidx.lifecycle.Observer

class EventObserver(private val handlerBlock: (NavigationEvent) -> Unit) : Observer<NavigationEvent> {
    private val executedEvents: HashSet<String> = hashSetOf()

    fun clearExecutedEvents() = executedEvents.clear()

    override fun onChanged(event: NavigationEvent?) {
        when (event?.type) {
            Type.EXECUTE_WITHOUT_LIMITS,
            Type.WAIT_OBSERVER_IF_NEEDED -> if (!event.isHandled) {
                event.isHandled = true
                event.apply(handlerBlock)
            }

            Type.WAIT_OBSERVER_IF_NEEDED_AND_EXECUTE_ONCE,
            Type.EXECUTE_ONCE -> if (event.javaClass.simpleName !in executedEvents && !event.isHandled) {
                event.isHandled = true
                executedEvents.add(event.javaClass.simpleName)
                event.apply(handlerBlock)
            }
        }
    }
}