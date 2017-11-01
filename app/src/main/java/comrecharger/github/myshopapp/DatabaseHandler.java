package comrecharger.github.myshopapp;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.FloatProperty;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
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

    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IS_AVAILABLE= "is_available";
    private static final String KEY_CATEGORY_ID = "category_id";

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
    public static class Product implements Parcelable {
        private int id;
        private String name;
        private int local_icon;
        private int remote_icon;
        private String description;
        private Float price;
        private boolean is_available;
        private int category_id;

        public Product()
        {
        }
        public Product(int id, String name, int local_icon, int remote_icon, String description, Float price, Boolean is_available, int category_id)
        {
            this.id=id;
            this.name=name;
            this.local_icon=local_icon;
            this.remote_icon=remote_icon;
            this.description=description;
            this.price=price;
            this.is_available=is_available;
            this.category_id=category_id;
        }


        public Product(Parcel in) {
            this.id=in.readInt();
            this.name=in.readString();
            this.local_icon=in.readInt();
            this.remote_icon=in.readInt();
            this.description=in.readString();
            this.price=in.readFloat();
            this.is_available= (in.readInt() == 1);
            this.category_id=in.readInt();
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

        public void setCategoryId(int category_id) {
            this.category_id = category_id;
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
        public int getCategoryId() {
            return category_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeInt(local_icon);
            dest.writeInt(remote_icon);
            dest.writeString(description);
            dest.writeFloat(price);
            dest.writeInt((is_available) ? 1 : 0);
            dest.writeInt(category_id);

        }

        public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>()
        {
            public Product createFromParcel(Parcel in)
            {
                return new Product(in);
            }
            public Product[] newArray(int size)
            {
                return new Product[size];
            }
        };
    }
    public static class ShopListItem {
        private int id;
        private int product_id;
        private int number_of_items;
//        private String last_modified;

        public ShopListItem()
        {
        }
        public ShopListItem(int id, int product_id, int number_of_items)
        {
            this.id=id;
            this.product_id=product_id;
            this.number_of_items=number_of_items;
//            this.last_modified=last_modified;
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
//        public void setLastModified(String last_modified) {
//            this.last_modified = last_modified;
//        }
        public int getId() {
            return id;
        }
        public int getProductId() {
            return product_id;
        }
        public int getNumberOfItems() {
            return number_of_items;
        }
//        public String getLastModified() {
//            return last_modified;
//        }
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
                + KEY_IS_AVAILABLE + " integer,"
                + KEY_CATEGORY_ID + " integer);");
        db.execSQL("create table " + TABLE_SHOP_LIST + "(" + KEY_ID
                + " integer primary key,"
                + KEY_PRODUCT_ID + " integer,"
                + KEY_NUMBER_OF_ITEMS + " integer);");
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
        values.put(KEY_CATEGORY_ID, product.getCategoryId());


        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
    public void addShopListItem(ShopListItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, item.getProductId());
        values.put(KEY_NUMBER_OF_ITEMS, item.getNumberOfItems());
//        values.put(KEY_LAST_MODIFIED, item.getLastModified());

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
                        KEY_PRODUCT_NAME, KEY_LOCAL_ICON, KEY_REMOTE_ICON, KEY_DESCRIPTION, KEY_PRICE, KEY_IS_AVAILABLE, KEY_CATEGORY_ID }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Product contact = new Product(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)),
                cursor.getString(4),
                Float.parseFloat(cursor.getString(5)),
                Boolean.parseBoolean(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7))
        );

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
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)));

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
                product.setCategoryId(Integer.parseInt(cursor.getString(7)));

                productsList.add(product);
            } while (cursor.moveToNext());
        }

        return productsList;
    }
    public List<ShopListItem> getShopListItems() {
        List<ShopListItem> shopListItems = new ArrayList<ShopListItem>();

        String selectQuery = "SELECT * FROM " + TABLE_SHOP_LIST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ShopListItem shopListItem = new ShopListItem();
                shopListItem.setId(Integer.parseInt(cursor.getString(0)));
                shopListItem.setProductId(Integer.parseInt(cursor.getString(1)));
                shopListItem.setNumberOfItems(Integer.parseInt(cursor.getString(2)));
//                shopListItem.setLastModified(cursor.getString(3));

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
        values.put(KEY_CATEGORY_ID, product.getCategoryId());

// updating row
        return db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
    }
    public int updateShopListItem(ShopListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, listItem.getProductId());
        values.put(KEY_NUMBER_OF_ITEMS, listItem.getNumberOfItems());
