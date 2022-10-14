package com.example.noteapplication.di

import android.app.Application
import androidx.room.Room
import com.example.noteapplication.feature_note.data.data_source.NoteDao
import com.example.noteapplication.feature_note.data.data_source.NoteDatabase
import com.example.noteapplication.feature_note.data.repository.NoteRepositoryImpl
import com.example.noteapplication.feature_note.domain.repository.NoteRepository
import com.example.noteapplication.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesUseCases(repo: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repo),
            deleteNoteUseCase = DeleteNoteUseCase(repo),
            addNoteUseCase = AddNoteUseCase(repo),
            getNoteUseCase = GetNoteUseCase(repo)
        )
    }
}