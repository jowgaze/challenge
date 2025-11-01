import { Interval } from "./interval";

export class ReportRequestDto{
    storeId: number;
    channelId: number;
    start: string | null;
    end: string | null;

    constructor(storeId: number, channelId: number, interval: Interval){
        this.storeId = storeId;
        this.channelId = channelId;
        this.start = interval.start;
        this.end = interval.end;
    }
}