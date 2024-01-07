package com.syalim.salt_technical_test.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/
@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey
    val label: String,
    val nextPage: Int?
)
