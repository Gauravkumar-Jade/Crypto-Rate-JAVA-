package com.jit.cryptoratejava.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jit.cryptoratejava.data.Datum;

@Database(entities = {Datum.class}, version = 1, exportSchema = false)
public abstract class CryptoDatabase extends RoomDatabase {

    public abstract CryptoDao cryptoDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile CryptoDatabase INSTANCE;


    public static CryptoDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CryptoDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            CryptoDatabase.class,
                            "crypto_db"
                    )
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                CryptoDao dao = INSTANCE.cryptoDao();
                //dao.deleteData();
            });
        }
    };

}
