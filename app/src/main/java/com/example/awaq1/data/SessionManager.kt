package com.example.awaq1.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.auth0.android.result.Credentials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

// Create the DataStore instance at the top level
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session_prefs")

class SessionManager(private val context: Context) {

    // Define keys for storing credentials
    companion object {
        private val ID_TOKEN = stringPreferencesKey("id_token")
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val USER_USERNAME = stringPreferencesKey("user_username")
        private val EXPIRES_AT = longPreferencesKey("expires_at")
        private val SCOPE = stringPreferencesKey("scope") // Key for scope
    }

    /**
     * Saves the authentication credentials and username to DataStore.
     */
    suspend fun saveCredentials(credentials: Credentials, username: String) {
        context.dataStore.edit { preferences ->
            preferences[ID_TOKEN] = credentials.idToken
            preferences[ACCESS_TOKEN] = credentials.accessToken
            credentials.refreshToken?.let { preferences[REFRESH_TOKEN] = it }
            preferences[USER_USERNAME] = username
            credentials.expiresAt?.time?.let { preferences[EXPIRES_AT] = it }
            credentials.scope?.let { preferences[SCOPE] = it } // Save scope
        }
    }

    /**
     * Clears all saved credentials from DataStore.
     */
    suspend fun clearCredentials() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    /**
     * Retrieves the saved credentials as a Flow.
     * Emits null if no valid credentials are found.
     */
    val credentialsFlow: Flow<Pair<Credentials, String>?> = context.dataStore.data.map { preferences ->
        val idToken = preferences[ID_TOKEN]
        val accessToken = preferences[ACCESS_TOKEN]
        val refreshToken = preferences[REFRESH_TOKEN]
        val username = preferences[USER_USERNAME]
        val expiresAtMillis = preferences[EXPIRES_AT]
        val scope = preferences[SCOPE] // Retrieve scope

        // If we have the essential tokens, username, and expiration, reconstruct the Credentials object
        if (idToken != null && accessToken != null && username != null && expiresAtMillis != null) {
            val expiresAtDate = Date(expiresAtMillis)

            // Construct Credentials correctly, including the scope
            // The scope might be null if it wasn't present or wasn't requested during login.
            // The Credentials constructor should accept a nullable scope.
            val credentials = Credentials(
                idToken,
                accessToken,
                "Bearer", // This is the token type, usually "Bearer"
                refreshToken,
                expiresAtDate,
                scope // Pass the retrieved scope here
            )
            Pair(credentials, username)
        } else {
            null
        }
    }
}
