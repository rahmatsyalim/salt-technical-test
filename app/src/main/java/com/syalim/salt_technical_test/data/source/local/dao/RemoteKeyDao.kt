/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.syalim.salt_technical_test.data.source.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {

    @Upsert
    suspend fun upsertKey(key: RemoteKeyEntity)

    @Query("select * from remote_keys where label=:label")
    suspend fun getKey(label: String): RemoteKeyEntity?

    @Query("delete from remote_keys WHERE label=:label")
    suspend fun deleteKey(label: String)
}