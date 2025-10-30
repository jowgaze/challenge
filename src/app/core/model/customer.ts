export class InactiveCustomerDto {
    customerId: number;
    customerName: string;
    totalPurchases: number;
    lastPurchaseDate: string;

    constructor(customerId: number, customerName: string, totalPurchases: number, lastPurchaseDate: string){
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPurchases = totalPurchases;
        this.lastPurchaseDate = lastPurchaseDate;
    }
}