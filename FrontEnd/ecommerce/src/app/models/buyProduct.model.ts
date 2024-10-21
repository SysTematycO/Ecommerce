export class BuyProduct {
    private idProduct: number;
    private emailBuyer: string;
    private emailSeller: string;
    private name: string;
    private price: number;
    private publicationDate: Date;
    private quantityToBuy: number;

    constructor(
        idProduct: number,
        emailBuyer: string,
        emailSeller: string,
        name: string,
        price: number,
        publicationDate: Date,
        quantityToBuy: number
    ) {
        this.idProduct = idProduct;
        this.emailBuyer = emailBuyer;
        this.emailSeller = emailSeller;
        this.name = name;
        this.price = price;
        this.publicationDate = publicationDate;
        this.quantityToBuy = quantityToBuy;
    }

    public getIdProduct(): number {
        return this.idProduct;
    }

    public getEmailBuyer(): string {
        return this.emailBuyer;
    }

    public getEmailSeller(): string {
        return this.emailSeller;
    }

    public getName(): string {
        return this.name;
    }

    public getPrice(): number {
        return this.price;
    }

    public getPublicationDate(): Date {
        return this.publicationDate;
    }

    public getQuantityToBuy(): number {
        return this.quantityToBuy;
    }

    public setIdProduct(idProduct: number): void {
        this.idProduct = idProduct;
    }

    public setEmailBuyer(emailBuyer: string): void {
        this.emailBuyer = emailBuyer;
    }

    public setEmailSeller(emailSeller: string): void {
        this.emailSeller = emailSeller;
    }

    public setName(name: string): void {
        this.name = name;
    }

    public setPrice(price: number): void {
        this.price = price;
    }

    public setPublicationDate(publicationDate: Date): void {
        this.publicationDate = publicationDate;
    }

    public setQuantityToBuy(quantityToBuy: number): void {
        this.quantityToBuy = quantityToBuy;
    }
}