//        values.put(KEY_LAST_MODIFIED, listItem.getLastModified());

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

    public List<Product> getProductsByCategory(int category_id) {
        List<Product> productsList = new ArrayList<Product>();

        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + KEY_CATEGORY_ID + " = " + category_id;
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
                product.setCategoryId(Integer.parseInt(cursor.getString(7)));

                productsList.add(product);
            } while (cursor.moveToNext());
        }
        return productsList;
    }
    public Product getProductById(int id) {
        Product product = null;
        List<Product> productsList = new ArrayList<Product>();

        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setLocalIcon(Integer.parseInt(cursor.getString(2)));
                product.setRemoteIcon(Integer.parseInt(cursor.getString(3)));
                product.setDescription(cursor.getString(4));
                product.setPrice(Float.parseFloat(cursor.getString(5)));
                product.setAvailableStatus(Boolean.parseBoolean(cursor.getString(6)));
                product.setCategoryId(Integer.parseInt(cursor.getString(7)));
            } while (cursor.moveToNext());
        }

        return product;
    }

    public boolean ifShopListItemExists(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_CATEGORIES, new String[] { KEY_ID,
                            KEY_PRODUCT_ID, KEY_NUMBER_OF_ITEMS, KEY_LAST_MODIFIED }, KEY_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            ShopListItem contact = new ShopListItem(Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)));

            if (contact != null) {
                return true;
            };
            return false;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CATEGORIES);
        onCreate(db);

    }

    private void fillData() {
        addCategory(new Category(0, "CPU", R.drawable.cpu, -1, "Central processing unit"));
        addCategory(new Category(1, "RAM", R.drawable.ram, -1, "Random access memory"));
        addCategory(new Category(2, "Motherboard", R.drawable.motherboard, -1, "The heart of the computer"));
        addCategory(new Category(3, "HDD", R.drawable.hdd, -1, "Hard Disk Drive"));
        addCategory(new Category(4, "SSD", R.drawable.ssd, -1, "Solid State Drive"));
        addCategory(new Category(5, "USB Storage", R.drawable.usb, -1, "Related products"));
        addCategory(new Category(6, "Power Supply", R.drawable.power, -1, "Long live, PC"));
        addProduct(new Product(0, "CPU1", R.drawable.cpu_0, -1, "Salam ebatb123", Float.parseFloat("84.04"), true, 0));
        addProduct(new Product(1, "CPU2", R.drawable.cpu_1, -1, "Salam ebatb346", Float.parseFloat("184.04"), true, 0));
        addProduct(new Product(2, "CPU3", R.drawable.cpu_2, -1, "Salam ebatb1432", Float.parseFloat("284.04"), true, 0));
        addProduct(new Product(3, "CPU4", R.drawable.cpu_3, -1, "Salam ebatb235523", Float.parseFloat("384.04"), true, 0));
        addProduct(new Product(4, "CPU5", R.drawable.cpu_4, -1, "Salam ebatb235523", Float.parseFloat("384.04"), true, 0));
        addProduct(new Product(5, "CPU6", R.drawable.cpu_5, -1, "Salam ebatb235523", Float.parseFloat("384.04"), true, 0));
        addProduct(new Product(6, "CPU7", R.drawable.cpu_6, -1, "Salam ebatb235523", Float.parseFloat("384.04"), true, 0));
        addProduct(new Product(7, "CPU8", R.drawable.cpu_7, -1, "Salam ebatb235523", Float.parseFloat("384.04"), true, 0));
        addProduct(new Product(8, "CPU9", R.drawable.cpu_8, -1, "Salam ebatb235523", Float.parseFloat("384.04"), true, 0));
        addProduct(new Product(9, "CPU10", R.drawable.cpu_9, -1, "Salam ebatb123", Float.parseFloat("84.04"), true, 0));
        addProduct(new Product(10, "RAM1", R.drawable.ram_0, -1, "Salam ebatb346", Float.parseFloat("184.04"), true, 1));
        addProduct(new Product(11, "RAM2", R.drawable.ram_1, -1, "Salam ebatb1432", Float.parseFloat("284.04"), true, 1));
        addProduct(new Product(12, "RAM3", R.drawable.ram_2, -1, "Salam ebatb235523", Float.parseFloat("384.04"), true, 1));
        addProduct(new Product(13, "RAM4", R.drawable.ram_3, -1, "Salam ebatb346", Float.parseFloat("184.04"), true, 1));
        addProduct(new Product(14, "RAM5", R.drawable.ram_4, -1, "Salam ebatb1432", Float.parseFloat("284.04"), true, 1));
        addProduct(new Product(15, "RAM6", R.drawable.ram_5, -1, "Salam ebatb235523", Float.parseFloat("384.04"), true, 1));
        addProduct(new Product(16, "RAM7", R.drawable.ram_6, -1, "Description", Float.parseFloat("184.04"), true, 1));
        addProduct(new Product(17, "RAM8", R.drawable.ram_7, -1, "Description", Float.parseFloat("284.04"), true, 1));
        addProduct(new Product(18, "RAM9", R.drawable.ram_8, -1, "Description", Float.parseFloat("384.04"), true, 1));
        addProduct(new Product(19, "RAM10", R.drawable.ram_9, -1, "Description", Float.parseFloat("184.04"), true, 1));
        addProduct(new Product(20, "Motherboard1", R.drawable.motherboard_0, -1, "Description", Float.parseFloat("284.04"), true, 2));
        addProduct(new Product(21, "Motherboard2", R.drawable.motherboard_1, -1, "Description", Float.parseFloat("384.04"), true, 2));
        addProduct(new Product(22, "Motherboard3", R.drawable.motherboard_2, -1, "Description", Float.parseFloat("284.04"), true, 2));
        addProduct(new Product(23, "Motherboard4", R.drawable.motherboard_3, -1, "Description", Float.parseFloat("384.04"), true, 2));
        addProduct(new Product(24, "Motherboard5", R.drawable.motherboard_4, -1, "Description", Float.parseFloat("284.04"), true, 2));
        addProduct(new Product(25, "Motherboard6", R.drawable.motherboard_5, -1, "Description", Float.parseFloat("384.04"), true, 2));
        addProduct(new Product(26, "Motherboard7", R.drawable.motherboard_6, -1, "Description", Float.parseFloat("284.04"), true, 2));
        addProduct(new Product(27, "Motherboard8", R.drawable.motherboard_7, -1, "Description", Float.parseFloat("384.04"), true, 2));
        addProduct(new Product(28, "Motherboard9", R.drawable.motherboard_8, -1, "Description", Float.parseFloat("284.04"), true, 2));
        addProduct(new Product(29, "Motherboard10", R.drawable.motherboard_9, -1, "Description", Float.parseFloat("384.04"), true, 2));
        addProduct(new Product(30, "HDD1", R.drawable.hdd_0, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(31, "HDD2", R.drawable.hdd_1, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(32, "HDD3", R.drawable.hdd_2, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(33, "HDD4", R.drawable.hdd_3, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(34, "HDD5", R.drawable.hdd_4, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(35, "HDD6", R.drawable.hdd_5, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(36, "HDD7", R.drawable.hdd_6, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(37, "HDD8", R.drawable.hdd_7, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(38, "HDD9", R.drawable.hdd_8, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(39, "HDD10", R.drawable.hdd_9, -1, "Description", Float.parseFloat("384.04"), true, 3));
        addProduct(new Product(40, "SSD1", R.drawable.ssd_0, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(41, "SSD2", R.drawable.ssd_1, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(42, "SSD3", R.drawable.ssd_2, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(43, "SSD4", R.drawable.ssd_3, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(44, "SSD5", R.drawable.ssd_4, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(45, "SSD6", R.drawable.ssd_5, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(46, "SSD7", R.drawable.ssd_6, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(47, "SSD8", R.drawable.ssd_7, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(48, "SSD9", R.drawable.ssd_8, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(49, "SSD10", R.drawable.ssd_9, -1, "Description", Float.parseFloat("384.04"), true, 4));
        addProduct(new Product(50, "USB Flash Drive 1", R.drawable.usb_0, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(51, "USB Flash Drive 2", R.drawable.usb_1, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(52, "USB Flash Drive 3", R.drawable.usb_2, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(53, "USB Flash Drive 4", R.drawable.usb_3, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(54, "USB Flash Drive 5", R.drawable.usb_4, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(55, "USB Flash Drive 6", R.drawable.usb_5, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(56, "USB Flash Drive 7", R.drawable.usb_6, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(57, "USB Flash Drive 8", R.drawable.usb_7, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(58, "USB Flash Drive 9", R.drawable.usb_8, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(59, "USB Flash Drive 10", R.drawable.usb_9, -1, "Description", Float.parseFloat("384.04"), true, 5));
        addProduct(new Product(60, "Power Supply 1", R.drawable.power_0, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(61, "Power Supply 2", R.drawable.power_1, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(62, "Power Supply 3", R.drawable.power_2, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(63, "Power Supply 4", R.drawable.power_3, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(64, "Power Supply 5", R.drawable.power_4, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(65, "Power Supply 6", R.drawable.power_5, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(66, "Power Supply 7", R.drawable.power_6, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(67, "Power Supply 8", R.drawable.power_7, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(68, "Power Supply 9", R.drawable.power_8, -1, "Description", Float.parseFloat("384.04"), true, 6));
        addProduct(new Product(69, "Power Supply 10", R.drawable.power_9, -1, "Description", Float.parseFloat("384.04"), true, 6));
    }
}
