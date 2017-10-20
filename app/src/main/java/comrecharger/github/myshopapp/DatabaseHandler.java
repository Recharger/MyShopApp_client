package comrecharger.github.myshopapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shop_database";

    //Shared fields
    private static final String KEY_LOCAL_ICON = "local_icon";
    private static final String KEY_REMOTE_ICON = "remote_icon";
    private static final String KEY_ID = "_id";
    private static final String KEY_DESCRIPTION= "description";

    //Category table
    private static final String TABLE_CATEGORIES = "categories";
    private static final String KEY_CATEGORY_NAME = "category_name";

    //Product table
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IS_AVAILABLE= "is_available";

    //Shopping List table
    private static final String TABLE_SHOP_LIST = "shop_list";

    private static final String KEY_PRODUCT_ID = "product_id";
    private static final String KEY_NUMBER_OF_ITEMS = "number_of_items";
    private static final String KEY_LAST_MODIFIED = "last_modified";



    public class Category {
        private int id;
        private String name;
        private int local_icon;
        private int remote_icon;
        private String description;

        public Category()
        {
        }
        public Category(int id, String name, int local_icon, int remote_icon, String description)
        {
            this.id=id;
            this.name=name;
            this.local_icon=local_icon;
            this.remote_icon=remote_icon;
            this.description=description;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setLocalIcon(int local_icon) {
            this.local_icon = local_icon;
        }
        public void setRemoteIcon(int remote_icon) {
            this.remote_icon = remote_icon;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getLocalIcon() {
            return local_icon;
        }
        public int getRemoteIcon() {
            return remote_icon;
        }
        public String getDescription() {
            return description;
        }
    }
    public class Product {
        private int id;
        private String name;
        private int local_icon;
        private int remote_icon;
        private String description;
        private Float price;
        private boolean is_available;

        public Product()
        {
        }
        public Product(int id, String name, int local_icon, int remote_icon, String description, Float price, Boolean is_available)
        {
            this.id=id;
            this.name=name;
            this.local_icon=local_icon;
            this.remote_icon=remote_icon;
            this.description=description;
            this.price=price;
            this.is_available=is_available;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void setName(String name) {
            this.name = name;
        }

        public void setLocalIcon(int local_icon) {
            this.local_icon = local_icon;
        }

        public void setRemoteIcon(int remote_icon) {
            this.remote_icon = remote_icon;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public void setAvailableStatus(Boolean is_available) {
            this.is_available = is_available;
        }

        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getLocalIcon() {
            return local_icon;
        }
        public int getRemoteIcon() {
            return remote_icon;
        }
        public String getDescription() {
            return description;
        }
        public Float getPrice() {
            return price;
        }
        public Boolean getAvailableStatus() {
            return is_available;
        }
    }
    public class ShopListItem {
        private int id;
        private int product_id;
        private int number_of_items;
        private String last_modified;

        public ShopListItem()
        {
        }
        public ShopListItem(int id, int product_id, int number_of_items, String last_modified)
        {
            this.id=id;
            this.product_id=product_id;
            this.number_of_items=number_of_items;
            this.last_modified=last_modified;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void setProductId(int product_id) {
            this.product_id = product_id;
        }
        public void setNumberOfItems(int number_of_items) {
            this.number_of_items = number_of_items;
        }
        public void setLastModified(String last_modified) {
            this.last_modified = last_modified;
        }
        public int getId() {
            return id;
        }
        public int getProductId() {
            return product_id;
        }
        public int getNumberOfItems() {
            return number_of_items;
        }
        public String getLastModified() {
            return last_modified;
        }
    }

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
                + KEY_IS_AVAILABLE + " integer);");
        db.execSQL("create table " + TABLE_SHOP_LIST + "(" + KEY_ID
                + " integer primary key,"
                + KEY_PRODUCT_ID + " integer,"
                + KEY_NUMBER_OF_ITEMS + " integer,"
                + KEY_LAST_MODIFIED + " text);");
        fillData();
    }


    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_NAME, category.getName());
        values.put(KEY_LOCAL_ICON, category.getLocalIcon());
        values.put(KEY_REMOTE_ICON, category.getRemoteIcon());
        values.put(KEY_DESCRIPTION, category.getDescription());

        db.insert(TABLE_CATEGORIES, null, values);
        db.close();
    }
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, product.getName());
        values.put(KEY_LOCAL_ICON, product.getLocalIcon());
        values.put(KEY_REMOTE_ICON, product.getRemoteIcon());
        values.put(KEY_DESCRIPTION, product.getDescription());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_IS_AVAILABLE, product.getAvailableStatus());


        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
    public void addShopListItem(ShopListItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, item.getProductId());
        values.put(KEY_NUMBER_OF_ITEMS, item.getNumberOfItems());
        values.put(KEY_LAST_MODIFIED, item.getLastModified());

        db.insert(TABLE_SHOP_LIST, null, values);
        db.close();
    }

    public Category getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CATEGORIES, new String[] { KEY_ID,
                        KEY_CATEGORY_NAME, KEY_LOCAL_ICON, KEY_REMOTE_ICON, KEY_DESCRIPTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Category contact = new Category(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), cursor.getString(4));

        return contact;
    }
    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { KEY_ID,
                        KEY_PRODUCT_NAME, KEY_LOCAL_ICON, KEY_REMOTE_ICON, KEY_DESCRIPTION, KEY_PRICE, KEY_IS_AVAILABLE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Product contact = new Product(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), cursor.getString(4), Float.parseFloat(cursor.getString(5)), Boolean.parseBoolean(cursor.getString(6)));

        return contact;
    }
    public ShopListItem getShopListItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CATEGORIES, new String[] { KEY_ID,
                        KEY_PRODUCT_ID, KEY_NUMBER_OF_ITEMS, KEY_LAST_MODIFIED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ShopListItem contact = new ShopListItem(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3));

        return contact;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<Category>();

        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(Integer.parseInt(cursor.getString(0)));
                category.setName(cursor.getString(1));
                category.setLocalIcon(Integer.parseInt(cursor.getString(2)));
                category.setRemoteIcon(Integer.parseInt(cursor.getString(3)));
                category.setDescription(cursor.getString(4));

                categoryList.add(category);
            } while (cursor.moveToNext());
        }

        return categoryList;
    }
    public List<Product> getAllProducts() {
        List<Product> productsList = new ArrayList<Product>();

        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setLocalIcon(Integer.parseInt(cursor.getString(2)));
                product.setRemoteIcon(Integer.parseInt(cursor.getString(3)));
                product.setDescription(cursor.getString(4));
                product.setPrice(Float.parseFloat(cursor.getString(5)));
                product.setAvailableStatus(Boolean.parseBoolean(cursor.getString(6)));

                productsList.add(product);
            } while (cursor.moveToNext());
        }

        return productsList;
    }
    public List<ShopListItem> getShopListItems() {
        List<ShopListItem> shopListItems = new ArrayList<ShopListItem>();

        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ShopListItem shopListItem = new ShopListItem();
                shopListItem.setId(Integer.parseInt(cursor.getString(0)));
                shopListItem.setProductId(Integer.parseInt(cursor.getString(1)));
                shopListItem.setNumberOfItems(Integer.parseInt(cursor.getString(2)));
                shopListItem.setLastModified(cursor.getString(3));

                shopListItems.add(shopListItem);
            } while (cursor.moveToNext());
        }

        return shopListItems;
    }

    public int updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_NAME, category.getName());
        values.put(KEY_LOCAL_ICON, category.getLocalIcon());
        values.put(KEY_REMOTE_ICON, category.getRemoteIcon());
        values.put(KEY_DESCRIPTION, category.getDescription());
