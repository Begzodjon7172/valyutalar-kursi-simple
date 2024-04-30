package uz.bnabiyev.valyutalarkursi.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.bnabiyev.valyutalarkursi.models.Valyuta

class MyDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyService {

    companion object {

        val DB_NAME = "my_db"
        val DB_VERSION = 1

        val TABLE_NAME = "valyuta"
        val ID = "id"
        val CCY = "ccy"
        val CCY_NM_EN = "ccy_nm_en"
        val CCY_NM_RU = "ccy_nm_ru"
        val CCY_NM_UZ = "ccy_nm_uz"
        val CCY_NM_UZC = "ccy_nm_uzc"
        val CODE = "code"
        val DATE = "date"
        val DIFF = "diff"
        val NOMINAL = "nominal"
        val RATE = "rate"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME($ID integer not null primary key autoincrement, $CCY text not null, $CCY_NM_EN text not null, $CCY_NM_RU text not null, $CCY_NM_UZ text not null, $CCY_NM_UZC text not null, $CODE text not null, $DATE text not null, $DIFF text not null, $NOMINAL text not null, $RATE text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addValyuta(valyuta: Valyuta) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CCY, valyuta.Ccy)
        contentValues.put(CCY_NM_EN, valyuta.CcyNm_EN)
        contentValues.put(CCY_NM_RU, valyuta.CcyNm_RU)
        contentValues.put(CCY_NM_UZ, valyuta.CcyNm_UZ)
        contentValues.put(CCY_NM_UZC, valyuta.CcyNm_UZC)
        contentValues.put(CODE, valyuta.Code)
        contentValues.put(DATE, valyuta.Date)
        contentValues.put(DIFF, valyuta.Diff)
        contentValues.put(NOMINAL, valyuta.Nominal)
        contentValues.put(RATE, valyuta.Rate)
        database.insert(TABLE_NAME, null, contentValues)
    }

    override fun listValyuta(): ArrayList<Valyuta> {
        val list = ArrayList<Valyuta>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val Ccy = cursor.getString(1)
                val CcyNm_EN = cursor.getString(2)
                val CcyNm_RU = cursor.getString(3)
                val CcyNm_UZ = cursor.getString(4)
                val CcyNm_UZC = cursor.getString(5)
                val Code = cursor.getString(6)
                val Date = cursor.getString(7)
                val Diff = cursor.getString(8)
                val Nominal = cursor.getString(9)
                val Rate = cursor.getString(10)
                val valyuta = Valyuta(
                    id,
                    Ccy,
                    CcyNm_EN,
                    CcyNm_RU,
                    CcyNm_UZ,
                    CcyNm_UZC,
                    Code,
                    Date,
                    Diff,
                    Nominal,
                    Rate
                )
                list.add(valyuta)
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getValyutaById(id: Int): Valyuta {
        val database = this.readableDatabase
        val cursor = database.query(
            TABLE_NAME,
            arrayOf(
                ID,
                CCY,
                CCY_NM_EN,
                CCY_NM_RU,
                CCY_NM_UZ,
                CCY_NM_UZC,
                CODE,
                DATE,
                DIFF,
                NOMINAL,
                RATE
            ),
            "$ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        cursor.moveToFirst()
        return Valyuta(
            cursor.getInt(0), cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4),
            cursor.getString(5),
            cursor.getString(6),
            cursor.getString(7),
            cursor.getString(8),
            cursor.getString(9),
            cursor.getString(10)
        )
    }

    override fun removeAllValyuta() {
        val db: SQLiteDatabase =
            this.writableDatabase // helper is object extends SQLiteOpenHelper

        db.delete(TABLE_NAME, null, null)
    }
}