"use strict";
exports.__esModule = true;
function transformDateToTimestamp(date) {
    return date.valueOf();
}
exports.transformDateToTimestamp = transformDateToTimestamp;
function transformTimestampToDate(timestamp) {
    return new Date(timestamp);
}
exports.transformTimestampToDate = transformTimestampToDate;
