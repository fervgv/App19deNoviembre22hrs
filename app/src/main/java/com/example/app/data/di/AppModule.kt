package com.example.app.data.di
import android.content.Context
import com.example.app.data.repository.auth.LoginRepository
import com.example.app.data.repository.auth.SignUpRepository
import com.example.app.data.repository.home.HomeRepository
import com.example.app.data.repository.user.AddContactRepository
import com.example.app.data.repository.user.RegistryContactRepository
import com.example.app.data.repository.version.VersionRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //Prove a LoginRepository para inyeccion
    @Singleton
    @Provides
    fun provideLoginRepository(firebaseAuth: FirebaseAuth): LoginRepository {
        return LoginRepository(firebaseAuth)
    }
    //Prove a SignUpRepository para inyeccion
    @Singleton
    @Provides
    fun provideSignUpRepository(firebaseAuth: FirebaseAuth): SignUpRepository {
        return SignUpRepository(firebaseAuth)
    }

    // Prover FirebaseAuth para inyección
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    // Prover FirebaseFirestorm para inyección
    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideVersionRepository(@ApplicationContext context: Context): VersionRepository {
            return VersionRepository(context)
        }
    @Singleton
    @Provides
    fun provideRegistryContactRepository(firestore: FirebaseFirestore): RegistryContactRepository {
        return RegistryContactRepository(firestore)
    }
    @Singleton
    @Provides
    fun provideAddContactRepository(firestore: FirebaseFirestore): AddContactRepository {
        return AddContactRepository(firestore)
    }


    @Singleton
    @Provides
    fun provideHomeRepository(): HomeRepository {
        return HomeRepository()
    }
}


















