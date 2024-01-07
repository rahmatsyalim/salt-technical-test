/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syalim.salt_technical_test.data.source.local.dao.NewsDao
import com.syalim.salt_technical_test.data.source.local.dao.RemoteKeyDao
import com.syalim.salt_technical_test.data.source.local.entity.NewsArticleEntity
import com.syalim.salt_technical_test.data.source.local.entity.RemoteKeyEntity


@Database(
    entities = [
        NewsArticleEntity::class,
        RemoteKeyEntity::class
    ],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao: NewsDao

    abstract val remoteKeyDao: RemoteKeyDao
}