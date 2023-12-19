package com.rowiosama.bohemianhijabapp.data

import com.rowiosama.bohemianhijabapp.model.AboutHijab
import com.rowiosama.bohemianhijabapp.model.HijabData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HijabRepository {
    private val hijab = mutableListOf<AboutHijab>()

    init {
        if (hijab.isEmpty()) {
            HijabData.hijabs.forEach {
                hijab.add(AboutHijab(it, false))
            }
        }
    }

    fun getAllHijab(): Flow<List<AboutHijab>> {
        return flowOf(hijab)
    }

    fun getHijabById(hijabId: String): AboutHijab {
        return hijab.first {
            it.hijab.id == hijabId
        }
    }

    fun updateHijab(aboutHijab: AboutHijab) {
        val index = hijab.indexOfFirst { it.hijab.id == aboutHijab.hijab.id }
        if (index != -1) {
            hijab[index] = aboutHijab
        }
    }

    fun searchHijab(query: String): Flow<List<AboutHijab>> {
        return getAllHijab()
            .map { hijabs ->
                hijabs.filter { hijab ->
                    hijab.hijab.title.contains(query, ignoreCase = true)
                }
            }
    }

    fun getFavoriteHijab(): Flow<List<AboutHijab>> {
        return getAllHijab().map { hijabs ->
            hijabs.filter { hijab ->
                hijab.isFavorite
            }
        }
    }

    companion object {
        @Volatile
        private var instance: HijabRepository? = null

        fun getInstance(): HijabRepository =
            instance ?: synchronized(this) {
                HijabRepository().apply {
                    instance = this
                }
            }
    }
}