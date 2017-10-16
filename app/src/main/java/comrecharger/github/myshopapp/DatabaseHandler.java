package comrecharger.github.myshopapp;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shop_database";

    //Shared fields
    private static final String KEY_LOCAL_ICON = "local_icon";
    private static final String KEY_REMOTE_ICON = "remote_icon";
    private static final String KEY_DESCRIPTION= "description";

    //Category table
    private static final String TABLE_CATEGORIES = "categories";
    private static final String KEY_ID = "_id";


    private static final String KEY_CATEGORY_NAME = "category_name";

    //Product table
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IS_AVAILIBLE= "is_availible";


    //Shopping List table
    private static final String TABLE_SHOP_LIST = "shop_list";

    private static final String KEY_PRODUCT_ID = "product_id";
    private static final String KEY_NUMBER_OF_ITEMS = "number_of_items";
    private static final String KEY_LAST_MODIFIED = "last_modified";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CATEGORIES + "(" + KEY_ID
                + " integer primary key,"
                + KEY_CATEGORY_NAME + " text,"
                + KEY_LOCAL_ICON + " text,"
                + KEY_REMOTE_ICON + " text,"
                + KEY_DESCRIPTION + " text);");
        db.execSQL("create table " + TABLE_PRODUCTS + "(" + KEY_ID
                + " integer primary key,"
                + KEY_PRODUCT_NAME + " text,"
                + KEY_LOCAL_ICON + " text,"
                + KEY_REMOTE_ICON + " text,"
                + KEY_DESCRIPTION + " text,"
                + KEY_PRICE + " real,"
                + KEY_IS_AVAILIBLE + " integer);");
        db.execSQL("create table " + TABLE_SHOP_LIST + "(" + KEY_ID
                + " integer primary key,"
                + KEY_PRODUCT_ID + " integer,"
                + KEY_NUMBER_OF_ITEMS + " integer,"
                + KEY_LAST_MODIFIED + " text);");
        fillData(db);
    }


    public void addCategoryRow(SQLiteDatabase db,
                               String category_name,
                               String local_icon,
                               String remote_icon,
                               String description) {
        db.execSQL("insert into " + TABLE_CATEGORIES + "("
                + KEY_CATEGORY_NAME+ ", "
                + KEY_LOCAL_ICON + ", "
                + KEY_REMOTE_ICON + ", "
                + KEY_DESCRIPTION + ") values ("
                + category_name + ", "
                + local_icon + ", "
                + remote_icon + ", "
                + description + ");");
    }


    public void addProductRow(SQLiteDatabase db,
                              String product_name,
                              String local_icon,
                              String remote_icon,
                              String description,
                              Float price,
                              String is_availible) {
        db.execSQL("insert into " + TABLE_PRODUCTS+ "("
                + KEY_PRODUCT_NAME+ ", "
                + KEY_LOCAL_ICON + ", "
                + KEY_REMOTE_ICON + ", "
                + KEY_DESCRIPTION + ", "
                + KEY_PRICE + ", "
                + KEY_IS_AVAILIBLE + ") values ("
                + product_name + ", "
                + local_icon + ", "
                + remote_icon + ", "
                + description + ", "
                + price + ", "
                + is_availible + ");");
    }


    public void addShopListRow(SQLiteDatabase db,
                               String item_id,
                               int number_of_items,
                               String last_modified) {
        db.execSQL("insert into " + TABLE_SHOP_LIST + "("
                + KEY_PRODUCT_ID + ", "
                + KEY_NUMBER_OF_ITEMS+ ", "
                + KEY_LAST_MODIFIED + ") values ("
                + item_id + ", "
                + number_of_items + ", "
                + last_modified + ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CATEGORIES);
        onCreate(db);

    }

    private void fillData(SQLiteDatabase db) {
        //разбираем файл data.xml лежащий например в assets
        //и вставляем данные в базу
        //либо читаем лежащие там же sql-скрипты и выполняем с помощью все того же db.execSQL() или аналогично
    }
}
