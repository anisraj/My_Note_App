package com.example.noteapplication.feature_note.presentation.notes

import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.noteapplication.feature_note.domain.util.NoteOrder
import com.example.noteapplication.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
