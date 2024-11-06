package com.example.app.domain.version
import com.example.app.data.repository.version.VersionRepository
import javax.inject.Inject

class CanAccessToApp @Inject constructor(
    private val repository: VersionRepository
) {
    suspend operator fun invoke(): Boolean {
        val currentVersion = repository.getCurrentVersion() //1.0.3
        val minAllowedVersion = repository.getMinAllowedVersion() //1.0.2

        for ((currentPart, minVersionPart) in currentVersion.zip(minAllowedVersion)) {
            if (currentPart != minVersionPart) {
                return currentPart > minVersionPart
            }
        }

        return true
    }
}



