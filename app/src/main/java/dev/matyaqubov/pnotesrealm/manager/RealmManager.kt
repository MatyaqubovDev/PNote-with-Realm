package dev.matyaqubov.pnotesrealm.manager

import dev.matyaqubov.pnotesrealm.model.Note
import io.realm.Realm
import io.realm.RealmResults

class RealmManager {

    companion object {
        private var realmManager: RealmManager? = null
        private lateinit var realm: Realm
        val instance: RealmManager?
            get() {
                if (realmManager == null) {
                    realmManager = RealmManager()
                }
                return realmManager
            }
    }

    init {
        realm = Realm.getDefaultInstance()
    }

    fun saveNote(note: Note) {
        realm.apply {
            beginTransaction()
            copyToRealmOrUpdate(note)
            commitTransaction()
        }
    }

    fun loadNote(): ArrayList<Note> {
        val notes = ArrayList<Note>()
        val results: RealmResults<Note> = realm.where(Note::class.java).findAll()
        for (result in results) {
            notes.add(result)
        }

        return notes
    }
}