// updating row
        return db.update(TABLE_CATEGORIES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(category.getId())});
    }
    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, product.getName());
        values.put(KEY_LOCAL_ICON, product.getLocalIcon());
        values.put(KEY_REMOTE_ICON, product.getRemoteIcon());
        values.put(KEY_DESCRIPTION, product.getDescription());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_IS_AVAILABLE, product.getAvailableStatus());

// updating row
        return db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
    }
    public int updateShopListItem(ShopListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, listItem.getProductId());
        values.put(KEY_NUMBER_OF_ITEMS, listItem.getNumberOfItems());
        values.put(KEY_LAST_MODIFIED, listItem.getLastModified());

        return db.update(TABLE_SHOP_LIST, values, KEY_ID + " = ?",
                new String[]{String.valueOf(listItem.getId())});
    }

    public void deleteCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(category.getId()) });
        db.close();
    }
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " = ?",
                new String[] { String.valueOf(product.getId()) });
        db.close();
    }
    public void deleteShopListItem(ShopListItem shopListItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SHOP_LIST, KEY_ID + " = ?",
                new String[] { String.valueOf(shopListItem.getId()) });
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CATEGORIES);
        onCreate(db);

    }

    private void fillData() {
        addCategory(new Category(0, "CPU", 123456, 123456, "Salam ebatb"));
        addCategory(new Category(1, "RAM", 123456, 123456, "Salam ebatb"));
        addCategory(new Category(2, "HDD", 123456, 123456, "Salam ebatb"));
        addCategory(new Category(3, "SSD", 123456, 123456, "Salam ebatb"));
    }
}
