export class User {
    private email!: string;
    private chain!: string;
    private role!: string;

    constructor(email: string, chain: string, role: string) {
        this.email = email;
        this.chain = chain;
        this.role = role;
    }
    
    getEmail(): string {
        return this.email;
    }

    setEmail(value: string) {
        this.email = value;
    }

    getChain(): string {
        return this.chain;
    }

    setChain(value: string) {
        this.chain = value;
    }

    getRole(): string {
        return this.role;
    }

    setRole(value: string) {
        this.role = value;
    }
}
