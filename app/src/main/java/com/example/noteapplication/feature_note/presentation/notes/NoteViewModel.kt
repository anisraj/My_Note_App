package com.example.noteapplication.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.feature_note.domain.model.Note
import com.example.noteapplication.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeleteNote: Note? = null

    fun onEvent(event: NotesEvents) {
        when(event) {
            is NotesEvents.Order -> {

            }
            is NotesEvents.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNoteUseCase(event.note)
                    recentlyDeleteNote = event.note
                }
            }
            is NotesEvents.RestoreNote -> {
                viewModelScope.launch {
                    viewModelScope.launch {

                    }
                }
            }
            is NotesEvents.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }
}