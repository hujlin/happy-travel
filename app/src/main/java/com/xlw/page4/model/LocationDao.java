package com.xlw.page4.model;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.xlw.page4.model.Location;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table LOCATION.
*/
public class LocationDao extends AbstractDao<Location, Long> {

    public static final String TABLENAME = "LOCATION";

    /**
     * Properties of entity Location.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Lat = new Property(1, String.class, "lat", false, "LAT");
        public final static Property Lng = new Property(2, String.class, "lng", false, "LNG");
        public final static Property LocDate = new Property(3, java.util.Date.class, "locDate", false, "LOC_DATE");
        public final static Property ServerId = new Property(4, Long.class, "serverId", false, "SERVER_ID");
        public final static Property StatusFlag = new Property(5, Integer.class, "statusFlag", false, "STATUS_FLAG");
        public final static Property TripId = new Property(6, long.class, "tripId", false, "TRIP_ID");
    };

    private DaoSession daoSession;

    private Query<Location> trip_LocationsQuery;

    public LocationDao(DaoConfig config) {
        super(config);
    }
    
    public LocationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'LOCATION' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'LAT' TEXT," + // 1: lat
                "'LNG' TEXT," + // 2: lng
                "'LOC_DATE' INTEGER," + // 3: locDate
                "'SERVER_ID' INTEGER," + // 4: serverId
                "'STATUS_FLAG' INTEGER," + // 5: statusFlag
                "'TRIP_ID' INTEGER NOT NULL );"); // 6: tripId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'LOCATION'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Location entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String lat = entity.getLat();
        if (lat != null) {
            stmt.bindString(2, lat);
        }
 
        String lng = entity.getLng();
        if (lng != null) {
            stmt.bindString(3, lng);
        }
 
        java.util.Date locDate = entity.getLocDate();
        if (locDate != null) {
            stmt.bindLong(4, locDate.getTime());
        }
 
        Long serverId = entity.getServerId();
        if (serverId != null) {
            stmt.bindLong(5, serverId);
        }
 
        Integer statusFlag = entity.getStatusFlag();
        if (statusFlag != null) {
            stmt.bindLong(6, statusFlag);
        }
        stmt.bindLong(7, entity.getTripId());
    }

    @Override
    protected void attachEntity(Location entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Location readEntity(Cursor cursor, int offset) {
        Location entity = new Location( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // lat
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // lng
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // locDate
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // serverId
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // statusFlag
            cursor.getLong(offset + 6) // tripId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Location entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLat(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLng(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLocDate(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setServerId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setStatusFlag(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setTripId(cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Location entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Location entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "locations" to-many relationship of Trip. */
    public List<Location> _queryTrip_Locations(long tripId) {
        synchronized (this) {
            if (trip_LocationsQuery == null) {
                QueryBuilder<Location> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.TripId.eq(null));
                queryBuilder.orderRaw("_id DESC");
                trip_LocationsQuery = queryBuilder.build();
            }
        }
        Query<Location> query = trip_LocationsQuery.forCurrentThread();
        query.setParameter(0, tripId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getTripDao().getAllColumns());
            builder.append(" FROM LOCATION T");
            builder.append(" LEFT JOIN TRIP T0 ON T.'TRIP_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Location loadCurrentDeep(Cursor cursor, boolean lock) {
        Location entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Trip trip = loadCurrentOther(daoSession.getTripDao(), cursor, offset);
         if(trip != null) {
            entity.setTrip(trip);
        }

        return entity;    
    }

    public Location loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Location> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Location> list = new ArrayList<Location>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Location> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Location> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
