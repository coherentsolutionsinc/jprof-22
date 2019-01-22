package com.issoft.meetup.app2.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document()
data class User(@Id val id: String? = null, val name: String? = null)