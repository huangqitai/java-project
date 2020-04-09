"use strict";
exports.__esModule = true;
/**
 * date 转为 时间戳
 * @param date
 */
function transformDateToTimestamp(date) {
    return date.valueOf();
}
exports.transformDateToTimestamp = transformDateToTimestamp;
/**
 * 时间戳 转为 date
 * @param timestamp
 */
function transformTimestampToDate(timestamp) {
    return new Date(timestamp);
}
exports.transformTimestampToDate = transformTimestampToDate;
