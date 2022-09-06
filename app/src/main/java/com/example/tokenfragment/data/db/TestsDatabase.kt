package com.example.tokenfragment.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tokenfragment.data.db.entities.Tests

//database degisincce version degismeli
@Database(
    entities = [Tests::class],
    version = 1,
    //exportSchema = false    //version history tutmasına gerek yok diye false dedik
)
abstract class TestsDatabase: RoomDatabase() {

    abstract fun getTestsDao(): TestsDao    //bunu inherit eden bu fonksiyonu çağırmak zorunda sonucunda da dao gelecek,
    //dao sonucunda da işlemleri yapmak zorunda kalacak

    companion object{ //içindeki variable val funlar static oluyor, her instanceta bakıyor yani
        //classı singleton(tekil) kullanabilmek için lazım

        @Volatile   //tek yaratmasını sağlıyor karışmasın 2 tane diye
        private var INSTANCE: TestsDatabase? = null //başta null sonra dolacak

        private val lock = Any()

        //operator operator overloadingden sanırım?
        //daha önce db instance'ı kurulmamışsa şimdi kuruyor
        //operator fun invoke(context: Context): TestsDatabase {
        fun invoke(context: Context): TestsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                Log.d("DB","db is already created")
                return tempInstance     //if instance already exists return that instance
            }
            synchronized(this){ //if not than sychronizely create and return that instance
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TestsDatabase::class.java,
                    "test_database"
                ).build() //eğer version updatelersen ekle .fallbackToDestructiveMigration()
                //version değiştirince kayıtlar gidiyo ama bir daha da etmiyo?
                Log.d("DB","db created")
                INSTANCE = instance
                return instance
            }

            //farklı yaptım bence aynısının daha readable hali
            /*
            return instance ?: synchronized(lock) { //if instance is null, synchronized da aynı anda sanırım?
                // if (instance == null)
                //val it = createDatabase(context)    //bir daha if instance is null demedim burda
                //instance = it
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }*/
        }

        //db kuruyor
        /*
        private fun createDatabase(context: Context) { // = yerine return diyince olmadı { } ile de olmadı
             Room.databaseBuilder(
                context.applicationContext,  //apple aynı contexti olmalı, this
                TestsDatabase::class.java,
                 "testsDB.db").build()
        //.fallbackToDestructiveMigration()
            Log.d("DB", "db is created")
        }

         */
    }



}