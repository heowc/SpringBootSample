package com.example.kotlin.domain

data class Book(val isbn: String, val title: String) {
    constructor() : this("", "")
}