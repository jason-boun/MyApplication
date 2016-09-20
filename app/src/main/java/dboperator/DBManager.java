package dboperator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import dao.DaoMaster;
import dao.DaoSession;
import dao.User;
import dao.UserDao;

/**
 * Created by jiabo on 16/8/11.
 */
public class DBManager {

    private Context context;
    private static DBManager instance;
    private DBManager(Context context){
        this.context = context;
        this.openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
    }
    public static DBManager getInstance(Context context){
        if (instance == null){
            synchronized (DBManager.class){
                if (instance == null){
                    instance = new DBManager(context);
                }
            }
        }
        return instance;
    }

    private final static String dbName = "test_db";
    private DaoMaster.DevOpenHelper openHelper;


    private SQLiteDatabase getReadableDataBase(){
        if (openHelper == null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        return openHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase(){
        if (openHelper == null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        return openHelper.getWritableDatabase();
    }

    public UserDao getUserWDao(){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        return userDao;
    }

    public UserDao getUserRDao(){
        DaoMaster daoMaster = new DaoMaster(getReadableDataBase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        return userDao;
    }
    /**
     * 插入一条记录
     * @param user
     */
    public void insertUser(User user){
        if (user == null){
            return;
        }
        getUserWDao().insert(user);
    }

    /**
     * 插入多条User记录
     * @param userList
     */
    public void insertUsers(List<User> userList){
        if (userList == null || userList.size() ==0){
            return;
        }
        getUserWDao().insertInTx(userList);
    }

    /**
     * 删除一条记录
     * @param user
     */
    public void deleteUser(User user){
        if (user == null){
            return;
        }
        getUserWDao().delete(user);
    }

    /**
     * 更新一条数据
     * @param user
     */
    public void updateUser(User user){
        if (user == null){
            return;
        }
        getUserWDao().update(user);
    }

    /**
     *查询所有用户
     * @return
     */
    public List<User> queryUsers(){
        UserDao userDao = getUserRDao();
        QueryBuilder<User>  queryBuilder = userDao.queryBuilder();
        return queryBuilder.list();
    }

    /**
     * 根据条件查询
     * @param age
     * @return
     */
    public List<User> queryUser(int age){
        UserDao userDao = getUserRDao();
        QueryBuilder<User>  queryBuilder = userDao.queryBuilder();
        queryBuilder.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
        return queryBuilder.list();
    }



}
