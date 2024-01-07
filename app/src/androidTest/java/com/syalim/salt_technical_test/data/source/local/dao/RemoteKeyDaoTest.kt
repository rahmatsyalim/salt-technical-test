/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source.local.dao

import com.syalim.salt_technical_test.data.source.local.NewsDatabase
import com.syalim.salt_technical_test.data.source.local.entity.RemoteKeyEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class RemoteKeyDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var newsDatabase: NewsDatabase

    private lateinit var remoteKeyDao: RemoteKeyDao

    @Before
    fun init() {
        hiltRule.inject()
        remoteKeyDao = newsDatabase.remoteKeyDao
    }

    @After
    fun cleanUp() {
        newsDatabase.close()
    }

    @Test
    fun getKey_assert_remoteKey_is_correct() = runTest {
        val key = RemoteKeyEntity("a", null)
        remoteKeyDao.upsertKey(key)
        val savedKey = remoteKeyDao.getKey("a")
        assertEquals(savedKey, key)
    }

    @Test
    fun upsertKey_assert_remoteKey_is_replaced() = runTest {
        val key = RemoteKeyEntity("a", null)
        remoteKeyDao.upsertKey(key)
        val updatedKey = RemoteKeyEntity("b", 1)
        remoteKeyDao.upsertKey(updatedKey)
        val savedKey = remoteKeyDao.getKey("b")
        assertEquals(savedKey, updatedKey)
    }

    @Test
    fun deleteKey_assert_remoteKey_is_deleted() = runTest {
        val key = RemoteKeyEntity("a", null)
        remoteKeyDao.upsertKey(key)
        val savedKey = remoteKeyDao.getKey("a")
        assertEquals(savedKey, key)
        remoteKeyDao.deleteKey("a")
        val updatedSavedKey = remoteKeyDao.getKey("a")
        assertEquals(updatedSavedKey, null)
    }

}