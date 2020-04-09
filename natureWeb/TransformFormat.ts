/**
 * date 转为 时间戳
 * @param date 
 */
export function transformDateToTimestamp(date : Date): number{
    return date.valueOf();

}
/**
 * 时间戳 转为 date
 * @param timestamp 
 */
export function transformTimestampToDate(timestamp : number): Date{
    return new Date(timestamp);
}