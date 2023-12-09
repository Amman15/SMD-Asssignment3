package com.example.datastorageapp.data
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contact)
    @Update
    fun updateContact(contact: Contact)
    @Delete
    fun deleteContact(contact: Contact)
    @Query("SELECT COUNT(*) FROM contacts WHERE phoneNumber = :phoneNumber")
    fun countContactsByPhoneNumber(phoneNumber: String): Int
}