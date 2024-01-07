package com.syalim.salt_technical_test.data.source.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LocalModule::class]
)
object TestLocalModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.inMemoryDatabaseBuilder(
            context = context,
            klass = NewsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }
}