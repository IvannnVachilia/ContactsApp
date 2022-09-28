package com.example.contactsapp.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.contactsapp.data.datasource.ContactDatabase
import com.example.contactsapp.data.repo.ContactsRepoImpl
import com.example.contactsapp.data.repo.ContactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@HiltAndroidApp
class App : Application() {

    @Provides
    @Singleton
    fun getContactRepo(contactDatabase: ContactDatabase): ContactsRepository {
        return ContactsRepoImpl(contactDatabase.contactDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ContactDatabase {
        return Room.databaseBuilder(
            appContext,
            ContactDatabase::class.java,
            "RssReader"
        ).build()
    }
}