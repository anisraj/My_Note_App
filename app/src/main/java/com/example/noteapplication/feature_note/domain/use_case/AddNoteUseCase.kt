package com.example.noteapplication.feature_note.domain.use_case

import com.example.noteapplication.feature_note.domain.model.InvalidNoteException
import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank() || note.content.isBlank()) {
            throw InvalidNoteException("Invalid Note")
        }
        repository.insertNote(note)
    }
}