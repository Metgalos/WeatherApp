package com.example.weatherapp.infrastructure.viewmodel

enum class Type {
    EXECUTE_WITHOUT_LIMITS,
    EXECUTE_ONCE,
    WAIT_OBSERVER_IF_NEEDED,
    WAIT_OBSERVER_IF_NEEDED_AND_EXECUTE_ONCE
}