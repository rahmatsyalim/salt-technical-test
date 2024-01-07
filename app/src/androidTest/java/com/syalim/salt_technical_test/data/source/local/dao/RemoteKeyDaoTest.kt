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
    fun getRemoteKey_assert_remoteKey_is_saved() = runTest {
        val key = testFakeRemoteKey("a", 1)
        remoteKeyDao.upsertAll(listOf(key))
        val savedKey = remoteKeyDao.getRemoteKey("a")
        assertEquals(savedKey, key)
    }

    @Test
    fun upsertAll_assert_remoteKey_is_replaced() = runTest {
        val key = testFakeRemoteKey("a", 1)
        remoteKeyDao.upsertAll(listOf(key))
        val updatedKey = testFakeRemoteKey("a",2)
        remoteKeyDao.upsertAll(listOf(updatedKey))
        val savedKey = remoteKeyDao.getRemoteKey("a")
        assertEquals(savedKey, updatedKey)
    }

    @Test
    fun clearAll_assert_remoteKeys_are_all_deleted() = runTest {
        val key = testFakeRemoteKey("a", 1)
        remoteKeyDao.upsertAll(listOf(key))
        val savedKey = remoteKeyDao.getRemoteKey("a")
        assertEquals(savedKey, key)
        remoteKeyDao.clearAll()
        val updatedSavedKey = remoteKeyDao.getRemoteKey("a")
        assertEquals(updatedSavedKey, null)
    }

}

private fun testFakeRemoteKey(label: String, currentPage: Int): RemoteKeyEntity {
    return RemoteKeyEntity(
        label,
        null,
        null,
        currentPage
    )
}