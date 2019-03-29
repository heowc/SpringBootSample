package com.example.kotlin.component

import com.example.kotlin.domain.Book

interface BookRepository {

    fun getByIsbn(isbn: String): Book

    fun refresh(isbn: String)

}