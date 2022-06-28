package com.midterm.db;

import android.content.Context;

import com.midterm.dao.BookDao;
import com.midterm.entity.Book;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class BookRoomDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    private static volatile BookRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BookRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                BookDao dao = INSTANCE.bookDao();
                dao.deleteAll();
                Book book1 = new Book("Money Ninja: A Children's Book About Saving, Investing, and Donating", "Mary Nhin, Grow Grit Press and Jelena Stupar",14.44 );
                Book book2 = new Book("Human Body Activity Book for Kids: Hands-On Fun for Grades K-3", "Katie Stokes M.Ed. Ph.D",11.35 );
                Book book3 = new Book("I Need a New Butt!", "Dawn McMillan and Ross Kinnaird",7.79 );

                dao.insert(book1);
                dao.insert(book2);
                dao.insert(book3);

            });

        }

    };

}
