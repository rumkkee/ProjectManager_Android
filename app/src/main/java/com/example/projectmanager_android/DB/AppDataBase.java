package com.example.projectmanager_android.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Board.class, CardList.class, Card.class} , version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "ProjectManager_android.db";
    public static final String USERS_TABLE = "users_table";
    public static final String WORKSPACES_TABLE = "workspaces_table";
    public static final String BOARDS_TABLE = "boards_table";
    public static final String LISTS_TABLE = "lists_table";
    public static final String CARDS_TABLE = "cards_table";

    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract UserDAO UserDAO();
    public abstract BoardDAO BoardDAO();
    public abstract CardListDAO CardListDAO();
    public abstract CardDAO CardDAO();
    
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME)
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {

                System.out.println("Executing database Callback \n\n");
                // Populating DB with users
                UserDAO userDAO = instance.UserDAO();

                User testUser1 = createUser("testUser1", "testUser1", false);
                User admin2 = createUser("admin2", "admin2", true);
                userDAO.insert(testUser1, admin2);

                admin2 = userDAO.getUserByID(2);

                // Populating DB's admin2 with boards
                BoardDAO boardDAO = instance.BoardDAO();
                Board board1 = createBoard("Pandora Logs", admin2.getUserId());
                Board board2 = createBoard("Anomalocaris Tributes", admin2.getUserId());
                Board board3 = createBoard("Watership Downloads", admin2.getUserId());
                boardDAO.insert(board1, board2, board3);

                board2 = boardDAO.getBoardByBoardId(2);

                CardListDAO cardListDAO = instance.CardListDAO();
                CardList cardList1 = createCardList("Vetulicolia", board2,  board2.getUserId());
                CardList cardList2 = createCardList("Worms", board2, board2.getUserId());
                cardListDAO.insert(cardList1, cardList2);

                cardList1 = cardListDAO.getListByListId(1);

                CardDAO cardDAO = instance.CardDAO();
                Card card1 = createCard("Rectangulata", board2, cardList1, board2.getUserId());
                Card card2 = createCard("Cuneata", board2, cardList1, board2.getUserId());
                Card card3 = createCard("Venustum", board2, cardList1, board2.getUserId());
                cardDAO.insert(card1, card2, card3);

                System.out.println("End of insertions!");
                System.out.println("Printing cards!\n\n\n\n\n");
                for (Card card : cardDAO.getAllCardsAsList()) {
                    System.out.println(card.toString());
                }


//                System.out.println("Should print testUser1 info: \n" + userDAO.getUserByID(1));
//                System.out.println("Should print admin2 info: \n" + userDAO.getUserByID(2));
//                System.out.println(admin2);
            });
        }
    };

    public static User createUser(String name, String password, boolean isAdmin){
        User userCredentials = new User(name, password);
        userCredentials.setIsAdmin(isAdmin);
        return userCredentials;
    }

    public static Board createBoard(String title, int userID){
        Board board = new Board(title);
        board.setUserId(userID);
        return board;
    }

    public static CardList createCardList(String title, Board board, int userId){
        CardList cardList = new CardList(title);
        cardList.setBoardId(board.getBoardId());
        cardList.setUserId(userId);
        return cardList;
    }

    public static Card createCard(String title, Board board, CardList cardList, int userId){
        Card card = new Card();
        card.setTitle(title);
        card.setBoardId(board.getBoardId());
        card.setCardListId(cardList.getListId());
        card.setUserId(userId);
        return card;
    }
}
