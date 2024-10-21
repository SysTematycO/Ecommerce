export class Product {
    private id!: string;
    private email!: string;
    private name!: string;
    private description!: string;
    private price!: number;
    private publication_date!: Date;
    private quantity!: number;

    constructor(
        id: string,
        email: string,
        name: string,
        description: string,
        price: number,
        publication_date: Date,
        quantity: number
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.description = description;
        this.price = price;
        this.publication_date = publication_date;
        this.quantity = quantity;
    }

    public getId(): string {
        return this.id;
    }

    public getEmail(): string {
        return this.email;
    }

    public getName(): string {
        return this.name;
    }

    public getDescription(): string {
        return this.description;
    }

    public getPrice(): number {
        return this.price;
    }

    public getPublicationDate(): Date {
        return this.publication_date;
    }

    public getQuantity(): number {
        return this.quantity;
    }

    public setId(id: string): void {
        this.id = id;
    }

    public setEmail(email: string): void {
        this.email = email;
    }

    public setName(name: string): void {
        this.name = name;
    }

    public setDescription(description: string): void {
        this.description = description;
    }

    public setPrice(price: number): void {
        this.price = price;
    }

    public setPublicationDate(publication_date: Date): void {
        this.publication_date = publication_date;
    }

    public setQuantity(quantity: number): void {
        this.quantity = quantity;
    }
}
