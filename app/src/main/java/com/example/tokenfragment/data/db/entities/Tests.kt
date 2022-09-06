package com.example.tokenfragment.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "tests")
data class Tests(
    @PrimaryKey(autoGenerate = true)
    public var id: Int,
    @ColumnInfo(name = "test_name")
    var tag: String,
    @ColumnInfo(name = "test_success_count")
    var successCount: Int,
    @ColumnInfo(name = "test_fail_count")
    var failCount: Int,
    @ColumnInfo(name = "test_success_last")
    var successLast: Boolean,
    @ColumnInfo(name = "test_info") //bos kalabilir mi nasıl bakarız
    var info: String    //bir şey girilmezse null say manasında
)